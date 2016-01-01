package com.vjentrps.oms.service;

import java.util.List;

import com.vjentrps.oms.exception.OmsServiceException;
import com.vjentrps.oms.model.ProductStock;
import com.vjentrps.oms.model.SearchObj;
import com.vjentrps.oms.model.StockRecord;

public interface ReportsService {
	 
	 List<ProductStock> fetchProductStock() throws OmsServiceException;
	 
	 List<StockRecord> fetchStockRecords() throws OmsServiceException;

     List<StockRecord> fetchStockRecords(SearchObj searchObj) throws OmsServiceException, Exception;

	 ProductStock fetchProductStock(long productId) throws OmsServiceException;  
	
}
