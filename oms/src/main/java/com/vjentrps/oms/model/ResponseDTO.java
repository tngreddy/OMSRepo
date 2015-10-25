package com.vjentrps.oms.model;

public class ResponseDTO {
	
	public ResponseDTO(Object object) {
		this.object = object;
	}
	
	public ResponseDTO(Error error) {
		this.error = error;
	}
	
	public ResponseDTO() {
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
