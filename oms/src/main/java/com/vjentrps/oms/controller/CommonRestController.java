package com.vjentrps.oms.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import org.apache.commons.collections.MapUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.vjentrps.oms.exception.OmsServiceException;
import com.vjentrps.oms.model.BasicInfo;
import com.vjentrps.oms.model.CommonConstants;
import com.vjentrps.oms.model.ErrorsEnum;
import com.vjentrps.oms.model.HomeResponseDTO;
import com.vjentrps.oms.model.ResponseDTO;

@RestController
@RequestMapping(value = "/service/common")
public class CommonRestController extends BaseRestController {
	
	private Logger log = LoggerFactory.getLogger(this.getClass());

	@RequestMapping(value = "/count", method = RequestMethod.GET)
	public ResponseDTO getCounts() {

		HomeResponseDTO homeResponseDTO = new HomeResponseDTO();
		try {
			
			homeResponseDTO.setCategoryCount(categoryService.getCategoryCount());
			homeResponseDTO.setProductCount(productService.getProductCount());
			homeResponseDTO.setCustomerCount(customerService.getCustomerCount());
			homeResponseDTO.setSupplierCount(supplierService.getSupplierCount());
			
		} catch (OmsServiceException e) {
			log.error("Error while getting counts",e);
			return new ResponseDTO(commonUtil.processError(ErrorsEnum.TECHNICAL_EXCEPTION));
		} catch (Exception e) {
			log.error("Error while getting counts",e);
			return new ResponseDTO(commonUtil.processError(ErrorsEnum.SERVICE_DOWN));
		}

		return new ResponseDTO(homeResponseDTO);
	}

	@RequestMapping(value = "/basicInfo", method = RequestMethod.GET)
	public ResponseDTO getInfoToPopulate() {

		Map<String, Object> basicInfo = new HashMap<String, Object>();
		try {
			basicInfo.put(CommonConstants.CUSTOMER, customerService.getCustomersBasicInfo());
			basicInfo.put(CommonConstants.SUPPLIER, supplierService.getSuppliersBasicInfo());
			// basicInfo.put(CommonConstants.OTHERS,
			// customerService.getCustomersBasicInfo());
			basicInfo.put(CommonConstants.PRODUCTS,	productService.getProductsBasicInfo());
			Map<String, Object> catProdMap = productService.getCatProductsMap();
			basicInfo.put(CommonConstants.CAT_PRODUCTS, catProdMap);
			if(MapUtils.isNotEmpty(catProdMap)) {
				basicInfo.put(CommonConstants.CATEGORIES, catProdMap.keySet());
			}
			BasicInfo bInfo = new BasicInfo();
			bInfo.setId(0);
			bInfo.setName("INITIAL_ENTRY");
			ArrayList<BasicInfo> blist = new ArrayList<BasicInfo>();
			blist.add(bInfo);
			
			basicInfo.put(CommonConstants.OTHERS, blist);
			
			
			
		} catch (OmsServiceException e) {
			log.error("Error while getting basic info",e);
			return new ResponseDTO(commonUtil.processError(ErrorsEnum.TECHNICAL_EXCEPTION));
		} catch (Exception e) {
			log.error("Error while getting basic info",e);
			return new ResponseDTO(commonUtil.processError(ErrorsEnum.SERVICE_DOWN));
		}

		return new ResponseDTO(basicInfo);
	}

}