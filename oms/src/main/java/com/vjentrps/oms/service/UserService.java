package com.vjentrps.oms.service;

import java.util.List;

import com.vjentrps.oms.exception.OmsServiceException;
import com.vjentrps.oms.model.User;

public interface UserService {
	
	List<User> getUsers() throws OmsServiceException;

	User getUserById(long userId) throws OmsServiceException;

	void updateUser(User user) throws OmsServiceException;

	void addUser(User user) throws OmsServiceException;

	void deleteUser(long userId) throws OmsServiceException;

	User authenticateUser(User user) throws OmsServiceException;
 
	
}
