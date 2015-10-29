package com.vjentrps.oms.service;

import java.util.List;

import com.vjentrps.oms.exception.OmsServiceException;
import com.vjentrps.oms.model.Category;

public interface CategoryService {
	
	 int addCategory(Category category) throws OmsServiceException;
	
	 int deleteCategory(long CategoryId) throws OmsServiceException;
	
	 int updateCategory(Category category) throws OmsServiceException;
	 
	 List<Category> listCategories() throws OmsServiceException;

	 int getCategoryCount() throws OmsServiceException;
	 
	
}
