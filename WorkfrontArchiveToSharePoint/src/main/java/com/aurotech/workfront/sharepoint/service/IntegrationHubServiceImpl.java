package com.aurotech.workfront.sharepoint.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.aurotech.workfront.sharepoint.dao.IntegrationHubDAO;
import com.aurotech.workfront.sharepoint.domain.WorkfrontSPRequest;
import com.aurotech.workfront.sharepoint.entity.Customer;
import com.aurotech.workfront.sharepoint.entity.CustomerSystem;
import com.aurotech.workfront.sharepoint.entity.System;

@Service
public class IntegrationHubServiceImpl implements IntegrationHubService {

	@Autowired
	IntegrationHubDAO integrationHubDAO;

	@Override
	@Transactional
	public Customer findCustomerFromEmail() {
		return integrationHubDAO.getCustomerFromEmail();
	}

	@Override
	@Transactional
	public com.aurotech.workfront.sharepoint.entity.System getSharePointSystem() {
		return integrationHubDAO.getSharePointSystem();		 
	}

	@Override
	@Transactional
	public CustomerSystem findByCustomerAndSystem(Customer customer, com.aurotech.workfront.sharepoint.entity.System system) {
		return integrationHubDAO.findCustomerAndSystem(customer, system);
	}

	@Override
	@Transactional
	public void readParametersFromDatabase(WorkfrontSPRequest workfrontSPRequest) {
		integrationHubDAO.getParamsFromDatabase(workfrontSPRequest);		
	}

	@Override
	@Transactional
	public com.aurotech.workfront.sharepoint.entity.System getWorkfrontSystem() {
		return integrationHubDAO.getWorkfrontSystem();	
	}
	
	

}
