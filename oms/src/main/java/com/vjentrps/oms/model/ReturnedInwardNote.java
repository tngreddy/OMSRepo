
package com.vjentrps.oms.model;

public class ReturnedInwardNote {
	
	private String rinNo;
	private String rinDate;
	private String from;
	private String fromName;
	private String docRefNo;
	private String docDate;
	private Product product;
	private int goodIn;
	private int defIn;
	private String status;
	
	public String getRinNo() {
		return rinNo;
	}
	public void setRinNo(String rinNo) {
		this.rinNo = rinNo;
	}
	public String getRinDate() {
		return rinDate;
	}
	public void setRinDate(String rinDate) {
		this.rinDate = rinDate;
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
	public int getDefIn() {
		return defIn;
	}
	public void setDefIn(int defIn) {
		this.defIn = defIn;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	

}
