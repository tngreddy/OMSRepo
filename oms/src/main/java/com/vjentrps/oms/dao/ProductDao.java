package com.vjentrps.oms.dao;

import java.util.List;

import com.vjentrps.oms.model.BasicInfo;
import com.vjentrps.oms.model.Product;

public interface ProductDao {
	
	 long addProduct(Product product) ;
		
	 void deleteProduct(long productId);
	
	 void updateProduct(Product product);
	 
	 List<Product> fetchAllproducts();
	 
	Product getProductById(long productId);

	int getProductCount();

	List<BasicInfo> getProductsBasicInfo();

}
