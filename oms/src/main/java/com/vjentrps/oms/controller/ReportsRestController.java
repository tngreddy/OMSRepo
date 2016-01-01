package com.vjentrps.oms.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.vjentrps.oms.model.ProductStock;
import com.vjentrps.oms.model.ResponseDTO;
import com.vjentrps.oms.model.SearchObj;
import com.vjentrps.oms.model.StockRecord;

@RestController
@RequestMapping(value="/service/reports")
public class ReportsRestController extends BaseRestController {


	@RequestMapping(value="/productStock" ,method = RequestMethod.GET)
	public ResponseDTO getProductStock() {

		List<ProductStock> productStocks = new ArrayList<ProductStock>();
		try {      
			productStocks = reportsService.fetchProductStock();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return new ResponseDTO(productStocks);
	}

	
	@RequestMapping(value="/productStock/{productId}" ,method = RequestMethod.GET)
	public ResponseDTO getProductStock(@PathVariable long productId) {

		ProductStock productStock = null;
		try {      
			productStock = reportsService.fetchProductStock(productId);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return new ResponseDTO(productStock);
	}
	
	@RequestMapping(value="/stockRecord" ,method = RequestMethod.GET)
	public ResponseDTO getStockRecord() {

		List<StockRecord> stockRecords = new ArrayList<StockRecord>();
		try {      
			stockRecords =  reportsService.fetchStockRecords();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return new ResponseDTO(stockRecords);
	}
	
	
	@RequestMapping(value="/stockRecord/search" ,method = RequestMethod.POST)
	public ResponseDTO fetchStockRecord(@RequestBody SearchObj searchObj) {

		List<StockRecord> stockRecords = new ArrayList<StockRecord>();
		if(null!=searchObj) {
			
		try {      
			stockRecords =  reportsService.fetchStockRecords(searchObj);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		}
		return new ResponseDTO(stockRecords);
	}




}