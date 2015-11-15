package com.vjentrps.oms.dao;

import java.util.List;

import com.vjentrps.oms.exception.OmsDataAccessException;
import com.vjentrps.oms.model.GoodsReturnableChallan;

public interface GRCDao {

	int createGRC(GoodsReturnableChallan grc) throws OmsDataAccessException;

	List<GoodsReturnableChallan> fetchAllGRCs() throws OmsDataAccessException;

	void updateGRC(GoodsReturnableChallan grc) throws OmsDataAccessException;

	void deleteGRC(String grcNo) throws OmsDataAccessException;

	void updateGRCStatus(GoodsReturnableChallan grc) throws OmsDataAccessException;


	
}
