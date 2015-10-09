package com.vjentrps.oms.model;

public class Supplier {
	
	private long supplierId;
	private String supplierName;
	private String tinNo;
	private String cstNo;
	private Contact contact;
	
	public long getSupplierId() {
		return supplierId;
	}
	public void setSupplierId(long supplierId) {
		this.supplierId = supplierId;
	}
	public String getSupplierName() {
		return supplierName;
	}
	public void setSupplierName(String supplierName) {
		this.supplierName = supplierName;
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
