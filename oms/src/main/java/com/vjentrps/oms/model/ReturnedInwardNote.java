package com.vjentrps.oms.model;

public class ReturnedInwardNote {
	
	private long rinNo;
	private String rinDate;
	private Product product;
	private int goodIn;
	private int defectiveIn;
	private String status;
	
	
	public long getRinNo() {
		return rinNo;
	}
	public void setRinNo(long rinNo) {
		this.rinNo = rinNo;
	}
	public String getRinDate() {
		return rinDate;
	}
	public void setRinDate(String rinDate) {
		this.rinDate = rinDate;
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
