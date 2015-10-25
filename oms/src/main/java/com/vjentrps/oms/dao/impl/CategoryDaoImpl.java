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
import com.vjentrps.oms.exception.OmsServiceException;
import com.vjentrps.oms.model.Category;

@Repository
public class CategoryDaoImpl extends BaseDao implements CategoryDao {

	private Logger log = LoggerFactory.getLogger(this.getClass());

	@Value("${ADD_CATEGORY}")
	private String addCategoryQuery;

	@Value("${UPDATE_CATEGORY}")
	private String updateCategoryQuery;

	@Value("${FETCH_CATEGORIES}")
	private String fetchAllCategoriesQuery;

	@Value("${DELETE_CATEGORY}")
	private String deleteCategoryQuery;

	@Value("${CATEGORY_COUNT}")
	private String categoryCountQuery;

	@Override
	public int addCategory(Category category) {
		int success = 0;
		try {
			return jdbcTemplate.update(addCategoryQuery,
					new Object[] { category.getCategoryName() });
		} catch (DataAccessException dae) {

		}
		return success;

	}

	@Override
	public int deleteCategory(long CategoryId) {
		int success = 0;
		try {
			success = jdbcTemplate.update(deleteCategoryQuery,
					new Object[] { CategoryId });
		} catch (DataAccessException dae) {

		}
		
		return success;

	}

	@Override
	public int updateCategory(Category category) {
		int success = 0;
		try {
			success = jdbcTemplate.update(
					updateCategoryQuery,
					new Object[] { category.getCategoryName(),
							category.getCategoryId() });
		} catch (DataAccessException dae) {

		}
		return success;

	}

	@Override
	public List<Category> fetchAllCategories() throws OmsServiceException {

		List<Category> categories = null;

		CategoryRowMapper resultSetExtractor = new CategoryRowMapper();
		try {
			categories = (List<Category>) jdbcTemplate.query(fetchAllCategoriesQuery,
					resultSetExtractor);
		} catch (EmptyResultDataAccessException emptyDataAccessException) {
			// log.debug();

		} catch (DataAccessException dataAccessException) {
			throw new OmsServiceException(dataAccessException);
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

	}

	@Override
	public int getCategoryCount() {
		int count = 0;
		try {
			count = jdbcTemplate.queryForObject(categoryCountQuery, Integer.class);
		} catch (DataAccessException dae) {

		}
		return count;
	}
}
