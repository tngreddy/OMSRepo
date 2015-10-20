package com.vjentrps.oms.service;

import java.util.List;

import com.vjentrps.oms.model.Category;

public interface CategoryService {
	
	 int addCategory(Category category) ;
	
	 int deleteCategory(long CategoryId);
	
	 int updateCategory(Category category);
	 
	 List<Category> listCategories();

	 int getCategoryCount();
	 
	
}
