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

import com.vjentrps.oms.dao.CategoryDao;
import com.vjentrps.oms.dao.RINDao;
import com.vjentrps.oms.model.ReturnedInwardNote;
import com.vjentrps.oms.model.Product;

@Repository
public class RINDaoImpl extends BaseDao implements RINDao {

	private Logger log = LoggerFactory.getLogger(this.getClass());

	@Value("${CREATE_RIN}")
	private String createRINQuery;

	@Value("${UPDATE_RIN}")
	private String updateRINQuery;

	@Value("${FETCH_RINS}")
	private String fetchAllRINSQuery;

	@Value("${DELETE_RIN}")
	private String deleteRINQuery;
	
	@Value("${UPDATE_RIN_STATUS}")
	private String updateRINStatusQuery;
	
	
	private static class RINRowMapper implements RowMapper<ReturnedInwardNote> {

		public ReturnedInwardNote mapRow(ResultSet resultSet, int arg1)
				throws SQLException {

			ReturnedInwardNote rin = new ReturnedInwardNote();
			rin.setRinNo(resultSet.getString("rin_no"));
			rin.setRinDate(resultSet.getString("rin_date"));
			rin.setFrom(resultSet.getString("_from"));
			rin.setFromName(resultSet.getString("from_name"));
			rin.setDocRefNo(resultSet.getString("doc_ref_no"));
			rin.setDocDate(resultSet.getString("doc_date"));
			rin.setGoodIn(resultSet.getInt("good_in"));
			rin.setDefIn(resultSet.getInt("def_in"));
			rin.setStatus(resultSet.getString("status"));
			Product product = new Product();
			product.setProductId(resultSet.getLong("product_id"));
			product.setProductName(resultSet.getString("product_name"));
			rin.setProduct(product);
			return rin;
		}

	}

	@Override
	public int createRIN(final ReturnedInwardNote rin) {
		
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
					new Object[] { rin.getRinNo(), rin.getFrom(), rin.getFromName(), rin.getDocRefNo(), rin.getDocDate(), rin.getProduct().getProductId(), rin.getGoodIn(), rin.getDefIn(), rin.getStatus() });
		} catch (DataAccessException dae) {

		}
		return success;
	}

	@Override
	public List<ReturnedInwardNote> fetchAllRINs() {
		List<ReturnedInwardNote> rins = null;

		RINRowMapper resultSetExtractor = new RINRowMapper();
		try {
			rins = (List<ReturnedInwardNote>) jdbcTemplate.query(fetchAllRINSQuery,
					resultSetExtractor);
		} catch (EmptyResultDataAccessException emptyDataAccessException) {
			// log.debug();

		} catch (DataAccessException dataAccessException) {

		}
		return rins;
	}

	@Override
	public void updateRIN(ReturnedInwardNote rin) {
		try {
			jdbcTemplate.update(updateRINQuery,
					new Object[] { rin.getProduct().getProductId(), rin.getGoodIn(), rin.getDefIn(), rin.getStatus() });
		} catch (DataAccessException dae) {

		}
		
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
}
