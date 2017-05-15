package com.jsako.crm.service;

import java.util.List;

import org.hibernate.criterion.DetachedCriteria;

import com.jsako.crm.domain.Customer;
import com.jsako.crm.utils.PageBean;

public interface CustomerService {

	void save(Customer customer);


	PageBean getCustomerPageBean(DetachedCriteria criteria, Integer currentPage, Integer pageSize);


	Customer getById(Long cust_id);

	List<Object[]> getIndustryCount();


	List<Object[]> getSourceCount();

}
