package com.vjentrps.oms.service;

import com.vjentrps.oms.exception.OmsServiceException;
import com.vjentrps.oms.model.User;

public interface AuthenticationService {
	
	 User authenticateUser(User user) throws OmsServiceException;
	
		 
	
}
