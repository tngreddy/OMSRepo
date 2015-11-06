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
import com.vjentrps.oms.model.GoodsOutwardChallan;
import com.vjentrps.oms.model.Product;

@Repository
public class GOCDaoImpl extends BaseDao implements GOCDao {

	private Logger log = LoggerFactory.getLogger(this.getClass());

	@Value("${CREATE_GOC}")
	private String createGOCQuery;

	@Value("${UPDATE_GOC}")
	private String updateGOCQuery;

	@Value("${FETCH_GOCS}")
	private String fetchAllGOCSQuery;

	@Value("${DELETE_GOC}")
	private String deleteGOCQuery;
	
	@Value("${UPDATE_GOC_STATUS}")
	private String updateGOCStatusQuery;
	
	
	private static class GOCRowMapper implements RowMapper<GoodsOutwardChallan> {

		public GoodsOutwardChallan mapRow(ResultSet resultSet, int arg1)
				throws SQLException {

			GoodsOutwardChallan goc = new GoodsOutwardChallan();
			goc.setGocNo(resultSet.getString("goc_no"));
			goc.setGocDate(resultSet.getString("goc_date"));
			goc.setTo(resultSet.getString("_to"));
			goc.setToName(resultSet.getString("to_name"));
			goc.setDocRefNo(resultSet.getString("doc_ref_no"));
			goc.setDocDate(resultSet.getString("doc_date"));
			goc.setGoodOut(resultSet.getInt("good_out"));
			goc.setDefOut(resultSet.getInt("def_out"));
			goc.setStatus(resultSet.getString("status"));
			Product product = new Product();
			product.setProductId(resultSet.getLong("product_id"));
			product.setProductName(resultSet.getString("product_name"));
			goc.setProduct(product);
			return goc;
		}

	}

	@Override
	public int createGOC(final GoodsOutwardChallan goc) {
		
		int success = 0;
		
		try {
			success = jdbcTemplate.update(createGOCQuery,
					new Object[] { goc.getGocNo(), goc.getTo(), goc.getToName(), goc.getDocRefNo(), goc.getDocDate(), goc.getProduct().getProductId(), goc.getGoodOut(), goc.getDefOut(), goc.getStatus() });
		} catch (DataAccessException dae) {

		}
		return success;
	}

	@Override
	public List<GoodsOutwardChallan> fetchAllGOCs() {
		List<GoodsOutwardChallan> gocs = null;

		GOCRowMapper resultSetExtractor = new GOCRowMapper();
		try {
			gocs = (List<GoodsOutwardChallan>) jdbcTemplate.query(fetchAllGOCSQuery,
					resultSetExtractor);
		} catch (EmptyResultDataAccessException emptyDataAccessException) {
			// log.debug();

		} catch (DataAccessException dataAccessException) {

		}
		return gocs;
	}

	@Override
	public void updateGOC(GoodsOutwardChallan goc) {
		try {
			jdbcTemplate.update(updateGOCQuery,
					new Object[] { goc.getProduct().getProductId(), goc.getGoodOut(), goc.getDefOut(), goc.getStatus() });
		} catch (DataAccessException dae) {

		}
		
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
}
