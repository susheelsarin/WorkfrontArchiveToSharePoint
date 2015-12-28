package com.aurotech.workfront.sharepoint.dao;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.aurotech.workfront.sharepoint.domain.WorkfrontSPRequest;
import com.aurotech.workfront.sharepoint.entity.Customer;
import com.aurotech.workfront.sharepoint.entity.CustomerSystem;
import com.aurotech.workfront.sharepoint.entity.CustomerSystemProperty;
import com.aurotech.workfront.sharepoint.entity.Users;

@Repository
public class IntegrationHubDAOImpl implements IntegrationHubDAO {
	
	@Autowired
	private SessionFactory sessionFactory;
	
	public Users findUserFromEmail(){
		Session session = this.sessionFactory.getCurrentSession();
		System.out.println(session);
		
		String hql = "FROM Users U WHERE U.email = :email";
		Query query = session.createQuery(hql);
		query.setParameter("email","susheel.sarin@aurotechcorp.com");
		List results = query.list();
		
		System.out.println(results.get(0));
		
		Users user = (Users) session.get(Users.class, new Long(125));
		System.out.println(user.getEmail());
		
		return null;
	}

	@Override
	public Customer getCustomerFromEmail() {
		String customerHql = "FROM Customer C WHERE C.pointOfContactEmail = :email";
		Query query = getListFromQuery(customerHql);
		query.setParameter("email","susheel.sarin@aurotechcorp.com");
		return (Customer) query.list().get(0);
	}

	@Override
	public com.aurotech.workfront.sharepoint.entity.System getSharePointSystem() {
		String systemHql = "FROM System C WHERE C.value = :value";
		Query query = getListFromQuery(systemHql);
		query.setParameter("value","SHAREPOINT");
		return (com.aurotech.workfront.sharepoint.entity.System) query.list().get(0);
	}
	
	@Override
	public com.aurotech.workfront.sharepoint.entity.System getWorkfrontSystem() {
		String systemHql = "FROM System C WHERE C.value = :value";
		Query query = getListFromQuery(systemHql);
		query.setParameter("value","WORKFRONT");
		return (com.aurotech.workfront.sharepoint.entity.System) query.list().get(0);
	}


	@Override
	public CustomerSystem findCustomerAndSystem(Customer customer, com.aurotech.workfront.sharepoint.entity.System system) {
		String systemHql = "FROM CustomerSystem CS WHERE CS.customer = :customer and CS.system = :system";
		Query query = getListFromQuery(systemHql);
		query.setParameter("customer", customer);
		query.setParameter("system", system);
		return (CustomerSystem) query.list().get(0);
	}

	
	@Override
	public void getParamsFromDatabase(WorkfrontSPRequest workfrontSPRequest) {
		String propertyHql = "FROM CustomerSystemProperty CSP WHERE CSP.customerSystem.id = :spCustomerSystem OR CSP.customerSystem.id = :wfCustomerSystem";
		Query query = getListFromQuery(propertyHql);
		System.out.println("ID: "+workfrontSPRequest.getSpCustomerSystem().getId());
		query.setParameter("spCustomerSystem", workfrontSPRequest.getSpCustomerSystem().getId());
		query.setParameter("wfCustomerSystem", workfrontSPRequest.getWfCustomerSystem().getId());
		setValuesFromQueryList(workfrontSPRequest, query.list());		
	}
	
	private void setValuesFromQueryList(WorkfrontSPRequest workfrontSPRequest, List<CustomerSystemProperty> cspList){
		for(CustomerSystemProperty csp: cspList){
			
			System.out.println(csp.getSystemProperty().getName()+" CSP Value: "+csp.getValue());
			
			if(StringUtils.equalsIgnoreCase(csp.getSystemProperty().getName(), "sharepointURL")){
				workfrontSPRequest.setSpUrl(csp.getValue());
			}if(StringUtils.equalsIgnoreCase(csp.getSystemProperty().getName(), "sharepointUsername")){
				workfrontSPRequest.setSpUsername(csp.getValue());
			}if(StringUtils.equalsIgnoreCase(csp.getSystemProperty().getName(), "sharepointPassword")){
				workfrontSPRequest.setSpPassword(csp.getValue());
			}if(StringUtils.equalsIgnoreCase(csp.getSystemProperty().getName(), "sharepointDocumentsLocation")){
				workfrontSPRequest.setSpBaseFolder(csp.getValue());
			}if(StringUtils.equalsIgnoreCase(csp.getSystemProperty().getName(), "url")){
				workfrontSPRequest.setWfUrl(csp.getValue());
			}
		}
	}
	
	private Query getListFromQuery(String hql){
		Session session = this.sessionFactory.getCurrentSession();		
		return session.createQuery(hql);
	}


}
