package com.aurotech.workfront.sharepoint.exception;

import org.apache.commons.lang3.exception.ContextedRuntimeException;
import org.apache.commons.lang3.exception.ExceptionContext;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class IntegrationException extends ContextedRuntimeException {

	private static final long serialVersionUID = 1L;

	public static final String CREATION_FAILED = "Add new ObjectCode Failed:";

	public static final String UPDATION_FAILED = "Update ObjectCode Failed:";
	
	public static final String CONTEXT_MESSAGE = "message";
	
	public static final String CONTEXT_WORKFRONT_ID = "WorkfrontID";
	
	public static final String CONTEXT_WORKFRONT_TYPE = "WorkfrontType";
	
	public static final String CONTEXT_JIRA_KEY = "JIRAKey";
	
	public static final String CONTEXT_ERROR_RESPONSE = "Error Response(s)";
	
	public static final String INVALID_ISSUE_MESSAGE = "Invalid issueType";
	
	public static final String INVALID_SYSTEM = "Invalid System\r\n";

	private static final transient Logger LOGGER = LoggerFactory.getLogger(IntegrationException.class);

	public IntegrationException() {
	}

	public IntegrationException(String message) {
		super(message);
	}

	public IntegrationException(Throwable cause) {
		super(cause);
	}

	public IntegrationException(String message, Throwable cause) {
		super(message, cause);
		logException();
	}

	public IntegrationException(String message, Throwable cause, ExceptionContext context) {
		super(message, cause, context);
	}

	private void logException() {
		String errMsg = getMessage();
		Throwable cause = this.getCause();
		if (cause != null && cause.getMessage() != null) {
			errMsg = errMsg.concat(cause.getMessage());
		}
		LOGGER.warn(errMsg, this);
	}
}
