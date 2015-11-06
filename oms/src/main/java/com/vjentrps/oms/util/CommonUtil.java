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
import com.vjentrps.oms.model.ProductStock;
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

	public static StockRecord buildStockRecord(Object transaction, ProductStock productStock, String transactionType) {

		StockRecord stockRecord = new StockRecord();

		if ( null!=transaction && null!=productStock && StringUtils.isNotBlank(transactionType)) {

			if(CommonConstants.GIN.equalsIgnoreCase(transactionType)) {
				
				GoodsInwardNote gin = (GoodsInwardNote) transaction;
				stockRecord.setTransDocRef(gin.getGinNo());
				stockRecord.setFromTo(gin.getFromName());
				stockRecord.setGoodIn(gin.getGoodIn());
				stockRecord.setDefIn(gin.getDefectiveIn());
				productStock.setGoodBalance(productStock.getGoodBalance()+gin.getGoodIn());
				productStock.setDefBalance(productStock.getDefBalance()+gin.getDefectiveIn());
				stockRecord.setGoodBalance(productStock.getGoodBalance());
				stockRecord.setDefBalance(productStock.getDefBalance());
				
			}else if(CommonConstants.GOC.equalsIgnoreCase(transactionType)) {
				
				GoodsOutwardChallan goc = (GoodsOutwardChallan) transaction;
				stockRecord.setTransDocRef(goc.getGocNo());
				stockRecord.setFromTo(goc.getToName());
				stockRecord.setGoodOut(goc.getGoodOut());
				stockRecord.setDefOut(goc.getDefOut());
				productStock.setGoodBalance(productStock.getGoodBalance()-goc.getGoodOut());
				productStock.setDefBalance(productStock.getDefBalance()-goc.getDefOut());
				stockRecord.setGoodBalance(productStock.getGoodBalance());
				stockRecord.setDefBalance(productStock.getDefBalance());
				
			}
		}

		return stockRecord;
	}
}
