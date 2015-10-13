package com.vjentrps.oms.service.impl;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.vjentrps.oms.dao.AddressDao;
import com.vjentrps.oms.dao.CategoryDao;
import com.vjentrps.oms.dao.ContactDao;
import com.vjentrps.oms.dao.CustomerDao;
import com.vjentrps.oms.dao.SupplierDao;
import com.vjentrps.oms.model.Category;
import com.vjentrps.oms.model.Customer;
import com.vjentrps.oms.model.Supplier;
import com.vjentrps.oms.service.CategoryService;
import com.vjentrps.oms.service.CustomerService;
import com.vjentrps.oms.service.SupplierService;

@Service
@Transactional
public class SupplierServiceImpl  implements SupplierService{

	private Logger log = LoggerFactory.getLogger(this.getClass());
	
	@Autowired
	public SupplierDao supplierDao;
	
	@Autowired
	public ContactDao contactDao;
	
	@Autowired
	public AddressDao addressDao;

	@Override
	public void addSupplier(Supplier supplier) {
		if (null != supplier && null != supplier.getContact()
				&& null != supplier.getContact().getAddress()) {
			long addressId = addressDao.addAddress(supplier.getContact().getAddress());

			supplier.getContact().getAddress().setAddressId(addressId);

			long contactId = contactDao.addContact(supplier.getContact());

			supplier.getContact().setContactId(contactId);

			supplierDao.addSupplier(supplier);
		}
		
	}

	@Override
	public void deleteSupplier(long supplierId) {
		Supplier supplier = supplierDao.getSupplierIds(supplierId);

		if (null != supplier && null != supplier.getContact()
				&& null != supplier.getContact().getAddress()) {
			supplierDao.deleteSupplier(supplierId);
			contactDao.deleteContact(supplier.getContact().getContactId());
			addressDao.deleteAddress(supplier.getContact().getAddress().getAddressId());
		}
		
	}

	@Override
	public void updateSupplier(Supplier supplier) {
		if (null != supplier && null != supplier.getContact()
				&& null != supplier.getContact().getAddress()) {
			supplierDao.updateSupplier(supplier);
			contactDao.updateContact(supplier.getContact());
			addressDao.updateAddress(supplier.getContact().getAddress());
		}
		
	}

	@Override
	public List<Supplier> listSuppliers() {
		return supplierDao.fetchAllSuppliers();
	}

	@Override
	public Supplier getSupplierById(long supplierId) {
		return supplierDao.getSupplierById(supplierId);
	}

	@Override
	public int getSupplierCount() {
		return supplierDao.getSupplierCount();
	}

	
	
	
}
