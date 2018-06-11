package com.vjentrps.oms.service.impl;

import java.text.ParseException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.collections.CollectionUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.support.incrementer.DataFieldMaxValueIncrementer;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.vjentrps.oms.dao.CustomerDao;
import com.vjentrps.oms.dao.GRCDao;
import com.vjentrps.oms.dao.StockDao;
import com.vjentrps.oms.dao.SupplierDao;
import com.vjentrps.oms.exception.OmsDataAccessException;
import com.vjentrps.oms.exception.OmsServiceException;
import com.vjentrps.oms.model.CommonConstants;
import com.vjentrps.oms.model.Customer;
import com.vjentrps.oms.model.GRCDetails;
import com.vjentrps.oms.model.GoodsReturnableChallan;
import com.vjentrps.oms.model.PendingGRC;
import com.vjentrps.oms.model.ProdInfo;
import com.vjentrps.oms.model.ProductStock;
import com.vjentrps.oms.model.StockRecord;
import com.vjentrps.oms.model.Supplier;
import com.vjentrps.oms.service.GRCService;
import com.vjentrps.oms.util.CommonUtil;

@Service
@Transactional(rollbackFor={RuntimeException.class, Exception.class})
public class GRCServiceImpl extends BaseService implements GRCService {

	private Logger log = LoggerFactory.getLogger(this.getClass());

	@Autowired
	private GRCDao grcDao;

	@Autowired
	private StockDao stockDao;
	
	@Autowired
	private SupplierDao supplierDao;

	@Autowired
	private CustomerDao customerDao;

	@Autowired
	DataFieldMaxValueIncrementer grcIdIncrementer;

	@Override
	public String createGRC(GoodsReturnableChallan grc) throws ParseException, OmsServiceException {

		String grcNo = "";
		if (null != grc && CollectionUtils.isNotEmpty(grc.getProdInfoList())) {
			try {

				grc.setGrcNo(CommonUtil.buildTransId(CommonConstants.GRC, grcIdIncrementer.nextStringValue()));
				grcNo = grc.getGrcNo();
				grc.setDocDate(CommonUtil.formatDate(grc.getDocDate()));
				
				// fetching and setting fromId using fromName
				grc.setToId(getIdFromName(grc.getTo(), grc.getToName()));

				int success = grcDao.createGRC(grc);

				for (ProdInfo prodInfo: grc.getProdInfoList()) {

					ProductStock productStock = stockDao.getProductStock(prodInfo.getProduct().getProductId());

					if(success > 0 && null != productStock && null != productStock.getProduct()) {

						prodInfo.setUnitBasicRate(productStock.getProduct().getUnitBasicRate());
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
		return grcNo;
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

	@Override
	public List<String> getGRCNoList(String toName, String type) throws OmsServiceException {
		try {
			long id = getIdFromName(type, toName);
			return grcDao.fetchGRCNoList(id, type);
		} catch (OmsDataAccessException e) {
			throw new OmsServiceException(e);
		}

		
	}
	
	@Override
	public GRCDetails buildGRCDetails(String grcNo, boolean fromToInfo) throws OmsServiceException {
		GRCDetails grcDetails = new GRCDetails();
		try {
			GoodsReturnableChallan grc = getGRCbyNo(grcNo);
			
			if (null != grc) {

				if (CommonConstants.SUPPLIER.equalsIgnoreCase(grc.getTo())) {
					Supplier supplier = supplierDao.getSupplierById(grc.getToId());
					if (null != supplier) {
						if (fromToInfo) {
							grcDetails.setToDetails(supplier);
							// gocDetails.setSupplier(supplierDao.getSupplierByName(goc.getToName()));
						}
						grc.setToName(supplier.getName());
					}

				} else if (CommonConstants.CUSTOMER.equalsIgnoreCase(grc.getTo())) {
					Customer customer = customerDao.getCustomerById(grc.getToId());
					if (null != customer) {
						if (fromToInfo) {
							grcDetails.setToDetails(customer);
							// gocDetails.setCustomer(customerDao.getCustomerByName(goc.getToName()));
						}
						grc.setToName(customer.getName());
					}
				} else if (CommonConstants.OTHERS.equalsIgnoreCase(grc.getTo())) {
					grc.setToName(CommonConstants.INITIAL_ENTRY);
				}
				grcDetails.setGrc(grc);
				
//			if (null != grc) {
//				if(fromToInfo) {
//					
//					if (CommonConstants.SUPPLIER.equalsIgnoreCase(grc.getTo())) {
//						
//						grcDetails.setToDetails(supplierDao.getSupplierByName(grc.getToName()));
//						//grcDetails.setSupplier(supplierDao.getSupplierByName(grc.getToName()));
//						//grcDetails.setHasSupplier(true);
//						
//					} else if (CommonConstants.CUSTOMER.equalsIgnoreCase(grc.getTo())) {
//						grcDetails.setToDetails(customerDao.getCustomerByName(grc.getToName()));
//						//grcDetails.setCustomer(customerDao.getCustomerByName(grc.getToName()));
//						//grcDetails.setHasCustomer(true);
//					}
//				}
//				grcDetails.setGrc(grc);
			}
		} catch (OmsDataAccessException e) {
			throw new OmsServiceException(e);
		}
		return grcDetails;
	}

	@Override
	public GoodsReturnableChallan getGRCbyNo(String grcNo) throws OmsServiceException {
		try {
			GoodsReturnableChallan grc = grcDao.fetchGRCByNo(grcNo);
			if (null != grc) {
				grc.setProdInfoList(grcDao.getGRCProdInfo(grcNo, CommonConstants.ALL_STATUS));
				grc.setPartPndgProdInfoList(grcDao.getGrcPartialPendingProdInfo(grcNo));
			}
			return grc;
		} catch (OmsDataAccessException e) {
			throw new OmsServiceException(e);
		}

	}

	@Override
	public Map<String, List<PendingGRC>> fetchAllGRCPendingProdInfo(String status)
			throws OmsServiceException {
		try {
			
			Map<String, List<PendingGRC>> pendingGRCsMap = new HashMap<String, List<PendingGRC>>();
			
			pendingGRCsMap.put(CommonConstants.PENDING_GRCS, updateToName(grcDao.getAllGrcPendingProdInfo(status)));
			pendingGRCsMap.put(CommonConstants.PARTIAL_PENDING_GRCS, grcDao.getAllGrcPartialPendingProdInfo(status));
					
			return pendingGRCsMap;
			
		} catch (OmsDataAccessException e) {
			throw new OmsServiceException(e);
		}
	}

	private List<PendingGRC> updateToName(List<PendingGRC> pendingGrcs) throws OmsDataAccessException {
		if(CollectionUtils.isNotEmpty(pendingGrcs)){
			for(PendingGRC pendingGRC : pendingGrcs){
				pendingGRC.setToName(getNameFromId(pendingGRC.getTo(), pendingGRC.getToId()));
			}
		}
		return pendingGrcs;	
		
	}


}
