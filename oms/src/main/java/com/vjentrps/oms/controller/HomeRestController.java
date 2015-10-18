package com.vjentrps.oms.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.vjentrps.oms.model.HomeResponseDTO;
import com.vjentrps.oms.service.CategoryService;
import com.vjentrps.oms.service.CustomerService;
import com.vjentrps.oms.service.ProductService;
import com.vjentrps.oms.service.SupplierService;

@RestController
@RequestMapping(value="/service/home")
public class HomeRestController {
	
	@Autowired
	CategoryService categoryService;
	
	@Autowired
	ProductService productService;
	
	@Autowired
	CustomerService	customerService;
	
	@Autowired
	SupplierService supplierService;
	
 
    @RequestMapping(value="/count" ,method = RequestMethod.GET)
    public HomeResponseDTO getCounts() {
              
         	HomeResponseDTO homeResponseDTO = new HomeResponseDTO();
         	homeResponseDTO.setCategoryCount(categoryService.getCategoryCount());
         	homeResponseDTO.setProductCount(productService.getProductCount());
         	homeResponseDTO.setCustomerCount(customerService.getCustomerCount());
         	homeResponseDTO.setSupplierCount(supplierService.getSupplierCount());
         	
    	
        return homeResponseDTO;
    }
    
  
    
 
}