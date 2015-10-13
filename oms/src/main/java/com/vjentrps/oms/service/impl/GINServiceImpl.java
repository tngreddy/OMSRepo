package com.vjentrps.oms.service.impl;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.vjentrps.oms.dao.CategoryDao;
import com.vjentrps.oms.dao.GINDao;
import com.vjentrps.oms.model.Category;
import com.vjentrps.oms.model.GoodsInwardNote;
import com.vjentrps.oms.service.CategoryService;
import com.vjentrps.oms.service.GINService;

@Service
@Transactional
public class GINServiceImpl  implements GINService{

	private Logger log = LoggerFactory.getLogger(this.getClass());
	
	@Autowired
	private GINDao ginDao;
	
		
	@Override
	public void createGIN(GoodsInwardNote gin) {
		 ginDao.createGIN(gin);
		
	}

	@Override
	public List<GoodsInwardNote> listGINs() {
		return ginDao.fetchAllGINs();
	}

	@Override
	public void updateGIN(GoodsInwardNote gin) {
		 ginDao.updateGIN(gin);
		
	}

	@Override
	public void deleteGIN(long ginNo) {
		 ginDao.deleteGIN(ginNo);
		
	}

	@Override
	public void updateGINStatus(long ginNo) {
		 ginDao.updateGINStatus(ginNo);
		
	}

}
