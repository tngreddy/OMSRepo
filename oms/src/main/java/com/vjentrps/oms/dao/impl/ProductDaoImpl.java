package com.vjentrps.oms.dao.impl;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.dao.DataAccessException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import com.vjentrps.oms.dao.ProductDao;
import com.vjentrps.oms.model.Category;
import com.vjentrps.oms.model.Product;

@Repository
public class ProductDaoImpl extends BaseDao implements ProductDao {

	private Logger log = LoggerFactory.getLogger(this.getClass());

	@Value("${ADD_PRODUCT}")
	private String addProductQuery;

	@Value("${UPDATE_PRODUCT}")
	private String updateProductQuery;

	@Value("${FETCH_PRODUCTS}")
	private String fetchAllProductsQuery;
	
	@Value("${GET_PRODUCT}")
	private String getProductQuery;

	@Value("${DELETE_PRODUCT}")
	private String deleteProductQuery;
	
	@Value("${UPDATE_STOCK}")
	private String updateStockQuery;
	
	@Value("${PRODUCT_COUNT}")
	private String productCountQuery;

	
		
	private static class ProductRowMapper implements RowMapper<Product> {

		public Product mapRow(ResultSet resultSet, int arg1)
				throws SQLException {

			Product product = new Product();
			Category category = new Category();

			product.setProductId(resultSet.getLong("product_id"));
			product.setProductName(resultSet.getString("product_name"));
			product.setUnitOfMeasure(resultSet.getInt("unit_of_measure"));
			product.setUnitBasicRate(resultSet.getInt("unit_basic_rate"));
			product.setStock(resultSet.getLong("stock"));
			category.setCategoryId(resultSet.getLong("category_id"));
			category.setCategoryName(resultSet.getString("category_name"));
			product.setCategory(category);
			return product;
		}

	}

	@Override
	public void addProduct(Product product) {
		try {
			jdbcTemplate.update(addProductQuery,
					new Object[] { product.getProductName(), product.getCategory().getCategoryId(), product.getUnitOfMeasure(), product.getUnitBasicRate(), product.getStock() });
		} catch (DataAccessException dae) {

		}
		
	}

	

	@Override
	public void updateProduct(Product product) {
		try {
			jdbcTemplate.update(
					updateProductQuery,
					new Object[] { product.getProductName(), product.getCategory().getCategoryId(), product.getUnitOfMeasure(), product.getUnitBasicRate(), product.getStock(),
							 product.getProductId() });
		} catch (DataAccessException dae) {

		}
		
	}
	
	@Override
	public void deleteProduct(long productId) {
		try {
			jdbcTemplate.update(deleteProductQuery,
					new Object[] { productId });
		} catch (DataAccessException dae) {

		}
		
	}

	@Override
	public List<Product> fetchAllproducts() {
		
		String sql = fetchAllProductsQuery;
		List<Product> products = null;

		ProductRowMapper resultSetExtractor = new ProductRowMapper();
		try {
			products = (List<Product>) jdbcTemplate.query(sql,
					resultSetExtractor);
		} catch (EmptyResultDataAccessException emptyDataAccessException) {
			// log.debug();

		} catch (DataAccessException dataAccessException) {

		}
		return products;
	}

	@Override
	public void updateStock(long productId, long stock) {
		try {
			jdbcTemplate.update(updateStockQuery,
					new Object[] { stock, productId });
		} catch (DataAccessException dae) {

		}
		
	}

	@Override
	public Product getProductById(long productId) {
		
		Product product = null;

		ProductRowMapper resultSetExtractor = new ProductRowMapper();
		try {
			product = jdbcTemplate.queryForObject(getProductQuery,new Object[] { productId },
					resultSetExtractor);
		} catch (EmptyResultDataAccessException emptyDataAccessException) {
			// log.debug();

		} catch (DataAccessException dataAccessException) {

		}
		return product;
	}



	@Override
	public int getProductCount() {
		int count = 0;
		try {
			count = jdbcTemplate.queryForObject(productCountQuery, Integer.class);
		} catch (DataAccessException dae) {

		}
		return count;
	}
}
