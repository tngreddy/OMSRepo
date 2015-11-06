package com.vjentrps.oms.model;

public class GoodsOutwardChallan {
	
	private String gocNo;
	private String gocDate;
	private String to;
	private String toName;
	private String docRefNo;
	private String docDate;
	private Product product;
	private int goodOut;
	private int defOut;
	private String status;
	public String getGocNo() {
		return gocNo;
	}
	public void setGocNo(String gocNo) {
		this.gocNo = gocNo;
	}
	public String getGocDate() {
		return gocDate;
	}
	public void setGocDate(String gocDate) {
		this.gocDate = gocDate;
	}
	public String getTo() {
		return to;
	}
	public void setTo(String to) {
		this.to = to;
	}
	public String getToName() {
		return toName;
	}
	public void setToName(String toName) {
		this.toName = toName;
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
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	
	

}
