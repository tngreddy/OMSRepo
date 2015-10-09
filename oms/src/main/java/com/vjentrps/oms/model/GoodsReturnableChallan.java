package com.vjentrps.oms.model;

public class GoodsReturnableChallan {
	
	private long grcNo;
	private String grcDate;
	private Product product;
	private int goodOut;
	private int defectiveOut;
	private String status;
	
	public long getGrcNo() {
		return grcNo;
	}
	public void setGrcNo(long grcNo) {
		this.grcNo = grcNo;
	}
	public String getGrcDate() {
		return grcDate;
	}
	public void setGrcDate(String grcDate) {
		this.grcDate = grcDate;
	}
	public Product getProduct() {
		return product;
	}
	public void setProduct(Product product) {
		this.product = product;
	}
	public int getGoodOut() {
		return goodOut;
	}
	public void setGoodOut(int goodOut) {
		this.goodOut = goodOut;
	}
	public int getDefectiveOut() {
		return defectiveOut;
	}
	public void setDefectiveOut(int defectiveOut) {
		this.defectiveOut = defectiveOut;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	
	
	
	
	

}
