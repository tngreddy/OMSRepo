package com.vjentrps.oms.dao;

import com.vjentrps.oms.exception.OmsDataAccessException;
import com.vjentrps.oms.model.Address;

public interface AddressDao {
	
	long addAddress(Address address) throws OmsDataAccessException;
	 
	void deleteAddress(long addressId) throws OmsDataAccessException;
	
	void updateAddress(Address address) throws OmsDataAccessException;

}
