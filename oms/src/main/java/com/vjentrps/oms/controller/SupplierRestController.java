package com.vjentrps.oms.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.vjentrps.oms.model.Supplier;
import com.vjentrps.oms.service.SupplierService;

@RestController
@RequestMapping(value="/supplier")
public class SupplierRestController {
	
	@Autowired
	SupplierService supplierService;
 
    @RequestMapping(method = RequestMethod.GET)
    public List<Supplier> getCustomers() {
              
        List<Supplier> suppliers = new ArrayList<Supplier>();
   	
        suppliers = supplierService.listSuppliers();
        return suppliers;
    }
    
    @RequestMapping(value="/{supplierId}",method = RequestMethod.GET)
    public Supplier getSupplier(@PathVariable long supplierId) {
              
        Supplier supplier = supplierService.getSupplierById(supplierId);
   	
        return supplier;
    }
    
    
    
    @RequestMapping(method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
    public String addSupplier(Supplier supplier) {
 
       supplierService.addSupplier(supplier);
       
        return "Success";
    }
    
   
    @RequestMapping(method = RequestMethod.PUT)
    public String updateSupplier(Supplier supplier) {
 
        supplierService.updateSupplier(supplier);
        
         return "Success";
    }
    
    @RequestMapping(value="/{supplierId}",method = RequestMethod.DELETE)
    public String deleteSupplier(@PathVariable long supplierId) {
    	
    	 supplierService.deleteSupplier(supplierId);
 
           return "Success";
    }
    
 
}