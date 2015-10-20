package com.vjentrps.oms.service.impl;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.vjentrps.oms.dao.CategoryDao;
import com.vjentrps.oms.model.Category;
import com.vjentrps.oms.service.CategoryService;

@Service
@Transactional
public class CategoryServiceImpl  implements CategoryService{

	private Logger log = LoggerFactory.getLogger(this.getClass());
	
	@Autowired
	public CategoryDao categoryDao;
	
	@Override
	public int addCategory(Category category) {
		return categoryDao.addCategory(category);
		
	}

	@Override
	public int deleteCategory(long CategoryId) {
		return categoryDao.deleteCategory(CategoryId);
		
	}

	@Override
	public int updateCategory(Category category) {
		return categoryDao.updateCategory(category);
		
	}

	@Override
	public List<Category> listCategories() {
		return categoryDao.fetchAllCategories();
	}

	@Override
	public int getCategoryCount() {
		return categoryDao.getCategoryCount();
	}

}
