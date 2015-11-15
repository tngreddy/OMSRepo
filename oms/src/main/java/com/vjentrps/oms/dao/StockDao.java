package com.vjentrps.oms.dao;

import java.util.List;

import com.vjentrps.oms.exception.OmsDataAccessException;
import com.vjentrps.oms.model.ProductStock;
import com.vjentrps.oms.model.StockRecord;


public interface StockDao {

	void addStockRecord(StockRecord stockRecord) throws OmsDataAccessException;
	void addProductStock(long productId) throws OmsDataAccessException;
	void deleteProductStock(long productId) throws OmsDataAccessException;
	ProductStock getProductStock(long productId) throws OmsDataAccessException;
	void updateProductStock(ProductStock productStock) throws OmsDataAccessException;
	List<ProductStock> getAllProductStock() throws OmsDataAccessException;
	List<StockRecord> getAllStockRecords() throws OmsDataAccessException;
	

}
