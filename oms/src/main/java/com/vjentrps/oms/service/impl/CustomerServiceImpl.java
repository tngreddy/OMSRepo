package com.vjentrps.oms.service.impl;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.vjentrps.oms.dao.AddressDao;
import com.vjentrps.oms.dao.ContactDao;
import com.vjentrps.oms.dao.CustomerDao;
import com.vjentrps.oms.exception.OmsDataAccessException;
import com.vjentrps.oms.exception.OmsServiceException;
import com.vjentrps.oms.model.BasicInfo;
import com.vjentrps.oms.model.Customer;
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
	public void addCustomer(Customer customer) throws OmsServiceException {

		if (null != customer && null != customer.getContact()
				&& null != customer.getContact().getAddress()) {
			long addressId;
			try {
			
				addressId = addressDao.addAddress(customer.getContact().getAddress());
			

			customer.getContact().getAddress().setAddressId(addressId);

			long contactId = contactDao.addContact(customer.getContact());

			customer.getContact().setContactId(contactId);

			customerDao.addCustomer(customer);
			
			} catch (OmsDataAccessException e) {
				throw new OmsServiceException(e);
			}
		}

	}

	@Override
	public void deleteCustomer(long customerId) throws OmsServiceException {


		Customer customer;
		try {
			customer = customerDao.getCustIds(customerId);
		

		if (null != customer && null != customer.getContact()
				&& null != customer.getContact().getAddress()) {
			customerDao.deleteCustomer(customerId);
			contactDao.deleteContact(customer.getContact().getContactId());
			addressDao.deleteAddress(customer.getContact().getAddress().getAddressId());
		}
		} catch (OmsDataAccessException e) {
			throw new OmsServiceException(e);
		}
	}

	@Override
	public void updateCustomer(Customer customer) throws OmsServiceException  {
		if (null != customer && null != customer.getContact()
				&& null != customer.getContact().getAddress()) {
			try {
				customerDao.updateCustomer(customer);
			
			contactDao.updateContact(customer.getContact());
			addressDao.updateAddress(customer.getContact().getAddress());
		
		} catch (OmsDataAccessException e) {
			throw new OmsServiceException(e);
		}
		}
	}

	@Override
	public List<Customer> listCustomers() throws OmsServiceException  {
		try {
			return customerDao.fetchAllCustomers();
		} catch (OmsDataAccessException e) {
			throw new OmsServiceException(e);
		}
		
	}

	@Override
	public Customer getCustomerById(long customerId) throws OmsServiceException {

		try {
			return customerDao.getCustomerById(customerId);
		} catch (OmsDataAccessException e) {
			throw new OmsServiceException(e);
		}

	}

	@Override
	public int getCustomerCount() throws OmsServiceException {
		try {
			return customerDao.getCustomerCount();
		} catch (OmsDataAccessException e) {
			throw new OmsServiceException(e);
		}
	
	}

	@Override
	public List<BasicInfo> getCustomersBasicInfo() throws OmsServiceException {
		try {
			return customerDao.getCustomersBasicInfo();
		} catch (OmsDataAccessException e) {
			throw new OmsServiceException(e);
		}
		
	}

}
