package com.vjentrps.oms.dao.impl;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import com.vjentrps.oms.dao.StockDao;
import com.vjentrps.oms.exception.OmsDataAccessException;
import com.vjentrps.oms.model.Category;
import com.vjentrps.oms.model.Product;
import com.vjentrps.oms.model.ProductStock;
import com.vjentrps.oms.model.StockRecord;

@Repository
public class StockDaoImpl extends BaseDao implements StockDao {

	private Logger log = LoggerFactory.getLogger(this.getClass());

	@Value("${ADD_STOCK_RECORD}")
	private String addStockRecordQuery;
	
	@Value("${ADD_PRODUCT_STOCK}")
	private String addProductStockQuery;
	
	@Value("${GET_PRODUCT_STOCK}")
	private String getProductStockQuery;
	
	@Value("${UPDATE_PRODUCT_STOCK}")
	private String updateProductStockQuery;
	
	@Value("${DELETE_PRODUCT_STOCK}")
	private String deleteProductStockQuery;
	
	@Value("${FETCH_ALL_PRODUCT_STOCK}")
	private String fetchAllProductStockQuery;
	
	@Value("${FETCH_ALL_STOCK_RECORDS}")
	private String fetchAllStockRecordsQuery;
	
	
	
	private static class PrdStockRowMapper implements RowMapper<ProductStock> {

		public ProductStock mapRow(ResultSet resultSet, int arg1)
				throws SQLException {
			ProductStock productStock = new ProductStock();
			Product product = new Product();
			product.setProductId(resultSet.getLong("product_id"));
			product.setGoodBalance(resultSet.getLong("good_balance"));
			product.setDefBalance(resultSet.getLong("def_balance"));
			productStock.setProduct(product);
			return productStock;
		}

	}
	
	private static class ProductStockRowMapper implements RowMapper<ProductStock> {

		public ProductStock mapRow(ResultSet resultSet, int arg1)
				throws SQLException {
			ProductStock productStock = new ProductStock();
			Product product = new Product();
			productStock.setId(resultSet.getLong("id"));
			product.setGoodBalance(resultSet.getLong("good_balance"));
			product.setDefBalance(resultSet.getLong("def_balance"));
			productStock.setLastModified(resultSet.getString("last_modified"));
			
			product.setProductId(resultSet.getLong("product_id"));
			product.setProductName(resultSet.getString("product_name"));
			Category category = new Category();
			category.setCategoryId(resultSet.getLong("category_id"));
			category.setCategoryName(resultSet.getString("category_name"));
			product.setCategory(category);
			productStock.setProduct(product);

			return productStock;
		}

	}
	
	
	private static class StockRecordRowMapper implements RowMapper<StockRecord> {

		public StockRecord mapRow(ResultSet resultSet, int arg1)
				throws SQLException {
			StockRecord stockRecord = new StockRecord();
			stockRecord.setRecordId(resultSet.getLong("record_id"));
			stockRecord.setTransDocRef(resultSet.getString("trans_doc_ref"));
			stockRecord.setFromTo(resultSet.getString("fromTo"));
			stockRecord.setGoodIn(resultSet.getLong("good_in"));
			stockRecord.setGoodOut(resultSet.getLong("good_out"));
			stockRecord.setGoodBalance(resultSet.getLong("good_balance"));
			stockRecord.setDefIn(resultSet.getLong("def_in"));
			stockRecord.setDefOut(resultSet.getLong("def_out"));
			stockRecord.setDefBalance(resultSet.getLong("def_balance"));
			stockRecord.setCreatedDate(resultSet.getString("created_date"));
			
			Product product = new Product();
			product.setProductId(resultSet.getLong("product_id"));
			product.setProductName(resultSet.getString("product_name"));
			
			stockRecord.setProduct(product);
			return stockRecord;
		}

	}


	
	@Override
	public void addStockRecord(StockRecord stockRecord) throws OmsDataAccessException {
		try {
			jdbcTemplate.update(
					addStockRecordQuery,
					new Object[] { stockRecord.getTransDocRef(), stockRecord.getProduct().getProductId(), 
							stockRecord.getFromTo(), stockRecord.getGoodIn(),
							stockRecord.getGoodOut(),
							stockRecord.getGoodBalance(),
							stockRecord.getDefIn(), stockRecord.getDefOut(),
							stockRecord.getDefBalance() });
		} catch (DataAccessException dae) {
			throw new OmsDataAccessException(dae);
		}

	}


	@Override
	public void addProductStock(Product product) throws OmsDataAccessException {
		try {
			jdbcTemplate.update(
					addProductStockQuery,
					new Object[] { product.getProductId(), product.getGoodBalance(), product.getDefBalance() });
		} catch (DataAccessException dae) {
			throw new OmsDataAccessException(dae);
		}
		
	}

	@Override
	public void updateProductStock(ProductStock productStock) throws OmsDataAccessException {
		try {
			jdbcTemplate.update(
					updateProductStockQuery,
					new Object[] {productStock.getProduct().getGoodBalance(), productStock.getProduct().getDefBalance(), productStock.getProduct().getProductId()});
		} catch (DataAccessException dae) {
			throw new OmsDataAccessException(dae);
		}
	}


	@Override
	public void deleteProductStock(long productId) throws OmsDataAccessException {
		try {
			jdbcTemplate.update(deleteProductStockQuery,
					new Object[] { productId });
		} catch (DataAccessException dae) {
			throw new OmsDataAccessException(dae);
		}
		
	}


	@Override
	public ProductStock getProductStock(long productId) throws OmsDataAccessException {
		ProductStock productStock = null;

		PrdStockRowMapper resultSetExtractor = new PrdStockRowMapper();
		try {
			productStock = jdbcTemplate.queryForObject(getProductStockQuery,new Object[] { productId },
					resultSetExtractor);
		} catch (DataAccessException dae) {
			throw new OmsDataAccessException(dae);
		}
		return productStock;
		
	}


	@Override
	public List<ProductStock> getAllProductStock() throws OmsDataAccessException {
		List<ProductStock> productStocks = null;

		ProductStockRowMapper resultSetExtractor = new ProductStockRowMapper();
		try {
			productStocks = (List<ProductStock>) jdbcTemplate.query(fetchAllProductStockQuery,
					resultSetExtractor);
		} catch (DataAccessException dae) {
			throw new OmsDataAccessException(dae);
		}
		return productStocks;
	}


	@Override
	public List<StockRecord> getAllStockRecords() throws OmsDataAccessException {
		List<StockRecord> stockRecords = null;

		StockRecordRowMapper resultSetExtractor = new StockRecordRowMapper();
		try {
			stockRecords = (List<StockRecord>) jdbcTemplate.query(fetchAllStockRecordsQuery,
					resultSetExtractor);
		} catch (DataAccessException dae) {
			throw new OmsDataAccessException(dae);
		}
		return stockRecords;
	}
}
