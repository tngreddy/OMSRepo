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
import com.vjentrps.oms.model.ErrorsEnum;
import com.vjentrps.oms.model.ResponseDTO;
import com.vjentrps.oms.model.Supplier;

@RestController
@RequestMapping(value="/service/supplier")
public class SupplierRestController extends BaseRestController {
	

    @RequestMapping(method = RequestMethod.GET)
    public ResponseDTO getSuppliers() {
              
        List<Supplier> suppliers = new ArrayList<Supplier>();
   	
        try {
			suppliers = supplierService.listSuppliers();
		} catch (OmsServiceException e) {
			return new ResponseDTO(commonUtil.processError(ErrorsEnum.TECHNICAL_EXCEPTION));
		}
        return new ResponseDTO(suppliers);
    }
    
    @RequestMapping(value="/{supplierId}",method = RequestMethod.GET)
    public ResponseDTO getSupplier(@PathVariable long supplierId) {
              
        Supplier supplier = null;
		try {
			supplier = supplierService.getSupplierById(supplierId);
		} catch (OmsServiceException e) {
			return new ResponseDTO(commonUtil.processError(ErrorsEnum.TECHNICAL_EXCEPTION));
		}
   	
        return new ResponseDTO(supplier);
    }
    
    
    
    @RequestMapping(method = RequestMethod.POST)
    public ResponseDTO addSupplier(@RequestBody Supplier supplier) {
 
       try {
		supplierService.addSupplier(supplier);
	} catch (OmsServiceException e) {
		return new ResponseDTO(commonUtil.processError(ErrorsEnum.TECHNICAL_EXCEPTION));
	}
       
        return new ResponseDTO();
    }
    
   
    @RequestMapping(method = RequestMethod.PUT)
    public ResponseDTO updateSupplier(@RequestBody Supplier supplier) {
 
        try {
			supplierService.updateSupplier(supplier);
		} catch (OmsServiceException e) {
			return new ResponseDTO(commonUtil.processError(ErrorsEnum.TECHNICAL_EXCEPTION));
		}
        
         return new ResponseDTO();
    }
    
    @RequestMapping(value="/{supplierId}",method = RequestMethod.DELETE)
    public ResponseDTO deleteSupplier(@PathVariable long supplierId) {
    	
    	 try {
			supplierService.deleteSupplier(supplierId);
		} catch (OmsServiceException e) {
			return new ResponseDTO(commonUtil.processError(ErrorsEnum.TECHNICAL_EXCEPTION));
		}
 
         return new ResponseDTO();
    }
    
    @RequestMapping(value="/count",method = RequestMethod.GET)
    public int getSupplierCount() {
    	
    	try {
			return supplierService.getSupplierCount();
		} catch (OmsServiceException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return 0;
 
           
    }
    
    @RequestMapping( value="/basicInfo", method = RequestMethod.GET)
    public ResponseDTO getSuppliersBasicInfo() {
              
        List<BasicInfo> suppliers = new ArrayList<BasicInfo>();
   	
        try {
			suppliers = supplierService.getSuppliersBasicInfo();
		} catch (OmsServiceException e) {
			return new ResponseDTO(commonUtil.processError(ErrorsEnum.TECHNICAL_EXCEPTION));
		}
        return new ResponseDTO(suppliers);
    }
 
}