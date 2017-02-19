package com.vjentrps.oms.model;

public class ProdInfo {
	
	private Product product;
	private long goodIn;
	private long defIn;
	private long goodOut;
	private long defOut;
	private long totalQty;
	private double unitBasicRate;
	private double totalAmount;
	private String status;
	
	public Product getProduct() {
		return product;
	}
	public void setProduct(Product product) {
		this.product = product;
	}
	public long getGoodIn() {
		return goodIn;
	}
	public void setGoodIn(long goodIn) {
		this.goodIn = goodIn;
	}
	public long getDefIn() {
		return defIn;
	}
	public void setDefIn(long defIn) {
		this.defIn = defIn;
	}
	public long getGoodOut() {
		return goodOut;
	}
	public void setGoodOut(long goodOut) {
		this.goodOut = goodOut;
	}
	public long getDefOut() {
		return defOut;
	}
	public void setDefOut(long defOut) {
		this.defOut = defOut;
	}
	public long getTotalQty() {
		return totalQty;
	}
	public void setTotalQty(long totalQty) {
		this.totalQty = totalQty;
	}
	public double getUnitBasicRate() {
		return unitBasicRate;
	}
	public void setUnitBasicRate(double unitBasicRate) {
		this.unitBasicRate = unitBasicRate;
	}
	public double getTotalAmount() {
		return totalAmount;
	}
	public void setTotalAmount(double totalAmount) {
		this.totalAmount = totalAmount;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	
	
	

	
	
}
