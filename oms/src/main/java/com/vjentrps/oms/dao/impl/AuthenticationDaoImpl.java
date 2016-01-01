package com.vjentrps.oms.dao.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Repository;

import com.vjentrps.oms.dao.AuthenticationDao;
import com.vjentrps.oms.exception.OmsDataAccessException;
import com.vjentrps.oms.model.User;

@Repository
public class AuthenticationDaoImpl extends BaseDao implements AuthenticationDao {

	private Logger log = LoggerFactory.getLogger(this.getClass());

	@Value("${AUTHENTICATE_USER}")
	private String authenticateUserQuery;
	
	@Override
	public User authenticateUser(User user) throws OmsDataAccessException {
		try {
			//jdbcTemplate.queryForObject(authenticateUserQuery,
			//		new Object[] { } );
		} catch (DataAccessException dae) {
			throw new OmsDataAccessException(dae);
		}
		return user;
	}
}
