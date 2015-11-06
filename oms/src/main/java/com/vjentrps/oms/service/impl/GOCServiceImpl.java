package com.vjentrps.oms.service.impl;

import java.text.ParseException;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.support.incrementer.DataFieldMaxValueIncrementer;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.vjentrps.oms.dao.GOCDao;
import com.vjentrps.oms.dao.StockDao;
import com.vjentrps.oms.model.CommonConstants;
import com.vjentrps.oms.model.GoodsOutwardChallan;
import com.vjentrps.oms.model.Product;
import com.vjentrps.oms.model.ProductStock;
import com.vjentrps.oms.model.StockRecord;
import com.vjentrps.oms.service.GOCService;
import com.vjentrps.oms.util.CommonUtil;

@Service
@Transactional
public class GOCServiceImpl implements GOCService {

	private Logger log = LoggerFactory.getLogger(this.getClass());

	@Autowired
	private GOCDao gocDao;

	@Autowired
	private StockDao stockDao;

	@Autowired
	DataFieldMaxValueIncrementer gocIdIncrementer;

	@Override
	public void createGOC(GoodsOutwardChallan goc) throws ParseException {

		if (null != goc && null != goc.getProduct()) {

			ProductStock productStock = stockDao.getProductStock(goc.getProduct().getProductId());

			goc.setGocNo(CommonUtil.buildTransId(CommonConstants.GOC, gocIdIncrementer.nextStringValue()));

			goc.setDocDate(CommonUtil.formatDate(goc.getDocDate()));

			int success = gocDao.createGOC(goc);

			if(success > 0 && null!=productStock) {
				
				StockRecord stockRecord = CommonUtil.buildStockRecord(goc, productStock, CommonConstants.GOC);
				stockDao.addStockRecord(stockRecord);
				stockDao.updateProductStock(productStock);

			}
		}

	}

	@Override
	public List<GoodsOutwardChallan> listGOCs() {
		return gocDao.fetchAllGOCs();
	}

	@Override
	public void updateGOC(GoodsOutwardChallan goc) {
		gocDao.updateGOC(goc);

	}

	@Override
	public void deleteGOC(long gocNo) {
		gocDao.deleteGOC(gocNo);

	}

	@Override
	public void updateGOCStatus(GoodsOutwardChallan goc) {
		gocDao.updateGOCStatus(goc);

	}

}
