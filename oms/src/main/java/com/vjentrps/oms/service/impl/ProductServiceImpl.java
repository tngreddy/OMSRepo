package com.vjentrps.oms.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.collections.map.HashedMap;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.vjentrps.oms.dao.CategoryDao;
import com.vjentrps.oms.dao.ProductDao;
import com.vjentrps.oms.dao.StockDao;
import com.vjentrps.oms.exception.OmsDataAccessException;
import com.vjentrps.oms.exception.OmsServiceException;
import com.vjentrps.oms.model.BasicInfo;
import com.vjentrps.oms.model.Category;
import com.vjentrps.oms.model.Product;
import com.vjentrps.oms.service.ProductService;

@Service
@Transactional
public class ProductServiceImpl  implements ProductService{

	private Logger log = LoggerFactory.getLogger(this.getClass());

	@Autowired
	public ProductDao productDao;

	@Autowired
	public StockDao stockDao;

	@Autowired
	public CategoryDao categoryDao;


	@Override
	public void addProduct(Product product) throws OmsServiceException {

		long productId;
		try {
			productId = productDao.addProduct(product);
			if (productId > 0) {
				stockDao.addProductStock(productId);
			}
		} catch (OmsDataAccessException e) {
			throw new OmsServiceException(e);
		}

	}

	@Override
	public void deleteProduct(long productId) throws OmsServiceException {

		try {
			stockDao.deleteProductStock(productId);

			productDao.deleteProduct(productId);
		} catch (OmsDataAccessException e) {
			throw new OmsServiceException(e);
		}

	}

	@Override
	public void updateProduct(Product product) throws OmsServiceException {
		try {
			productDao.updateProduct(product);
		} catch (OmsDataAccessException e) {
			throw new OmsServiceException(e);
		}

	}

	@Override
	public List<Product> getAllproducts() throws OmsServiceException {
		try {
			return productDao.fetchAllproducts();
		} catch (OmsDataAccessException e) {
			throw new OmsServiceException(e);
		}
	}

	@Override
	public Product getProductById(long productId) throws OmsServiceException {

		try {
			return productDao.getProductById(productId);
		} catch (OmsDataAccessException e) {
			throw new OmsServiceException(e);
		}
	}


	@Override
	public int getProductCount() throws OmsServiceException {
		try {
			return productDao.getProductCount();
		} catch (OmsDataAccessException e) {
			throw new OmsServiceException(e);
		}
	}

	@Override
	public List<BasicInfo> getProductsBasicInfo() throws OmsServiceException {
		try {
			return productDao.getProductsBasicInfo();
		} catch (OmsDataAccessException e) {
			throw new OmsServiceException(e);
		}
	}

	@Override
	public Map<String, Object> getCatProductsMap() throws OmsServiceException {

		Map<String, Object> catProdInfoMap = new HashMap<String, Object>();
		try {

			List<Category> categories = categoryDao.fetchAllCategories();

			if(CollectionUtils.isNotEmpty(categories)) {
				List<BasicInfo> catProdInfoList = productDao.catProdInfo();

				if(CollectionUtils.isNotEmpty(catProdInfoList)) {

					for (Category category : categories) {
						
						List<String> productNames = getProductNameList(category.getCategoryId(),catProdInfoList);
						
						if (CollectionUtils.isNotEmpty(productNames)) {
							catProdInfoMap.put(category.getCategoryName(), productNames);
						}

					}

				}

			}
		} catch (OmsDataAccessException e) {
			throw new OmsServiceException(e);
		}
		return catProdInfoMap;
	}

	private List<String> getProductNameList(long categoryId, List<BasicInfo> catProdInfoList) {

		List<String> productNames =  new ArrayList<String>();

		for (BasicInfo catProdInfo: catProdInfoList) {
			if( categoryId == catProdInfo.getId()) {
				productNames.add(catProdInfo.getName());
			}
		}
		// TODO Auto-generated method stub
		return productNames;
	}



}
