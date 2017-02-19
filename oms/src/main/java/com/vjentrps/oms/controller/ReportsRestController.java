package com.vjentrps.oms.controller;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.collections.CollectionUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.vjentrps.oms.exception.OmsServiceException;
import com.vjentrps.oms.model.ErrorsEnum;
import com.vjentrps.oms.model.ProductStock;
import com.vjentrps.oms.model.ResponseDTO;
import com.vjentrps.oms.model.SearchObj;
import com.vjentrps.oms.model.StockRecord;

@RestController
@RequestMapping(value="/service/reports")
public class ReportsRestController extends BaseRestController {
	
	private Logger log = LoggerFactory.getLogger(this.getClass());


	@RequestMapping(value="/productStock" ,method = RequestMethod.GET)
	public ResponseDTO getProductStock() {

		List<ProductStock> productStocks = new ArrayList<ProductStock>();
		try {      
			productStocks = reportsService.fetchProductStock();
		} catch (OmsServiceException e) {
			log.error("Error while getting productstock",e);
			return new ResponseDTO(commonUtil.processError(ErrorsEnum.TECHNICAL_EXCEPTION));
		} catch (Exception e) {
			log.error("Error while getting productstock",e);
			return new ResponseDTO(commonUtil.processError(ErrorsEnum.SERVICE_DOWN));
		}

		return new ResponseDTO(productStocks);
	}

	
	@RequestMapping(value="/productStock/{productId}" ,method = RequestMethod.GET)
	public ResponseDTO getProductStock(@PathVariable long productId) {

		ProductStock productStock = null;
		try {      
			productStock = reportsService.fetchProductStock(productId);
		} catch (OmsServiceException e) {
			log.error("Error while getting product"+productId+"productstock",e);
			return new ResponseDTO(commonUtil.processError(ErrorsEnum.TECHNICAL_EXCEPTION));
		} catch (Exception e) {
			log.error("Error while getting productstock",e);
			return new ResponseDTO(commonUtil.processError(ErrorsEnum.SERVICE_DOWN));
		}

		return new ResponseDTO(productStock);
	}
	
	@RequestMapping(value="/stockRecord" ,method = RequestMethod.GET)
	public ResponseDTO getStockRecord() {

		List<StockRecord> stockRecords = new ArrayList<StockRecord>();
		try {      
			stockRecords =  reportsService.fetchStockRecords();
			if (CollectionUtils.isEmpty(stockRecords)) {
				return new ResponseDTO(commonUtil.processError(ErrorsEnum.NO_DATA_FOUND));
			}
				
		} catch (OmsServiceException e) {
			log.error("Error while getting stockRecord",e);
			return new ResponseDTO(commonUtil.processError(ErrorsEnum.TECHNICAL_EXCEPTION));
		} catch (Exception e) {
			log.error("Error while getting stockRecord",e);
			return new ResponseDTO(commonUtil.processError(ErrorsEnum.SERVICE_DOWN));
		}

		return new ResponseDTO(stockRecords);
	}
	
	
	@RequestMapping(value="/stockRecord/search" ,method = RequestMethod.POST)
	public ResponseDTO fetchStockRecord(@RequestBody SearchObj searchObj) {

		List<StockRecord> stockRecords = new ArrayList<StockRecord>();
		if(null!=searchObj) {
			
		try {      
			stockRecords =  reportsService.fetchStockRecords(searchObj);
			if (CollectionUtils.isEmpty(stockRecords)) {
				return new ResponseDTO(commonUtil.processError(ErrorsEnum.NO_DATA_FOUND));
			}
		} catch (OmsServiceException e) {
			log.error("Error while fetching stockrecords",e);
			return new ResponseDTO(commonUtil.processError(ErrorsEnum.TECHNICAL_EXCEPTION));
		} catch (Exception e) {
			log.error("Error while fetching stockrecords",e);
			return new ResponseDTO(commonUtil.processError(ErrorsEnum.SERVICE_DOWN));
		}
	}
		return new ResponseDTO(stockRecords);
	}




}