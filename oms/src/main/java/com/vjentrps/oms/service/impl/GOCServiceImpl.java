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
import com.vjentrps.oms.exception.OmsDataAccessException;
import com.vjentrps.oms.exception.OmsServiceException;
import com.vjentrps.oms.model.CommonConstants;
import com.vjentrps.oms.model.GoodsOutwardChallan;
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
	public void createGOC(GoodsOutwardChallan goc) throws OmsServiceException,ParseException  {

		if (null != goc && null != goc.getProduct()) {
			try {
				ProductStock productStock = stockDao.getProductStock(goc.getProduct().getProductId());

				goc.setGocNo(CommonUtil.buildTransId(CommonConstants.GOC, gocIdIncrementer.nextStringValue()));

				goc.setDocDate(CommonUtil.formatDate(goc.getDocDate()));

				int success = gocDao.createGOC(goc);

				if(success > 0 && null != productStock) {

					StockRecord stockRecord = CommonUtil.buildStockRecord(goc, productStock, CommonConstants.GOC);
					stockDao.addStockRecord(stockRecord);
					stockDao.updateProductStock(productStock);

				}

			} catch (OmsDataAccessException e) {
				throw new OmsServiceException(e);
			}
		}

	}

	@Override
	public List<GoodsOutwardChallan> listGOCs() throws OmsServiceException {
		try {
			return gocDao.fetchAllGOCs();
		} catch (OmsDataAccessException e) {
			throw new OmsServiceException(e);
		}
	}

	@Override
	public void updateGOC(GoodsOutwardChallan goc) {
		try {
			gocDao.updateGOC(goc);
		} catch (OmsDataAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	@Override
	public void deleteGOC(long gocNo) {
		try {
			gocDao.deleteGOC(gocNo);
		} catch (OmsDataAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	@Override
	public void updateGOCStatus(GoodsOutwardChallan goc) {
		try {
			gocDao.updateGOCStatus(goc);
		} catch (OmsDataAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

}
