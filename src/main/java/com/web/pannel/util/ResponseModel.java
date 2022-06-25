package com.web.pannel.util;

public class ResponseModel {

	private String status ;
	private Object Data;
	private static ResponseModel responseModel;
	
	
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public Object getData() {
		return Data;
	}
	public void setData(Object data) {
		Data = data;
	}
	
	@Override
	public String toString() {
		return "ResponseModel [status=" + status + ", Data=" + Data + "]";
	}
	
	public ResponseModel() {
		super();
	}
	public ResponseModel(String status, Object data) {
		super();
		this.status = status;
		Data = data;
	}
	
	public static ResponseModel getInstance() {
		  if (responseModel == null)
			  responseModel = new ResponseModel();
	 
	        return responseModel;
	}
	
	
}
