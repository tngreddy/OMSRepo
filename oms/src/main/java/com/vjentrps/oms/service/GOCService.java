package com.vjentrps.oms.service;

import java.text.ParseException;
import java.util.List;

import com.vjentrps.oms.exception.OmsServiceException;
import com.vjentrps.oms.model.GoodsInwardNote;
import com.vjentrps.oms.model.GoodsOutwardChallan;

public interface GOCService {
	
	void createGOC(GoodsOutwardChallan goc) throws ParseException, OmsServiceException;

	List<GoodsOutwardChallan> listGOCs() throws OmsServiceException;

	void updateGOC(GoodsOutwardChallan goc) throws OmsServiceException;

	void deleteGOC(long gocNo) throws OmsServiceException;

	void updateGOCStatus(GoodsOutwardChallan goc) throws OmsServiceException;
	
	

}
