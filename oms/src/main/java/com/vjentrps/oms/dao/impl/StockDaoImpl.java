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

import com.vjentrps.oms.dao.StockDao;
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
			productStock.setGoodBalance(resultSet.getLong("good_balance"));
			productStock.setDefBalance(resultSet.getLong("def_balance"));
			productStock.setProduct(product);
			return productStock;
		}

	}
	
	private static class ProductStockRowMapper implements RowMapper<ProductStock> {

		public ProductStock mapRow(ResultSet resultSet, int arg1)
				throws SQLException {
			ProductStock productStock = new ProductStock();
			productStock.setId(resultSet.getLong("id"));
			productStock.setGoodBalance(resultSet.getLong("good_balance"));
			productStock.setDefBalance(resultSet.getLong("def_balance"));
			productStock.setLastModified(resultSet.getString("last_modified"));
			Product product = new Product();
			product.setProductId(resultSet.getLong("product_id"));
			product.setProductName(resultSet.getString("product_name"));
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
			
			return stockRecord;
		}

	}


	
	@Override
	public void addStockRecord(StockRecord stockRecord) {
		try {
			jdbcTemplate.update(
					addStockRecordQuery,
					new Object[] { stockRecord.getTransDocRef(),
							stockRecord.getFromTo(), stockRecord.getGoodIn(),
							stockRecord.getGoodOut(),
							stockRecord.getGoodBalance(),
							stockRecord.getDefIn(), stockRecord.getDefOut(),
							stockRecord.getDefBalance() });
		} catch (DataAccessException dae) {

		}

	}


	@Override
	public void addProductStock(long product_id) {
		try {
			jdbcTemplate.update(
					addProductStockQuery,
					new Object[] { product_id });
		} catch (DataAccessException dae) {

		}
		
	}

	@Override
	public void updateProductStock(ProductStock productStock) {
		try {
			jdbcTemplate.update(
					updateProductStockQuery,
					new Object[] {productStock.getGoodBalance(), productStock.getDefBalance(), productStock.getProduct().getProductId()});
		} catch (DataAccessException dae) {

		}
	}


	@Override
	public void deleteProductStock(long productId) {
		try {
			jdbcTemplate.update(deleteProductStockQuery,
					new Object[] { productId });
		} catch (DataAccessException dae) {

		}
		
	}


	@Override
	public ProductStock getProductStock(long productId) {
		ProductStock productStock = null;

		PrdStockRowMapper resultSetExtractor = new PrdStockRowMapper();
		try {
			productStock = jdbcTemplate.queryForObject(getProductStockQuery,new Object[] { productId },
					resultSetExtractor);
		} catch (EmptyResultDataAccessException emptyDataAccessException) {
			// log.debug();

		} catch (DataAccessException dataAccessException) {

		}
		return productStock;
		
	}


	@Override
	public List<ProductStock> getAllProductStock() {
		List<ProductStock> productStocks = null;

		ProductStockRowMapper resultSetExtractor = new ProductStockRowMapper();
		try {
			productStocks = (List<ProductStock>) jdbcTemplate.query(fetchAllProductStockQuery,
					resultSetExtractor);
		} catch (EmptyResultDataAccessException emptyDataAccessException) {
			// log.debug();

		} catch (DataAccessException dataAccessException) {
				dataAccessException.printStackTrace();
		}
		return productStocks;
	}


	@Override
	public List<StockRecord> getAllStockRecords() {
		List<StockRecord> stockRecords = null;

		StockRecordRowMapper resultSetExtractor = new StockRecordRowMapper();
		try {
			stockRecords = (List<StockRecord>) jdbcTemplate.query(fetchAllStockRecordsQuery,
					resultSetExtractor);
		} catch (EmptyResultDataAccessException emptyDataAccessException) {
			// log.debug();

		} catch (DataAccessException dataAccessException) {

		}
		return stockRecords;
	}
}
