package com.aurotech.workfront.sharepoint.dao;

import com.aurotech.workfront.sharepoint.domain.WorkfrontSPRequest;
import com.aurotech.workfront.sharepoint.entity.Customer;
import com.aurotech.workfront.sharepoint.entity.CustomerSystem;

public interface IntegrationHubDAO {
	
	Customer getCustomerFromEmail();
	
	com.aurotech.workfront.sharepoint.entity.System getSharePointSystem();
	
	com.aurotech.workfront.sharepoint.entity.System getWorkfrontSystem();
	
	CustomerSystem findCustomerAndSystem(Customer customer, com.aurotech.workfront.sharepoint.entity.System system);
	
	void getParamsFromDatabase(WorkfrontSPRequest workfrontSPRequest);

}
