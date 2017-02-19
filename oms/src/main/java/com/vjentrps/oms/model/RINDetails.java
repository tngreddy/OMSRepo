package com.vjentrps.oms.model;

public class RINDetails {
	
	private ReturnedInwardNote rin;
	private Supplier supplier;
	private Customer customer;
	
	private Object fromDetails;
	
	public ReturnedInwardNote getRin() {
		return rin;
	}
	public void setRin(ReturnedInwardNote rin) {
		this.rin = rin;
	}
	public Supplier getSupplier() {
		return supplier;
	}
	public void setSupplier(Supplier supplier) {
		this.supplier = supplier;
	}
	public Customer getCustomer() {
		return customer;
	}
	public void setCustomer(Customer customer) {
		this.customer = customer;
	}
	public Object getFromDetails() {
		return fromDetails;
	}
	public void setFromDetails(Object fromDetails) {
		this.fromDetails = fromDetails;
	}
	
	


}
