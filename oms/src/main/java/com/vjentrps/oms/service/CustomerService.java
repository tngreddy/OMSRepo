package com.vjentrps.oms.service;

import java.util.List;

import com.vjentrps.oms.model.Customer;

public interface CustomerService {
	
	 void addCustomer(Customer customer) ;
	
	 void deleteCustomer(long customerId);
	
	 void updateCustomer(Customer customer);
	 
	 List<Customer> listCustomers();

	Customer getCustomerById(long customerId);

	int getCustomerCount();

	 
	
}
