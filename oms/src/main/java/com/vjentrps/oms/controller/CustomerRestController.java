package com.vjentrps.oms.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.vjentrps.oms.exception.OmsServiceException;
import com.vjentrps.oms.model.BasicInfo;
import com.vjentrps.oms.model.Customer;
import com.vjentrps.oms.model.ErrorsEnum;
import com.vjentrps.oms.model.ResponseDTO;

@RestController
@RequestMapping(value="/service/customer")
public class CustomerRestController extends BaseRestController{
	
    @RequestMapping(method = RequestMethod.GET)
    public ResponseDTO getCustomers() {
              
        List<Customer> customers = new ArrayList<Customer>();
   	
        try {
			customers = customerService.listCustomers();
		} catch (OmsServiceException e) {
			return new ResponseDTO(commonUtil.processError(ErrorsEnum.TECHNICAL_EXCEPTION));
		}
        return new ResponseDTO(customers);
    }
    
    @RequestMapping(value="/{customerId}",method = RequestMethod.GET)
    public ResponseDTO getCustomer(@PathVariable long customerId) {
              
        Customer customer;
		try {
			customer = customerService.getCustomerById(customerId);
		} catch (OmsServiceException e) {
			return new ResponseDTO(commonUtil.processError(ErrorsEnum.TECHNICAL_EXCEPTION));
		}
   	
        return new ResponseDTO(customer);
    }
    
    
    
    @RequestMapping(method = RequestMethod.POST)
    public ResponseDTO addCustomer(@RequestBody Customer customer) {
 
    	try {
			customerService.addCustomer(customer);
		} catch (OmsServiceException e) {
			return new ResponseDTO(commonUtil.processError(ErrorsEnum.TECHNICAL_EXCEPTION));
		}
       
        return new ResponseDTO();
    }
    
    
   
    @RequestMapping(method = RequestMethod.PUT)
    public ResponseDTO updateCustomer(Customer customer) {
 
        try {
			customerService.updateCustomer(customer);
		} catch (OmsServiceException e) {
			return new ResponseDTO(commonUtil.processError(ErrorsEnum.TECHNICAL_EXCEPTION));
		}
        
         return new ResponseDTO();
    }
    
    @RequestMapping(value="/{customerId}",method = RequestMethod.DELETE)
    public ResponseDTO deleteCustomer(@PathVariable long customerId) {
    	
    	 try {
			customerService.deleteCustomer(customerId);
		} catch (OmsServiceException e) {
			return new ResponseDTO(commonUtil.processError(ErrorsEnum.TECHNICAL_EXCEPTION));
		}
 
           return new ResponseDTO();
    }
    
 
    @RequestMapping(value="/count",method = RequestMethod.GET)
    public ResponseDTO getCustomerCount() {
    	
    	try {
			return new ResponseDTO(customerService.getCustomerCount());
		} catch (OmsServiceException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
 
           
    }
    
    @RequestMapping(value="/{basicInfo",method = RequestMethod.GET)
    public ResponseDTO getCustomersBasicInfo() {
              
        List<BasicInfo> customers = new ArrayList<BasicInfo>();
   	
        try {
			customers = customerService.getCustomersBasicInfo();
		} catch (OmsServiceException e) {
			return new ResponseDTO(commonUtil.processError(ErrorsEnum.TECHNICAL_EXCEPTION));
		}
        return new ResponseDTO(customers);
    }
}