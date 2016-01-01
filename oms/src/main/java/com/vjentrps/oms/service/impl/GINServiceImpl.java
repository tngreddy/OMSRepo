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

import com.vjentrps.oms.dao.CustomerDao;
import com.vjentrps.oms.dao.GINDao;
import com.vjentrps.oms.dao.StockDao;
import com.vjentrps.oms.dao.SupplierDao;
import com.vjentrps.oms.exception.OmsDataAccessException;
import com.vjentrps.oms.exception.OmsServiceException;
import com.vjentrps.oms.model.CommonConstants;
import com.vjentrps.oms.model.GINDetails;
import com.vjentrps.oms.model.GoodsInwardNote;
import com.vjentrps.oms.model.ProdInfo;
import com.vjentrps.oms.model.ProductStock;
import com.vjentrps.oms.model.StockRecord;
import com.vjentrps.oms.service.GINService;
import com.vjentrps.oms.util.CommonUtil;

@Service
@Transactional(rollbackFor = { RuntimeException.class, Exception.class })
public class GINServiceImpl implements GINService {

	private Logger log = LoggerFactory.getLogger(this.getClass());

	@Autowired
	private GINDao ginDao;

	@Autowired
	private StockDao stockDao;

	@Autowired
	private SupplierDao supplierDao;

	@Autowired
	private CustomerDao customerDao;

	@Autowired
	DataFieldMaxValueIncrementer ginIdIncrementer;

	@Override
	public String createGIN(GoodsInwardNote gin) throws OmsServiceException,
			ParseException {
		
		String ginNo = "";

		if (null != gin && CollectionUtils.isNotEmpty(gin.getProdInfoList())) {
			try {

				gin.setGinNo(CommonUtil.buildTransId(CommonConstants.GIN, ginIdIncrementer.nextStringValue()));
				
				ginNo = gin.getGinNo();

				gin.setDocDate(CommonUtil.formatDate(gin.getDocDate()));

				int success = ginDao.createGIN(gin);

				for (ProdInfo prodInfo : gin.getProdInfoList()) {

					ProductStock productStock = stockDao.getProductStock(prodInfo.getProduct().getProductId());

					if (success > 0 && null != productStock) {

						prodInfo.setTotalQty(prodInfo.getGoodIn() + prodInfo.getDefIn());
						prodInfo.setTotalAmount(prodInfo.getTotalQty() * prodInfo.getUnitBasicRate());

						ginDao.addGinProdInfo(gin.getGinNo(), prodInfo);

						StockRecord stockRecord = CommonUtil.buildStockRecord(gin, prodInfo, productStock.getProduct(),	CommonConstants.GIN);

						stockDao.addStockRecord(stockRecord);
						stockDao.updateProductStock(productStock);

					}
				}
			} catch (OmsDataAccessException e) {
				throw new OmsServiceException(e);
			}
		}
		return ginNo;

	}

	@Override
	public List<GoodsInwardNote> listGINs() throws OmsServiceException {
		try {
			return ginDao.fetchAllGINs();
		} catch (OmsDataAccessException e) {
			throw new OmsServiceException(e);
		}
	}

	@Override
	public void updateGIN(GoodsInwardNote gin) {
		
		if (null != gin && CollectionUtils.isNotEmpty(gin.getProdInfoList())) {
		try {
			
			List<StockRecord> stockRecords = stockDao.getStockRecords(gin.getGinNo());
			
			if(CollectionUtils.isNotEmpty(stockRecords)) {
				
				for(StockRecord record:stockRecords) {
					
				}
												
			}
			
			
			ginDao.updateGIN(gin);
			
		
		} catch (OmsDataAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		}
	}

	@Override
	public void deleteGIN(long ginNo) {
		try {
			ginDao.deleteGIN(ginNo);
		} catch (OmsDataAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	@Override
	public void updateGINStatus(GoodsInwardNote gin) {
		try {
			ginDao.updateGINStatus(gin);
		} catch (OmsDataAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	@Override
	public GINDetails buildGINDetails(String ginNo, boolean fromToInfo) throws OmsServiceException {
		GINDetails ginDetails = new GINDetails();
		try {
			GoodsInwardNote gin = getGINbyNo(ginNo);
			if (null != gin) {

				if(fromToInfo){

					if (CommonConstants.SUPPLIER.equalsIgnoreCase(gin.getFrom())) {

						ginDetails.setSupplier(supplierDao.getSupplierByName(gin.getFromName()));

					} else if (CommonConstants.CUSTOMER.equalsIgnoreCase(gin.getFrom())) {

						ginDetails.setCustomer(customerDao.getCustomerByName(gin.getFromName()));
					}
				}
				ginDetails.setGin(gin);
			}
		} catch (OmsDataAccessException e) {
			throw new OmsServiceException(e);
		}
		return ginDetails;
	}

	@Override
	public GoodsInwardNote getGINbyNo(String ginNo) throws OmsServiceException {
		try {
			GoodsInwardNote gin = ginDao.fetchGINByNo(ginNo);
			if (null != gin) {
				gin.setProdInfoList(ginDao.getGINProdInfo(ginNo));
			}
			return gin;
		} catch (OmsDataAccessException e) {
			throw new OmsServiceException(e);
		}

	}

}
