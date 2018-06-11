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
import com.vjentrps.oms.model.Customer;
import com.vjentrps.oms.model.GINDetails;
import com.vjentrps.oms.model.GoodsInwardNote;
import com.vjentrps.oms.model.ProdInfo;
import com.vjentrps.oms.model.ProductStock;
import com.vjentrps.oms.model.StockRecord;
import com.vjentrps.oms.model.Supplier;
import com.vjentrps.oms.service.GINService;
import com.vjentrps.oms.util.CommonUtil;

@Service
@Transactional(rollbackFor = { RuntimeException.class, Exception.class })
public class GINServiceImpl extends BaseService implements GINService {

	private Logger log = LoggerFactory.getLogger(this.getClass());

	@Autowired
	private GINDao ginDao;

	@Autowired
	private StockDao stockDao;

	@Autowired
	DataFieldMaxValueIncrementer ginIdIncrementer;

	@Override
	public String createGIN(GoodsInwardNote gin) throws OmsServiceException, ParseException {

		String ginNo = "";

		if (null != gin && CollectionUtils.isNotEmpty(gin.getProdInfoList())) {
			try {

				gin.setGinNo(CommonUtil.buildTransId(CommonConstants.GIN, ginIdIncrementer.nextStringValue()));

				ginNo = gin.getGinNo();

				gin.setDocDate(CommonUtil.formatDate(gin.getDocDate()));

				// fetching and setting fromId using fromName
				gin.setFromId(getIdFromName(gin.getFrom(), gin.getFromName()));

				int success = ginDao.createGIN(gin);

				for (ProdInfo prodInfo : gin.getProdInfoList()) {

					ProductStock productStock = stockDao.getProductStock(prodInfo.getProduct().getProductId());

					if (success > 0 && null != productStock && null != productStock.getProduct()) {

						prodInfo.setUnitBasicRate(productStock.getProduct().getUnitBasicRate());
						prodInfo.setTotalQty(prodInfo.getGoodIn() + prodInfo.getDefIn());
						prodInfo.setTotalAmount(prodInfo.getTotalQty() * prodInfo.getUnitBasicRate());

						ginDao.addGinProdInfo(gin.getGinNo(), prodInfo);

						StockRecord stockRecord = CommonUtil.buildStockRecord(gin, prodInfo, productStock.getProduct(),
								CommonConstants.GIN);

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
			List<GoodsInwardNote> ginsList = ginDao.fetchAllGINs();
			if (CollectionUtils.isNotEmpty(ginsList)) {
				for (GoodsInwardNote gin : ginsList) {
					gin.setFromName(getNameFromId(gin.getFrom(), gin.getFromId()));
				}
			}
			return ginsList;
		} catch (OmsDataAccessException e) {
			throw new OmsServiceException(e);
		}
	}

	@Override
	public void updateGIN(GoodsInwardNote gin) {

		if (null != gin && CollectionUtils.isNotEmpty(gin.getProdInfoList())) {
			try {

				List<StockRecord> stockRecords = stockDao.getStockRecords(gin.getGinNo());

				if (CollectionUtils.isNotEmpty(stockRecords)) {

					for (StockRecord record : stockRecords) {

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

				if (CommonConstants.SUPPLIER.equalsIgnoreCase(gin.getFrom())) {
					Supplier supplier = supplierDao.getSupplierById(gin.getFromId());
					if (null != supplier) {
						if (fromToInfo) {
							ginDetails.setFromDetails(supplier);
							// ginDetails.setSupplier(supplierDao.getSupplierByName(gin.getFromName()));
						}
						gin.setFromName(supplier.getName());
					}

				} else if (CommonConstants.CUSTOMER.equalsIgnoreCase(gin.getFrom())) {
					Customer customer = customerDao.getCustomerById(gin.getFromId());
					if (null != customer) {
						if (fromToInfo) {
							ginDetails.setFromDetails(customer);
							// ginDetails.setCustomer(customerDao.getCustomerByName(gin.getFromName()));
						}
						gin.setFromName(customer.getName());
					}

				} else if (CommonConstants.OTHERS.equalsIgnoreCase(gin.getFrom())) {
					gin.setFromName(CommonConstants.INITIAL_ENTRY);
				}
			}
			ginDetails.setGin(gin);

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
