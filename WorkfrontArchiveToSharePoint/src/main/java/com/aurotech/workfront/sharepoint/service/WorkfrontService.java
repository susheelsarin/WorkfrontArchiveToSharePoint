package com.aurotech.workfront.sharepoint.service;

import com.aurotech.workfront.sharepoint.domain.WorkfrontSPRequest;

public interface WorkfrontService {

	void createSession(WorkfrontSPRequest workfrontSPRequest);
	
	void searchDocumentsInProject(WorkfrontSPRequest workfrontSPRequest);
	
	void downloadFile(WorkfrontSPRequest workfrontSPRequest, String documentId);
	
	void updateWorkfrontAfterMove(WorkfrontSPRequest workfrontSPRequest);
	
}
