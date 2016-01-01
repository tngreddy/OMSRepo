package com.vjentrps.oms.dao;

import java.util.List;

import com.vjentrps.oms.exception.OmsDataAccessException;
import com.vjentrps.oms.model.GoodsOutwardChallan;
import com.vjentrps.oms.model.ProdInfo;

public interface GOCDao {

	int createGOC(GoodsOutwardChallan goc) throws OmsDataAccessException;

	List<GoodsOutwardChallan> fetchAllGOCs() throws OmsDataAccessException;

	void updateGOC(GoodsOutwardChallan goc) throws OmsDataAccessException;

	void deleteGOC(long gocNo) throws OmsDataAccessException;

	void updateGOCStatus(GoodsOutwardChallan goc) throws OmsDataAccessException;

	void addGocProdInfo(String gocNo, ProdInfo prodInfo) throws OmsDataAccessException;

	GoodsOutwardChallan fetchGOCByNo(String gocNo) throws OmsDataAccessException;

	List<ProdInfo> getGOCProdInfo(String gocNo) throws OmsDataAccessException;


	
}
