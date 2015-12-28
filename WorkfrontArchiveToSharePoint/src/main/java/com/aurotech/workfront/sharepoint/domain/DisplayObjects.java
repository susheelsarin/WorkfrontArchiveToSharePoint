package com.aurotech.workfront.sharepoint.domain;

import java.util.ArrayList;

public class DisplayObjects {
	
	private ArrayList<LookupBean> lookup;
	private String sessionId;
	private String projectId;
	private String serverURL;
	private String spFolderPath;
	private boolean validSubsribtion;
		
	public String getSessionId() {
		return sessionId;
	}
	public void setSessionId(String sessionId) {
		this.sessionId = sessionId;
	}
	public String getProjectId() {
		return projectId;
	}
	public void setProjectId(String projectId) {
		this.projectId = projectId;
	}
	public String getServerURL() {
		return serverURL;
	}
	public void setServerURL(String serverURL) {
		this.serverURL = serverURL;
	}
	public ArrayList<LookupBean> getLookup() {
		return lookup;
	}
	public void setLookup(ArrayList<LookupBean> lookup) {
		this.lookup = lookup;
	}
	public boolean isValidSubsribtion() {
		return validSubsribtion;
	}
	public void setValidSubsribtion(boolean validSubsribtion) {
		this.validSubsribtion = validSubsribtion;
	}
	public String getSpFolderPath() {
		return spFolderPath;
	}
	public void setSpFolderPath(String spFolderPath) {
		this.spFolderPath = spFolderPath;
	}

}
