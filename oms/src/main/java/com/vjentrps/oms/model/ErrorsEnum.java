package com.vjentrps.oms.model;

public enum ErrorsEnum {
	
	SERVICE_DOWN(1000),
	TECHNICAL_EXCEPTION(1001);
	
	
	private int code;
	
	private ErrorsEnum(int code) {
        this.code = code;
    }

	public int getCode() {
		return code;
	}
}
