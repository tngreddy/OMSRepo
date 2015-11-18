
package com.vjentrps.oms.model;

import java.util.List;

public class GoodsInwardNote {
	
	private String ginNo;
	private String ginDate;
	private String from;
	private String fromName;
	private String docRefNo;
	private String docDate;
	private List<ProdInfo> prodInfoList;
	private String status;
	private String remarks;
	
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
