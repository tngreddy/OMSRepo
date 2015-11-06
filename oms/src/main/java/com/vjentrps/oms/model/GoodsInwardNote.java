
package com.vjentrps.oms.model;

public class GoodsInwardNote {
	
	private String ginNo;
	private String ginDate;
	private String from;
	private String fromName;
	private String docRefNo;
	private String docDate;
	private Product product;
	private int goodIn;
	private int defectiveIn;
	private String status;
	

	public String getGinNo() {
		return ginNo;
	}
	public void setGinNo(String ginNo) {
		this.ginNo = ginNo;
	}
	public String getGinDate() {
		return ginDate;
	}
	public void setGinDate(String ginDate) {
		this.ginDate = ginDate;
	}
	public String getFrom() {
		return from;
	}
	public void setFrom(String from) {
		this.from = from;
	}
	
	public String getFromName() {
		return fromName;
	}
	public void setFromName(String fromName) {
		this.fromName = fromName;
	}
	public String getDocRefNo() {
		return docRefNo;
	}
	public void setDocRefNo(String docRefNo) {
		this.docRefNo = docRefNo;
	}
	public String getDocDate() {
		return docDate;
	}
	public void setDocDate(String docDate) {
		this.docDate = docDate;
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
