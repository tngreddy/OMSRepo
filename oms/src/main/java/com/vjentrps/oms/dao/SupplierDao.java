package com.vjentrps.oms.dao;

import java.util.List;

import com.vjentrps.oms.model.Customer;
import com.vjentrps.oms.model.Supplier;

public interface SupplierDao {

	 void addSupplier(Supplier supplier) ;
		
	 void deleteSupplier(long supplierId);
	
	 void updateSupplier(Supplier supplier);
	 
	 List<Supplier> fetchAllSuppliers();

	Supplier getSupplierById(long supplierId);
	
	Supplier getSupplierIds(long supplierId);

	int getSupplierCount();
}
