package com.vjentrps.oms.service;

import java.util.List;

import com.vjentrps.oms.model.Supplier;

public interface SupplierService {
	
	 void addSupplier(Supplier supplier) ;
	
	 void deleteSupplier(long supplierId);
	
	 void updateSupplier(Supplier supplier);
	 
	 List<Supplier> listSuppliers();

	 Supplier getSupplierById(long supplierId);

	int getSupplierCount();
	 
	
}
