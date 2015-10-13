package com.vjentrps.oms.service;

import java.util.List;

import com.vjentrps.oms.model.Category;

public interface CategoryService {
	
	 void addCategory(Category category) ;
	
	 void deleteCategory(long CategoryId);
	
	 void updateCategory(Category category);
	 
	 List<Category> listCategories();

	 int getCategoryCount();
	 
	
}
