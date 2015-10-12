package com.vjentrps.oms.model;

public class Product {
	
	private long productId;
	private String productName;
	private Category category;
	private int unitOfMeasure;
	private int unitBasicRate;
	private long stock;
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
	public int getUnitOfMeasure() {
		return unitOfMeasure;
	}
	public void setUnitOfMeasure(int unitOfMeasure) {
		this.unitOfMeasure = unitOfMeasure;
	}
	public int getUnitBasicRate() {
		return unitBasicRate;
	}
	public void setUnitBasicRate(int unitBasicRate) {
		this.unitBasicRate = unitBasicRate;
	}
	public long getStock() {
		return stock;
	}
	public void setStock(long stock) {
		this.stock = stock;
	}
	
	
	

}
