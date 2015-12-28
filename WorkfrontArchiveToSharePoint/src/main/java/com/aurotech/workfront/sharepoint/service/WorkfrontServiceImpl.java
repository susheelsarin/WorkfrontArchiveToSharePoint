package com.aurotech.workfront.sharepoint.service;

import java.io.DataInputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.net.URL;
import java.net.URLConnection;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.stereotype.Service;

import com.aurotech.workfront.sharepoint.dao.WorkfrontException;
import com.aurotech.workfront.sharepoint.dao.WorkfrontServiceDao;
import com.aurotech.workfront.sharepoint.domain.LookupBean;
import com.aurotech.workfront.sharepoint.domain.WorkfrontSPRequest;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

@Service
public class WorkfrontServiceImpl implements WorkfrontService {
	
	private WorkfrontServiceDao workfrontServiceDao;
	private ArrayList<LookupBean> lookupArray = null;
	private LookupBean lookup;
	
	@Override
	public void createSession(WorkfrontSPRequest workfrontSPRequest){		
		try {
		if(!StringUtils.isEmpty(workfrontSPRequest.getWfSessionId())){
			System.out.println("creating session from: "+workfrontSPRequest.getWfSessionId());
			workfrontServiceDao = new WorkfrontServiceDao(workfrontSPRequest.getWfUrl(), "/attask/api", workfrontSPRequest.getWfSessionId());
		}
		} catch (Exception e) {
			e.printStackTrace();
		}		
	}
	
	@Override
	public void searchDocumentsInProject(WorkfrontSPRequest workfrontSPRequest){
		Map<String, Object> map = new HashMap<String, Object>();
//		map.put("apiKey", workfrontSPRequest.getWfApiKey());
		map.put("topObjID", workfrontSPRequest.getProjectId());
		try {
			JSONArray docs = workfrontServiceDao.search("DOCU", map, new String[]{"ID", "name", "currentVersionID", "extRefID", "objID", "topObjID", "docObjCode", "currentVersion:version"});
//			System.out.println(docs);
			lookupArray = new ArrayList<LookupBean>();
			for (int i=0; i<docs.length(); i++) {
				//System.out.println(docs.get(i));				
				JsonObject gsonObj = convertToGSONFromJSONObject((JSONObject) docs.get(i));
				System.out.println(gsonObj);
				setWFAttributes(workfrontSPRequest, gsonObj);
			}
		workfrontSPRequest.setLookup(lookupArray);
		} catch (WorkfrontException e) {
			e.printStackTrace();
		}
	}
	
	private void setWFAttributes(WorkfrontSPRequest workfrontSPRequest, JsonObject gsonObj){
		
		lookup = new LookupBean();		
		lookup.setServerURL(workfrontSPRequest.getServerURL());
		lookup.setCurrentVersionID(checkJsonIsNull(gsonObj.get("currentVersionID")));
		lookup.setExtRefID(checkJsonIsNull(gsonObj.get("extRefID")));		
		lookup.setDocumentName(checkJsonIsNull(gsonObj.get("name")));
		lookup.setDocumentID(checkJsonIsNull(gsonObj.get("ID")));
		lookup.setCurrentVersion(checkJsonIsNull(gsonObj.getAsJsonObject("currentVersion").get("version")));
		workfrontSPRequest.setDocumentID(lookup.getDocumentID());
		lookup.setObjectID(checkJsonIsNull(gsonObj.get("objID")));
		workfrontSPRequest.setDocObjCode(checkJsonIsNull(gsonObj.get("docObjCode")));
		setObjectTypeAndName(workfrontSPRequest, lookup);
		if(checkJsonIsNull(gsonObj.get("docObjCode")).equalsIgnoreCase("PROJ")){
			lookup.setObjectType("project");
			lookup.setObjectName(getProjectName(lookup.getObjectID()));
			workfrontSPRequest.setProjectName(lookup.getObjectName());
		}else if(checkJsonIsNull(gsonObj.get("docObjCode")).equalsIgnoreCase("TASk")){
			lookup.setObjectType("task");
			lookup.setObjectName(getTaskName(lookup.getObjectID()));
		}
		workfrontSPRequest.setSpFolderPath(workfrontSPRequest.getSpUrl()+workfrontSPRequest.getSpBaseFolder()+"/"+workfrontSPRequest.getProjectName().replaceAll(" ", "%20"));
		lookupArray.add(lookup);		
	}
	
	private void setObjectTypeAndName(WorkfrontSPRequest workfrontSPRequest, LookupBean lookup){
		if(StringUtils.equalsIgnoreCase(workfrontSPRequest.getDocObjCode(), "PROJ")){
			lookup.setObjectType("project");
			lookup.setObjectName(getObjectName(lookup.getObjectID(), "proj"));
		}else if(StringUtils.equalsIgnoreCase(workfrontSPRequest.getDocObjCode(), "TASK")){
			lookup.setObjectType("task");
			lookup.setObjectName(getObjectName(lookup.getObjectID(), "task"));
		}else if(StringUtils.equalsIgnoreCase(workfrontSPRequest.getDocObjCode(), "optask")){
			lookup.setObjectType("issue");
			lookup.setObjectName(getObjectName(lookup.getObjectID(), "optask"));
		}else if(StringUtils.equalsIgnoreCase(workfrontSPRequest.getDocObjCode(), "prgm")){
			lookup.setObjectType("program");
			lookup.setObjectName(getObjectName(lookup.getObjectID(), "prgm"));
		}else if(StringUtils.equalsIgnoreCase(workfrontSPRequest.getDocObjCode(), "port")){
			lookup.setObjectType("portfolio");
			lookup.setObjectName(getObjectName(lookup.getObjectID(), "port"));
		}
	}
		
	private void getDocument(WorkfrontSPRequest workfrontSPRequest, String documentId){
		try {
			JSONObject docs = workfrontServiceDao.get("DOCU", documentId, new String[]{"ID", "name", "currentVersionID", "extRefID", "objID", "topObjID", "downloadURL", "docObjCode", "currentVersion:ext"});
			workfrontSPRequest.setCurrentVersionID(docs.getString("currentVersionID"));
			workfrontSPRequest.setDocumentID(docs.getString("ID"));
			workfrontSPRequest.setDocumentName(docs.getString("name"));
			workfrontSPRequest.setDocumentExtension(docs.getJSONObject("currentVersion").getString("ext"));
			workfrontSPRequest.setDownloadURL(docs.getString("downloadURL"));
			workfrontSPRequest.setDocObjCode(docs.getString("docObjCode"));
			workfrontSPRequest.setObjID(docs.getString("objID"));
		} catch (WorkfrontException e) {
			e.printStackTrace();
		}
	}
	
	@Override
	public void updateWorkfrontAfterMove(WorkfrontSPRequest workfrontSPRequest){
		getLoginUser(workfrontSPRequest);
		updateExtRefID(workfrontSPRequest);		
		updateNote(workfrontSPRequest);
	}
	
	private void updateExtRefID(WorkfrontSPRequest workfrontSPRequest){
		Map<String, Object> map = new HashMap<String, Object>();
		String auditText = "Moved by "+workfrontSPRequest.getLoginUserName()+" at "+getCurrentDate()+" for Version "+workfrontSPRequest.getCurrentVersionID();
		map.put("extRefID", auditText);
		map.put("docObjCode", workfrontSPRequest.getDocObjCode());
		try {
			workfrontServiceDao.put("docu", workfrontSPRequest.getDocumentID(), map);
		} catch (WorkfrontException e) {
			e.printStackTrace();
		}
	}
	
	private void updateNote(WorkfrontSPRequest workfrontSPRequest){
		System.out.println("Updating note: "+workfrontSPRequest.getObjID());
		Map<String, Object> map = new HashMap<String, Object>();
		String noteText = workfrontSPRequest.getDocumentName()+" was sent to SharePoint by "+workfrontSPRequest.getLoginUserName()+" at "+getCurrentDate()+" for Version "+workfrontSPRequest.getCurrentVersionID();
		map.put("noteText", noteText);
		map.put("auditType", "NO");
		map.put("objID", workfrontSPRequest.getObjID());
		map.put("noteObjCode", workfrontSPRequest.getDocObjCode());
		try {
			workfrontServiceDao.post("note", map);
		} catch (WorkfrontException e) {
			e.printStackTrace();
		}
	}
	
	private String getCurrentDate(){
		DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
		Date date = new Date();
		return dateFormat.format(date);
	}
	
	private void getLoginUser(WorkfrontSPRequest workfrontSPRequest){		
		try {
			System.out.println(workfrontSPRequest.getWfSession());
			JSONObject user = workfrontServiceDao.get("user", workfrontSPRequest.getLoginUserID(), new String[]{"ID", "name"});
			workfrontSPRequest.setLoginUserName(user.getString("name"));
		} catch (JSONException | WorkfrontException e) {
			e.printStackTrace();
		}		
	}
	
	@Override
	public void downloadFile(WorkfrontSPRequest workfrontSPRequest, String documentId){
		getDocument(workfrontSPRequest, documentId);
		URL url;
        URLConnection con;
        DataInputStream dis;
        FileOutputStream fos;
        File file = null;
        byte[] fileData;
        String downloadStr = workfrontSPRequest.getWfUrl()+workfrontSPRequest.getDownloadURL()+"&sessionID="+workfrontSPRequest.getWfSessionId();
        System.out.println("download URL: "+downloadStr);
            try {
				url = new URL(downloadStr);
		    con = url.openConnection();
            dis = new DataInputStream(con.getInputStream());
            fileData = new byte[con.getContentLength()]; 
            for (int x = 0; x < fileData.length; x++) {
                fileData[x] = dis.readByte();
            }
            dis.close();
            String filePath = "c:\\Temp\\"+workfrontSPRequest.getDocumentName()+"."+workfrontSPRequest.getDocumentExtension();
            System.out.println("filePath: "+filePath);
            file = new File(filePath);
            workfrontSPRequest.setFile(file);
            fos = new FileOutputStream(file); 
            fos.write(fileData);  
            fos.close();
        	} catch (Exception e) {
				e.printStackTrace();
			}
        
	}

	
	private String getObjectName(String objId, String objType) {
		String objName = "";
		try {
			JSONObject obj = workfrontServiceDao.get(objType, objId, new String[]{"ID", "name"});
			objName = obj.getString("name");
		} catch (WorkfrontException e) {
			e.printStackTrace();
		}
		return objName;
	}

	private String getProjectName(String projId) {
		String projName = "";
		try {
			JSONObject task = workfrontServiceDao.get("proj", projId, new String[]{"ID", "name"});
			projName = task.getString("name");
		} catch (WorkfrontException e) {
			e.printStackTrace();
		}
		return projName;
	}
	
	private String getTaskName(String taskId) {
		String taskName = "";
		try {
			JSONObject task = workfrontServiceDao.get("task", taskId, new String[]{"ID", "name"});
			taskName = task.getString("name");
		} catch (WorkfrontException e) {
			e.printStackTrace();
		}
		return taskName;
	}
	
	private String checkJsonIsNull(JsonElement obj){
		String str = "";
		if(!obj.isJsonNull()){
			str = obj.getAsString();
		}
		return str;
	}
	
	private JsonObject convertToGSONFromJSONObject(JSONObject jsonObject){
		String strT = jsonObject.toString();
		JsonParser parser = new JsonParser();
		JsonObject jObj = (JsonObject) parser.parse(strT);
		return jObj;		
	}
}
