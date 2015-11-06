package com.vjentrps.oms.dao;

import java.util.List;

import com.vjentrps.oms.model.ProductStock;
import com.vjentrps.oms.model.StockRecord;


public interface StockDao {

	void addStockRecord(StockRecord stockRecord);
	void addProductStock(long productId);
	void deleteProductStock(long productId);
	ProductStock getProductStock(long productId);
	void updateProductStock(ProductStock productStock);
	List<ProductStock> getAllProductStock();
	List<StockRecord> getAllStockRecords();
	

}
