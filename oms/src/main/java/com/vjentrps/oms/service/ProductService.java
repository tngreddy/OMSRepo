package com.vjentrps.oms.service;

import java.util.List;

import com.vjentrps.oms.model.Category;
import com.vjentrps.oms.model.Product;

public interface ProductService {
	
	void addProduct(Product product) ;
	
	 void deleteProduct(long productId);
	
	 void updateProduct(Product product);
	 
	 List<Product> getAllproducts();
	 
	 void updateStock(long productId, long stock);
	 
	
}
