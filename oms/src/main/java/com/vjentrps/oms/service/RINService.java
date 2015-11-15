package com.vjentrps.oms.service;

import java.text.ParseException;
import java.util.List;

import com.vjentrps.oms.exception.OmsServiceException;
import com.vjentrps.oms.model.ReturnedInwardNote;

public interface RINService {
	
	void createRIN(ReturnedInwardNote rin) throws ParseException, OmsServiceException;

	List<ReturnedInwardNote> listRINs() throws OmsServiceException;

	void updateRIN(ReturnedInwardNote rin) throws OmsServiceException;

	void deleteRIN(String rinNo) throws OmsServiceException;

	void updateRINStatus(ReturnedInwardNote rin) throws OmsServiceException;
	
	

}
