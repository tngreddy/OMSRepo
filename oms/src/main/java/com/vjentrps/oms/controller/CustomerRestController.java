package com.vjentrps.oms.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.vjentrps.oms.model.Address;
import com.vjentrps.oms.model.Contact;
import com.vjentrps.oms.model.Customer;
import com.vjentrps.oms.service.CustomerService;

@RestController
@RequestMapping(value="/customer")
public class CustomerRestController {
	
	@Autowired
	CustomerService customerService;
 
    @RequestMapping(method = RequestMethod.GET)
    public List<Customer> getCustomers() {
              
        List<Customer> customers = new ArrayList<Customer>();
   	
        customers = customerService.listCustomers();
        return customers;
    }
    
    @RequestMapping(value="/{customerId}",method = RequestMethod.GET)
    public Customer getCustomer(@PathVariable long customerId) {
              
        Customer customer = customerService.getCustomerById(customerId);
   	
        return customer;
    }
    
    
    
    @RequestMapping(method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
    public String addCustomer(Customer customer) {
 
    	Address address = new Address();
    	Contact contact = new Contact();
    	address.setAddressLine1("afadsf");
    	address.setAddressLine2("Sssss.F colony");
    	address.setCity("Hyderabad");
    	address.setState("ap");
    	address.setPinCode(500070);
    	
    	contact.setAddress(address);
    	contact.setContactPerson("rao");
    	contact.setContactDesignation("maasdfnager");
    	contact.setMobileNo(1234566789);
    	contact.setPhoneNo(1234567891);
    	customer.setContact(contact);
    	customer.setCustomerName("manasdfasdfastech");
    	customer.setCstNo("12asdfa3abc");
    	customer.setTinNo("12ss3xyz");

    	customerService.addCustomer(customer);
       
        return "Success";
    }
    
    
   
    @RequestMapping(method = RequestMethod.PUT)
    public String updateCustomer(Customer customer) {
 
    	
    	Address address = new Address();
    	Contact contact = new Contact();
    	address.setAddressId(4);
    	address.setAddressLine1("afadsf");
    	address.setAddressLine2("Sssss.F colony");
    	address.setCity("Hyderabad");
    	address.setState("ap");
    	address.setPinCode(500070);
    	
    	contact.setAddress(address);
    	contact.setContactId(1);
    	contact.setContactPerson("rao");
    	contact.setContactDesignation("maasdfnager");
    	contact.setMobileNo(1234566789);
    	contact.setPhoneNo(1234567891);
    	customer.setContact(contact);
    	customer.setCustomerName("manasdfasdfastech");
    	customer.setCstNo("12asdfa3abc");
    	customer.setTinNo("12ss3xyz");
    	customer.setCustomerId(1);
    	
        customerService.updateCustomer(customer);
        
         return "Success";
    }
    
    @RequestMapping(value="/{customerId}",method = RequestMethod.DELETE)
    public String deleteCustomer(@PathVariable long customerId) {
    	
    	 customerService.deleteCustomer(customerId);
 
           return "Success";
    }
    
 
}