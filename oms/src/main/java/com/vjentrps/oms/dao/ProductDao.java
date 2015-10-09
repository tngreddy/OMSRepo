package com.vjentrps.oms.dao;

import java.util.List;

import com.vjentrps.oms.model.Product;

public interface ProductDao {
	
	 void addProduct(Product product) ;
		
	 void deleteProduct(long productId);
	
	 void updateProduct(Product product);
	 
	 List<Product> fetchAllproducts();
	 
	 void updateStock(long productId, long stock);

}
