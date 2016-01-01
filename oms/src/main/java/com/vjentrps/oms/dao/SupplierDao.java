package com.vjentrps.oms.dao;

import java.util.List;

import com.vjentrps.oms.exception.OmsDataAccessException;
import com.vjentrps.oms.model.BasicInfo;
import com.vjentrps.oms.model.Supplier;

public interface SupplierDao {

	 void addSupplier(Supplier supplier) throws OmsDataAccessException;
		
	 void deleteSupplier(long supplierId) throws OmsDataAccessException;
	
	 void updateSupplier(Supplier supplier) throws OmsDataAccessException;
	 
	 List<Supplier> fetchAllSuppliers() throws OmsDataAccessException;

	Supplier getSupplierById(long supplierId) throws OmsDataAccessException;
	
	Supplier getSupplierIds(long supplierId) throws OmsDataAccessException;

	int getSupplierCount() throws OmsDataAccessException;

	List<BasicInfo> getSuppliersBasicInfo() throws OmsDataAccessException;

	Supplier getSupplierByName(String supplierName)	throws OmsDataAccessException;
}
