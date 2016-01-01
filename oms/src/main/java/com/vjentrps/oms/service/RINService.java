package com.vjentrps.oms.service;

import java.text.ParseException;
import java.util.List;

import com.vjentrps.oms.exception.OmsServiceException;
import com.vjentrps.oms.model.GoodsInwardNote;
import com.vjentrps.oms.model.RINDetails;
import com.vjentrps.oms.model.ReturnedInwardNote;

public interface RINService {
	
	String createRIN(ReturnedInwardNote rin) throws ParseException, OmsServiceException;

	List<ReturnedInwardNote> listRINs() throws OmsServiceException;

	void updateRIN(ReturnedInwardNote rin) throws OmsServiceException;

	void deleteRIN(String rinNo) throws OmsServiceException;

	void updateRINStatus(ReturnedInwardNote rin) throws OmsServiceException;

	RINDetails buildRINDetails(String rinNo, boolean fromToInfo) throws OmsServiceException;

	ReturnedInwardNote getRINbyNo(String rinNo) throws OmsServiceException;
	
	

}
