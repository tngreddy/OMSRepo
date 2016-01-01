package com.vjentrps.oms.service;

import java.util.List;

import com.vjentrps.oms.exception.OmsServiceException;
import com.vjentrps.oms.model.BasicInfo;
import com.vjentrps.oms.model.Supplier;

public interface SupplierService {
	
	 void addSupplier(Supplier supplier) throws OmsServiceException;
	
	 void deleteSupplier(long supplierId) throws OmsServiceException;
	
	 void updateSupplier(Supplier supplier) throws OmsServiceException;
	 
	 List<Supplier> listSuppliers() throws OmsServiceException;

	 Supplier getSupplierById(long supplierId) throws OmsServiceException;

	int getSupplierCount() throws OmsServiceException;

	List<BasicInfo> getSuppliersBasicInfo() throws OmsServiceException;

	Supplier getSupplierByName(String from) throws OmsServiceException;
	 
	
}
