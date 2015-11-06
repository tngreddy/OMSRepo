package com.vjentrps.oms.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.vjentrps.oms.model.ProductStock;
import com.vjentrps.oms.model.ResponseDTO;
import com.vjentrps.oms.model.StockRecord;
import com.vjentrps.oms.service.ReportsService;

@RestController
@RequestMapping(value="/service/reports")
public class ReportsRestController {

	@Autowired
	ReportsService reportsService;

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




}