package com.vjentrps.oms.service.impl;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.vjentrps.oms.dao.UserDao;
import com.vjentrps.oms.exception.OmsDataAccessException;
import com.vjentrps.oms.exception.OmsServiceException;
import com.vjentrps.oms.model.User;
import com.vjentrps.oms.service.UserService;
import com.vjentrps.oms.util.CommonUtil;

@Service
@Transactional(rollbackFor={RuntimeException.class, Exception.class})
public class UserServiceImpl implements UserService {

	private Logger log = LoggerFactory.getLogger(this.getClass());

	@Autowired
	public UserDao userDao;

	@Override
	public List<User> getUsers() throws OmsServiceException {
		
		try {
			return userDao.fetchAllUsers();
			
		} catch (OmsDataAccessException e) {
			throw new OmsServiceException(e);
		}
	}

	@Override
	public User getUserById(long userId) throws OmsServiceException {
		try {
			return userDao.getUserById(userId);
			
		} catch (OmsDataAccessException e) {
			throw new OmsServiceException(e);
		}
	}

	@Override
	public void updateUser(User user) throws OmsServiceException {
		try {
			user.setEncryptedPassword(CommonUtil.encryptString(user.getPassword()));
			userDao.updateUser(user);
			
		} catch (OmsDataAccessException e) {
			throw new OmsServiceException(e);
		}
		
	}

	@Override
	public void addUser(User user) throws OmsServiceException {
		try {
			
			user.setEncryptedPassword(CommonUtil.encryptString(user.getPassword()));
			userDao.addUser(user);
			
		} catch (OmsDataAccessException e) {
			throw new OmsServiceException(e);
		}
		
	}

	@Override
	public void deleteUser(long userId) throws OmsServiceException {
		try {
			userDao.deleteUser(userId);;
			
		} catch (OmsDataAccessException e) {
			throw new OmsServiceException(e);
		}
		
	}

	@Override
	public User authenticateUser(User user) throws OmsServiceException {
		try {
			user.setEncryptedPassword(CommonUtil.encryptString(user.getPassword()));
			 User validUser = userDao.authenticateUser(user);
			 if(null!=validUser) {
				 validUser.setEncryptedPassword(null); 
			 }
		     return validUser;
			
		} catch (OmsDataAccessException e) {
			throw new OmsServiceException(e);
		}
	}



	
}
