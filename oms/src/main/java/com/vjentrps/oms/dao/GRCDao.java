package com.vjentrps.oms.dao;

import java.util.List;

import com.vjentrps.oms.model.GoodsReturnableChallan;

public interface GRCDao {

	int createGRC(GoodsReturnableChallan grc);

	List<GoodsReturnableChallan> fetchAllGRCs();

	void updateGRC(GoodsReturnableChallan grc);

	void deleteGRC(String grcNo);

	void updateGRCStatus(GoodsReturnableChallan grc);


	
}
