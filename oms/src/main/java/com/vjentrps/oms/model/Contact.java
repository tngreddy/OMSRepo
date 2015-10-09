package com.vjentrps.oms.model;

public class Contact {
	
	private long contactId;
	private Address address;
	private String contactPerson;
	private String contactDesignation;
	private int phoneNo;
	private int mobileNo;
	
	public long getContactId() {
		return contactId;
	}
	public void setContactId(long contactId) {
		this.contactId = contactId;
	}
	public Address getAddress() {
		return address;
	}
	public void setAddress(Address address) {
		this.address = address;
	}
	public String getContactPerson() {
		return contactPerson;
	}
	public void setContactPerson(String contactPerson) {
		this.contactPerson = contactPerson;
	}
	public String getContactDesignation() {
		return contactDesignation;
	}
	public void setContactDesignation(String contactDesignation) {
		this.contactDesignation = contactDesignation;
	}
	public int getPhoneNo() {
		return phoneNo;
	}
	public void setPhoneNo(int phoneNo) {
		this.phoneNo = phoneNo;
	}
	public int getMobileNo() {
		return mobileNo;
	}
	public void setMobileNo(int mobileNo) {
		this.mobileNo = mobileNo;
	}
	
	
	

}
