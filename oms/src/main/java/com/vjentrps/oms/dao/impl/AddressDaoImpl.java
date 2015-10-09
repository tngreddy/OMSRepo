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
import com.vjentrps.oms.model.Address;

@Repository
public class AddressDaoImpl extends BaseDao implements AddressDao {

	private Logger log = LoggerFactory.getLogger(this.getClass());

	@Value("${ADD_ADDRESS}")
	private String addAddressQuery;
	
	@Value("${DELETE_ADDRESS}")
	private String deleteAddressQuery;
	
	@Value("${UPDATE_ADDRESS}")
	private String updateAddressQuery;

	
	@Override
	public long addAddress(final Address address) {
		
		KeyHolder keyHolder = new GeneratedKeyHolder();
		try {
		jdbcTemplate.update(
		    new PreparedStatementCreator() {
		        public PreparedStatement createPreparedStatement(Connection connection) throws SQLException {
		            PreparedStatement ps =
		                connection.prepareStatement(addAddressQuery,Statement.RETURN_GENERATED_KEYS);
		            ps.setString(1, address.getAddressLine1());
		            ps.setString(2, address.getAddressLine2());
		            ps.setString(3, address.getCity());
		            ps.setString(4, address.getState());
		            ps.setInt(5, address.getPinCode());
		            return ps;
		        }
		    },
		    keyHolder);
		} catch (DataAccessException dae) {

		}
		return (Long) keyHolder.getKey();
		
	}


	@Override
	public void deleteAddress(long addressId) {
		try {
			jdbcTemplate.update(deleteAddressQuery,
					new Object[] { addressId });
		} catch (DataAccessException dae) {

		}
		
	}


	@Override
	public void updateAddress(Address address) {
		try {
			jdbcTemplate.update(updateAddressQuery,
					new Object[] { address.getAddressLine1(), address.getAddressLine2(), address.getCity(), address.getState(), address.getPinCode(), address.getAddressId() });
		} catch (DataAccessException dae) {

		}
		
	}
}
