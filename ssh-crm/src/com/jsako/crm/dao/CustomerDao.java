package com.jsako.crm.dao;

import java.util.List;

import org.hibernate.criterion.DetachedCriteria;

import com.jsako.crm.domain.Customer;

public interface CustomerDao extends BaseDao<Customer>{

	List<Object[]> getIndustryCount();

	List<Object[]> getSourceCount();
	
}
