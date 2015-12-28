package com.aurotech.workfront.sharepoint.controller;

import javax.servlet.ServletContext;

import org.apache.commons.lang3.StringUtils;
import org.joda.time.DateTime;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.aurotech.workfront.sharepoint.domain.DisplayObjects;
import com.aurotech.workfront.sharepoint.domain.LookupBean;
import com.aurotech.workfront.sharepoint.domain.WorkfrontSPRequest;
import com.aurotech.workfront.sharepoint.entity.Customer;
import com.aurotech.workfront.sharepoint.entity.CustomerSystem;
import com.aurotech.workfront.sharepoint.service.IntegrationHubService;
import com.aurotech.workfront.sharepoint.service.SharePointService;
import com.aurotech.workfront.sharepoint.service.WorkfrontService;

@Controller
@PropertySource("classpath:archive.properties")
public class WorkfrontSharePointController {
	
	@Autowired
	private Environment env;
	
	@Autowired
	private IntegrationHubService integrationHubService;
	
	@Autowired
	private ServletContext servletContext;
	
	private WorkfrontSPRequest workfrontSPRequest = new WorkfrontSPRequest();
	
	@Autowired
	private WorkfrontService workfrontService;
	
	@Autowired
	private SharePointService sharePointService;
	
	private DisplayObjects displayObjects = new DisplayObjects();
	
	@RequestMapping("/documents")
	public ModelAndView getWorkfrontDocuments(ModelMap model, 
			@RequestParam(required = true) String sessionId,
			@RequestParam(required = true) String projectID,
			@RequestParam(required = true) String userID,
			@RequestParam(required = false) String docID,
			@RequestParam(required = false) String version,
			@RequestParam(required = false) String versionID){		
				
		workfrontSPRequest.setWfSessionId(sessionId);
		workfrontSPRequest.setProjectId(projectID);
		workfrontSPRequest.setLoginUserID(userID);

		getCustemAndSystem(workfrontSPRequest);
		getSubscriptionValidity(workfrontSPRequest);
		integrationHubService.readParametersFromDatabase(workfrontSPRequest);
		
		workfrontService.createSession(workfrontSPRequest);
		workfrontService.searchDocumentsInProject(workfrontSPRequest);
		
		if(StringUtils.isNotEmpty(docID)){
			System.out.println("docID not empty: "+docID+" -- "+versionID+" -- "+version);
			workfrontService.downloadFile(workfrontSPRequest, docID);
			sharePointService.createConnection(workfrontSPRequest);
			if(sharePointService.uploadFile(workfrontSPRequest)){
				workfrontService.updateWorkfrontAfterMove(workfrontSPRequest);
			}
		}
		
		
		displayObjects.setLookup(workfrontSPRequest.getLookup());
		displayObjects.setServerURL(workfrontSPRequest.getWfUrl());
		displayObjects.setSpFolderPath(workfrontSPRequest.getSpFolderPath());
		
		System.out.println(workfrontSPRequest.getWfUrl());
		
		for(LookupBean ll : workfrontSPRequest.getLookup()){
			System.out.println(ll.getDocumentName()+" -- "+ll.getCurrentVersionID()+" -- "+ll.getDocumentID());
		}
		
		return new ModelAndView("documentsList", "list", displayObjects);	
	}
	
	@RequestMapping("/archive")
	public ModelAndView archiveDocument(ModelMap model){
		System.out.println("Archive");		
		return null;
	}
	
	private void getCustemAndSystem(WorkfrontSPRequest workfrontSPRequest){
		workfrontSPRequest.setCustomer(integrationHubService.findCustomerFromEmail());		
		workfrontSPRequest.setSpSystem(integrationHubService.getSharePointSystem());
		workfrontSPRequest.setWfSystem(integrationHubService.getWorkfrontSystem());
		workfrontSPRequest.setWfCustomerSystem(integrationHubService.findByCustomerAndSystem(workfrontSPRequest.getCustomer(), workfrontSPRequest.getWfSystem()));
		workfrontSPRequest.setSpCustomerSystem(integrationHubService.findByCustomerAndSystem(workfrontSPRequest.getCustomer(), workfrontSPRequest.getSpSystem()));
	}
	
	private void getSubscriptionValidity(WorkfrontSPRequest workfrontSPRequest){
		DateTimeFormatter dtf = DateTimeFormat.forPattern("MM/dd/yyyy");		
	
		DateTime endDate = dtf.parseDateTime(workfrontSPRequest.getSpCustomerSystem().getEndDate());
		
		if(endDate.isAfterNow()){
			displayObjects.setValidSubsribtion(true);
		}else{
			displayObjects.setValidSubsribtion(false);	
		}
		
	}
	
	private void readProperties(WorkfrontSPRequest workfrontSPRequest){
		workfrontSPRequest.setWfUrl(env.getProperty("workfront-url"));
		workfrontSPRequest.setWfUsername(env.getProperty("workfront-username"));
		workfrontSPRequest.setWfPassword(env.getProperty("workfront-password"));
		workfrontSPRequest.setWfApiKey(env.getProperty("workfront-apiKey"));
		workfrontSPRequest.setSpUrl(env.getProperty("sharepoint-url"));
		workfrontSPRequest.setSpUsername(env.getProperty("sharepoint-username"));
		workfrontSPRequest.setSpPassword(env.getProperty("sharepoint-password"));
		workfrontSPRequest.setSpBaseFolder(env.getProperty("sharepoint-base-folder"));
	}

}
