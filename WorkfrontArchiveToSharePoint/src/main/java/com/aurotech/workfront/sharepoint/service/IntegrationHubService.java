package com.aurotech.workfront.sharepoint.service;

import com.aurotech.workfront.sharepoint.domain.WorkfrontSPRequest;
import com.aurotech.workfront.sharepoint.entity.Customer;
import com.aurotech.workfront.sharepoint.entity.CustomerSystem;


public interface IntegrationHubService {
	
	Customer findCustomerFromEmail();
	
	com.aurotech.workfront.sharepoint.entity.System getSharePointSystem();
	
	com.aurotech.workfront.sharepoint.entity.System getWorkfrontSystem();
	
	CustomerSystem findByCustomerAndSystem(Customer customer, com.aurotech.workfront.sharepoint.entity.System system);

	void readParametersFromDatabase(WorkfrontSPRequest workfrontSPRequest);
	
}
