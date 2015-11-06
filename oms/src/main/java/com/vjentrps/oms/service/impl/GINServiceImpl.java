package com.vjentrps.oms.service.impl;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.support.incrementer.DataFieldMaxValueIncrementer;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.vjentrps.oms.dao.GINDao;
import com.vjentrps.oms.dao.StockDao;
import com.vjentrps.oms.model.CommonConstants;
import com.vjentrps.oms.model.GoodsInwardNote;
import com.vjentrps.oms.model.Product;
import com.vjentrps.oms.model.ProductStock;
import com.vjentrps.oms.model.StockRecord;
import com.vjentrps.oms.service.GINService;
import com.vjentrps.oms.util.CommonUtil;

@Service
@Transactional
public class GINServiceImpl implements GINService {

	private Logger log = LoggerFactory.getLogger(this.getClass());

	@Autowired
	private GINDao ginDao;

	@Autowired
	private StockDao stockDao;

	@Autowired
	DataFieldMaxValueIncrementer ginIdIncrementer;

	@Override
	public void createGIN(GoodsInwardNote gin) throws ParseException {

		if (null != gin && null != gin.getProduct()) {

			gin.setGinNo(CommonUtil.buildTransId(CommonConstants.GIN, ginIdIncrementer.nextStringValue()));
			
			gin.setDocDate(CommonUtil.formatDate(gin.getDocDate()));
			int success = ginDao.createGIN(gin);

			if(success > 0) {

				ProductStock productStock = stockDao.getProductStock(gin.getProduct().getProductId());

				if (null != productStock) {
					StockRecord stockRecord = CommonUtil.buildStockRecord(gin, productStock, CommonConstants.GIN);
					stockDao.addStockRecord(stockRecord);
					stockDao.updateProductStock(productStock);
				}

			}
		}

	}

	@Override
	public List<GoodsInwardNote> listGINs() {
		return ginDao.fetchAllGINs();
	}

	@Override
	public void updateGIN(GoodsInwardNote gin) {
		ginDao.updateGIN(gin);

	}

	@Override
	public void deleteGIN(long ginNo) {
		ginDao.deleteGIN(ginNo);

	}

	@Override
	public void updateGINStatus(GoodsInwardNote gin) {
		ginDao.updateGINStatus(gin);

	}

}
