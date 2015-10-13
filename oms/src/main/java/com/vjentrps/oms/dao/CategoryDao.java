package com.vjentrps.oms.dao;

import java.util.List;

import com.vjentrps.oms.model.Category;

public interface CategoryDao {
	
	 void addCategory(Category category) ;
		
	 void deleteCategory(long CategoryId);
	
	 void updateCategory(Category category);
	 
	 List<Category> fetchAllCategories();

	int getCategoryCount();

}
