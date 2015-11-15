package com.vjentrps.oms.dao;

import java.util.List;

import com.vjentrps.oms.exception.OmsDataAccessException;
import com.vjentrps.oms.model.BasicInfo;
import com.vjentrps.oms.model.Customer;

public interface CustomerDao {

	 void addCustomer(Customer customer) throws OmsDataAccessException ;
		
	 void deleteCustomer(long customerId) throws OmsDataAccessException;
	
	 void updateCustomer(Customer customer) throws OmsDataAccessException;
	 
	 List<Customer> fetchAllCustomers() throws OmsDataAccessException;
	 
	 Customer getCustomerById(long customerId) throws OmsDataAccessException;
	 
	 Customer getCustIds(long customerId) throws OmsDataAccessException;

	int getCustomerCount() throws OmsDataAccessException;

	List<BasicInfo> getCustomersBasicInfo() throws OmsDataAccessException;
}
