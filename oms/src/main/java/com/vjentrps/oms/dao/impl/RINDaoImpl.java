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

import com.vjentrps.oms.dao.RINDao;
import com.vjentrps.oms.exception.OmsDataAccessException;
import com.vjentrps.oms.model.GoodsInwardNote;
import com.vjentrps.oms.model.ProdInfo;
import com.vjentrps.oms.model.Product;
import com.vjentrps.oms.model.ReturnedInwardNote;
import com.vjentrps.oms.util.CommonUtil;

@Repository
public class RINDaoImpl extends BaseDao implements RINDao {

	private Logger log = LoggerFactory.getLogger(this.getClass());

	@Value("${CREATE_RIN}")
	private String createRINQuery;

	@Value("${UPDATE_RIN}")
	private String updateRINQuery;

	@Value("${FETCH_RINS}")
	private String fetchAllRINSQuery;
	
	@Value("${FETCH_RIN_BY_NO}")
	private String fetchRINByNoQuery;

	@Value("${DELETE_RIN}")
	private String deleteRINQuery;
	
	@Value("${UPDATE_RIN_STATUS}")
	private String updateRINStatusQuery;
	
	@Value("${ADD_RIN_PROD_INFO}")
	private String addRinProdInfoQuery;
	
	@Value("${FETCH_RIN_PROD_INFO_LIST}")
	private String fetchGinProdInfoListQuery;
	
	
	
	
	private static class RINRowMapper implements RowMapper<ReturnedInwardNote> {

		public ReturnedInwardNote mapRow(ResultSet resultSet, int arg1)
				throws SQLException {

			ReturnedInwardNote rin = new ReturnedInwardNote();
			rin.setRinNo(resultSet.getString("rin_no"));
			rin.setRinDate(CommonUtil.formatFromSQLDate(resultSet.getString("rin_date")));
			rin.setFrom(resultSet.getString("_from"));
			rin.setFromName(resultSet.getString("from_name"));
			rin.setDocRefNo(resultSet.getString("doc_ref_no"));
			rin.setDocDate(CommonUtil.formatFromSQLDate(resultSet.getString("doc_date")));
			rin.setStatus(resultSet.getString("status"));
			
			return rin;
		}

	}
	
	private static class ProdInfoRowMapper implements RowMapper<ProdInfo> {

		public ProdInfo mapRow(ResultSet resultSet, int arg1)
				throws SQLException {

			ProdInfo prodInfo = new ProdInfo();
			Product product = new Product();
			product.setProductId(resultSet.getLong("product_id"));
			product.setProductName(resultSet.getString("product_name"));
			prodInfo.setProduct(product);
			prodInfo.setGoodIn(resultSet.getLong("good_in"));
			prodInfo.setDefIn(resultSet.getLong("def_in"));
			prodInfo.setTotalQty(resultSet.getLong("total_qty"));
			prodInfo.setUnitBasicRate(resultSet.getLong("unit_basic_rate"));
			prodInfo.setTotalAmount(resultSet.getLong("total_amount"));
			return prodInfo;
		}

	}
	

	@Override
	public int createRIN(final ReturnedInwardNote rin) throws OmsDataAccessException {
		
		/*KeyHolder keyHolder = new GeneratedKeyHolder();
		try {
		jdbcTemplate.update(
		    new PreparedStatementCreator() {
		        public PreparedStatement createPreparedStatement(Connection connection) throws SQLException {
		            PreparedStatement ps =
		                connection.prepareStatement(createRINQuery,Statement.RETURN_GENERATED_KEYS);
		            ps.setString(1, rin.getRinNo());
		            ps.setString(2, rin.getFrom());
		            ps.setString(3, rin.getFromName());
		            ps.setString(4, rin.getDocRefNo());
		            ps.setString(5, rin.getDocDate());
		            ps.setLong(6, rin.getProduct().getProductId());
		            ps.setLong(7,  rin.getGoodIn());
		            ps.setLong(8, rin.getDefectiveIn());
		            ps.setString(9, rin.getStatus());
		            return ps;
		        }
		    },
		    keyHolder);
		} catch (DataAccessException dae) {

		}
		return String.valueOf(keyHolder.getKey());*/
		int success = 0;
		
		try {
			success = jdbcTemplate.update(createRINQuery,
					new Object[] { rin.getRinNo(), rin.getFrom(), rin.getFromName(), rin.getDocRefNo(), rin.getDocDate(), rin.getStatus(), rin.getRemarks() });
		} catch (DataAccessException dae) {
			throw new OmsDataAccessException(dae);
		}
		return success;
	}

	@Override
	public List<ReturnedInwardNote> fetchAllRINs() throws OmsDataAccessException {
		List<ReturnedInwardNote> rins = null;

		RINRowMapper resultSetExtractor = new RINRowMapper();
		try {
			rins = (List<ReturnedInwardNote>) jdbcTemplate.query(fetchAllRINSQuery,
					resultSetExtractor);
		} catch (DataAccessException dae) {
			throw new OmsDataAccessException(dae);
		}
		return rins;
	}

	@Override
	public void updateRIN(ReturnedInwardNote rin) {
		/*try {
			jdbcTemplate.update(updateRINQuery,
					new Object[] { rin.getProduct().getProductId(), rin.getGoodIn(), rin.getDefIn(), rin.getStatus() });
		} catch (DataAccessException dae) {

		}*/
		
	}

	@Override
	public void deleteRIN(String rinNo) {
		try {
			jdbcTemplate.update(deleteRINQuery,
					new Object[] { rinNo });
		} catch (DataAccessException dae) {

		}
		
	}

	@Override
	public void updateRINStatus(ReturnedInwardNote rin) {
		try {
			jdbcTemplate.update(deleteRINQuery,
					new Object[] { rin.getStatus(), rin.getRinNo() });
		} catch (DataAccessException dae) {

		}
		
	}

	@Override
	public void addRinProdInfo(String rinNo, ProdInfo prodInfo)
			throws OmsDataAccessException {
		try {
			 jdbcTemplate.update(addRinProdInfoQuery,
					new Object[] { rinNo, prodInfo.getProduct().getProductId(), prodInfo.getGoodIn(), prodInfo.getDefIn(), prodInfo.getTotalQty(), prodInfo.getUnitBasicRate(), prodInfo.getTotalAmount() });
		} catch (DataAccessException dae) {
			throw new OmsDataAccessException(dae);
		}
		
	}
	
	@Override
	public ReturnedInwardNote fetchRINByNo(String rinNo)
			throws OmsDataAccessException {
		ReturnedInwardNote rin = null;
		RINRowMapper resultSetExtractor = new RINRowMapper();
		try {
			rin = (ReturnedInwardNote) jdbcTemplate.queryForObject(fetchRINByNoQuery,
					new Object[] { rinNo}, 
					resultSetExtractor);
		} catch (EmptyResultDataAccessException e) {
		} catch (DataAccessException dae) {
			throw new OmsDataAccessException(dae);
		}
		return rin;
		
	}

	@Override
	public List<ProdInfo> getRINProdInfo(String rinNo)
			throws OmsDataAccessException {
		List<ProdInfo> prodInfos = null;

		ProdInfoRowMapper resultSetExtractor = new ProdInfoRowMapper();
		try {
			prodInfos = (List<ProdInfo>) jdbcTemplate.query(fetchGinProdInfoListQuery,new Object[] { rinNo }, resultSetExtractor);
		} catch (DataAccessException dae) {
			throw new OmsDataAccessException(dae);
		}
		return prodInfos;
	}
}
