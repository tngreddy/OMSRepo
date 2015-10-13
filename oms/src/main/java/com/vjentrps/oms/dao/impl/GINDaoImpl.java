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

import com.vjentrps.oms.dao.CategoryDao;
import com.vjentrps.oms.dao.GINDao;
import com.vjentrps.oms.model.Category;
import com.vjentrps.oms.model.GoodsInwardNote;

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
	
	
	
/*
	@Override
	public void addCategory(Category category) {

		try {
			jdbcTemplate.update(addCategoryQuery,
					new Object[] { category.getCategoryName() });
		} catch (DataAccessException dae) {

		}

	}

	@Override
	public void deleteCategory(long CategoryId) {
		try {
			jdbcTemplate.update(deleteCategoryQuery,
					new Object[] { CategoryId });
		} catch (DataAccessException dae) {

		}

	}

	@Override
	public void updateCategory(Category category) {
		try {
			jdbcTemplate.update(
					updateCategoryQuery,
					new Object[] { category.getCategoryName(),
							category.getCategoryId() });
		} catch (DataAccessException dae) {

		}

	}

	@Override
	public List<Category> fetchAllCategories() {

		Object[] args = {};
		String sql = fetchAllCategoriesQuery;
		List<Category> categories = null;

		CategoryRowMapper resultSetExtractor = new CategoryRowMapper();
		try {
			categories = (List<Category>) jdbcTemplate.query(sql,
					resultSetExtractor);
		} catch (EmptyResultDataAccessException emptyDataAccessException) {
			// log.debug();

		} catch (DataAccessException dataAccessException) {

		}
		return categories;

	}

	private static class CategoryRowMapper implements RowMapper<Category> {

		public Category mapRow(ResultSet resultSet, int arg1)
				throws SQLException {

			Category category = new Category();

			category.setCategoryId(resultSet.getInt("category_id"));
			category.setCategoryName(resultSet.getString("category_name"));

			return category;
		}

	}*/

	@Override
	public Object createGIN(GoodsInwardNote gin) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<GoodsInwardNote> fetchAllGINs() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void updateGIN(GoodsInwardNote gin) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void deleteGIN(long ginNo) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void updateGINStatus(long ginNo) {
		// TODO Auto-generated method stub
		
	}
}
