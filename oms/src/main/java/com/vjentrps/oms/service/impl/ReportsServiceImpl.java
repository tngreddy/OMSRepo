package com.vjentrps.oms.service.impl;

import java.text.ParseException;
import java.util.List;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
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
import com.vjentrps.oms.model.CommonConstants;
import com.vjentrps.oms.model.ProductStock;
import com.vjentrps.oms.model.SearchObj;
import com.vjentrps.oms.model.StockRecord;
import com.vjentrps.oms.service.CategoryService;
import com.vjentrps.oms.service.ReportsService;
import com.vjentrps.oms.util.CommonUtil;

@Service
@Transactional
public class ReportsServiceImpl extends BaseService implements ReportsService {

	private Logger log = LoggerFactory.getLogger(this.getClass());

	@Autowired
	public StockDao stockDao;

	@Override
	public List<ProductStock> fetchProductStock() throws OmsServiceException {
		try {
			return stockDao.getAllProductStock();
		} catch (OmsDataAccessException e) {
			throw new OmsServiceException(e);
		}
	}
	
	@Override
	public ProductStock fetchProductStock(long productId)
			throws OmsServiceException {
		try {
			return stockDao.getProductStock(productId);
		} catch (OmsDataAccessException e) {
			throw new OmsServiceException(e);
		}
	}


	@Override
	public List<StockRecord> fetchStockRecords() throws OmsServiceException {
		try {
			List<StockRecord> stockRecords = stockDao.getAllStockRecords();
			//set fromTo Name for the respective fromToId and fromToType
			setFromToName(stockRecords);
			return stockRecords;
		} catch (OmsDataAccessException e) {
			throw new OmsServiceException(e);
		}
	}

	private void setFromToName(List<StockRecord> stockRecords) throws OmsDataAccessException {
		
		if(CollectionUtils.isNotEmpty(stockRecords)){
			for(StockRecord stockRecord : stockRecords) {
				stockRecord.setFromTo(getNameFromId(stockRecord.getFromToType(), stockRecord.getFromToId()));
			}
		}
		
	}

	@Override
	public List<StockRecord> fetchStockRecords(SearchObj searchObj)
			throws OmsServiceException, Exception {
		try {
			if (null != searchObj
					&& StringUtils.isNotBlank(searchObj.getCategoryName())) {

				if (StringUtils.isNoneBlank(searchObj.getFromDate(), searchObj.getToDate())) {
					searchObj.setFromDate(CommonUtil.formatDate(searchObj.getFromDate()));
					searchObj.setToDate(CommonUtil.formatDate(searchObj.getToDate()));
				}

				if (CommonConstants.ALL.equalsIgnoreCase(searchObj.getCategoryName()) && !StringUtils.isNoneBlank(searchObj.getFromDate(), searchObj.getToDate())) {
					List<StockRecord> stockRecords = stockDao.getAllStockRecords();
					//set fromTo Name for the respective fromToId and fromToType
					setFromToName(stockRecords);
					return stockRecords;
				} else {
					List<StockRecord> stockRecords = stockDao.getAllStockRecords(searchObj);
					//set fromTo Name for the respective fromToId and fromToType
					setFromToName(stockRecords);
					return stockRecords;
				}

			}

		} catch (OmsDataAccessException e) {
			throw new OmsServiceException(e);
		}
		return null;

	}

	
}
