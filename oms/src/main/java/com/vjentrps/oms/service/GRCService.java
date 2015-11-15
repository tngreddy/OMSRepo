package com.vjentrps.oms.service;

import java.text.ParseException;
import java.util.List;

import com.vjentrps.oms.exception.OmsServiceException;
import com.vjentrps.oms.model.GoodsReturnableChallan;

public interface GRCService {
	
	void createGRC(GoodsReturnableChallan grc) throws ParseException, OmsServiceException;

	List<GoodsReturnableChallan> listGRCs() throws OmsServiceException;

	void updateGRC(GoodsReturnableChallan grc) throws OmsServiceException;

	void deleteGRC(String grcNo) throws OmsServiceException;

	void updateGRCStatus(GoodsReturnableChallan grc) throws OmsServiceException;
	
	

}
