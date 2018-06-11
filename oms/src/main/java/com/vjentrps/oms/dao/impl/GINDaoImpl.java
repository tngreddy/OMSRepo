package com.vjentrps.oms.dao.impl;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.dao.DataAccessException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import com.vjentrps.oms.dao.GINDao;
import com.vjentrps.oms.exception.OmsDataAccessException;
import com.vjentrps.oms.model.GoodsInwardNote;
import com.vjentrps.oms.model.ProdInfo;
import com.vjentrps.oms.model.Product;
import com.vjentrps.oms.util.CommonUtil;

@Repository
public class GINDaoImpl extends BaseDao implements GINDao {

	private Logger log = LoggerFactory.getLogger(this.getClass());

	@Value("${CREATE_GIN}")
	private String createGINQuery;

	@Value("${UPDATE_GIN}")
	private String updateGINQuery;

	@Value("${FETCH_GINS}")
	private String fetchAllGINSQuery;
	
	@Value("${FETCH_GIN_BY_NO}")
	private String fetchGINByNoQuery;
	
	@Value("${FETCH_GIN_PROD_INFO_LIST}")
	private String fetchGinProdInfoListQuery;
	
	@Value("${DELETE_GIN}")
	private String deleteGINQuery;
	
	@Value("${UPDATE_GIN_STATUS}")
	private String updateGINStatusQuery;
	
	@Value("${ADD_GIN_PROD_INFO}")
	private String addGinProdInfoQuery;
	
	
	private static class GINRowMapper implements RowMapper<GoodsInwardNote> {

		public GoodsInwardNote mapRow(ResultSet resultSet, int arg1)
				throws SQLException {

			GoodsInwardNote gin = new GoodsInwardNote();
			gin.setGinNo(resultSet.getString("gin_no"));
			gin.setGinDate(CommonUtil.formatFromSQLDate(resultSet.getString("gin_date")));
			gin.setFrom(resultSet.getString("_from"));
			gin.setFromId(resultSet.getLong("from_id"));
			gin.setDocRefNo(resultSet.getString("doc_ref_no"));
			gin.setDocDate(CommonUtil.formatFromSQLDate(resultSet.getString("doc_date")));
			gin.setStatus(resultSet.getString("status"));
			gin.setRemarks(resultSet.getString("remarks"));
			
			return gin;
		}

	}
	
	private static class ProdInfoRowMapper implements RowMapper<ProdInfo> {

		public ProdInfo mapRow(ResultSet resultSet, int arg1)
				throws SQLException {

			ProdInfo prodInfo = new ProdInfo();
			Product product = new Product();
			product.setProductId(resultSet.getLong("product_id"));
			product.setProductName(resultSet.getString("product_name"));
			product.setUnitOfMeasure(resultSet.getString("unit_of_measure"));
			prodInfo.setProduct(product);
			prodInfo.setGoodIn(resultSet.getLong("good_in"));
			prodInfo.setDefIn(resultSet.getLong("def_in"));
			prodInfo.setTotalQty(resultSet.getLong("total_qty"));
			
			prodInfo.setUnitBasicRate(resultSet.getDouble("unit_basic_rate"));
			prodInfo.setTotalAmount(resultSet.getLong("total_amount"));
			return prodInfo;
		}

	}
	

	@Override
	public int createGIN(final GoodsInwardNote gin) throws OmsDataAccessException {
		
		int success = 0;
		
		try {
			success = jdbcTemplate.update(createGINQuery,
					new Object[] { gin.getGinNo(), gin.getFrom(), gin.getFromId(), gin.getDocRefNo(), gin.getDocDate(), gin.getStatus(), gin.getRemarks() });
		} catch (DataAccessException dae) {
			throw new OmsDataAccessException(dae);
		}
		return success;
	}

	@Override
	public List<GoodsInwardNote> fetchAllGINs() throws OmsDataAccessException {
		List<GoodsInwardNote> gins = null;

		GINRowMapper resultSetExtractor = new GINRowMapper();
		try {
			gins = (List<GoodsInwardNote>) jdbcTemplate.query(fetchAllGINSQuery,
					resultSetExtractor);
		} catch (DataAccessException dae) {
			throw new OmsDataAccessException(dae);
		}
		return gins;
	}

	@Override
	public void updateGIN(GoodsInwardNote gin) {
		try {
			jdbcTemplate.update(updateGINQuery,
					new Object[] { });
		} catch (DataAccessException dae) {

		}
		
	}

	@Override
	public void deleteGIN(long ginNo) {
		try {
			jdbcTemplate.update(deleteGINQuery,
					new Object[] { ginNo });
		} catch (DataAccessException dae) {

		}
		
	}

	@Override
	public void updateGINStatus(GoodsInwardNote gin) {
		try {
			jdbcTemplate.update(deleteGINQuery,
					new Object[] { gin.getStatus(), gin.getGinNo() });
		} catch (DataAccessException dae) {

		}
		
	}

	@Override
	public void addGinProdInfo(String ginNo, ProdInfo prodInfo) throws OmsDataAccessException {
		
		try {
			 jdbcTemplate.update(addGinProdInfoQuery,
					new Object[] { ginNo, prodInfo.getProduct().getProductId(), prodInfo.getGoodIn(), prodInfo.getDefIn(), prodInfo.getTotalQty(), prodInfo.getUnitBasicRate(), prodInfo.getTotalAmount() });
		} catch (DataAccessException dae) {
			throw new OmsDataAccessException(dae);
		}
		
	}

	@Override
	public GoodsInwardNote fetchGINByNo(String ginNo)
			throws OmsDataAccessException {
		GoodsInwardNote gin = null;
		GINRowMapper resultSetExtractor = new GINRowMapper();
		try {
			gin = (GoodsInwardNote) jdbcTemplate.queryForObject(fetchGINByNoQuery,
					new Object[] { ginNo}, 
					resultSetExtractor);
		} catch (EmptyResultDataAccessException e) {
		} catch (DataAccessException dae) {
			throw new OmsDataAccessException(dae);
		}
		return gin;
		
	}

	@Override
	public List<ProdInfo> getGINProdInfo(String ginNo)
			throws OmsDataAccessException {
		List<ProdInfo> prodInfos = null;

		ProdInfoRowMapper resultSetExtractor = new ProdInfoRowMapper();
		try {
			prodInfos = (List<ProdInfo>) jdbcTemplate.query(fetchGinProdInfoListQuery,new Object[] { ginNo }, resultSetExtractor);
		} catch (DataAccessException dae) {
			throw new OmsDataAccessException(dae);
		}
		return prodInfos;
	}
}
