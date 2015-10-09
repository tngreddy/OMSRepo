package com.vjentrps.oms.model;

public class Customer {

	private long customerId;
	private String customerName;
	private String tinNo;
	private String cstNo;
	private Contact contact;
	
	public long getCustomerId() {
		return customerId;
	}
	public void setCustomerId(long customerId) {
		this.customerId = customerId;
	}
	public String getCustomerName() {
		return customerName;
	}
	public void setCustomerName(String customerName) {
		this.customerName = customerName;
	}
	public String getTinNo() {
		return tinNo;
	}
	public void setTinNo(String tinNo) {
		this.tinNo = tinNo;
	}
	public String getCstNo() {
		return cstNo;
	}
	public void setCstNo(String cstNo) {
		this.cstNo = cstNo;
	}
	public Contact getContact() {
		return contact;
	}
	public void setContact(Contact contact) {
		this.contact = contact;
	}
	
	
	
	
	
}
