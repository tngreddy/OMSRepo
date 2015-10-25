package com.vjentrps.oms.model;

public enum ErrorsEnum {
	
	TECHNICAL_EXCEPTION(1000);
	
	
	private int code;
	
	private ErrorsEnum(int code) {
        this.code = code;
    }

	public int getCode() {
		return code;
	}
}
