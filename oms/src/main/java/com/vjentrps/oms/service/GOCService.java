package com.vjentrps.oms.service;

import java.text.ParseException;
import java.util.List;

import com.vjentrps.oms.exception.OmsServiceException;
import com.vjentrps.oms.model.GOCDetails;
import com.vjentrps.oms.model.GoodsOutwardChallan;
import com.vjentrps.oms.model.ServiceResponse;

public interface GOCService {
	
	String createGOC(GoodsOutwardChallan goc) throws ParseException, OmsServiceException;

	List<GoodsOutwardChallan> listGOCs() throws OmsServiceException;

	void updateGOC(GoodsOutwardChallan goc) throws OmsServiceException;

	void deleteGOC(long gocNo) throws OmsServiceException;

	void updateGOCStatus(GoodsOutwardChallan goc) throws OmsServiceException;

	GOCDetails buildGOCDetails(String gocNo, boolean fromToInfo) throws OmsServiceException;

	GoodsOutwardChallan getGOCbyNo(String gocNo) throws OmsServiceException;
	
	

}
