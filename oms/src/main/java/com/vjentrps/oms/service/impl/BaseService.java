package com.vjentrps.oms.service.impl;

import org.springframework.beans.factory.annotation.Autowired;

import com.vjentrps.oms.dao.CustomerDao;
import com.vjentrps.oms.dao.SupplierDao;
import com.vjentrps.oms.exception.OmsDataAccessException;
import com.vjentrps.oms.model.CommonConstants;
import com.vjentrps.oms.model.Customer;
import com.vjentrps.oms.model.GoodsInwardNote;
import com.vjentrps.oms.model.Supplier;

public class BaseService {
	
	@Autowired
	protected SupplierDao supplierDao;

	@Autowired
	protected CustomerDao customerDao;
	
	/**
	 * Method to fetch the respective Customer or Supplier id based on the name provided
	 * @param type
	 * @param name
	 * @return
	 * @throws OmsDataAccessException
	 */

	public long getIdFromName(String type, String name) throws OmsDataAccessException {
		if (CommonConstants.CUSTOMER.equalsIgnoreCase(type)) {
			Customer customer = customerDao.getCustomerByName(name);
			if (customer != null) {
				return customer.getCustomerId();
			}
		} else if (CommonConstants.SUPPLIER.equalsIgnoreCase(type)) {
			Supplier supplier = supplierDao.getSupplierByName(name);
			if (supplier != null) {
				return supplier.getSupplierId();
			}
		}
		return 0;

	}

	/**
	 * Method to fetch the respective Customer or Supplier name based on the id provided
	 * @param type
	 * @param id
	 * @return
	 * @throws OmsDataAccessException
	 */
	public String getNameFromId(String type, long id) throws OmsDataAccessException {
		if (CommonConstants.CUSTOMER.equalsIgnoreCase(type)) {
			Customer customer = customerDao.getCustomerById(id);
			if (customer != null) {
				return customer.getName();
			}
		} else if (CommonConstants.SUPPLIER.equalsIgnoreCase(type)) {
			Supplier supplier = supplierDao.getSupplierById(id);
			if (supplier != null) {
				return supplier.getName();
			}
		} else if (CommonConstants.OTHERS.equalsIgnoreCase(type)) {
			 return CommonConstants.INITIAL_ENTRY;
		}
		return null;

	}
}
