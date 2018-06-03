package com.vjentrps.oms.model;

public class PendingGRC {
	
	 private String grcNo;
	 private String grcDate;
	 private String to;
	 private String toName;
	 private ProdInfo prodInfo;
	 private String lastModified;
	 
	public String getGrcNo() {
		return grcNo;
	}
	public void setGrcNo(String grcNo) {
		this.grcNo = grcNo;
	}
	public ProdInfo getProdInfo() {
		return prodInfo;
	}
	public void setProdInfo(ProdInfo prodInfo) {
		this.prodInfo = prodInfo;
	}
	public String getGrcDate() {
		return grcDate;
	}
	public void setGrcDate(String grcDate) {
		this.grcDate = grcDate;
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
	public String getLastModified() {
		return lastModified;
	}
	public void setLastModified(String lastModified) {
		this.lastModified = lastModified;
	}
	 
	

}
