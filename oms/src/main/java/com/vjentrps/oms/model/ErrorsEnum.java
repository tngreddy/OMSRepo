package com.vjentrps.oms.model;

public enum ErrorsEnum {
	
	SERVICE_DOWN(1000),
	TECHNICAL_EXCEPTION(1001),
	NO_DATA_FOUND(1002),
	NO_USER_FOUND(1003),
	GOOD_OUT_EXCEEDED(1004),
	DEF_OUT_EXCEEDED(1005),
	GOOD_DEF_OUT_EXCEEDED(1006);
	
	
	private int code;
	
	private ErrorsEnum(int code) {
        this.code = code;
    }

	public int getCode() {
		return code;
	}
}
