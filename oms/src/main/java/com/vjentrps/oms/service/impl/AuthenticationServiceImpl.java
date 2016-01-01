package com.vjentrps.oms.service.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.vjentrps.oms.dao.AuthenticationDao;
import com.vjentrps.oms.exception.OmsDataAccessException;
import com.vjentrps.oms.exception.OmsServiceException;
import com.vjentrps.oms.model.User;
import com.vjentrps.oms.service.AuthenticationService;

@Service
@Transactional
public class AuthenticationServiceImpl  implements AuthenticationService{

	private Logger log = LoggerFactory.getLogger(this.getClass());
	
	@Autowired
	public AuthenticationDao authenticateDao;
	

	@Override
	public User authenticateUser(User user) throws OmsServiceException {
		try {
			return authenticateDao.authenticateUser(user);
		} catch (OmsDataAccessException e) {
			throw new OmsServiceException(e);
		}
	}

	

}
