package com.vjentrps.oms.service.impl;

import java.text.ParseException;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.support.incrementer.DataFieldMaxValueIncrementer;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.vjentrps.oms.dao.GRCDao;
import com.vjentrps.oms.dao.StockDao;
import com.vjentrps.oms.exception.OmsDataAccessException;
import com.vjentrps.oms.exception.OmsServiceException;
import com.vjentrps.oms.model.CommonConstants;
import com.vjentrps.oms.model.GoodsReturnableChallan;
import com.vjentrps.oms.model.ProductStock;
import com.vjentrps.oms.model.StockRecord;
import com.vjentrps.oms.service.GRCService;
import com.vjentrps.oms.util.CommonUtil;

@Service
@Transactional
public class GRCServiceImpl implements GRCService {

	private Logger log = LoggerFactory.getLogger(this.getClass());

	@Autowired
	private GRCDao grcDao;

	@Autowired
	private StockDao stockDao;

	@Autowired
	DataFieldMaxValueIncrementer grcIdIncrementer;

	@Override
	public void createGRC(GoodsReturnableChallan grc) throws ParseException {

		if (null != grc && null != grc.getProduct()) {
			try {
			ProductStock productStock = stockDao.getProductStock(grc.getProduct().getProductId());

			grc.setGrcNo(CommonUtil.buildTransId(CommonConstants.GRC, grcIdIncrementer.nextStringValue()));

			grc.setDocDate(CommonUtil.formatDate(grc.getDocDate()));

			int success = grcDao.createGRC(grc);
			
			if(success > 0 && null!=productStock) {
				
				StockRecord stockRecord = CommonUtil.buildStockRecord(grc, productStock, CommonConstants.GRC);
				stockDao.addStockRecord(stockRecord);
				stockDao.updateProductStock(productStock);

			}
			
			} catch (OmsDataAccessException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

	}

	@Override
	public List<GoodsReturnableChallan> listGRCs() throws OmsServiceException {
		try {
			return grcDao.fetchAllGRCs();
		} catch (OmsDataAccessException e) {
			throw new OmsServiceException(e);
		}
	}

	@Override
	public void updateGRC(GoodsReturnableChallan grc) {
		try {
			grcDao.updateGRC(grc);
		} catch (OmsDataAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	@Override
	public void deleteGRC(String grcNo) {
		try {
			grcDao.deleteGRC(grcNo);
		} catch (OmsDataAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	@Override
	public void updateGRCStatus(GoodsReturnableChallan grc) {
		try {
			grcDao.updateGRCStatus(grc);
		} catch (OmsDataAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

}
