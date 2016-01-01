package com.vjentrps.oms.service;

import java.text.ParseException;
import java.util.List;

import com.vjentrps.oms.exception.OmsServiceException;
import com.vjentrps.oms.model.GINDetails;
import com.vjentrps.oms.model.GoodsInwardNote;
import com.vjentrps.oms.model.ProdInfo;

public interface GINService {
	
	String createGIN(GoodsInwardNote gin) throws OmsServiceException, ParseException;

	List<GoodsInwardNote> listGINs() throws OmsServiceException;

	void updateGIN(GoodsInwardNote gin) throws OmsServiceException;

	void deleteGIN(long ginNo) throws OmsServiceException;

	void updateGINStatus(GoodsInwardNote gin) throws OmsServiceException;

	GINDetails buildGINDetails(String ginNo, boolean fromToInfo) throws OmsServiceException;

	GoodsInwardNote getGINbyNo(String ginNo) throws OmsServiceException;

	
	
	

}
