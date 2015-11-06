package com.vjentrps.oms.dao;

import java.util.List;

import com.vjentrps.oms.model.GoodsOutwardChallan;

public interface GOCDao {

	int createGOC(GoodsOutwardChallan goc);

	List<GoodsOutwardChallan> fetchAllGOCs();

	void updateGOC(GoodsOutwardChallan goc);

	void deleteGOC(long gocNo);

	void updateGOCStatus(GoodsOutwardChallan goc);


	
}
