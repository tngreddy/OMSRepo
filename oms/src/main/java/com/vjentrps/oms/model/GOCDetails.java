package com.vjentrps.oms.model;

public class GOCDetails {
	
	private GoodsOutwardChallan goc;
	private Supplier supplier;
	private Customer customer;
	
	private Object toDetails;
		
	public GoodsOutwardChallan getGoc() {
		return goc;
	}
	public void setGoc(GoodsOutwardChallan goc) {
		this.goc = goc;
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
	public Object getToDetails() {
		return toDetails;
	}
	public void setToDetails(Object toDetails) {
		this.toDetails = toDetails;
	}
	
	


}
