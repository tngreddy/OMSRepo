package com.vjentrps.oms.service;

import java.text.ParseException;
import java.util.List;

import com.vjentrps.oms.model.GoodsInwardNote;
import com.vjentrps.oms.model.GoodsOutwardChallan;

public interface GOCService {
	
	void createGOC(GoodsOutwardChallan goc) throws ParseException;

	List<GoodsOutwardChallan> listGOCs();

	void updateGOC(GoodsOutwardChallan goc);

	void deleteGOC(long gocNo);

	void updateGOCStatus(GoodsOutwardChallan goc);
	
	

}
