package com.vjentrps.oms.dao;

import java.util.List;

import com.vjentrps.oms.exception.OmsDataAccessException;
import com.vjentrps.oms.exception.OmsServiceException;
import com.vjentrps.oms.model.Category;

public interface CategoryDao {
	
	 int addCategory(Category category) throws OmsDataAccessException;
		
	 int deleteCategory(long CategoryId) throws OmsDataAccessException;
	
	 int updateCategory(Category category) throws OmsDataAccessException;
	 
	 List<Category> fetchAllCategories() throws OmsDataAccessException;

	int getCategoryCount() throws OmsDataAccessException;

}
