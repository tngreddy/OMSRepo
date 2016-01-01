package com.vjentrps.oms.dao.impl;


import java.sql.ResultSet;
import java.sql.SQLException;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;

import com.vjentrps.oms.model.ProdInfo;
import com.vjentrps.oms.model.Product;

@Component
public class BaseDao {
	
	protected DataSource dataSource;
	
	protected JdbcTemplate jdbcTemplate;
	
	@Autowired
	public void setMysqlDataSource(DataSource dataSource) {
		this.jdbcTemplate = new JdbcTemplate(dataSource);
	}
	
	


}
