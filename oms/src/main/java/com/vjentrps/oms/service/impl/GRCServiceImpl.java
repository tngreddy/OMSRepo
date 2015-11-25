package com.vjentrps.oms.service.impl;

import java.text.ParseException;
import java.util.List;

import org.apache.commons.collections.CollectionUtils;
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
import com.vjentrps.oms.model.ProdInfo;
import com.vjentrps.oms.model.ProductStock;
import com.vjentrps.oms.model.StockRecord;
import com.vjentrps.oms.service.GRCService;
import com.vjentrps.oms.util.CommonUtil;

@Service
@Transactional(rollbackFor={RuntimeException.class, Exception.class})
public class GRCServiceImpl implements GRCService {

	private Logger log = LoggerFactory.getLogger(this.getClass());

	@Autowired
	private GRCDao grcDao;

	@Autowired
	private StockDao stockDao;

	@Autowired
	DataFieldMaxValueIncrementer grcIdIncrementer;

	@Override
	public void createGRC(GoodsReturnableChallan grc) throws ParseException, OmsServiceException {

		if (null != grc && CollectionUtils.isNotEmpty(grc.getProdInfoList())) {
			try {

				grc.setGrcNo(CommonUtil.buildTransId(CommonConstants.GRC, grcIdIncrementer.nextStringValue()));

				grc.setDocDate(CommonUtil.formatDate(grc.getDocDate()));

				int success = grcDao.createGRC(grc);

				for (ProdInfo prodInfo: grc.getProdInfoList()) {

					ProductStock productStock = stockDao.getProductStock(prodInfo.getProduct().getProductId());

					if(success > 0 && null != productStock) {

						prodInfo.setTotalQty(prodInfo.getGoodOut()+prodInfo.getDefOut());
						prodInfo.setTotalAmount(prodInfo.getTotalQty()*prodInfo.getUnitBasicRate());
						
						grcDao.addGrcProdInfo(grc.getGrcNo(),prodInfo);
						
						StockRecord stockRecord = CommonUtil.buildStockRecord(grc, prodInfo, productStock.getProduct(), CommonConstants.GRC);
						
						stockDao.addStockRecord(stockRecord);
						stockDao.updateProductStock(productStock);

					}
				}
			} catch (OmsDataAccessException e) {
				throw new OmsServiceException(e);
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
