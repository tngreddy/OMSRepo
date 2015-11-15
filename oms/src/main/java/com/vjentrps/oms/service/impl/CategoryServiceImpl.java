package com.vjentrps.oms.service.impl;

import java.util.List;
import java.util.Map;

import org.apache.commons.collections.CollectionUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.vjentrps.oms.dao.CategoryDao;
import com.vjentrps.oms.exception.OmsDataAccessException;
import com.vjentrps.oms.exception.OmsServiceException;
import com.vjentrps.oms.model.BasicInfo;
import com.vjentrps.oms.model.Category;
import com.vjentrps.oms.service.CategoryService;

@Service
@Transactional
public class CategoryServiceImpl  implements CategoryService{

	private Logger log = LoggerFactory.getLogger(this.getClass());
	
	@Autowired
	public CategoryDao categoryDao;
	
	@Override
	public int addCategory(Category category) throws OmsServiceException {
		try {
			return categoryDao.addCategory(category);
		} catch (OmsDataAccessException e) {
			throw new OmsServiceException(e);
		}
		
	}

	@Override
	public int deleteCategory(long CategoryId) throws OmsServiceException {
		try {
			return categoryDao.deleteCategory(CategoryId);
		} catch (OmsDataAccessException e) {
			throw new OmsServiceException(e);
		}
		
	}

	@Override
	public int updateCategory(Category category) throws OmsServiceException{
		try {
			return categoryDao.updateCategory(category);
		} catch (OmsDataAccessException e) {
			throw new OmsServiceException(e);
		}
		
	}

	@Override
	public List<Category> listCategories() throws OmsServiceException {
		try {
			return categoryDao.fetchAllCategories();
		} catch (OmsDataAccessException e) {
			throw new OmsServiceException(e);
		}
	}

	@Override
	public int getCategoryCount() throws OmsServiceException {
		try {
			return categoryDao.getCategoryCount();
		} catch (OmsDataAccessException e) {
			throw new OmsServiceException(e);
		}
	}

	

}
