package com.vjentrps.oms.dao;

import java.util.List;

import com.vjentrps.oms.exception.OmsDataAccessException;
import com.vjentrps.oms.model.GoodsOutwardChallan;

public interface GOCDao {

	int createGOC(GoodsOutwardChallan goc) throws OmsDataAccessException;

	List<GoodsOutwardChallan> fetchAllGOCs() throws OmsDataAccessException;

	void updateGOC(GoodsOutwardChallan goc) throws OmsDataAccessException;

	void deleteGOC(long gocNo) throws OmsDataAccessException;

	void updateGOCStatus(GoodsOutwardChallan goc) throws OmsDataAccessException;


	
}
