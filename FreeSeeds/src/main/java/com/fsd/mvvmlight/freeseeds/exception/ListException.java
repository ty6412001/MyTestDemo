package com.fsd.mvvmlight.freeseeds.exception;

import com.fsd.mvvmlight.freeseeds.entity.DomainError;

import org.json.JSONObject;

public class ListException extends Exception {
			
	/**
	 * 
	 */
	private static final long serialVersionUID = 12345L;

	public ListException() {}
	
	public static String createExceptionMessage(Integer errorCode, String errorDescription) {

		DomainError error = null;
		if (errorCode != null || errorDescription != null) {
			error = new DomainError(errorCode, errorDescription);
		} 

		if (error != null) {
			JSONObject joo = error.toJSONObject();
			return joo.toString();
		} else {
			return null;
		}
	}
	
	public ListException(String message) {
		super(message);
	}
	
	public ListException(Throwable cause) {
		super(cause);
	}

	public ListException(String message, Throwable cause) {
		super(message, cause);
	}
	
	public ListException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
		super(message, cause);
	}
	
	public ListException(JSONObject message_joo) {
		super(message_joo.toString());
	}
	
}
