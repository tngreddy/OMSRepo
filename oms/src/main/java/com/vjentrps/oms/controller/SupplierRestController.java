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

import com.vjentrps.oms.model.BasicInfo;
import com.vjentrps.oms.model.ResponseDTO;
import com.vjentrps.oms.model.Supplier;
import com.vjentrps.oms.service.SupplierService;

@RestController
@RequestMapping(value="/service/supplier")
public class SupplierRestController {
	
	@Autowired
	SupplierService supplierService;
 
    @RequestMapping(method = RequestMethod.GET)
    public ResponseDTO getSuppliers() {
              
        List<Supplier> suppliers = new ArrayList<Supplier>();
   	
        suppliers = supplierService.listSuppliers();
        return new ResponseDTO(suppliers);
    }
    
    @RequestMapping(value="/{supplierId}",method = RequestMethod.GET)
    public ResponseDTO getSupplier(@PathVariable long supplierId) {
              
        Supplier supplier = supplierService.getSupplierById(supplierId);
   	
        return new ResponseDTO(supplier);
    }
    
    
    
    @RequestMapping(method = RequestMethod.POST)
    public ResponseDTO addSupplier(@RequestBody Supplier supplier) {
 
       supplierService.addSupplier(supplier);
       
        return new ResponseDTO();
    }
    
   
    @RequestMapping(method = RequestMethod.PUT)
    public ResponseDTO updateSupplier(@RequestBody Supplier supplier) {
 
        supplierService.updateSupplier(supplier);
        
         return new ResponseDTO();
    }
    
    @RequestMapping(value="/{supplierId}",method = RequestMethod.DELETE)
    public ResponseDTO deleteSupplier(@PathVariable long supplierId) {
    	
    	 supplierService.deleteSupplier(supplierId);
 
         return new ResponseDTO();
    }
    
    @RequestMapping(value="/count",method = RequestMethod.GET)
    public int getSupplierCount() {
    	
    	return supplierService.getSupplierCount();
 
           
    }
    
    @RequestMapping( value="/basicInfo", method = RequestMethod.GET)
    public ResponseDTO getSuppliersBasicInfo() {
              
        List<BasicInfo> suppliers = new ArrayList<BasicInfo>();
   	
        suppliers = supplierService.getSuppliersBasicInfo();
        return new ResponseDTO(suppliers);
    }
 
}