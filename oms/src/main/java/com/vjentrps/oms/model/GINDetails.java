package com.vjentrps.oms.model;

public class GINDetails {
	
	private GoodsInwardNote gin;
	private Supplier supplier;
	private Customer customer;
	
	private Object fromDetails;
	
	public GoodsInwardNote getGin() {
		return gin;
	}
	public void setGin(GoodsInwardNote gin) {
		this.gin = gin;
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
