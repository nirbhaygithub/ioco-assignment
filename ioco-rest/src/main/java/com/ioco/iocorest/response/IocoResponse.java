package com.ioco.iocorest.response;

public class IocoResponse {

	private Object responseMessage;

	private String responseCode;

	private Object responseObject;

	public IocoResponse() {
		super();
	}

	public IocoResponse(Object responseMessage, String responseCode, Object responseObject) {
		super();
		this.responseMessage = responseMessage;
		this.responseCode = responseCode;
		this.responseObject = responseObject;
	}

	public Object getResponseMessage() {
		return responseMessage;
	}

	public void setResponseMessage(String responseMessage) {
		this.responseMessage = responseMessage;
	}

	public String getResponseCode() {
		return responseCode;
	}

	public void setResponseCode(String responseCode) {
		this.responseCode = responseCode;
	}

	public Object getResponseObject() {
		return responseObject;
	}

	public void setResponseObject(Object responseObject) {
		this.responseObject = responseObject;
	}

}
