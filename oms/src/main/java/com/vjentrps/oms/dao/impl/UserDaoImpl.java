package com.vjentrps.oms.dao.impl;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.dao.DataAccessException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import com.vjentrps.oms.dao.UserDao;
import com.vjentrps.oms.exception.OmsDataAccessException;
import com.vjentrps.oms.model.User;
import com.vjentrps.oms.model.UserRole;

@Repository
public class UserDaoImpl extends BaseDao implements UserDao {
	
	@Value("${ADD_USER}")
	private String addUserQuery;
	
	@Value("${FETCH_USERS}")
	private String fetchAllUsersQuery;
	
	@Value("${GET_USER}")
	private String getUserQuery;
	
	@Value("${UPDATE_USER}")
	private String updateUserQuery;
	
	@Value("${DELETE_USER}")
	private String deleteUserQuery;
	
	@Value("${AUTHENTICATE_USER}")
	private String authenticateUserQuery;
	

	@Override
	public void addUser(User user) throws OmsDataAccessException {
		try {
			jdbcTemplate.update(addUserQuery,
					new Object[] { user.getUserName(), user.getEncryptedPassword(), user.getUserRole().getRoleId() });
		} catch (DataAccessException dae) {
			throw new OmsDataAccessException(dae);
		}
		
	}

	@Override
	public void deleteUser(long userId)  throws OmsDataAccessException{
		try {
		jdbcTemplate.update(deleteUserQuery,
				new Object[] { userId });
	} catch (DataAccessException dae) {
		throw new OmsDataAccessException(dae);
	}
	}
		

	@Override
	public void updateUser(User user) throws OmsDataAccessException {
		try {
			jdbcTemplate.update(updateUserQuery,
					new Object[] { user.getUserName(), user.getEncryptedPassword(), user.getUserRole().getRoleId(), user.getUserId()});
		} catch (DataAccessException dae) {
			throw new OmsDataAccessException(dae);
		}
	}

	@Override
	public List<User> fetchAllUsers() throws OmsDataAccessException{
		String sql = fetchAllUsersQuery;
		List<User> users = null;

		UserRowMapper resultSetExtractor = new UserRowMapper();
		try {
			users = (List<User>) jdbcTemplate.query(sql,
					resultSetExtractor);
		} catch (DataAccessException dae) {
			throw new OmsDataAccessException(dae);
		}
		return users;
	}
	
	
	@Override
	public User getUserById(long userId) throws OmsDataAccessException{
		User user = null;
		UserRowMapper resultSetExtractor = new UserRowMapper();
		try {
			user = (User) jdbcTemplate.queryForObject(getUserQuery,
					new Object[] { userId }, resultSetExtractor);
		} catch (DataAccessException dae) {
			throw new OmsDataAccessException(dae);
		}
		return user;
	}
	
	@Override
	public User authenticateUser(User user) throws OmsDataAccessException{
		User validUser = null;
		UserRowMapper resultSetExtractor = new UserRowMapper();
		try {
			validUser = (User) jdbcTemplate.queryForObject(authenticateUserQuery,
					new Object[] { user.getUserName(), user.getEncryptedPassword() }, resultSetExtractor);
		} catch (EmptyResultDataAccessException er) {
			
		} catch (DataAccessException dae) {
			throw new OmsDataAccessException(dae);
		}
		return validUser;
	}



	private static class UserRowMapper implements RowMapper<User> {

		
		public User mapRow(ResultSet resultSet, int arg1)
				throws SQLException {

			User user = new User();
			user.setUserId(resultSet.getLong("user_id"));
			user.setUserName(resultSet.getString("user_name"));
			user.setEncryptedPassword(resultSet.getString("password"));
			UserRole userRole = new UserRole();
			userRole.setRoleId(resultSet.getLong("role_id"));
			userRole.setRoleName(resultSet.getString("role_name"));
			user.setUserRole(userRole);
			return user;
		}

	}
	



}

