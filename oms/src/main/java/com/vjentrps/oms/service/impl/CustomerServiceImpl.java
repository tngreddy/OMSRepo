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
import com.vjentrps.oms.model.Category;
import com.vjentrps.oms.model.Customer;
import com.vjentrps.oms.service.CategoryService;
import com.vjentrps.oms.service.CustomerService;

@Service
@Transactional
public class CustomerServiceImpl implements CustomerService {

	private Logger log = LoggerFactory.getLogger(this.getClass());

	@Autowired
	public CustomerDao customerDao;

	@Autowired
	public ContactDao contactDao;
	
	@Autowired
	public AddressDao addressDao;

	@Override
	public void addCustomer(Customer customer) {

		if (null != customer && null != customer.getContact()
				&& null != customer.getContact().getAddress()) {
			long addressId = addressDao.addAddress(customer.getContact().getAddress());

			customer.getContact().getAddress().setAddressId(addressId);

			long contactId = contactDao.addContact(customer.getContact());

			customer.getContact().setContactId(contactId);

			customerDao.addCustomer(customer);
		}

	}

	@Override
	public void deleteCustomer(long customerId) {


		Customer customer = customerDao.getCustIds(customerId);

		if (null != customer && null != customer.getContact()
				&& null != customer.getContact().getAddress()) {
			customerDao.deleteCustomer(customerId);
			contactDao.deleteContact(customer.getContact().getContactId());
			addressDao.deleteAddress(customer.getContact().getAddress().getAddressId());
		}
	}

	@Override
	public void updateCustomer(Customer customer) {
		if (null != customer && null != customer.getContact()
				&& null != customer.getContact().getAddress()) {
			customerDao.updateCustomer(customer);
			contactDao.updateContact(customer.getContact());
			addressDao.updateAddress(customer.getContact().getAddress());
		}
	}

	@Override
	public List<Customer> listCustomers() {
		return customerDao.fetchAllCustomers();
	}

	@Override
	public Customer getCustomerById(long customerId) {

		return customerDao.getCustomerById(customerId);
	}

	@Override
	public int getCustomerCount() {
		return customerDao.getCustomerCount();
	}

}
