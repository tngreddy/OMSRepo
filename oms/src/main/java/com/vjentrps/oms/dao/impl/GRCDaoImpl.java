package com.vjentrps.oms.dao.impl;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.dao.DataAccessException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.security.access.method.P;
import org.springframework.stereotype.Repository;

import com.vjentrps.oms.dao.GRCDao;
import com.vjentrps.oms.exception.OmsDataAccessException;
import com.vjentrps.oms.model.GoodsReturnableChallan;
import com.vjentrps.oms.model.PendingGRC;
import com.vjentrps.oms.model.ProdInfo;
import com.vjentrps.oms.model.Product;
import com.vjentrps.oms.util.CommonUtil;

@Repository
public class GRCDaoImpl extends BaseDao implements GRCDao {

	private Logger log = LoggerFactory.getLogger(this.getClass());

	@Value("${CREATE_GRC}")
	private String createGRCQuery;

	@Value("${UPDATE_GRC}")
	private String updateGRCQuery;

	@Value("${FETCH_GRCS}")
	private String fetchAllGRCSQuery;
	
	@Value("${FETCH_GRC_BY_NO}")
	private String fetchGRCByNoQuery;

	@Value("${DELETE_GRC}")
	private String deleteGRCQuery;
	
	@Value("${UPDATE_GRC_STATUS}")
	private String updateGRCStatusQuery;
	
	@Value("${ADD_GRC_PROD_INFO}")
	private String addGrcProdInfoQuery;
	
	@Value("${FETCH_GRC_PROD_INFO}")
	private String fetchGrcProdInfoQuery;

	@Value("${FETCH_GRC_PROD_INFO_LIST}")
	private String fetchGrcProdInfoListQuery;
	
	@Value("${FETCH_GRC_NO_LIST}")
	private String fetchGRCNoListQuery;
	
	
	@Value("${UPDATE_GRC_PROD_INFO_STATUS}")
	private String updateGrcProdInfoStatusQuery;
	
	@Value("${ADD_GRC_PARTIAL_PENDING_PROD_INFO}")
	private String addGrcPartialPendingProdInfoQuery;
	
	@Value("${FETCH_GRC_PARTIAL_PENDING_PROD_INFO}")
	private String fetchGrcPartialPendingProdInfoQuery;
	
	@Value("${FETCH_GRC_PARTIAL_PENDING_PROD_INFO_LIST}")
	private String fetchGRCPartPendProdInfoListQuery;
	
	@Value("${FETCH_All_GRC_PARTIAL_PENDING_PROD_INFO_LIST}")
	private String fetchAllGRCPartPendProdInfoListQuery;
	
		
	@Value("${UPDATE_GRC_PARTIAL_PENDING_PROD_INFO}")
	private String updateGrcPartialPendingProdInfoQuery;
	
	
	
	
	
	
	private static class GRCRowMapper implements RowMapper<GoodsReturnableChallan> {

		public GoodsReturnableChallan mapRow(ResultSet resultSet, int arg1)
				throws SQLException {

			GoodsReturnableChallan grc = new GoodsReturnableChallan();
			grc.setGrcNo(resultSet.getString("grc_no"));
			grc.setGrcDate(CommonUtil.formatFromSQLDate(resultSet.getString("grc_date")));
			grc.setTo(resultSet.getString("_to"));
			grc.setToName(resultSet.getString("to_name"));
			grc.setDocRefNo(resultSet.getString("doc_ref_no"));
			grc.setDocDate(CommonUtil.formatFromSQLDate(resultSet.getString("doc_date")));
			grc.setStatus(resultSet.getString("status"));
			return grc;
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
			prodInfo.setGoodOut(resultSet.getLong("good_out"));
			prodInfo.setDefOut(resultSet.getLong("def_out"));
			prodInfo.setTotalQty(resultSet.getLong("total_qty"));
			prodInfo.setUnitBasicRate(resultSet.getLong("unit_basic_rate"));
			prodInfo.setTotalAmount(resultSet.getLong("total_amount"));
			prodInfo.setStatus(resultSet.getString("status"));
			return prodInfo;
		}

	}

	
	private static class PendingProdInfoRowMapper implements RowMapper<ProdInfo> {

		public ProdInfo mapRow(ResultSet resultSet, int arg1)
				throws SQLException {

			ProdInfo prodInfo = new ProdInfo();
			Product product = new Product();
			product.setProductId(resultSet.getLong("product_id"));
			product.setProductName(resultSet.getString("product_name"));
			prodInfo.setProduct(product);
			prodInfo.setGoodOut(resultSet.getLong("rem_good_out"));
			prodInfo.setDefOut(resultSet.getLong("rem_def_out"));
			prodInfo.setTotalQty(resultSet.getLong("rem_total_qty"));
			prodInfo.setUnitBasicRate(resultSet.getLong("unit_basic_rate"));
			prodInfo.setTotalAmount(resultSet.getLong("rem_total_amount"));
			prodInfo.setStatus(resultSet.getString("status"));
			return prodInfo;
		}

	}
	
	private static class PendingGRCRowMapper implements RowMapper<PendingGRC> {

		public PendingGRC mapRow(ResultSet resultSet, int arg1)
				throws SQLException {

			ProdInfo prodInfo = new ProdInfo();
			Product product = new Product();
			product.setProductId(resultSet.getLong("product_id"));
			product.setProductName(resultSet.getString("product_name"));
			prodInfo.setProduct(product);
			prodInfo.setGoodOut(resultSet.getInt("rem_good_out"));
			prodInfo.setDefOut(resultSet.getInt("rem_def_out"));
			prodInfo.setTotalQty(resultSet.getInt("rem_total_qty"));
			prodInfo.setUnitBasicRate(resultSet.getInt("unit_basic_rate"));
			prodInfo.setTotalAmount(resultSet.getInt("rem_total_amount"));
			prodInfo.setStatus(resultSet.getString("status"));
			PendingGRC pendingGRC = new PendingGRC();
			pendingGRC.setGrcNo(resultSet.getString("grc_no"));
			pendingGRC.setProdInfo(prodInfo);
			return pendingGRC;
		}

	}

	@Override
	public int createGRC(final GoodsReturnableChallan grc) throws OmsDataAccessException {
		
		int success = 0;
		
		try {
			success = jdbcTemplate.update(createGRCQuery,
					new Object[] { grc.getGrcNo(), grc.getTo(), grc.getToName(), grc.getDocRefNo(), grc.getDocDate(), grc.getStatus(), grc.getRemarks() });
		} catch (DataAccessException dae) {
			throw new OmsDataAccessException(dae);
		}
		return success;
	}

	@Override
	public List<GoodsReturnableChallan> fetchAllGRCs() throws OmsDataAccessException {
		List<GoodsReturnableChallan> grcs = null;

		GRCRowMapper resultSetExtractor = new GRCRowMapper();
		try {
			grcs = (List<GoodsReturnableChallan>) jdbcTemplate.query(fetchAllGRCSQuery,
					resultSetExtractor);
		} catch (DataAccessException dae) {
			throw new OmsDataAccessException(dae);
		}
		return grcs;
	}

	@Override
	public void updateGRC(GoodsReturnableChallan grc) {
		/*try {
			jdbcTemplate.update(updateGRCQuery,
					new Object[] { grc.getProduct().getProductId(), grc.getGoodOut(), grc.getDefOut(), grc.getStatus() });
		} catch (DataAccessException dae) {

		}*/
		
	}

	@Override
	public void deleteGRC(String grcNo) {
		try {
			jdbcTemplate.update(deleteGRCQuery,
					new Object[] { grcNo });
		} catch (DataAccessException dae) {

		}
		
	}

	@Override
	public void updateGRCStatus(GoodsReturnableChallan grc) {
		try {
			jdbcTemplate.update(updateGRCStatusQuery,
					new Object[] { grc.getStatus(), grc.getGrcNo() });
		} catch (DataAccessException dae) {

		}
		
	}

	@Override
	public void addGrcProdInfo(String grcNo, ProdInfo prodInfo) throws OmsDataAccessException {
		try {
			 jdbcTemplate.update(addGrcProdInfoQuery,
					new Object[] { grcNo, prodInfo.getProduct().getProductId(), prodInfo.getGoodOut(), prodInfo.getDefOut(), prodInfo.getTotalQty(), prodInfo.getUnitBasicRate(), prodInfo.getTotalAmount() });
		} catch (DataAccessException dae) {
			throw new OmsDataAccessException(dae);
		}
		
	}

	@Override
	public List<String> fetchGRCNoList(String toName) throws OmsDataAccessException {
		List<String> grcNoList = new ArrayList<String>();
		try {
			grcNoList =  jdbcTemplate.queryForList(fetchGRCNoListQuery, new Object[] { toName }, String.class);
		} catch (DataAccessException dae) {
			throw new OmsDataAccessException(dae);
		}
		return grcNoList;
		// TODO Auto-generated method stub
		
	}

	@Override
	public List<ProdInfo> getGRCProdInfo(String grcNo, String status) throws OmsDataAccessException {
		List<ProdInfo> prodInfos = null;
		
		StringBuffer sb = new StringBuffer(fetchGrcProdInfoListQuery);
		
		sb.replace(sb.lastIndexOf("?"), sb.lastIndexOf("?") + 1, "'"+status+"'");
	

		ProdInfoRowMapper resultSetExtractor = new ProdInfoRowMapper();
		try {
			prodInfos = (List<ProdInfo>) jdbcTemplate.query(sb.toString(), new Object[] { grcNo }, resultSetExtractor);
		} catch (DataAccessException dae) {
			throw new OmsDataAccessException(dae);
		}
		return prodInfos;
	}
	
	@Override
	public ProdInfo getGRCProdInfo(String grcNo, long productId) throws OmsDataAccessException {
		ProdInfo prodInfo = null;

		ProdInfoRowMapper resultSetExtractor = new ProdInfoRowMapper();
		try {
			prodInfo = (ProdInfo) jdbcTemplate.queryForObject(fetchGrcProdInfoQuery,
					new Object[] { grcNo, productId }, 
					resultSetExtractor);
		} catch (DataAccessException dae) {
			throw new OmsDataAccessException(dae);
		}
		return prodInfo;
	}

	@Override
	public void updateGrcProdInfoStatus(String grcNo, ProdInfo grcProdInfo) throws OmsDataAccessException {
		try {
			 jdbcTemplate.update(updateGrcProdInfoStatusQuery,
					new Object[] { grcProdInfo.getStatus(), grcNo, grcProdInfo.getProduct().getProductId() });
		} catch (DataAccessException dae) {
			throw new OmsDataAccessException(dae);
		}
		
	}

	@Override
	public ProdInfo getGrcPartialPendingProdInfo(String grcNo, long productId)
			throws OmsDataAccessException {
		ProdInfo prodInfo = null;

		PendingProdInfoRowMapper resultSetExtractor = new PendingProdInfoRowMapper();
		try {
			prodInfo =  jdbcTemplate.queryForObject(fetchGrcPartialPendingProdInfoQuery, 
					new Object[] { grcNo, productId},
					resultSetExtractor);
		}catch (EmptyResultDataAccessException edae) {
				
		} catch (DataAccessException dae) {
			throw new OmsDataAccessException(dae);
		}
		return prodInfo;
	}
	
	@Override
	public List<ProdInfo> getGrcPartialPendingProdInfo(String grcNo)
			throws OmsDataAccessException {
		List<ProdInfo> prodInfos = null;

		PendingProdInfoRowMapper resultSetExtractor = new PendingProdInfoRowMapper();
		try {
			prodInfos = (List<ProdInfo>) jdbcTemplate.query(fetchGRCPartPendProdInfoListQuery,new Object[] { grcNo }, resultSetExtractor);
		} catch (DataAccessException dae) {
			throw new OmsDataAccessException(dae);
		}
		return prodInfos;
	}
	
	@Override
	public List<PendingGRC> getAllGrcPartialPendingProdInfo(String status)
			throws OmsDataAccessException {
		List<PendingGRC> pendingGRCs = null;

		PendingGRCRowMapper resultSetExtractor = new PendingGRCRowMapper();
		try {
			pendingGRCs = (List<PendingGRC>) jdbcTemplate.query(fetchAllGRCPartPendProdInfoListQuery,new Object[] { status }, resultSetExtractor);
		} catch (DataAccessException dae) {
			throw new OmsDataAccessException(dae);
		}
		return pendingGRCs;
	}

	@Override
	public void addGrcPartialPendingProdInfo(String grcNo, ProdInfo parPendProdInfo) throws OmsDataAccessException {
		int success = 0;
		
		try {
			success = jdbcTemplate.update(addGrcPartialPendingProdInfoQuery,
					new Object[] { grcNo, parPendProdInfo.getProduct().getProductId(), parPendProdInfo.getGoodOut(), parPendProdInfo.getDefOut(), parPendProdInfo.getTotalQty(), parPendProdInfo.getUnitBasicRate(), parPendProdInfo.getTotalAmount() });
		} catch (DataAccessException dae) {
			throw new OmsDataAccessException(dae);
		}
		//return success;
		
	}

	@Override
	public void updateGrcPartialPendingProdInfo(String grcNo, ProdInfo parPendProdInfo) throws OmsDataAccessException {
		int success = 0;
		
		try {
			success = jdbcTemplate.update(updateGrcPartialPendingProdInfoQuery,
					new Object[] { parPendProdInfo.getGoodOut(), parPendProdInfo.getDefOut(), parPendProdInfo.getTotalQty(), parPendProdInfo.getUnitBasicRate(), parPendProdInfo.getTotalAmount(), parPendProdInfo.getStatus(), grcNo, parPendProdInfo.getProduct().getProductId() });
		} catch (DataAccessException dae) {
			throw new OmsDataAccessException(dae);
		}
		//return success;
		
	}

	
	@Override
	public GoodsReturnableChallan fetchGRCByNo(String grcNo)
			throws OmsDataAccessException {
		GoodsReturnableChallan grc = null;
		GRCRowMapper resultSetExtractor = new GRCRowMapper();
		try {
			grc = (GoodsReturnableChallan) jdbcTemplate.queryForObject(fetchGRCByNoQuery,
					new Object[] { grcNo}, 
					resultSetExtractor);
		} catch (EmptyResultDataAccessException e) {
		} catch (DataAccessException dae) {
			throw new OmsDataAccessException(dae);
		}
		return grc;
	}


	
}
