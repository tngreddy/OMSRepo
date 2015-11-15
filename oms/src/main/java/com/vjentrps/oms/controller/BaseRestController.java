package com.vjentrps.oms.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.vjentrps.oms.service.CategoryService;
import com.vjentrps.oms.service.CustomerService;
import com.vjentrps.oms.service.GINService;
import com.vjentrps.oms.service.GOCService;
import com.vjentrps.oms.service.GRCService;
import com.vjentrps.oms.service.ProductService;
import com.vjentrps.oms.service.RINService;
import com.vjentrps.oms.service.ReportsService;
import com.vjentrps.oms.service.SupplierService;
import com.vjentrps.oms.util.CommonUtil;

@Component
public class BaseRestController {
	
	@Autowired
	CategoryService categoryService;
	
	@Autowired
	ProductService productService;
	
	@Autowired
	CustomerService customerService;
	
	@Autowired
	SupplierService supplierService;
	
	@Autowired
	ReportsService reportsService;
	
	@Autowired
	GINService ginService;
	
	@Autowired
	GOCService gocService;
	
	@Autowired
	GRCService grcService;
	
	@Autowired
	RINService rinService;
	
	@Autowired
	CommonUtil commonUtil;

}
