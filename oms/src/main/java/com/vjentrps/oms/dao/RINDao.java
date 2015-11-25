package com.vjentrps.oms.dao;

import java.util.List;

import com.vjentrps.oms.exception.OmsDataAccessException;
import com.vjentrps.oms.model.ProdInfo;
import com.vjentrps.oms.model.ReturnedInwardNote;

public interface RINDao {

	int createRIN(ReturnedInwardNote rin) throws OmsDataAccessException;

	List<ReturnedInwardNote> fetchAllRINs() throws OmsDataAccessException;

	void updateRIN(ReturnedInwardNote rin) throws OmsDataAccessException;

	void deleteRIN(String rinNo) throws OmsDataAccessException;

	void updateRINStatus(ReturnedInwardNote rin) throws OmsDataAccessException;

	void addRinProdInfo(String rinNo, ProdInfo prodInfo) throws OmsDataAccessException;

	
}
