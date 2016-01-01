package com.vjentrps.oms.service.impl;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.vjentrps.oms.dao.AddressDao;
import com.vjentrps.oms.dao.ContactDao;
import com.vjentrps.oms.dao.SupplierDao;
import com.vjentrps.oms.exception.OmsDataAccessException;
import com.vjentrps.oms.exception.OmsServiceException;
import com.vjentrps.oms.model.BasicInfo;
import com.vjentrps.oms.model.Supplier;
import com.vjentrps.oms.service.SupplierService;

@Service
@Transactional(rollbackFor={RuntimeException.class, Exception.class})
public class SupplierServiceImpl  implements SupplierService{

	private Logger log = LoggerFactory.getLogger(this.getClass());

	@Autowired
	public SupplierDao supplierDao;

	@Autowired
	public ContactDao contactDao;

	@Autowired
	public AddressDao addressDao;

	@Override
	public void addSupplier(Supplier supplier) throws OmsServiceException{
		if (null != supplier && null != supplier.getContact()
				&& null != supplier.getContact().getAddress()) {
			long addressId;
			try {
				addressId = addressDao.addAddress(supplier.getContact().getAddress());

				supplier.getContact().getAddress().setAddressId(addressId);

				long contactId = contactDao.addContact(supplier.getContact());

				supplier.getContact().setContactId(contactId);

				supplierDao.addSupplier(supplier);

			} catch (OmsDataAccessException e) {
				throw new OmsServiceException(e);
			}
		}

	}

	@Override
	public void deleteSupplier(long supplierId)  throws OmsServiceException {
		try {
			Supplier supplier = supplierDao.getSupplierIds(supplierId);

			if (null != supplier && null != supplier.getContact()
					&& null != supplier.getContact().getAddress()) {

				supplierDao.deleteSupplier(supplierId);
				contactDao.deleteContact(supplier.getContact().getContactId());
				addressDao.deleteAddress(supplier.getContact().getAddress().getAddressId());

			}
		} catch (OmsDataAccessException e) {
			throw new OmsServiceException(e);
		}

	}

	@Override
	public void updateSupplier(Supplier supplier) throws OmsServiceException{
		if (null != supplier && null != supplier.getContact()
				&& null != supplier.getContact().getAddress()) {
			try {
				supplierDao.updateSupplier(supplier);
				contactDao.updateContact(supplier.getContact());
				addressDao.updateAddress(supplier.getContact().getAddress());

			} catch (OmsDataAccessException e) {
				throw new OmsServiceException(e);
			}
		}
	}

	@Override
	public List<Supplier> listSuppliers()  throws OmsServiceException{
		try {
			return supplierDao.fetchAllSuppliers();
		} catch (OmsDataAccessException e) {
			throw new OmsServiceException(e);
		}
	}

	@Override
	public Supplier getSupplierById(long supplierId) throws OmsServiceException{
		try {
			return supplierDao.getSupplierById(supplierId);
		} catch (OmsDataAccessException e) {
			throw new OmsServiceException(e);
		}
	}

	@Override
	public int getSupplierCount() throws OmsServiceException{
		try {
			return supplierDao.getSupplierCount();
		} catch (OmsDataAccessException e) {
			throw new OmsServiceException(e);
		}
	}

	@Override
	public List<BasicInfo> getSuppliersBasicInfo() throws OmsServiceException{
		try {
			return supplierDao.getSuppliersBasicInfo();
		} catch (OmsDataAccessException e) {
			throw new OmsServiceException(e);
		}
	}

	@Override
	public Supplier getSupplierByName(String from) throws OmsServiceException {
		// TODO Auto-generated method stub
		return null;
	}




}
