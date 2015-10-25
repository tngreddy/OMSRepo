package com.vjentrps.oms.dao;

import java.util.List;

import com.vjentrps.oms.exception.OmsServiceException;
import com.vjentrps.oms.model.Category;

public interface CategoryDao {
	
	 int addCategory(Category category) ;
		
	 int deleteCategory(long CategoryId);
	
	 int updateCategory(Category category);
	 
	 List<Category> fetchAllCategories() throws OmsServiceException;

	int getCategoryCount();

}
