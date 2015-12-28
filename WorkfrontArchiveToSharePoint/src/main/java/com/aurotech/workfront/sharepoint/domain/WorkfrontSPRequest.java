package com.aurotech.workfront.sharepoint.domain;

import java.io.File;
import java.util.ArrayList;

import org.json.JSONObject;

import com.aurotech.workfront.sharepoint.dao.WorkfrontServiceDao;
import com.aurotech.workfront.sharepoint.entity.Customer;
import com.aurotech.workfront.sharepoint.entity.CustomerSystem;
import com.independentsoft.share.Folder;

public class WorkfrontSPRequest {
	
	private String wfUrl;
	private String wfUsername;
	private String wfPassword;
	private String wfSessionId;
	private JSONObject wfSession;
	private String projectId;
	private String projectName;
	private String spUrl;
	private String spUsername;
	private String spPassword;
	private String wfApiKey;
	private WorkfrontServiceDao workfrontServiceDao;
	private String documentName;
	private String currentVersionID;
	private String extRefID;
	private ArrayList<LookupBean> lookup;
	private String serverURL;
	private String spBaseFolder;
	private Folder folder;
	private String projectFolderPath;
	private String downloadURL;
	private String documentID;;
	private File file;
	private Customer customer;
	private com.aurotech.workfront.sharepoint.entity.System wfSystem;
	private com.aurotech.workfront.sharepoint.entity.System spSystem;
	private CustomerSystem wfCustomerSystem;
	private CustomerSystem spCustomerSystem;
	private String docObjCode;
	private String spFolderPath;
	private String documentExtension;
	private String loginUserID;
	private String loginUserName;
	private String objID;
	
	public String getWfUrl() {
		return wfUrl;
	}
	public void setWfUrl(String wfUrl) {
		this.wfUrl = wfUrl;
	}
	public String getWfUsername() {
		return wfUsername;
	}
	public void setWfUsername(String wfUsername) {
		this.wfUsername = wfUsername;
	}
	public String getWfPassword() {
		return wfPassword;
	}
	public void setWfPassword(String wfPassword) {
		this.wfPassword = wfPassword;
	}
	public String getWfSessionId() {
		return wfSessionId;
	}
	public void setWfSessionId(String wfSessionId) {
		this.wfSessionId = wfSessionId;
	}
	public JSONObject getWfSession() {
		return wfSession;
	}
	public void setWfSession(JSONObject wfSession) {
		this.wfSession = wfSession;
	}
	public String getProjectId() {
		return projectId;
	}
	public void setProjectId(String projectId) {
		this.projectId = projectId;
	}
	public String getProjectName() {
		return projectName;
	}
	public void setProjectName(String projectName) {
		this.projectName = projectName;
	}
	public String getSpUrl() {
		return spUrl;
	}
	public void setSpUrl(String spUrl) {
		this.spUrl = spUrl;
	}
	public String getSpUsername() {
		return spUsername;
	}
	public void setSpUsername(String spUsername) {
		this.spUsername = spUsername;
	}
	public String getSpPassword() {
		return spPassword;
	}
	public void setSpPassword(String spPassword) {
		this.spPassword = spPassword;
	}
	public String getWfApiKey() {
		return wfApiKey;
	}
	public void setWfApiKey(String wfApiKey) {
		this.wfApiKey = wfApiKey;
	}
	public WorkfrontServiceDao getWorkfrontServiceDao() {
		return workfrontServiceDao;
	}
	public void setWorkfrontServiceDao(WorkfrontServiceDao workfrontServiceDao) {
		this.workfrontServiceDao = workfrontServiceDao;
	}
	public String getDocumentName() {
		return documentName;
	}
	public void setDocumentName(String documentName) {
		this.documentName = documentName;
	}
	public String getCurrentVersionID() {
		return currentVersionID;
	}
	public void setCurrentVersionID(String currentVersionID) {
		this.currentVersionID = currentVersionID;
	}
	public String getExtRefID() {
		return extRefID;
	}
	public void setExtRefID(String extRefID) {
		this.extRefID = extRefID;
	}
	public ArrayList<LookupBean> getLookup() {
		return lookup;
	}
	public void setLookup(ArrayList<LookupBean> lookup) {
		this.lookup = lookup;
	}
	public String getServerURL() {
		return serverURL;
	}
	public void setServerURL(String serverURL) {
		this.serverURL = serverURL;
	}
	public String getSpBaseFolder() {
		return spBaseFolder;
	}
	public void setSpBaseFolder(String spBaseFolder) {
		this.spBaseFolder = spBaseFolder;
	}
	public Folder getFolder() {
		return folder;
	}
	public void setFolder(Folder folder) {
		this.folder = folder;
	}
	public String getDownloadURL() {
		return downloadURL;
	}
	public void setDownloadURL(String downloadURL) {
		this.downloadURL = downloadURL;
	}
	public String getDocumentID() {
		return documentID;
	}
	public void setDocumentID(String documentID) {
		this.documentID = documentID;
	}
	public File getFile() {
		return file;
	}
	public void setFile(File file) {
		this.file = file;
	}
	public String getProjectFolderPath() {
		return projectFolderPath;
	}
	public void setProjectFolderPath(String projectFolderPath) {
		this.projectFolderPath = projectFolderPath;
	}
	public Customer getCustomer() {
		return customer;
	}
	public void setCustomer(Customer customer) {
		this.customer = customer;
	}
	public com.aurotech.workfront.sharepoint.entity.System getWfSystem() {
		return wfSystem;
	}
	public void setWfSystem(com.aurotech.workfront.sharepoint.entity.System wfSystem) {
		this.wfSystem = wfSystem;
	}
	public com.aurotech.workfront.sharepoint.entity.System getSpSystem() {
		return spSystem;
	}
	public void setSpSystem(com.aurotech.workfront.sharepoint.entity.System spSystem) {
		this.spSystem = spSystem;
	}
	public CustomerSystem getWfCustomerSystem() {
		return wfCustomerSystem;
	}
	public void setWfCustomerSystem(CustomerSystem wfCustomerSystem) {
		this.wfCustomerSystem = wfCustomerSystem;
	}
	public CustomerSystem getSpCustomerSystem() {
		return spCustomerSystem;
	}
	public void setSpCustomerSystem(CustomerSystem spCustomerSystem) {
		this.spCustomerSystem = spCustomerSystem;
	}
	public String getDocObjCode() {
		return docObjCode;
	}
	public void setDocObjCode(String docObjCode) {
		this.docObjCode = docObjCode;
	}
	public String getSpFolderPath() {
		return spFolderPath;
	}
	public void setSpFolderPath(String spFolderPath) {
		this.spFolderPath = spFolderPath;
	}
	public String getDocumentExtension() {
		return documentExtension;
	}
	public void setDocumentExtension(String documentExtension) {
		this.documentExtension = documentExtension;
	}
	public String getLoginUserID() {
		return loginUserID;
	}
	public void setLoginUserID(String loginUserID) {
		this.loginUserID = loginUserID;
	}
	public String getLoginUserName() {
		return loginUserName;
	}
	public void setLoginUserName(String loginUserName) {
		this.loginUserName = loginUserName;
	}
	public String getObjID() {
		return objID;
	}
	public void setObjID(String objID) {
		this.objID = objID;
	}

}
