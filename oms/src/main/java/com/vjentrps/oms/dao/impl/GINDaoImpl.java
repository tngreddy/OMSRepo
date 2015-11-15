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
import com.vjentrps.oms.dao.GINDao;
import com.vjentrps.oms.exception.OmsDataAccessException;
import com.vjentrps.oms.model.GoodsInwardNote;
import com.vjentrps.oms.model.Product;

@Repository
public class GINDaoImpl extends BaseDao implements GINDao {

	private Logger log = LoggerFactory.getLogger(this.getClass());

	@Value("${CREATE_GIN}")
	private String createGINQuery;

	@Value("${UPDATE_GIN}")
	private String updateGINQuery;

	@Value("${FETCH_GINS}")
	private String fetchAllGINSQuery;

	@Value("${DELETE_GIN}")
	private String deleteGINQuery;
	
	@Value("${UPDATE_GIN_STATUS}")
	private String updateGINStatusQuery;
	
	
	private static class GINRowMapper implements RowMapper<GoodsInwardNote> {

		public GoodsInwardNote mapRow(ResultSet resultSet, int arg1)
				throws SQLException {

			GoodsInwardNote gin = new GoodsInwardNote();
			gin.setGinNo(resultSet.getString("gin_no"));
			gin.setGinDate(resultSet.getString("gin_date"));
			gin.setFrom(resultSet.getString("_from"));
			gin.setFromName(resultSet.getString("from_name"));
			gin.setDocRefNo(resultSet.getString("doc_ref_no"));
			gin.setDocDate(resultSet.getString("doc_date"));
			gin.setGoodIn(resultSet.getInt("good_in"));
			gin.setDefectiveIn(resultSet.getInt("def_in"));
			gin.setStatus(resultSet.getString("status"));
			Product product = new Product();
			product.setProductId(resultSet.getLong("product_id"));
			product.setProductName(resultSet.getString("product_name"));
			gin.setProduct(product);
			return gin;
		}

	}

	@Override
	public int createGIN(final GoodsInwardNote gin) throws OmsDataAccessException {
		
		/*KeyHolder keyHolder = new GeneratedKeyHolder();
		try {
		jdbcTemplate.update(
		    new PreparedStatementCreator() {
		        public PreparedStatement createPreparedStatement(Connection connection) throws SQLException {
		            PreparedStatement ps =
		                connection.prepareStatement(createGINQuery,Statement.RETURN_GENERATED_KEYS);
		            ps.setString(1, gin.getGinNo());
		            ps.setString(2, gin.getFrom());
		            ps.setString(3, gin.getFromName());
		            ps.setString(4, gin.getDocRefNo());
		            ps.setString(5, gin.getDocDate());
		            ps.setLong(6, gin.getProduct().getProductId());
		            ps.setLong(7,  gin.getGoodIn());
		            ps.setLong(8, gin.getDefectiveIn());
		            ps.setString(9, gin.getStatus());
		            return ps;
		        }
		    },
		    keyHolder);
		} catch (DataAccessException dae) {

		}
		return String.valueOf(keyHolder.getKey());*/
		int success = 0;
		
		try {
			success = jdbcTemplate.update(createGINQuery,
					new Object[] { gin.getGinNo(), gin.getFrom(), gin.getFromName(), gin.getDocRefNo(), gin.getDocDate(), gin.getProduct().getProductId(), gin.getGoodIn(), gin.getDefectiveIn(), gin.getStatus() });
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
					new Object[] { gin.getProduct().getProductId(), gin.getGoodIn(), gin.getDefectiveIn(), gin.getStatus() });
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
}
