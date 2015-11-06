package com.vjentrps.oms.dao;

import java.util.List;

import com.vjentrps.oms.model.BasicInfo;
import com.vjentrps.oms.model.Customer;


public interface CustomerDao {

	 void addCustomer(Customer customer) ;
		
	 void deleteCustomer(long customerId);
	
	 void updateCustomer(Customer customer);
	 
	 List<Customer> fetchAllCustomers();
	 
	 Customer getCustomerById(long customerId);
	 
	 Customer getCustIds(long customerId);

	int getCustomerCount();

	List<BasicInfo> getCustomersBasicInfo();
}
