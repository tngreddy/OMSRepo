package com.vjentrps.oms.dao;

import java.util.List;

import com.vjentrps.oms.exception.OmsDataAccessException;
import com.vjentrps.oms.model.GoodsInwardNote;

public interface GINDao {

	int createGIN(GoodsInwardNote gin) throws OmsDataAccessException;

	List<GoodsInwardNote> fetchAllGINs() throws OmsDataAccessException;

	void updateGIN(GoodsInwardNote gin) throws OmsDataAccessException;

	void deleteGIN(long ginNo) throws OmsDataAccessException;

	void updateGINStatus(GoodsInwardNote gin) throws OmsDataAccessException;

	
}
