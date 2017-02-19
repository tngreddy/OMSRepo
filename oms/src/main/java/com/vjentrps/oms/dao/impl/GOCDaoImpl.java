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

import com.vjentrps.oms.dao.GOCDao;
import com.vjentrps.oms.exception.OmsDataAccessException;
import com.vjentrps.oms.model.GoodsOutwardChallan;
import com.vjentrps.oms.model.ProdInfo;
import com.vjentrps.oms.model.Product;
import com.vjentrps.oms.util.CommonUtil;

@Repository
public class GOCDaoImpl extends BaseDao implements GOCDao {

	private Logger log = LoggerFactory.getLogger(this.getClass());

	@Value("${CREATE_GOC}")
	private String createGOCQuery;

	@Value("${UPDATE_GOC}")
	private String updateGOCQuery;

	@Value("${FETCH_GOCS}")
	private String fetchAllGOCSQuery;
	
	@Value("${FETCH_GOC_BY_NO}")
	private String fetchGOCByNoQuery;
	
	@Value("${DELETE_GOC}")
	private String deleteGOCQuery;
	
	@Value("${UPDATE_GOC_STATUS}")
	private String updateGOCStatusQuery;
	
	@Value("${ADD_GOC_PROD_INFO}")
	private String addGocProdInfoQuery;
	
	@Value("${FETCH_GOC_PROD_INFO_LIST}")
	private String fetchGOCProdInfoListQuery;
	
	
	
	
	
	
	private static class GOCRowMapper implements RowMapper<GoodsOutwardChallan> {

		public GoodsOutwardChallan mapRow(ResultSet resultSet, int arg1)
				throws SQLException {

			GoodsOutwardChallan goc = new GoodsOutwardChallan();
			goc.setGocNo(resultSet.getString("goc_no"));
			goc.setGocDate(CommonUtil.formatFromSQLDate(resultSet.getString("goc_date")));
			goc.setTo(resultSet.getString("_to"));
			goc.setToName(resultSet.getString("to_name"));
			goc.setDocRefNo(resultSet.getString("doc_ref_no"));
			goc.setDocDate(CommonUtil.formatFromSQLDate(resultSet.getString("doc_date")));
			goc.setStatus(resultSet.getString("status"));
			goc.setRemarks(resultSet.getString("remarks"));
			return goc;
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
			prodInfo.setGoodOut(resultSet.getLong("good_out"));
			prodInfo.setDefOut(resultSet.getLong("def_out"));
			prodInfo.setTotalQty(resultSet.getLong("total_qty"));
			prodInfo.setUnitBasicRate(resultSet.getDouble("unit_basic_rate"));
			prodInfo.setTotalAmount(resultSet.getLong("total_amount"));
			return prodInfo;
		}

	}
	

	@Override
	public int createGOC(final GoodsOutwardChallan goc) throws OmsDataAccessException {
		
		int success = 0;
		
		try {
			success = jdbcTemplate.update(createGOCQuery,
					new Object[] { goc.getGocNo(), goc.getTo(), goc.getToName(), goc.getDocRefNo(), goc.getDocDate(), goc.getStatus(), goc.getRemarks() });
		} catch (DataAccessException dae) {
			throw new OmsDataAccessException(dae);
		}
		return success;
	}

	@Override
	public List<GoodsOutwardChallan> fetchAllGOCs() throws OmsDataAccessException {
		List<GoodsOutwardChallan> gocs = null;

		GOCRowMapper resultSetExtractor = new GOCRowMapper();
		try {
			gocs = (List<GoodsOutwardChallan>) jdbcTemplate.query(fetchAllGOCSQuery,
					resultSetExtractor);
		} catch (DataAccessException dae) {
			throw new OmsDataAccessException(dae);
		}
		return gocs;
	}

	@Override
	public void updateGOC(GoodsOutwardChallan goc) {
		/*try {
			jdbcTemplate.update(updateGOCQuery,
					new Object[] { goc.getProduct().getProductId(), goc.getGoodOut(), goc.getDefOut(), goc.getStatus() });
		} catch (DataAccessException dae) {

		}*/
		
	}

	@Override
	public void deleteGOC(long gocNo) {
		try {
			jdbcTemplate.update(deleteGOCQuery,
					new Object[] { gocNo });
		} catch (DataAccessException dae) {

		}
		
	}

	@Override
	public void updateGOCStatus(GoodsOutwardChallan goc) {
		try {
			jdbcTemplate.update(deleteGOCQuery,
					new Object[] { goc.getStatus(), goc.getGocNo() });
		} catch (DataAccessException dae) {

		}
		
	}

	@Override
	public void addGocProdInfo(String gocNo, ProdInfo prodInfo) throws OmsDataAccessException {
		
		try {
				 jdbcTemplate.update(addGocProdInfoQuery,
						new Object[] { gocNo, prodInfo.getProduct().getProductId(), prodInfo.getGoodOut(), prodInfo.getDefOut(), prodInfo.getTotalQty(), prodInfo.getUnitBasicRate(), prodInfo.getTotalAmount() });
			} catch (DataAccessException dae) {
				throw new OmsDataAccessException(dae);
			}
				
	}

	@Override
	public GoodsOutwardChallan fetchGOCByNo(String gocNo)
			throws OmsDataAccessException {
		GoodsOutwardChallan goc = null;
		GOCRowMapper resultSetExtractor = new GOCRowMapper();
		try {
			goc = (GoodsOutwardChallan) jdbcTemplate.queryForObject(fetchGOCByNoQuery,
					new Object[] { gocNo}, 
					resultSetExtractor);
		} catch (EmptyResultDataAccessException e) {
		} catch (DataAccessException dae) {
			throw new OmsDataAccessException(dae);
		}
		return goc;
	}

	@Override
	public List<ProdInfo> getGOCProdInfo(String gocNo)
			throws OmsDataAccessException {
		List<ProdInfo> prodInfos = null;

		ProdInfoRowMapper resultSetExtractor = new ProdInfoRowMapper();
		try {
			prodInfos = (List<ProdInfo>) jdbcTemplate.query(fetchGOCProdInfoListQuery,new Object[] { gocNo }, resultSetExtractor);
		} catch (DataAccessException dae) {
			throw new OmsDataAccessException(dae);
		}
		return prodInfos;
	}
	}

	
