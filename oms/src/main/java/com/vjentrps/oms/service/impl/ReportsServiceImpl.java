package com.vjentrps.oms.service.impl;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.vjentrps.oms.dao.CategoryDao;
import com.vjentrps.oms.dao.StockDao;
import com.vjentrps.oms.exception.OmsDataAccessException;
import com.vjentrps.oms.exception.OmsServiceException;
import com.vjentrps.oms.model.Category;
import com.vjentrps.oms.model.ProductStock;
import com.vjentrps.oms.model.StockRecord;
import com.vjentrps.oms.service.CategoryService;
import com.vjentrps.oms.service.ReportsService;

@Service
@Transactional
public class ReportsServiceImpl  implements ReportsService{

	private Logger log = LoggerFactory.getLogger(this.getClass());
	
	@Autowired
	public StockDao stockDao;
	
	@Override
	public List<ProductStock> fetchProductStock() {
		return stockDao.getAllProductStock();
	}

	@Override
	public List<StockRecord> fetchStockRecords() {
		return stockDao.getAllStockRecords();
	}

}
