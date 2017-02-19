package com.vjentrps.oms.controller;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.vjentrps.oms.exception.OmsServiceException;
import com.vjentrps.oms.model.BasicInfo;
import com.vjentrps.oms.model.ErrorsEnum;
import com.vjentrps.oms.model.Product;
import com.vjentrps.oms.model.ResponseDTO;

@RestController
@RequestMapping(value = "/service/product")
public class ProductRestController extends BaseRestController{
	
	private Logger log = LoggerFactory.getLogger(this.getClass());


	@RequestMapping(method = RequestMethod.GET)
	public ResponseDTO getProducts() {

		List<Product> products = new ArrayList<Product>();

		try {
			products = productService.getAllproducts();
		} catch (OmsServiceException e) {
			log.error("Error while getting products",e);
			return new ResponseDTO(commonUtil.processError(ErrorsEnum.TECHNICAL_EXCEPTION));
		} catch (Exception e) {
			log.error("Error while getting products",e);
			return new ResponseDTO(commonUtil.processError(ErrorsEnum.SERVICE_DOWN));
		}
		return new ResponseDTO(products);
	}
	
	@RequestMapping(value = "/{productId}", method = RequestMethod.GET)
	public Product getProduct(@PathVariable long productId) {

		Product product = null;
		try {
			product = productService.getProductById(productId);
		} catch (OmsServiceException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return product;
	}

	@RequestMapping(method = RequestMethod.POST)
	public ResponseDTO addproduct(@RequestBody Product product) {

		try {
			productService.addProduct(product);
		} catch (OmsServiceException e) {
			log.error("Error while adding a product",e);
			return new ResponseDTO(commonUtil.processError(ErrorsEnum.TECHNICAL_EXCEPTION));
		} catch (Exception e) {
			log.error("Error while adding a product",e);
			return new ResponseDTO(commonUtil.processError(ErrorsEnum.SERVICE_DOWN));
		}
		return new ResponseDTO();
	}

	@RequestMapping(method = RequestMethod.PUT)
	public ResponseDTO updateProduct(@RequestBody Product product) {

		try {
			productService.updateProduct(product);
		} catch (OmsServiceException e) {
			log.error("Error while updating a product",e);
			return new ResponseDTO(commonUtil.processError(ErrorsEnum.TECHNICAL_EXCEPTION));
		} catch (Exception e) {
			log.error("Error while updating a product",e);
			return new ResponseDTO(commonUtil.processError(ErrorsEnum.SERVICE_DOWN));
		}
		return new ResponseDTO();
	}

	@RequestMapping(value = "/{productId}", method = RequestMethod.DELETE)
	public ResponseDTO deleteProduct(@PathVariable int productId) {

		try {
			productService.deleteProduct(productId);
		} catch (OmsServiceException e) {
			log.error("Error while deleting a product",e);
			return new ResponseDTO(commonUtil.processError(ErrorsEnum.TECHNICAL_EXCEPTION));
		} catch (Exception e) {
			log.error("Error while deleting a product",e);
			return new ResponseDTO(commonUtil.processError(ErrorsEnum.SERVICE_DOWN));
		}

		return new ResponseDTO();
	}
	
	@RequestMapping(value="/count",method = RequestMethod.GET)
    public int getProductCount() {
    	
    	try {
			return productService.getProductCount();
		} catch (OmsServiceException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return 0;
 
           
    }
	
	@RequestMapping(value = "/basicInfo" , method = RequestMethod.GET)
	public ResponseDTO getProductInfoMap() {

		List<BasicInfo> products = new ArrayList<BasicInfo>();

		try {
			products = productService.getProductsBasicInfo();
		} catch (OmsServiceException e) {
			log.error("Error while getting product basicInfo",e);
			return new ResponseDTO(commonUtil.processError(ErrorsEnum.TECHNICAL_EXCEPTION));
		} catch (Exception e) {
			log.error("Error while getting product basicInfo",e);
			return new ResponseDTO(commonUtil.processError(ErrorsEnum.SERVICE_DOWN));
		}
		return new ResponseDTO(products);
	}

}