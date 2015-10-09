package com.vjentrps.oms.dao.impl;


import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

@Component
public class BaseDao {
	
	protected DataSource dataSource;
	
	protected JdbcTemplate jdbcTemplate;
	
	@Autowired
	public void setMysqlDataSource(DataSource dataSource) {
		this.jdbcTemplate = new JdbcTemplate(dataSource);
	}
	
	

}
