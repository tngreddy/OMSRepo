package com.vjentrps.oms.service;

import java.text.ParseException;
import java.util.List;

import com.vjentrps.oms.model.GoodsReturnableChallan;

public interface GRCService {
	
	void createGRC(GoodsReturnableChallan grc) throws ParseException;

	List<GoodsReturnableChallan> listGRCs();

	void updateGRC(GoodsReturnableChallan grc);

	void deleteGRC(String grcNo);

	void updateGRCStatus(GoodsReturnableChallan grc);
	
	

}
