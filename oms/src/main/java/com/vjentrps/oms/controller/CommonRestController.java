package com.vjentrps.oms.controller;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.vjentrps.oms.exception.OmsServiceException;
import com.vjentrps.oms.model.CommonConstants;
import com.vjentrps.oms.model.HomeResponseDTO;
import com.vjentrps.oms.model.ResponseDTO;
import com.vjentrps.oms.service.CategoryService;
import com.vjentrps.oms.service.CustomerService;
import com.vjentrps.oms.service.ProductService;
import com.vjentrps.oms.service.SupplierService;

@RestController
@RequestMapping(value="/service/common")
public class CommonRestController {
	
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
    	try {      
			homeResponseDTO.setCategoryCount(categoryService.getCategoryCount());
         	homeResponseDTO.setProductCount(productService.getProductCount());
         	homeResponseDTO.setCustomerCount(customerService.getCustomerCount());
         	homeResponseDTO.setSupplierCount(supplierService.getSupplierCount());
    	} catch (OmsServiceException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    	
    	 catch (Exception e) {
 			// TODO Auto-generated catch block
 			e.printStackTrace();
 		}
    	
        return homeResponseDTO;
    }
    
    @RequestMapping(value="/basicInfo" ,method = RequestMethod.GET)
    public ResponseDTO getInfoToPopulate() {
    	
    	Map<String, Object> basicInfo =  new HashMap<String, Object>();
    	try {      
    		basicInfo.put(CommonConstants.CUSTOMER, customerService.getCustomersBasicInfo());
    		basicInfo.put(CommonConstants.SUPPLIER, supplierService.getSuppliersBasicInfo());
    		//basicInfo.put(CommonConstants.OTHERS, customerService.getCustomersBasicInfo());
    		basicInfo.put(CommonConstants.PRODUCTS, productService.getProductsBasicInfo());
  		
    	} catch (Exception e) {
 			// TODO Auto-generated catch block
 			e.printStackTrace();
 		}
    	
        return new ResponseDTO(basicInfo);
    }
    
  
    
 
}