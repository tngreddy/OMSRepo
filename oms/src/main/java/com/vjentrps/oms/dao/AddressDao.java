package com.vjentrps.oms.dao;

import com.vjentrps.oms.model.Address;

public interface AddressDao {
	
	long addAddress(Address address);
	
	void deleteAddress(long addressId);
	
	void updateAddress(Address address);

}
