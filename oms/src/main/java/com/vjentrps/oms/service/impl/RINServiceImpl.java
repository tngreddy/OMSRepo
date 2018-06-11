package com.vjentrps.oms.service.impl;

import java.text.ParseException;
import java.util.List;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.support.incrementer.DataFieldMaxValueIncrementer;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;

import com.vjentrps.oms.dao.CustomerDao;
import com.vjentrps.oms.dao.GRCDao;
import com.vjentrps.oms.dao.RINDao;
import com.vjentrps.oms.dao.StockDao;
import com.vjentrps.oms.dao.SupplierDao;
import com.vjentrps.oms.exception.OmsDataAccessException;
import com.vjentrps.oms.exception.OmsServiceException;
import com.vjentrps.oms.model.CommonConstants;
import com.vjentrps.oms.model.Customer;
import com.vjentrps.oms.model.GoodsReturnableChallan;
import com.vjentrps.oms.model.ProdInfo;
import com.vjentrps.oms.model.ProductStock;
import com.vjentrps.oms.model.RINDetails;
import com.vjentrps.oms.model.ReturnedInwardNote;
import com.vjentrps.oms.model.StatusEnum;
import com.vjentrps.oms.model.StockRecord;
import com.vjentrps.oms.model.Supplier;
import com.vjentrps.oms.service.RINService;
import com.vjentrps.oms.util.CommonUtil;

@Service
@Transactional(rollbackFor = { RuntimeException.class, Exception.class }, isolation = Isolation.READ_UNCOMMITTED)
public class RINServiceImpl extends BaseService implements RINService {

	private Logger log = LoggerFactory.getLogger(this.getClass());

	@Autowired
	private RINDao rinDao;

	@Autowired
	private GRCDao grcDao;

	@Autowired
	private StockDao stockDao;

	@Autowired
	private SupplierDao supplierDao;

	@Autowired
	private CustomerDao customerDao;

	@Autowired
	DataFieldMaxValueIncrementer rinIdIncrementer;

	@Override
	public String createRIN(ReturnedInwardNote rin) throws OmsServiceException, ParseException {

		String rinNo = "";
		if (null != rin && CollectionUtils.isNotEmpty(rin.getProdInfoList())) {
			try {

				rin.setRinNo(CommonUtil.buildTransId(CommonConstants.RIN, rinIdIncrementer.nextStringValue()));
				rinNo = rin.getRinNo();
				rin.setDocDate(CommonUtil.formatDate(rin.getDocDate()));
				// fetching and setting fromId using fromName
				rin.setFromId(getIdFromName(rin.getFrom(), rin.getFromName()));

				int success = rinDao.createRIN(rin);

				for (ProdInfo prodInfo : rin.getProdInfoList()) {

					ProductStock productStock = stockDao.getProductStock(prodInfo.getProduct().getProductId());

					if (success > 0 && null != productStock && null != productStock.getProduct()) {

						prodInfo.setUnitBasicRate(productStock.getProduct().getUnitBasicRate());
						prodInfo.setTotalQty(prodInfo.getGoodIn() + prodInfo.getDefIn());
						prodInfo.setTotalAmount(prodInfo.getTotalQty() * prodInfo.getUnitBasicRate());

						rinDao.addRinProdInfo(rin.getRinNo(), prodInfo);

						StockRecord stockRecord = CommonUtil.buildStockRecord(rin, prodInfo, productStock.getProduct(),
								CommonConstants.RIN);

						stockDao.addStockRecord(stockRecord);
						stockDao.updateProductStock(productStock);

						if (StringUtils.isNotBlank(rin.getGrcNo())
								&& !CommonConstants.NONE.equalsIgnoreCase(rin.getGrcNo())) {

							// checking the respective GRC, checking for the
							// pending GRCs and closing them
							ProdInfo grcProdInfo = grcDao.getGRCProdInfo(rin.getGrcNo(),
									prodInfo.getProduct().getProductId());

							if (null != grcProdInfo && null != grcProdInfo.getProduct()) {

								if (isProdOutCountMatching(prodInfo, grcProdInfo)) {
									grcProdInfo.setStatus(StatusEnum.CLOSED.name());
									// updating the grc product status to closed
									grcDao.updateGrcProdInfoStatus(rin.getGrcNo(), grcProdInfo);
								} else {
									// fetching the grc partial pending product
									// info
									ProdInfo grcExistingPendInfo = grcDao.getGrcPartialPendingProdInfo(rin.getGrcNo(),
											grcProdInfo.getProduct().getProductId());

									if (null == grcExistingPendInfo) {

										ProdInfo partialPendingProdInfo = buildGRCPartialPendingProdInfo(prodInfo,
												grcProdInfo);
										grcDao.addGrcPartialPendingProdInfo(rin.getGrcNo(), partialPendingProdInfo);

									} else if (!StatusEnum.CLOSED.name()
											.equalsIgnoreCase(grcExistingPendInfo.getStatus())) {

										ProdInfo partialPendingProdInfo = buildGRCPartialPendingProdInfo(prodInfo,
												grcExistingPendInfo);
										grcDao.updateGrcPartialPendingProdInfo(rin.getGrcNo(), partialPendingProdInfo);

										if (StatusEnum.CLOSED.name()
												.equalsIgnoreCase(partialPendingProdInfo.getStatus())) {
											// updating the grc product status
											// to closed
											grcProdInfo.setStatus(StatusEnum.CLOSED.name());
											grcDao.updateGrcProdInfoStatus(rin.getGrcNo(), grcProdInfo);
										}
									}

								}
							}
						}
					}
				}

				List<ProdInfo> prodInfos = grcDao.getGRCProdInfo(rin.getGrcNo(), StatusEnum.PENDING.name());
				// if there is no prodinfo with PENDING status then updating the
				// GRC status to CLOSED
				if (CollectionUtils.isEmpty(prodInfos)) {
					GoodsReturnableChallan grc = new GoodsReturnableChallan();
					grc.setStatus(StatusEnum.CLOSED.name());
					grc.setGrcNo(rin.getGrcNo());

					grcDao.updateGRCStatus(grc);
				}

			} catch (OmsDataAccessException e) {
				throw new OmsServiceException(e);
			}
		}
		return rinNo;

	}

	/**
	 * Method to check if the goods out count matches with grc out count
	 * 
	 * @param prodInfo
	 * @param grcProdInfo
	 * @return
	 */
	private boolean isProdOutCountMatching(ProdInfo prodInfo, ProdInfo grcProdInfo) {
		return (grcProdInfo.getGoodOut() == prodInfo.getGoodIn() && grcProdInfo.getDefOut() == prodInfo.getDefIn())
				|| ((grcProdInfo.getGoodOut() + grcProdInfo.getDefOut()) == (prodInfo.getGoodIn()
						+ prodInfo.getDefIn()));
	}

	/**
	 * Method to build partial pending grc product information
	 * 
	 * @param prodInfo
	 * @param grcProdInfo
	 * @return
	 */
	private ProdInfo buildGRCPartialPendingProdInfo(ProdInfo prodInfo, ProdInfo grcProdInfo) {

		ProdInfo resultProdInfo = new ProdInfo();
		long qtyToMinusFromGoodOut = 0;
		long qtyToMinusFromDefOut = 0;
		long goodOut = 0;
		long defOut = 0;

		if (prodInfo.getGoodIn() > grcProdInfo.getGoodOut()) {

			qtyToMinusFromDefOut = prodInfo.getGoodIn() - grcProdInfo.getGoodOut();
		} else {
			goodOut = grcProdInfo.getGoodOut() - prodInfo.getGoodIn();
		}

		if (prodInfo.getDefIn() > grcProdInfo.getDefOut()) {
			qtyToMinusFromGoodOut = prodInfo.getDefIn() - grcProdInfo.getDefOut();
		} else {
			defOut = grcProdInfo.getDefOut() - prodInfo.getDefIn();
		}

		resultProdInfo.setGoodOut(goodOut - qtyToMinusFromGoodOut);
		resultProdInfo.setDefOut(defOut - qtyToMinusFromDefOut);
		resultProdInfo.setTotalQty(resultProdInfo.getGoodOut() + resultProdInfo.getDefOut());
		resultProdInfo.setUnitBasicRate(grcProdInfo.getUnitBasicRate());
		resultProdInfo.setTotalAmount(resultProdInfo.getTotalQty() * resultProdInfo.getUnitBasicRate());
		resultProdInfo.setProduct(prodInfo.getProduct());

		if (resultProdInfo.getGoodOut() == 0 && resultProdInfo.getDefOut() == 0) {
			resultProdInfo.setStatus(StatusEnum.CLOSED.name());
		} else {
			resultProdInfo.setStatus(StatusEnum.PENDING.name());
		}

		return resultProdInfo;
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

	@Override
	public RINDetails buildRINDetails(String rinNo, boolean fromToInfo) throws OmsServiceException {
		RINDetails rinDetails = new RINDetails();
		try {
			ReturnedInwardNote rin = getRINbyNo(rinNo);

			if (null != rin) {

				if (CommonConstants.SUPPLIER.equalsIgnoreCase(rin.getFrom())) {
					Supplier supplier = supplierDao.getSupplierById(rin.getFromId());
					if (null != supplier) {
						if (fromToInfo) {
							rinDetails.setFromDetails(supplier);
							// rinDetails.setSupplier(supplierDao.getSupplierByName(rin.getFromName()));

						}
						rin.setFromName(supplier.getName());
					}

				} else if (CommonConstants.CUSTOMER.equalsIgnoreCase(rin.getFrom())) {
					Customer customer = customerDao.getCustomerById(rin.getFromId());
					if (null != customer) {
						if (fromToInfo) {
							rinDetails.setFromDetails(customer);
							// rinDetails.setCustomer(customerDao.getCustomerByName(rin.getFromName()));
						}
						rin.setFromName(customer.getName());
					}

				} else if (CommonConstants.OTHERS.equalsIgnoreCase(rin.getFrom())) {
					rin.setFromName(CommonConstants.INITIAL_ENTRY);
				}
				rinDetails.setRin(rin);
			}
		} catch (OmsDataAccessException e) {
			throw new OmsServiceException(e);
		}
		return rinDetails;
	}

	@Override
	public ReturnedInwardNote getRINbyNo(String rinNo) throws OmsServiceException {
		try {
			ReturnedInwardNote rin = rinDao.fetchRINByNo(rinNo);
			if (null != rin) {
				rin.setProdInfoList(rinDao.getRINProdInfo(rinNo));
			}
			return rin;
		} catch (OmsDataAccessException e) {
			throw new OmsServiceException(e);
		}

	}

}
