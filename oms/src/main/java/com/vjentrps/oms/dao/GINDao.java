package com.vjentrps.oms.dao;

import java.util.List;

import com.vjentrps.oms.model.GoodsInwardNote;

public interface GINDao {

	Object createGIN(GoodsInwardNote gin);

	List<GoodsInwardNote> fetchAllGINs();

	void updateGIN(GoodsInwardNote gin);

	void deleteGIN(long ginNo);

	void updateGINStatus(long ginNo);

}
