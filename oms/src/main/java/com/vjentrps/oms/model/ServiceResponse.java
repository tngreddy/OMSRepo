package com.vjentrps.oms.model;

public class ServiceResponse {
	
	public ServiceResponse(Object object) {
		this.object = object;
	}
	
	public ServiceResponse(Error error) {
		this.error = error;
	}
	
	public ServiceResponse() {
	}

	private Object object;
	private Error error;
	public Object getObject() {
		return object;
	}

	public void setObject(Object object) {
		this.object = object;
	}

	public Error getError() {
		return error;
	}

	public void setError(Error error) {
		this.error = error;
	}
	
	
	
	
	
	

}
