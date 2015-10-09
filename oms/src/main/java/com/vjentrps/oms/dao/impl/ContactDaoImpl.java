package com.vjentrps.oms.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import com.vjentrps.oms.dao.AddressDao;
import com.vjentrps.oms.dao.ContactDao;
import com.vjentrps.oms.model.Contact;

@Repository
public class ContactDaoImpl extends BaseDao implements ContactDao {

	private Logger log = LoggerFactory.getLogger(this.getClass());

	@Value("${ADD_CONTACT}")
	private String addContactQuery;
	
	@Value("${DELETE_CONTACT}")
	private String deleteContactQuery;
	
	@Value("${UPDATE_CONTACT}")
	private String updateContactQuery;

	
	@Override
	public long addContact(final Contact contact) {
		
		KeyHolder keyHolder = new GeneratedKeyHolder();
		try {
		jdbcTemplate.update(
		    new PreparedStatementCreator() {
		        public PreparedStatement createPreparedStatement(Connection connection) throws SQLException {
		            PreparedStatement ps =
		                connection.prepareStatement(addContactQuery, Statement.RETURN_GENERATED_KEYS);
		            ps.setString(1, contact.getContactPerson());
		            ps.setString(2, contact.getContactDesignation());
		            ps.setLong(3, contact.getAddress().getAddressId());
		            ps.setInt(4, contact.getMobileNo());
		            ps.setInt(5, contact.getPhoneNo());
		            return ps;
		        }
		    },
		    keyHolder);
		} catch (DataAccessException dae) {

		}
		return (Long) keyHolder.getKey();
	}


	@Override
	public void deleteContact(long contactId) {
		try {
			jdbcTemplate.update(deleteContactQuery,
					new Object[] { contactId });
		} catch (DataAccessException dae) {

		}
		
	}


	@Override
	public void updateContact(Contact contact) {
		try {
			jdbcTemplate.update(updateContactQuery,
					new Object[] { contact.getContactPerson(), contact.getContactDesignation(), contact.getPhoneNo(), contact.getMobileNo(), contact.getContactId()});
		} catch (DataAccessException dae) {

		}
		
	}
}
