package com.aurotech.workfront.sharepoint.service;

import java.io.FileInputStream;
import java.util.List;

import com.aurotech.workfront.sharepoint.domain.WorkfrontSPRequest;
import com.aurotech.workfront.sharepoint.utils.EncryptionUtil;
import com.independentsoft.share.File;
import com.independentsoft.share.Folder;
import com.independentsoft.share.Service;
import com.independentsoft.share.ServiceException;

@org.springframework.stereotype.Service
public class SharePointServiceImpl implements SharePointService {
	
	private Service service = null;
	
	@Override
	public void createConnection(WorkfrontSPRequest workfrontSPRequest){
		System.out.println("create SharePoint connection");
		System.out.println(workfrontSPRequest.getSpUrl()+"-- "+workfrontSPRequest.getSpUsername()+"-- "+workfrontSPRequest.getSpPassword());
		service = new Service(workfrontSPRequest.getSpUrl(), workfrontSPRequest.getSpUsername(), EncryptionUtil.decrypt(workfrontSPRequest.getSpPassword()));
	}
	
	public void createProjectFolder(WorkfrontSPRequest workfrontSPRequest){
		 try {
			Folder folder = service.createFolder(workfrontSPRequest.getSpBaseFolder()+"/"+workfrontSPRequest.getProjectName());
			workfrontSPRequest.setFolder(folder);
			workfrontSPRequest.setProjectFolderPath(folder.getServerRelativeUrl());
		} catch (ServiceException e) {
			e.printStackTrace();
		}
	}

	@Override
	public boolean uploadFile(WorkfrontSPRequest workfrontSPRequest){
		boolean success = true;
		createProjectFolder(workfrontSPRequest);
		FileInputStream fileStream = null;    	
        try
        {        	
        	fileStream = new FileInputStream(workfrontSPRequest.getFile());
        	service.createFile(workfrontSPRequest.getProjectFolderPath()+"/"+workfrontSPRequest.getFile().getName(), fileStream);
        }catch(Exception e){
        	e.printStackTrace();
        	success = false;
        }
        return success;
	}

}
