package com.vjentrps.oms.service;

import java.util.List;

import com.vjentrps.oms.model.GoodsInwardNote;

public interface GINService {
	
	void createGIN(GoodsInwardNote gin);

	List<GoodsInwardNote> listGINs();

	void updateGIN(GoodsInwardNote gin);

	void deleteGIN(long ginNo);

	void updateGINStatus(GoodsInwardNote gin);
	
	

}
