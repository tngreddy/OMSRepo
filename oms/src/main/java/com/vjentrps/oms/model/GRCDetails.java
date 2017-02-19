package com.vjentrps.oms.model;

public class GRCDetails {
	
	private GoodsReturnableChallan grc;
	private Supplier supplier;
	private Customer customer;
	private boolean hasSupplier;
	private boolean hasCustomer;
	
	private Object toDetails;
	
	public GoodsReturnableChallan getGrc() {
		return grc;
	}
	public void setGrc(GoodsReturnableChallan grc) {
		this.grc = grc;
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
	public boolean isHasSupplier() {
		return hasSupplier;
	}
	public void setHasSupplier(boolean hasSupplier) {
		this.hasSupplier = hasSupplier;
	}
	public boolean isHasCustomer() {
		return hasCustomer;
	}
	public void setHasCustomer(boolean hasCustomer) {
		this.hasCustomer = hasCustomer;
	}
	public Object getToDetails() {
		return toDetails;
	}
	public void setToDetails(Object toDetails) {
		this.toDetails = toDetails;
	}
	
	
	

	

}
