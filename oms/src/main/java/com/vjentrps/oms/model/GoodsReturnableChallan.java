package com.vjentrps.oms.model;

import java.util.List;

public class GoodsReturnableChallan {
	
	private String grcNo;
	private String grcDate;
	private String to;
	private String toName;
	private String docRefNo;
	private String docDate;
	private List<ProdInfo> prodInfoList;
	private String status;
	private String remarks;
	public String getGrcNo() {
		return grcNo;
	}
	public void setGrcNo(String grcNo) {
		this.grcNo = grcNo;
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
	public List<ProdInfo> getProdInfoList() {
		return prodInfoList;
	}
	public void setProdInfoList(List<ProdInfo> prodInfoList) {
		this.prodInfoList = prodInfoList;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public String getRemarks() {
		return remarks;
	}
	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}
	
	

}
