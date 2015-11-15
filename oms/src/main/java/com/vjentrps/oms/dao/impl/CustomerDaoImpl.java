package com.vjentrps.oms.dao.impl;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.dao.DataAccessException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import com.vjentrps.oms.dao.CustomerDao;
import com.vjentrps.oms.exception.OmsDataAccessException;
import com.vjentrps.oms.model.Address;
import com.vjentrps.oms.model.BasicInfo;
import com.vjentrps.oms.model.Contact;
import com.vjentrps.oms.model.Customer;

@Repository
public class CustomerDaoImpl extends BaseDao implements CustomerDao {
	
	@Value("${ADD_CUSTOMER}")
	private String addCustomerQuery;
	
	@Value("${FETCH_CUSTOMERS}")
	private String fetchAllCustomersQuery;
	
	@Value("${FETCH_CUSTOMERS_BASIC_INFO}")
	private String fetchCustomersBasicInfoQuery;
	
	@Value("${GET_CUSTOMER}")
	private String getCustomerQuery;
	
	@Value("${UPDATE_CUSTOMER}")
	private String updateCustomerQuery;
	
	@Value("${DELETE_CUSTOMER}")
	private String deleteCustomerQuery;
	
	@Value("${GET_CUST_IDS}")
	private String getCustIdsQuery;
	
	@Value("${CUSTOMER_COUNT}")
	private String customerCountQuery;
	

	@Override
	public void addCustomer(Customer customer) throws OmsDataAccessException {
		try {
			jdbcTemplate.update(addCustomerQuery,
					new Object[] { customer.getCustomerName(), customer.getTinNo(), customer.getCstNo(), customer.getContact().getContactId() });
		} catch (DataAccessException dae) {
			throw new OmsDataAccessException(dae);
		}
		
	}

	@Override
	public void deleteCustomer(long customerId)  throws OmsDataAccessException{
		try {
		jdbcTemplate.update(deleteCustomerQuery,
				new Object[] { customerId });
	} catch (DataAccessException dae) {
		throw new OmsDataAccessException(dae);
	}
	}
		

	@Override
	public void updateCustomer(Customer customer) throws OmsDataAccessException {
		try {
			jdbcTemplate.update(updateCustomerQuery,
					new Object[] { customer.getCustomerName(), customer.getTinNo(), customer.getCstNo(), customer.getCustomerId()});
		} catch (DataAccessException dae) {
			throw new OmsDataAccessException(dae);
		}
	}

	@Override
	public List<Customer> fetchAllCustomers() throws OmsDataAccessException{
		String sql = fetchAllCustomersQuery;
		List<Customer> customers = null;

		CustomerRowMapper resultSetExtractor = new CustomerRowMapper();
		try {
			customers = (List<Customer>) jdbcTemplate.query(sql,
					resultSetExtractor);
		} catch (DataAccessException dae) {
			throw new OmsDataAccessException(dae);
		}
		return customers;
	}
	
	
	@Override
	public Customer getCustomerById(long customerId) throws OmsDataAccessException{
		Customer customer = null;
		CustomerRowMapper resultSetExtractor = new CustomerRowMapper();
		try {
			customer = (Customer) jdbcTemplate.queryForObject(getCustomerQuery,
					new Object[] { customerId }, resultSetExtractor);
		} catch (DataAccessException dae) {
			throw new OmsDataAccessException(dae);
		}
		return customer;
	}

	@Override
	public Customer getCustIds(long customerId) throws OmsDataAccessException {
		Customer customer = null;
		CustomerIdsRowMapper resultSetExtractor = new CustomerIdsRowMapper();
		try {
			customer = (Customer) jdbcTemplate.queryForObject(getCustIdsQuery,
					new Object[] { customerId }, resultSetExtractor);
			
			
			
		} catch (DataAccessException dae) {
			throw new OmsDataAccessException(dae);
		}
		return customer;
	}
	
	
	@Override
	public int getCustomerCount() throws OmsDataAccessException{
		int count = 0;
		try {
			count = jdbcTemplate.queryForObject(customerCountQuery, Integer.class);
		} catch (DataAccessException dae) {
			throw new OmsDataAccessException(dae);
		}
		return count;
	}

	
	private static class CustomerRowMapper implements RowMapper<Customer> {

		
		public Customer mapRow(ResultSet resultSet, int arg1)
				throws SQLException {

			Customer customer = new Customer();
			Contact contact = new Contact();
			Address address =  new Address();
			address.setAddressId(resultSet.getLong("address_id"));
			address.setAddressLine1(resultSet.getString("address1"));
			address.setAddressLine2(resultSet.getString("address2"));
			address.setCity(resultSet.getString("city"));
			address.setState(resultSet.getString("state"));
			address.setPinCode(resultSet.getInt("pincode"));
			contact.setAddress(address);
			contact.setContactId(resultSet.getLong("contact_id"));
			contact.setContactPerson(resultSet.getString("contact_person"));
			contact.setContactDesignation(resultSet.getString("contact_desgn"));
			contact.setMobileNo(resultSet.getString("mobile"));
			contact.setPhoneNo(resultSet.getString("phone"));
			customer.setContact(contact);
			customer.setCustomerId(resultSet.getLong("customer_id"));
			customer.setCustomerName(resultSet.getString("customer_name"));
			customer.setTinNo(resultSet.getString("tin_no"));
			customer.setCstNo(resultSet.getString("cst_no"));
			return customer;
		}

	}
	
private static class CustomerIdsRowMapper implements RowMapper<Customer> {

		
		public Customer mapRow(ResultSet resultSet, int arg1)
				throws SQLException {

			Customer customer = new Customer();
			Contact contact = new Contact();
			Address address =  new Address();
			address.setAddressId(resultSet.getLong("address_id"));
			contact.setAddress(address);
			contact.setContactId(resultSet.getLong("contact_id"));
			customer.setContact(contact);
			return customer;
		}

	}

private static class CustomerBasicInfoRowMapper implements RowMapper<BasicInfo> {

	
	public BasicInfo mapRow(ResultSet resultSet, int arg1)
			throws SQLException {

		BasicInfo customer = new BasicInfo();
		customer.setId(resultSet.getLong("customer_id"));
		customer.setName(resultSet.getString("customer_name"));
		return customer;
	}

}

@Override
public List<BasicInfo> getCustomersBasicInfo() throws OmsDataAccessException{
	List<BasicInfo> customers = null;

	CustomerBasicInfoRowMapper resultSetExtractor = new CustomerBasicInfoRowMapper();
	try {
		customers = (List<BasicInfo>) jdbcTemplate.query(fetchCustomersBasicInfoQuery,
				resultSetExtractor);
	} catch (DataAccessException dae) {
		throw new OmsDataAccessException(dae);
	}
	return customers;
}



	



}

