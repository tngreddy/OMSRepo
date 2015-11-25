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

import com.vjentrps.oms.dao.GRCDao;
import com.vjentrps.oms.exception.OmsDataAccessException;
import com.vjentrps.oms.model.GoodsReturnableChallan;
import com.vjentrps.oms.model.ProdInfo;
import com.vjentrps.oms.model.Product;

@Repository
public class GRCDaoImpl extends BaseDao implements GRCDao {

	private Logger log = LoggerFactory.getLogger(this.getClass());

	@Value("${CREATE_GRC}")
	private String createGRCQuery;

	@Value("${UPDATE_GRC}")
	private String updateGRCQuery;

	@Value("${FETCH_GRCS}")
	private String fetchAllGRCSQuery;

	@Value("${DELETE_GRC}")
	private String deleteGRCQuery;
	
	@Value("${UPDATE_GRC_STATUS}")
	private String updateGRCStatusQuery;
	
	@Value("${ADD_GRC_PROD_INFO}")
	private String addGrcProdInfoQuery;
	
	
	
	
	private static class GRCRowMapper implements RowMapper<GoodsReturnableChallan> {

		public GoodsReturnableChallan mapRow(ResultSet resultSet, int arg1)
				throws SQLException {

			GoodsReturnableChallan grc = new GoodsReturnableChallan();
			grc.setGrcNo(resultSet.getString("grc_no"));
			grc.setGrcDate(resultSet.getString("grc_date"));
			grc.setTo(resultSet.getString("_to"));
			grc.setToName(resultSet.getString("to_name"));
			grc.setDocRefNo(resultSet.getString("doc_ref_no"));
			grc.setDocDate(resultSet.getString("doc_date"));
			//grc.setGoodOut(resultSet.getInt("good_out"));
			//grc.setDefOut(resultSet.getInt("def_out"));
			grc.setStatus(resultSet.getString("status"));
			Product product = new Product();
			product.setProductId(resultSet.getLong("product_id"));
			product.setProductName(resultSet.getString("product_name"));
		//	grc.setProduct(product);
			return grc;
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
			jdbcTemplate.update(deleteGRCQuery,
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
}
