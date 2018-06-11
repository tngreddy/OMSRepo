package com.vjentrps.oms.model;

public class StockRecord {
	
	private long recordId;
	private String transDocRef;
	private Product product;
	private long fromToId;
	private String fromToType;
	private String fromTo;
	private long goodIn;
	private long goodOut;
	private long goodBalance;
	private long defIn;
	private long defOut;
	private long defBalance;
	private String createdDate;
	
	public long getRecordId() {
		return recordId;
	}
	public void setRecordId(long recordId) {
		this.recordId = recordId;
	}
	public String getTransDocRef() {
		return transDocRef;
	}
	public void setTransDocRef(String transDocRef) {
		this.transDocRef = transDocRef;
	}
	
	public long getGoodIn() {
		return goodIn;
	}
	public void setGoodIn(long goodIn) {
		this.goodIn = goodIn;
	}
	public long getGoodOut() {
		return goodOut;
	}
	public void setGoodOut(long goodOut) {
		this.goodOut = goodOut;
	}
	public long getGoodBalance() {
		return goodBalance;
	}
	public void setGoodBalance(long goodBalance) {
		this.goodBalance = goodBalance;
	}
	public long getDefIn() {
		return defIn;
	}
	public void setDefIn(long defIn) {
		this.defIn = defIn;
	}
	public long getDefOut() {
		return defOut;
	}
	public void setDefOut(long defOut) {
		this.defOut = defOut;
	}
	public long getDefBalance() {
		return defBalance;
	}
	public void setDefBalance(long defBalance) {
		this.defBalance = defBalance;
	}
	
	public String getCreatedDate() {
		return createdDate;
	}
	public void setCreatedDate(String createdDate) {
		this.createdDate = createdDate;
	}
	public Product getProduct() {
		return product;
	}
	public void setProduct(Product product) {
		this.product = product;
	}
	public String getFromToType() {
		return fromToType;
	}
	public void setFromToType(String fromToType) {
		this.fromToType = fromToType;
	}
	public long getFromToId() {
		return fromToId;
	}
	public void setFromToId(long fromToId) {
		this.fromToId = fromToId;
	}
	public String getFromTo() {
		return fromTo;
	}
	public void setFromTo(String fromTo) {
		this.fromTo = fromTo;
	}	

}
