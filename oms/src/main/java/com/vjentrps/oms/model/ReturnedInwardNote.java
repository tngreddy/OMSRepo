
package com.vjentrps.oms.model;

import java.util.List;

public class ReturnedInwardNote {
	
	private String rinNo;
	private String rinDate;
	private String from;
	private String fromName;
	private String docRefNo;
	private String docDate;
	private List<ProdInfo> prodInfoList;
	private String grcNo;
	private String status;
	private String remarks;
	
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
	public List<ProdInfo> getProdInfoList() {
		return prodInfoList;
	}
	public void setProdInfoList(List<ProdInfo> prodInfoList) {
		this.prodInfoList = prodInfoList;
	}
	public String getGrcNo() {
		return grcNo;
	}
	public void setGrcNo(String grcNo) {
		this.grcNo = grcNo;
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
