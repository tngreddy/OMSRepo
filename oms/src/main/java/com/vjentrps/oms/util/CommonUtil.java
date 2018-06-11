package com.vjentrps.oms.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.annotation.Resource;

import org.acegisecurity.util.EncryptionUtils;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.vjentrps.oms.exception.OmsServiceException;
import com.vjentrps.oms.model.CommonConstants;
import com.vjentrps.oms.model.Error;
import com.vjentrps.oms.model.ErrorsEnum;
import com.vjentrps.oms.model.GoodsInwardNote;
import com.vjentrps.oms.model.GoodsOutwardChallan;
import com.vjentrps.oms.model.GoodsReturnableChallan;
import com.vjentrps.oms.model.ProdInfo;
import com.vjentrps.oms.model.Product;
import com.vjentrps.oms.model.ProductStock;
import com.vjentrps.oms.model.ReturnedInwardNote;
import com.vjentrps.oms.model.StockRecord;
import com.vjentrps.oms.service.GRCService;
import com.vjentrps.oms.service.ReportsService;

@Component
public class CommonUtil {

	@Resource(name = "errorMessages")
	public Map<Integer, String> errorMessages;
	
	@Autowired
	ReportsService reportsService;
	
	@Autowired
	GRCService grcService;

	public Error processError(ErrorsEnum err) {

		return new Error(err.getCode(), errorMessages.get(err.getCode()));

	}
	
	
	public static String buildTransId(String preToken, String postToken) {

		return StringUtils.join(preToken, postToken);
	}

	public static String formatDate(String dateString) throws ParseException {

		if (StringUtils.isNotBlank(dateString)) {
			Date date = new SimpleDateFormat("MM/dd/yyyy").parse(dateString);

			/*
			 * SimpleDateFormat format = new SimpleDateFormat(
			 * "yyyy-MM-dd'T'HH:mm:SSSZ"); Date date =
			 * format.parse(dateString.replaceAll( "([\\+\\-]\\d\\d):(\\d\\d)",
			 * "$1$2"));
			 */

			dateString = new SimpleDateFormat("yyyy-MM-dd")
					.format(date);
		}
		return dateString;

	}
	
	public static String formatFromSQLDate(String dateString) {

		if (StringUtils.isNotBlank(dateString)) {
			
			try {
				Date date = new SimpleDateFormat("yyyy-MM-dd").parse(dateString);

				/*
				 * SimpleDateFormat format = new SimpleDateFormat(
				 * "yyyy-MM-dd'T'HH:mm:SSSZ"); Date date =
				 * format.parse(dateString.replaceAll( "([\\+\\-]\\d\\d):(\\d\\d)",
				 * "$1$2"));
				 */

				dateString = new SimpleDateFormat("dd/MM/yyyy")
						.format(date);
			} catch (Exception e) {
				
			}
			
		}
		return dateString;

	}
	
	public static String encryptString(String data) {
		
		return EncryptionUtils.encrypt(CommonConstants.KEY, data);
		
	}
	
	public static String decryptString(String data) {
		
		return EncryptionUtils.decrypt(CommonConstants.KEY, data);
		
	}
	
	public Error frameError(String productName, ErrorsEnum errEnum) {
		
		Error error = new Error(errEnum.getCode(), errorMessages.get(errEnum.getCode()));
		error.setMessage("<Strong>"+productName+"</Strong>--"+error.getMessage());
		
		return error;
		
	}

	
	public Error isCrossedLimit(List<ProdInfo> prodInfos, String grcNo, String transType) throws OmsServiceException {
		
		GoodsReturnableChallan grc = null;
		Error error = isDuplicateProductSelected(prodInfos);
		if (error != null) {
			return error;
		}

		if (null != grcNo) {
			grc = grcService.getGRCbyNo(grcNo);
		}
		
		for (ProdInfo prodInfo : prodInfos) {
			if (null != prodInfo && null != prodInfo.getProduct()) {
				
				if (null != grc) {
					
					List<ProdInfo> prodInfListToCheck = null;
					
					if(CollectionUtils.isNotEmpty(grc.getPartPndgProdInfoList())){
						
						prodInfListToCheck = grc.getPartPndgProdInfoList();
						
					} else if(CollectionUtils.isNotEmpty(grc.getProdInfoList())){
						
						prodInfListToCheck = grc.getProdInfoList();
					}
					
					for (ProdInfo grcPrdInf : prodInfListToCheck) {
						if(prodInfo.getProduct().getProductId() == grcPrdInf.getProduct().getProductId()) {
							boolean goodError = prodInfo.getGoodIn() > (grcPrdInf.getGoodOut()+grcPrdInf.getDefOut()-prodInfo.getDefIn());
							 boolean defError = prodInfo.getDefIn() > ((grcPrdInf.getGoodOut()+grcPrdInf.getDefOut())-prodInfo.getGoodIn());
							 
								if (goodError && defError) {
									return frameError(grcPrdInf.getProduct().getProductName(), ErrorsEnum.GOOD_DEF_IN_GRC_EXCEEDED);
								} else if (goodError) {
									return frameError(grcPrdInf.getProduct().getProductName(), ErrorsEnum.GOOD_IN_GRC_EXCEEDED);
								} else if (defError) {
									return frameError(grcPrdInf.getProduct().getProductName(), ErrorsEnum.DEF_IN_GRC_EXCEEDED);
								}
						}
					}

				} else if (!CommonConstants.RIN.equalsIgnoreCase(transType)){
				
					ProductStock productStock = reportsService.fetchProductStock(prodInfo.getProduct().getProductId());
	
					if (null != productStock && null != productStock.getProduct()) {
						
						boolean goodOutError = prodInfo.getGoodOut() > productStock.getProduct().getGoodBalance();
						boolean defOutError = prodInfo.getDefOut() > productStock.getProduct().getDefBalance();
						if (goodOutError && defOutError) {
							return frameError(productStock.getProduct().getProductName(), ErrorsEnum.GOOD_DEF_OUT_EXCEEDED);
						} else if (goodOutError) {
							return frameError(productStock.getProduct().getProductName(), ErrorsEnum.GOOD_OUT_EXCEEDED);
						} else if (defOutError) {
							return frameError(productStock.getProduct().getProductName(), ErrorsEnum.DEF_OUT_EXCEEDED);
						}
					}
			   }
			}

		}

		return null;

	}
	
	public Error isDuplicateProductSelected(List<ProdInfo> prodInfos) throws OmsServiceException {

		final Set<Long> set1 = new HashSet<Long>();
		
		for (ProdInfo prodInfo : prodInfos) {
			if (null != prodInfo && null != prodInfo.getProduct()) {
				if (!set1.add(prodInfo.getProduct().getProductId())) {
					return processError(ErrorsEnum.DUP_PRODS_SELECTED);
				}
			}
		}

		return null;

	}
	
	public static StockRecord buildStockRecord(Object transaction,
			ProdInfo prodInfo, Product prodt, String transactionType) {

		StockRecord stockRecord = new StockRecord();

		if (null != transaction && null != prodInfo
				&& null != prodInfo.getProduct() && null != prodt
				&& StringUtils.isNotBlank(transactionType)) {

			if (CommonConstants.GIN.equalsIgnoreCase(transactionType)) {

				GoodsInwardNote gin = (GoodsInwardNote) transaction;
				Product product = new Product();
				product.setProductId(prodInfo.getProduct().getProductId());
				stockRecord.setProduct(product);
				stockRecord.setTransDocRef(gin.getGinNo());
				stockRecord.setFromToId(gin.getFromId());
				stockRecord.setFromToType(gin.getFrom());
				stockRecord.setGoodIn(prodInfo.getGoodIn());
				stockRecord.setDefIn(prodInfo.getDefIn());
				prodt.setGoodBalance(prodt.getGoodBalance()	+ prodInfo.getGoodIn());
				prodt.setDefBalance(prodt.getDefBalance() + prodInfo.getDefIn());
				stockRecord.setGoodBalance(prodt.getGoodBalance());
				stockRecord.setDefBalance(prodt.getDefBalance());

			} else if (CommonConstants.GOC.equalsIgnoreCase(transactionType)) {

				GoodsOutwardChallan goc = (GoodsOutwardChallan) transaction;
				Product product = new Product();
				product.setProductId(prodInfo.getProduct().getProductId());
				stockRecord.setProduct(product);
				stockRecord.setTransDocRef(goc.getGocNo());
				stockRecord.setFromToId(goc.getToId());
				stockRecord.setFromToType(goc.getTo());
				stockRecord.setGoodOut(prodInfo.getGoodOut());
				stockRecord.setDefOut(prodInfo.getDefOut());
				prodt.setGoodBalance(prodt.getGoodBalance()	- prodInfo.getGoodOut());
				prodt.setDefBalance(prodt.getDefBalance() - prodInfo.getDefOut());
				stockRecord.setGoodBalance(prodt.getGoodBalance());
				stockRecord.setDefBalance(prodt.getDefBalance());

			}

			if (CommonConstants.RIN.equalsIgnoreCase(transactionType)) {

				ReturnedInwardNote rin = (ReturnedInwardNote) transaction;
				Product product = new Product();
				product.setProductId(prodInfo.getProduct().getProductId());
				stockRecord.setProduct(product);
				stockRecord.setTransDocRef(rin.getRinNo());
				stockRecord.setFromToId(rin.getFromId());
				stockRecord.setFromToType(rin.getFrom());
				stockRecord.setGoodIn(prodInfo.getGoodIn());
				stockRecord.setDefIn(prodInfo.getDefIn());
				prodt.setGoodBalance(prodt.getGoodBalance()	+ prodInfo.getGoodIn());
				prodt.setDefBalance(prodt.getDefBalance() + prodInfo.getDefIn());
				stockRecord.setGoodBalance(prodt.getGoodBalance());
				stockRecord.setDefBalance(prodt.getDefBalance());

			} else if (CommonConstants.GRC.equalsIgnoreCase(transactionType)) {

				GoodsReturnableChallan grc = (GoodsReturnableChallan) transaction;
				Product product = new Product();
				product.setProductId(prodInfo.getProduct().getProductId());
				stockRecord.setProduct(product);
				stockRecord.setTransDocRef(grc.getGrcNo());
				stockRecord.setFromToId(grc.getToId());
				stockRecord.setFromToType(grc.getTo());
				stockRecord.setGoodOut(prodInfo.getGoodOut());
				stockRecord.setDefOut(prodInfo.getDefOut());
				prodt.setGoodBalance(prodt.getGoodBalance()	- prodInfo.getGoodOut());
				prodt.setDefBalance(prodt.getDefBalance() - prodInfo.getDefOut());
				stockRecord.setGoodBalance(prodt.getGoodBalance());
				stockRecord.setDefBalance(prodt.getDefBalance());

			}
		}

		return stockRecord;
	}
}
