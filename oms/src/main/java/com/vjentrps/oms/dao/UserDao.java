package com.vjentrps.oms.dao;

import java.util.List;

import com.vjentrps.oms.exception.OmsDataAccessException;
import com.vjentrps.oms.model.User;

public interface UserDao {

	 void addUser(User user) throws OmsDataAccessException ;
		
	 void deleteUser(long userId) throws OmsDataAccessException;
	
	 void updateUser(User user) throws OmsDataAccessException;
	 
	 List<User> fetchAllUsers() throws OmsDataAccessException;
	 
	 User getUserById(long userId) throws OmsDataAccessException;

	User authenticateUser(User user) throws OmsDataAccessException;
	 
 }
