package com.vjentrps.oms.service.impl;

import java.text.ParseException;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.support.incrementer.DataFieldMaxValueIncrementer;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.vjentrps.oms.dao.RINDao;
import com.vjentrps.oms.dao.StockDao;
import com.vjentrps.oms.exception.OmsDataAccessException;
import com.vjentrps.oms.exception.OmsServiceException;
import com.vjentrps.oms.model.CommonConstants;
import com.vjentrps.oms.model.ProductStock;
import com.vjentrps.oms.model.ReturnedInwardNote;
import com.vjentrps.oms.model.StockRecord;
import com.vjentrps.oms.service.RINService;
import com.vjentrps.oms.util.CommonUtil;

@Service
@Transactional
public class RINServiceImpl implements RINService {

	private Logger log = LoggerFactory.getLogger(this.getClass());

	@Autowired
	private RINDao rinDao;

	@Autowired
	private StockDao stockDao;

	@Autowired
	DataFieldMaxValueIncrementer rinIdIncrementer;

	@Override
	public void createRIN(ReturnedInwardNote rin) throws OmsServiceException, ParseException {

		if (null != rin && null != rin.getProduct()) {
			try {
				ProductStock productStock = stockDao.getProductStock(rin.getProduct().getProductId());
				
				rin.setRinNo(CommonUtil.buildTransId(CommonConstants.RIN, rinIdIncrementer.nextStringValue()));

				rin.setDocDate(CommonUtil.formatDate(rin.getDocDate()));
				
				int success = rinDao.createRIN(rin);

				if(success > 0 && null != productStock) {

					StockRecord stockRecord = CommonUtil.buildStockRecord(rin, productStock, CommonConstants.RIN);
					stockDao.addStockRecord(stockRecord);
					stockDao.updateProductStock(productStock);

				}
			} catch (OmsDataAccessException e) {
				throw new OmsServiceException(e);
			}
		}

	}

	@Override
	public List<ReturnedInwardNote> listRINs() throws OmsServiceException {
		try {
			return rinDao.fetchAllRINs();
		} catch (OmsDataAccessException e) {
			throw new OmsServiceException(e);
		}
	}

	@Override
	public void updateRIN(ReturnedInwardNote rin) {
		try {
			rinDao.updateRIN(rin);
		} catch (OmsDataAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	@Override
	public void deleteRIN(String rinNo) {
		try {
			rinDao.deleteRIN(rinNo);
		} catch (OmsDataAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	@Override
	public void updateRINStatus(ReturnedInwardNote rin) {
		try {
			rinDao.updateRINStatus(rin);
		} catch (OmsDataAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

}
