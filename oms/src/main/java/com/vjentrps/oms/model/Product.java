package com.vjentrps.oms.model;

public class Product {
	
	private long productId;
	private String productName;
	private long categoryId;
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
	public long getCategoryId() {
		return categoryId;
	}
	public void setCategoryId(long categoryId) {
		this.categoryId = categoryId;
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
