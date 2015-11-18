package com.vjentrps.oms.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Component;

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

@Component
public class CommonUtil {

	@Resource(name = "errorMessages")
	public Map<Integer, String> errorMessages;

	public Error processError(ErrorsEnum err) {

		return new Error(err.getCode(), errorMessages.get(err.getCode()));

	}

	public static String buildTransId(String preToken, String postToken) {

		return StringUtils.join(preToken, postToken);
	}

	public static String formatDate(String dateString) throws ParseException {

		if (StringUtils.isNotBlank(dateString)) {
			Date date = new SimpleDateFormat("yyyy-MM-dd").parse(dateString);

			/*
			 * SimpleDateFormat format = new SimpleDateFormat(
			 * "yyyy-MM-dd'T'HH:mm:SSSZ"); Date date =
			 * format.parse(dateString.replaceAll( "([\\+\\-]\\d\\d):(\\d\\d)",
			 * "$1$2"));
			 */

			dateString = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss")
					.format(date);
		}
		return dateString;

	}

	public static StockRecord buildStockRecord(Object transaction, ProdInfo prodInfo,  Product prodStock, String transactionType) {

		StockRecord stockRecord = new StockRecord();

		if ( null!=transaction && null!=prodInfo && null!=prodInfo.getProduct() && null!=prodStock && StringUtils.isNotBlank(transactionType)) {

			if(CommonConstants.GIN.equalsIgnoreCase(transactionType)) {
				
				GoodsInwardNote gin = (GoodsInwardNote) transaction;
				Product product = new Product();
				product.setProductId(prodInfo.getProduct().getProductId());
				stockRecord.setProduct(product);
				stockRecord.setTransDocRef(gin.getGinNo());
				stockRecord.setFromTo(gin.getFromName());
				stockRecord.setGoodIn(prodInfo.getGoodIn());
				stockRecord.setDefIn(prodInfo.getDefIn());
				prodStock.setGoodBalance(prodStock.getGoodBalance()+prodInfo.getGoodIn());
				prodStock.setDefBalance(prodStock.getGoodBalance()+prodInfo.getDefIn());
				stockRecord.setGoodBalance(prodStock.getGoodBalance());
				stockRecord.setDefBalance(prodStock.getDefBalance());
				
			}else if(CommonConstants.GOC.equalsIgnoreCase(transactionType)) {
				
				GoodsOutwardChallan goc = (GoodsOutwardChallan) transaction;
				/*Product product = new Product();
				product.setProductId(goc.getProduct().getProductId());
				stockRecord.setProduct(product);
				stockRecord.setTransDocRef(goc.getGocNo());
				stockRecord.setFromTo(goc.getToName());
				stockRecord.setGoodOut(goc.getGoodOut());
				stockRecord.setDefOut(goc.getDefOut());
				productStock.setGoodBalance(productStock.getGoodBalance()-goc.getGoodOut());
				productStock.setDefBalance(productStock.getDefBalance()-goc.getDefOut());
				stockRecord.setGoodBalance(productStock.getGoodBalance());
				stockRecord.setDefBalance(productStock.getDefBalance());*/
				
			}
			
			if(CommonConstants.RIN.equalsIgnoreCase(transactionType)) {
				
				/*ReturnedInwardNote rin = (ReturnedInwardNote) transaction;
				Product product = new Product();
				product.setProductId(rin.getProduct().getProductId());
				stockRecord.setProduct(product);
				stockRecord.setTransDocRef(rin.getRinNo());
				stockRecord.setFromTo(rin.getFromName());
				stockRecord.setGoodIn(rin.getGoodIn());
				stockRecord.setDefIn(rin.getDefIn());
				productStock.setGoodBalance(productStock.getGoodBalance()+rin.getGoodIn());
				productStock.setDefBalance(productStock.getDefBalance()+rin.getDefIn());
				stockRecord.setGoodBalance(productStock.getGoodBalance());
				stockRecord.setDefBalance(productStock.getDefBalance());*/
				
			}else if(CommonConstants.GRC.equalsIgnoreCase(transactionType)) {
				
				/*GoodsReturnableChallan grc = (GoodsReturnableChallan) transaction;
				Product product = new Product();
				product.setProductId(grc.getProduct().getProductId());
				stockRecord.setProduct(product);
				stockRecord.setTransDocRef(grc.getGrcNo());
				stockRecord.setFromTo(grc.getToName());
				stockRecord.setGoodOut(grc.getGoodOut());
				stockRecord.setDefOut(grc.getDefOut());
				productStock.setGoodBalance(productStock.getGoodBalance()-grc.getGoodOut());
				productStock.setDefBalance(productStock.getDefBalance()-grc.getDefOut());
				stockRecord.setGoodBalance(productStock.getGoodBalance());
				stockRecord.setDefBalance(productStock.getDefBalance());*/
				
			}
		}

		return stockRecord;
	}
}
