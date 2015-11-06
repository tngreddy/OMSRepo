package com.vjentrps.oms.dao;

import java.util.List;

import com.vjentrps.oms.model.ReturnedInwardNote;

public interface RINDao {

	int createRIN(ReturnedInwardNote rin);

	List<ReturnedInwardNote> fetchAllRINs();

	void updateRIN(ReturnedInwardNote rin);

	void deleteRIN(String rinNo);

	void updateRINStatus(ReturnedInwardNote rin);

	
}
