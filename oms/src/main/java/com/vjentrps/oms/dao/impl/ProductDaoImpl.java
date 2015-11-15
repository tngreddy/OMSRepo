package com.vjentrps.oms.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.dao.DataAccessException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import com.vjentrps.oms.dao.ProductDao;
import com.vjentrps.oms.exception.OmsDataAccessException;
import com.vjentrps.oms.model.BasicInfo;
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
	
	@Value("${FETCH_PRODUCTS_BASIC_INFO}")
	private String fetchProductsBasicInfoQuery;
	
	
	
	@Value("${FETCH_CAT_PROD_BASIC_INFO}")
	private String fetchCatProdInfoQuery;
	
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
			product.setUnitOfMeasure(resultSet.getString("unit_of_measure"));
			product.setUnitBasicRate(resultSet.getInt("unit_basic_rate"));
			category.setCategoryId(resultSet.getLong("category_id"));
			category.setCategoryName(resultSet.getString("category_name"));
			product.setCategory(category);
			return product;
		}

	}
	
	private static class ProductBasicInfoRowMapper implements RowMapper<BasicInfo> {

		public BasicInfo mapRow(ResultSet resultSet, int arg1)
				throws SQLException {
			BasicInfo product = new BasicInfo();
			product.setId(resultSet.getLong("product_id"));
			product.setName(resultSet.getString("product_name"));
			return product;
		}

	}

	
	private static class CatProdInfoRowMapper implements RowMapper<BasicInfo> {

		public BasicInfo mapRow(ResultSet resultSet, int arg1)
				throws SQLException {
			BasicInfo product = new BasicInfo();
			product.setId(resultSet.getLong("category_id"));
			product.setName(resultSet.getString("product_name"));
			return product;
		}

	}

	@Override
	public long addProduct(final Product product) throws OmsDataAccessException {
		
		
		KeyHolder keyHolder = new GeneratedKeyHolder();
		try {
		jdbcTemplate.update(
		    new PreparedStatementCreator() {
		        public PreparedStatement createPreparedStatement(Connection connection) throws SQLException {
		            PreparedStatement ps =
		                connection.prepareStatement(addProductQuery,Statement.RETURN_GENERATED_KEYS);
		            ps.setString(1, product.getProductName());
		            ps.setLong(2, product.getCategory().getCategoryId());
		            ps.setString(3, product.getUnitOfMeasure());
		            ps.setLong(4, product.getUnitBasicRate());
		            return ps;
		        }
		    },
		    keyHolder);
		} catch (DataAccessException dae) {
			throw new OmsDataAccessException(dae);
		}
		return (Long) keyHolder.getKey();
				
	}

	

	@Override
	public void updateProduct(Product product) throws OmsDataAccessException{
		try {
			jdbcTemplate.update(
					updateProductQuery,
					new Object[] { product.getProductName(), product.getCategory().getCategoryId(), product.getUnitOfMeasure(), product.getUnitBasicRate(),
							 product.getProductId() });
		} catch (DataAccessException dae) {
			throw new OmsDataAccessException(dae);
		}
		
	}
	
	@Override
	public void deleteProduct(long productId) throws OmsDataAccessException{
		try {
			jdbcTemplate.update(deleteProductQuery,
					new Object[] { productId });
		} catch (DataAccessException dae) {
			throw new OmsDataAccessException(dae);
		}
		
	}

	@Override
	public List<Product> fetchAllproducts() throws OmsDataAccessException{
		
		String sql = fetchAllProductsQuery;
		List<Product> products = null;

		ProductRowMapper resultSetExtractor = new ProductRowMapper();
		try {
			products = (List<Product>) jdbcTemplate.query(sql,
					resultSetExtractor);
		} catch (DataAccessException dae) {
			throw new OmsDataAccessException(dae);
		}
		return products;
	}


	@Override
	public Product getProductById(long productId) throws OmsDataAccessException{
		
		Product product = null;

		ProductRowMapper resultSetExtractor = new ProductRowMapper();
		try {
			product = jdbcTemplate.queryForObject(getProductQuery,new Object[] { productId },
					resultSetExtractor);
		}  catch (DataAccessException dae) {
			throw new OmsDataAccessException(dae);
		}
		return product;
	}



	@Override
	public int getProductCount() throws OmsDataAccessException{
		int count = 0;
		try {
			count = jdbcTemplate.queryForObject(productCountQuery, Integer.class);
		} catch (DataAccessException dae) {
			throw new OmsDataAccessException(dae);
		}
		return count;
	}



	@Override
	public List<BasicInfo> getProductsBasicInfo() throws OmsDataAccessException {
		List<BasicInfo> products = null;

		ProductBasicInfoRowMapper resultSetExtractor = new ProductBasicInfoRowMapper();
		try {
			products = (List<BasicInfo>) jdbcTemplate.query(fetchProductsBasicInfoQuery,
					resultSetExtractor);
		}  catch (DataAccessException dae) {
			throw new OmsDataAccessException(dae);
		}
		return products;
	}
	
	
	@Override
	public List<BasicInfo> catProdInfo() throws OmsDataAccessException {
		List<BasicInfo> products = null;

		CatProdInfoRowMapper resultSetExtractor = new CatProdInfoRowMapper();
		try {
			products = (List<BasicInfo>) jdbcTemplate.query(fetchCatProdInfoQuery,
					resultSetExtractor);
		}  catch (DataAccessException dae) {
			throw new OmsDataAccessException(dae);
		}
		return products;
	}
}

