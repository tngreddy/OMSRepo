package com.vjentrps.oms.service;

import java.text.ParseException;
import java.util.List;

import com.vjentrps.oms.model.ReturnedInwardNote;

public interface RINService {
	
	void createRIN(ReturnedInwardNote rin) throws ParseException;

	List<ReturnedInwardNote> listRINs();

	void updateRIN(ReturnedInwardNote rin);

	void deleteRIN(String rinNo);

	void updateRINStatus(ReturnedInwardNote rin);
	
	

}
