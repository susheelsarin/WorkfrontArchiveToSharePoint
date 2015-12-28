package com.aurotech.workfront.sharepoint.service;

import com.aurotech.workfront.sharepoint.domain.WorkfrontSPRequest;

public interface SharePointService {

	void createConnection(WorkfrontSPRequest workfrontSPRequest);
	
	boolean uploadFile(WorkfrontSPRequest workfrontSPRequest);
	
}
