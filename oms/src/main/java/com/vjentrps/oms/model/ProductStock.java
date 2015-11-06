package com.vjentrps.oms.model;

public class ProductStock {
	
	private long id;
	private Product product;
	private long goodBalance;
	private long defBalance;
	private String lastModified;
	
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	
	public Product getProduct() {
		return product;
	}
	public void setProduct(Product product) {
		this.product = product;
	}
	public long getGoodBalance() {
		return goodBalance;
	}
	public void setGoodBalance(long goodBalance) {
		this.goodBalance = goodBalance;
	}
	public String getLastModified() {
		return lastModified;
	}
	public void setLastModified(String lastModified) {
		this.lastModified = lastModified;
	}
	public long getDefBalance() {
		return defBalance;
	}
	public void setDefBalance(long defBalance) {
		this.defBalance = defBalance;
	}
	
	
	

}
