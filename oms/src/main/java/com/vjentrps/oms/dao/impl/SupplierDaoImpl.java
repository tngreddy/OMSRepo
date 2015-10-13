package com.vjentrps.oms.dao.impl;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.dao.DataAccessException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import com.vjentrps.oms.dao.SupplierDao;
import com.vjentrps.oms.model.Address;
import com.vjentrps.oms.model.Contact;
import com.vjentrps.oms.model.Supplier;

@Repository
public class SupplierDaoImpl extends BaseDao implements SupplierDao {

	@Value("${ADD_SUPPLIER}")
	private String addSupplierQuery;

	@Value("${FETCH_SUPPLIERS}")
	private String fetchAllSuppliersQuery;

	@Value("${GET_SUPPLIER}")
	private String getSupplierQuery;

	@Value("${UPDATE_SUPPLIER}")
	private String updateSupplierQuery;

	@Value("${DELETE_SUPPLIER}")
	private String deleteSupplierQuery;

	@Value("${GET_SUPPLIER_IDS}")
	private String getSupplierIdsQuery;
	
	@Value("${SUPPLIER_COUNT}")
	private String supplierCountQuery;

	@Override
	public void addSupplier(Supplier supplier) {
		try {
			jdbcTemplate.update(addSupplierQuery,
					new Object[] { supplier.getSupplierName(), supplier.getTinNo(), supplier.getCstNo(), supplier.getContact().getContactId() });
		} catch (DataAccessException dae) {

		}

	}

	@Override
	public void deleteSupplier(long supplierId) {
		try {
			jdbcTemplate.update(deleteSupplierQuery,
					new Object[] { supplierId });
		} catch (DataAccessException dae) {

		}

	}

	@Override
	public void updateSupplier(Supplier supplier) {
		try {
			jdbcTemplate.update(updateSupplierQuery,
					new Object[] { supplier.getSupplierName(), supplier.getTinNo(), supplier.getCstNo(), supplier.getSupplierId()});
		} catch (DataAccessException dae) {

		}

	}

	@Override
	public List<Supplier> fetchAllSuppliers() {
		String sql = fetchAllSuppliersQuery;
		List<Supplier> suppliers = null;

		SupplierRowMapper resultSetExtractor = new SupplierRowMapper();
		try {
			suppliers = (List<Supplier>) jdbcTemplate.query(sql,
					resultSetExtractor);
		} catch (EmptyResultDataAccessException emptyDataAccessException) {
			// log.debug();

		} catch (DataAccessException dataAccessException) {

		}
		return suppliers;
	}


	@Override
	public Supplier getSupplierById(long supplierId) {
		Supplier supplier = null;
		SupplierRowMapper resultSetExtractor = new SupplierRowMapper();
		try {
			supplier = (Supplier) jdbcTemplate.queryForObject(getSupplierQuery,
					new Object[] { supplierId }, resultSetExtractor);
		} catch (DataAccessException dae) {

		}
		return supplier;
	}

	@Override
	public Supplier getSupplierIds(long supplierId) {
		Supplier supplier = null;
		SupplierIdsRowMapper resultSetExtractor = new SupplierIdsRowMapper();
		try {
			supplier = (Supplier) jdbcTemplate.queryForObject(getSupplierIdsQuery,
					new Object[] { supplierId }, resultSetExtractor);
		} catch (DataAccessException dae) {

		}
		return supplier;
	}

	
	@Override
	public int getSupplierCount() {
		int count = 0;
		try {
			count = jdbcTemplate.queryForObject(supplierCountQuery, Integer.class);
		} catch (DataAccessException dae) {

		}
		return count;
	}


	private static class SupplierRowMapper implements RowMapper<Supplier> {


		public Supplier mapRow(ResultSet resultSet, int arg1)
				throws SQLException {

			Supplier supplier = new Supplier();
			Contact contact = new Contact();
			Address address =  new Address();
			address.setAddressId(resultSet.getLong("address_id"));
			address.setAddressLine1(resultSet.getString("address1"));
			address.setAddressLine2(resultSet.getString("address2"));
			address.setCity(resultSet.getString("city"));
			address.setState(resultSet.getString("state"));
			address.setPinCode(resultSet.getInt("pincode"));
			contact.setAddress(address);
			contact.setContactId(resultSet.getLong("contact_id"));
			contact.setContactPerson(resultSet.getString("contact_person"));
			contact.setContactDesignation(resultSet.getString("contact_desgn"));
			contact.setMobileNo(resultSet.getString("mobile"));
			contact.setPhoneNo(resultSet.getString("phone"));
			supplier.setContact(contact);
			supplier.setSupplierId(resultSet.getLong("supplier_id"));
			supplier.setSupplierName(resultSet.getString("supplier_name"));
			supplier.setTinNo(resultSet.getString("tin_no"));
			supplier.setCstNo(resultSet.getString("cst_no"));
			return supplier;
		}

	}

	private static class SupplierIdsRowMapper implements RowMapper<Supplier> {


		public Supplier mapRow(ResultSet resultSet, int arg1)
				throws SQLException {

			Supplier supplier = new Supplier();
			Contact contact = new Contact();
			Address address =  new Address();
			address.setAddressId(resultSet.getLong("address_id"));
			contact.setAddress(address);
			contact.setContactId(resultSet.getLong("contact_id"));
			supplier.setContact(contact);
			return supplier;
		}

	}


}
