package com.vjentrps.oms.dao.impl;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import com.vjentrps.oms.dao.SupplierDao;
import com.vjentrps.oms.exception.OmsDataAccessException;
import com.vjentrps.oms.model.Address;
import com.vjentrps.oms.model.BasicInfo;
import com.vjentrps.oms.model.Contact;
import com.vjentrps.oms.model.Supplier;

@Repository
public class SupplierDaoImpl extends BaseDao implements SupplierDao {

	@Value("${ADD_SUPPLIER}")
	private String addSupplierQuery;

	@Value("${FETCH_SUPPLIERS}")
	private String fetchAllSuppliersQuery;
	
	@Value("${FETCH_SUPPLIERS_BASIC_INFO}")
	private String fetchSuppliersBasicInfoQuery;

	@Value("${GET_SUPPLIER}")
	private String getSupplierQuery;
	
	@Value("${GET_SUPPLIER_BY_NAME}")
	private String getSupplierByNameQuery;

	@Value("${UPDATE_SUPPLIER}")
	private String updateSupplierQuery;

	@Value("${DELETE_SUPPLIER}")
	private String deleteSupplierQuery;

	@Value("${GET_SUPPLIER_IDS}")
	private String getSupplierIdsQuery;
	
	@Value("${SUPPLIER_COUNT}")
	private String supplierCountQuery;

	@Override
	public void addSupplier(Supplier supplier) throws OmsDataAccessException {
		try {
			jdbcTemplate.update(addSupplierQuery,
					new Object[] { supplier.getName(), supplier.getGstNo(), supplier.getPanNo(), supplier.getContact().getContactId() });
		} catch (DataAccessException dae) {
			throw new OmsDataAccessException(dae);
		}

	}

	@Override
	public void deleteSupplier(long supplierId) throws OmsDataAccessException {
		try {
			jdbcTemplate.update(deleteSupplierQuery,
					new Object[] { supplierId });
		} catch (DataAccessException dae) {
			throw new OmsDataAccessException(dae);
		}

	}

	@Override
	public void updateSupplier(Supplier supplier) throws OmsDataAccessException {
		try {
			jdbcTemplate.update(updateSupplierQuery,
					new Object[] { supplier.getName(), supplier.getGstNo(), supplier.getPanNo(), supplier.getSupplierId()});
		} catch (DataAccessException dae) {
			throw new OmsDataAccessException(dae);
		}

	}

	@Override
	public List<Supplier> fetchAllSuppliers() throws OmsDataAccessException {
		String sql = fetchAllSuppliersQuery;
		List<Supplier> suppliers = null;

		SupplierRowMapper resultSetExtractor = new SupplierRowMapper();
		try {
			suppliers = (List<Supplier>) jdbcTemplate.query(sql,
					resultSetExtractor);
		} catch (DataAccessException dae) {
			throw new OmsDataAccessException(dae);
		}
		return suppliers;
	}


	@Override
	public Supplier getSupplierById(long supplierId) throws OmsDataAccessException {
		Supplier supplier = null;
		SupplierRowMapper resultSetExtractor = new SupplierRowMapper();
		try {
			supplier = (Supplier) jdbcTemplate.queryForObject(getSupplierQuery,
					new Object[] { supplierId }, resultSetExtractor);
		} catch (DataAccessException dae) {
			throw new OmsDataAccessException(dae);
		}
		return supplier;
	}
	
	@Override
	public Supplier getSupplierByName(String supplierName) throws OmsDataAccessException {
		Supplier supplier = null;
		SupplierRowMapper resultSetExtractor = new SupplierRowMapper();
		try {
			supplier = (Supplier) jdbcTemplate.queryForObject(getSupplierByNameQuery,
					new Object[] { supplierName }, resultSetExtractor);
		} catch (DataAccessException dae) {
			throw new OmsDataAccessException(dae);
		}
		return supplier;
	}


	@Override
	public Supplier getSupplierIds(long supplierId) throws OmsDataAccessException {
		Supplier supplier = null;
		SupplierIdsRowMapper resultSetExtractor = new SupplierIdsRowMapper();
		try {
			supplier = (Supplier) jdbcTemplate.queryForObject(getSupplierIdsQuery,
					new Object[] { supplierId }, resultSetExtractor);
		} catch (DataAccessException dae) {
			throw new OmsDataAccessException(dae);
		}
		return supplier;
	}

	
	@Override
	public int getSupplierCount() throws OmsDataAccessException{
		int count = 0;
		try {
			count = jdbcTemplate.queryForObject(supplierCountQuery, Integer.class);
		} catch (DataAccessException dae) {
			throw new OmsDataAccessException(dae);
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
			supplier.setName(resultSet.getString("supplier_name"));
			supplier.setGstNo(resultSet.getString("gst_no"));
			supplier.setPanNo(resultSet.getString("pan_no"));
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
	
	private static class SupplierBasicInfoRowMapper implements RowMapper<BasicInfo> {


		public BasicInfo mapRow(ResultSet resultSet, int arg1)
				throws SQLException {

			BasicInfo supplier = new BasicInfo();
			supplier.setId(resultSet.getLong("supplier_id"));
			supplier.setName(resultSet.getString("supplier_name"));

			return supplier;
		}

	}

	@Override
	public List<BasicInfo> getSuppliersBasicInfo() throws OmsDataAccessException {
		List<BasicInfo> suppliers = null;

		SupplierBasicInfoRowMapper resultSetExtractor = new SupplierBasicInfoRowMapper();
		try {
			suppliers = (List<BasicInfo>) jdbcTemplate.query(fetchSuppliersBasicInfoQuery,
					resultSetExtractor);
		} catch (DataAccessException dae) {
			throw new OmsDataAccessException(dae);
		}
		return suppliers;
	}


}
