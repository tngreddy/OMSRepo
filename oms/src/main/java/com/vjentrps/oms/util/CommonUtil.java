package com.vjentrps.oms.util;

import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Component;

import com.vjentrps.oms.model.Error;
import com.vjentrps.oms.model.ErrorsEnum;

@Component
public class CommonUtil {

	@Resource(name = "errorMessages")
	public Map<Integer, String> errorMessages;

	public Error processError(ErrorsEnum err) {

		return new Error(err.getCode(), errorMessages.get(err.getCode()));

	}

}
