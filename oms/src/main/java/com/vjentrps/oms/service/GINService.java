package com.vjentrps.oms.service;

import java.text.ParseException;
import java.util.List;

import com.vjentrps.oms.model.GoodsInwardNote;

public interface GINService {
	
	void createGIN(GoodsInwardNote gin) throws ParseException;

	List<GoodsInwardNote> listGINs();

	void updateGIN(GoodsInwardNote gin);

	void deleteGIN(long ginNo);

	void updateGINStatus(GoodsInwardNote gin);
	
	

}
