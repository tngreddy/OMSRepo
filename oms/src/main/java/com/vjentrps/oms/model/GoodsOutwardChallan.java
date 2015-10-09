package com.vjentrps.oms.model;

public class GoodsOutwardChallan {
	
	private long gocNo;
	private String gocDate;
	private Product product;
	private int goodOut;
	private int defectiveOut;
	private String status;
	
	public long getGocNo() {
		return gocNo;
	}
	public void setGocNo(long gocNo) {
		this.gocNo = gocNo;
	}
	public String getGocDate() {
		return gocDate;
	}
	public void setGocDate(String gocDate) {
		this.gocDate = gocDate;
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
