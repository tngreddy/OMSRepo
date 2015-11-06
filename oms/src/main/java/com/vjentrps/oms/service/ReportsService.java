package com.vjentrps.oms.service;

import java.util.List;

import com.vjentrps.oms.dao.StockDao;
import com.vjentrps.oms.exception.OmsServiceException;
import com.vjentrps.oms.model.Category;
import com.vjentrps.oms.model.ProductStock;
import com.vjentrps.oms.model.StockRecord;

public interface ReportsService {
	 
	 List<ProductStock> fetchProductStock();
	 
	 List<StockRecord> fetchStockRecords();  
	
}
