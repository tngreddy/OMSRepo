package com.vjentrps.oms.model;

public class Product {
	
	private long productId;
	private String productName;
	private Category category;
	private String unitOfMeasure;
	private long goodBalance;
	private long defBalance;
	

	public long getProductId() {
		return productId;
	}
	public void setProductId(long productId) {
		this.productId = productId;
	}
	public String getProductName() {
		return productName;
	}
	public void setProductName(String productName) {
		this.productName = productName;
	}
	
	public Category getCategory() {
		return category;
	}
	public void setCategory(Category category) {
		this.category = category;
	}
	
	public String getUnitOfMeasure() {
		return unitOfMeasure;
	}
	public void setUnitOfMeasure(String unitOfMeasure) {
		this.unitOfMeasure = unitOfMeasure;
	}
	public long getGoodBalance() {
		return goodBalance;
	}
	public void setGoodBalance(long goodBalance) {
		this.goodBalance = goodBalance;
	}
	public long getDefBalance() {
		return defBalance;
	}
	public void setDefBalance(long defBalance) {
		this.defBalance = defBalance;
	}
	
	
	
	
	
	
	
	

}
