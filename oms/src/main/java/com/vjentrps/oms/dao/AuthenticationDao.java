package com.vjentrps.oms.dao;

import com.vjentrps.oms.exception.OmsDataAccessException;
import com.vjentrps.oms.model.User;

public interface AuthenticationDao {

	User authenticateUser(User user) throws OmsDataAccessException;
	
	

}
