package com.vjentrps.oms.model;

public class ProdInfo {
	
	private Product product;
	private long goodIn;
	private long defIn;
	private long goodOut;
	private long defOut;
	private long totalQty;
	private long unitBasicRate;
	private long totalAmount;
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
	public long getUnitBasicRate() {
		return unitBasicRate;
	}
	public void setUnitBasicRate(long unitBasicRate) {
		this.unitBasicRate = unitBasicRate;
	}
	public long getTotalAmount() {
		return totalAmount;
	}
	public void setTotalAmount(long totalAmount) {
		this.totalAmount = totalAmount;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	
	
	

	
	
}
