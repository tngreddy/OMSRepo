package com.vjentrps.oms.service;

import java.util.List;
import java.util.Map;

import com.vjentrps.oms.exception.OmsServiceException;
import com.vjentrps.oms.model.BasicInfo;
import com.vjentrps.oms.model.Category;
import com.vjentrps.oms.model.Product;

public interface ProductService {
	
	void addProduct(Product product) throws OmsServiceException;
	
	 void deleteProduct(long productId) throws OmsServiceException;
	
	 void updateProduct(Product product) throws OmsServiceException;
	 
	 List<Product> getAllproducts() throws OmsServiceException;
	 
	Product getProductById(long productId) throws OmsServiceException;

	int getProductCount() throws OmsServiceException;

	List<BasicInfo> getProductsBasicInfo() throws OmsServiceException;

	Map<String, Object> getCatProductsMap() throws OmsServiceException;
	 
	
}
