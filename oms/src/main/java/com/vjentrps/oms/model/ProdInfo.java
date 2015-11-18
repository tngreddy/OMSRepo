package com.vjentrps.oms.model;

public class ProdInfo {
	
	private Product product;
	private int goodIn;
	private int defIn;
	private int goodOut;
	private int defOut;
	private int totalQty;
	private int unitBasicRate;
	private long totalAmount;
	
	public Product getProduct() {
		return product;
	}
	public void setProduct(Product product) {
		this.product = product;
	}
	public int getGoodIn() {
		return goodIn;
	}
	public void setGoodIn(int goodIn) {
		this.goodIn = goodIn;
	}
	public int getDefIn() {
		return defIn;
	}
	public void setDefIn(int defIn) {
		this.defIn = defIn;
	}
	public int getGoodOut() {
		return goodOut;
	}
	public void setGoodOut(int goodOut) {
		this.goodOut = goodOut;
	}
	public int getDefOut() {
		return defOut;
	}
	public void setDefOut(int defOut) {
		this.defOut = defOut;
	}
	public int getTotalQty() {
		return totalQty;
	}
	public void setTotalQty(int totalQty) {
		this.totalQty = totalQty;
	}
	public int getUnitBasicRate() {
		return unitBasicRate;
	}
	public void setUnitBasicRate(int unitBasicRate) {
		this.unitBasicRate = unitBasicRate;
	}
	public long getTotalAmount() {
		return totalAmount;
	}
	public void setTotalAmount(long totalAmount) {
		this.totalAmount = totalAmount;
	}
	

	
	
}
