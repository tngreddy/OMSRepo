package com.vjentrps.oms.dao;

import java.util.List;

import com.vjentrps.oms.exception.OmsDataAccessException;
import com.vjentrps.oms.model.BasicInfo;
import com.vjentrps.oms.model.Product;

public interface ProductDao {
	
	 long addProduct(Product product) throws OmsDataAccessException ;
		
	 void deleteProduct(long productId) throws OmsDataAccessException;
	
	 void updateProduct(Product product) throws OmsDataAccessException;
	 
	 List<Product> fetchAllproducts() throws OmsDataAccessException;
	 
	Product getProductById(long productId) throws OmsDataAccessException;

	int getProductCount() throws OmsDataAccessException;

	List<BasicInfo> getProductsBasicInfo() throws OmsDataAccessException;

	List<BasicInfo> catProdInfo() throws OmsDataAccessException;

}
