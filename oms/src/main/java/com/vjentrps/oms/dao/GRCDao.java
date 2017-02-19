package com.vjentrps.oms.dao;

import java.util.List;

import com.vjentrps.oms.exception.OmsDataAccessException;
import com.vjentrps.oms.model.GoodsReturnableChallan;
import com.vjentrps.oms.model.PendingGRC;
import com.vjentrps.oms.model.ProdInfo;

public interface GRCDao {

	int createGRC(GoodsReturnableChallan grc) throws OmsDataAccessException;

	List<GoodsReturnableChallan> fetchAllGRCs() throws OmsDataAccessException;

	void updateGRC(GoodsReturnableChallan grc) throws OmsDataAccessException;

	void deleteGRC(String grcNo) throws OmsDataAccessException;

	void updateGRCStatus(GoodsReturnableChallan grc) throws OmsDataAccessException;

	void addGrcProdInfo(String grcNo, ProdInfo prodInfo) throws OmsDataAccessException;

	List<String> fetchGRCNoList(String fromName) throws OmsDataAccessException;

	List<ProdInfo> getGRCProdInfo(String grcNo, String status) throws OmsDataAccessException;

	void updateGrcProdInfoStatus(String grcNo, ProdInfo grcProdInfo) throws OmsDataAccessException;
	
	List<ProdInfo> getGrcPartialPendingProdInfo(String grcNo) throws OmsDataAccessException;

	ProdInfo getGrcPartialPendingProdInfo(String grcNo, long productId) throws OmsDataAccessException;

	void addGrcPartialPendingProdInfo(String grcNo, ProdInfo partialPendingProdInfo) throws OmsDataAccessException;

	void updateGrcPartialPendingProdInfo(String grcNo, ProdInfo partialPendingProdInfo) throws OmsDataAccessException;

	ProdInfo getGRCProdInfo(String grcNo, long productId) throws OmsDataAccessException;

	GoodsReturnableChallan fetchGRCByNo(String grcNo) throws OmsDataAccessException;

	List<PendingGRC> getAllGrcPartialPendingProdInfo(String status)	throws OmsDataAccessException;

	List<PendingGRC> getAllGrcPendingProdInfo(String status) throws OmsDataAccessException;
	
	
}
