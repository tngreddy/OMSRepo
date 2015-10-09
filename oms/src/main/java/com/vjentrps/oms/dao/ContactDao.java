package com.vjentrps.oms.dao;

import com.vjentrps.oms.model.Contact;

public interface ContactDao {
	
	long addContact(Contact contact);
	
	void deleteContact(long contactId);
	
	void updateContact(Contact contact);

}
