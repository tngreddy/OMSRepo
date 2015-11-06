package com.vjentrps.oms.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.vjentrps.oms.model.Address;
import com.vjentrps.oms.model.BasicInfo;
import com.vjentrps.oms.model.Contact;
import com.vjentrps.oms.model.Customer;
import com.vjentrps.oms.model.ResponseDTO;
import com.vjentrps.oms.service.CustomerService;

@RestController
@RequestMapping(value="/service/customer")
public class CustomerRestController {
	
	@Autowired
	CustomerService customerService;
 
    @RequestMapping(method = RequestMethod.GET)
    public ResponseDTO getCustomers() {
              
        List<Customer> customers = new ArrayList<Customer>();
   	
        customers = customerService.listCustomers();
        return new ResponseDTO(customers);
    }
    
    @RequestMapping(value="/{customerId}",method = RequestMethod.GET)
    public ResponseDTO getCustomer(@PathVariable long customerId) {
              
        Customer customer = customerService.getCustomerById(customerId);
   	
        return new ResponseDTO(customer);
    }
    
    
    
    @RequestMapping(method = RequestMethod.POST)
    public ResponseDTO addCustomer(@RequestBody Customer customer) {
 
    	customerService.addCustomer(customer);
       
        return new ResponseDTO();
    }
    
    
   
    @RequestMapping(method = RequestMethod.PUT)
    public ResponseDTO updateCustomer(Customer customer) {
 
        customerService.updateCustomer(customer);
        
         return new ResponseDTO();
    }
    
    @RequestMapping(value="/{customerId}",method = RequestMethod.DELETE)
    public ResponseDTO deleteCustomer(@PathVariable long customerId) {
    	
    	 customerService.deleteCustomer(customerId);
 
           return new ResponseDTO();
    }
    
 
    @RequestMapping(value="/count",method = RequestMethod.GET)
    public ResponseDTO getCustomerCount() {
    	
    	return new ResponseDTO(customerService.getCustomerCount());
 
           
    }
    
    @RequestMapping(value="/{basicInfo",method = RequestMethod.GET)
    public ResponseDTO getCustomersBasicInfo() {
              
        List<BasicInfo> customers = new ArrayList<BasicInfo>();
   	
        customers = customerService.getCustomersBasicInfo();
        return new ResponseDTO(customers);
    }
}