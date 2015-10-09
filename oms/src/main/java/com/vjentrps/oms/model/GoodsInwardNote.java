package com.vjentrps.oms.model;

public class GoodsInwardNote {
	
	private long ginNo;
	private String ginDate;
	private Product product;
	private int goodIn;
	private int defectiveIn;
	private String status;
	
	public long getGinNo() {
		return ginNo;
	}
	public void setGinNo(long ginNo) {
		this.ginNo = ginNo;
	}
	public String getGinDate() {
		return ginDate;
	}
	public void setGinDate(String ginDate) {
		this.ginDate = ginDate;
	}
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
	public int getDefectiveIn() {
		return defectiveIn;
	}
	public void setDefectiveIn(int defectiveIn) {
		this.defectiveIn = defectiveIn;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	
	
	

}
