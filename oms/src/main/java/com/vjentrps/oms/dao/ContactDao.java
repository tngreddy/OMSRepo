package com.vjentrps.oms.dao;

import com.vjentrps.oms.exception.OmsDataAccessException;
import com.vjentrps.oms.model.Contact;

public interface ContactDao {
	
	long addContact(Contact contact) throws OmsDataAccessException;
	
	void deleteContact(long contactId) throws OmsDataAccessException;
	
	void updateContact(Contact contact) throws OmsDataAccessException;

}
