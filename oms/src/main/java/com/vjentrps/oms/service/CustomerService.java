package com.vjentrps.oms.service;

import java.util.List;

import com.vjentrps.oms.exception.OmsServiceException;
import com.vjentrps.oms.model.BasicInfo;
import com.vjentrps.oms.model.Customer;

public interface CustomerService {
	
	 void addCustomer(Customer customer) throws OmsServiceException;
	
	 void deleteCustomer(long customerId) throws OmsServiceException;
	
	 void updateCustomer(Customer customer) throws OmsServiceException;
	 
	 List<Customer> listCustomers() throws OmsServiceException;

	Customer getCustomerById(long customerId) throws OmsServiceException;

	int getCustomerCount() throws OmsServiceException;

	List<BasicInfo> getCustomersBasicInfo() throws OmsServiceException;

	 
	
}
