package com.vjentrps.oms.service.impl;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.vjentrps.oms.dao.ProductDao;
import com.vjentrps.oms.model.Product;
import com.vjentrps.oms.service.ProductService;

@Service
@Transactional
public class ProductServiceImpl  implements ProductService{

	private Logger log = LoggerFactory.getLogger(this.getClass());
	
	@Autowired
	public ProductDao productDao;
	

	@Override
	public void addProduct(Product product) {
		productDao.addProduct(product);
		
	}

	@Override
	public void deleteProduct(long productId) {
		productDao.deleteProduct(productId);
		
	}

	@Override
	public void updateProduct(Product product) {
		productDao.updateProduct(product);
		
	}

	@Override
	public List<Product> getAllproducts() {
		return productDao.fetchAllproducts();
	}
	
	@Override
	public Product getProductById(long productId) {
		
		return productDao.getProductById(productId);
	}

	@Override
	public void updateStock(long productId, long stock) {
		productDao.updateStock(productId, stock);
		
	}

	@Override
	public int getProductCount() {
		return productDao.getProductCount();
	}

	

}
