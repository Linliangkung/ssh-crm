package com.jsako.crm.service.impl;

import java.util.List;

import org.apache.commons.io.filefilter.FalseFileFilter;
import org.hibernate.Transaction;
import org.hibernate.criterion.DetachedCriteria;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.jsako.crm.dao.CustomerDao;
import com.jsako.crm.domain.Customer;
import com.jsako.crm.service.CustomerService;
import com.jsako.crm.utils.PageBean;

public class CustomerServiceImpl implements CustomerService {
	private CustomerDao customerDao;

	public void setCustomerDao(CustomerDao customerDao) {
		this.customerDao = customerDao;
	}

	@Override
	@Transactional(isolation = Isolation.REPEATABLE_READ, propagation = Propagation.REQUIRED, readOnly = false)
	public void save(Customer customer) {
		customerDao.saveOrUpdate(customer);
	}


	@Override
	@Transactional(isolation = Isolation.READ_COMMITTED, propagation = Propagation.REQUIRED, readOnly = true)
	public PageBean getCustomerPageBean(DetachedCriteria criteria, Integer currentPage, Integer pageSize) {
		// 1.调用Dao查询总记录数
		Integer totalCount = customerDao.getTotalCount(criteria);
		// 2.创建PageBean
		PageBean customerPageBean = new PageBean(currentPage, totalCount, pageSize);
		
		System.out.println(customerPageBean.getCurrentPage());
		
		// 3.根据当前页数和当前页显示条数调用Dao查询客户列表
		List<Customer> customers = customerDao.getPageList(criteria, 
				customerPageBean.getStart(),
				customerPageBean.getPageSize());
		// 4.给PageBean设置客户列表
		customerPageBean.setList(customers);
		// 5.返回PageBean
		return customerPageBean;
	}

	@Override
	@Transactional(isolation = Isolation.READ_COMMITTED, propagation = Propagation.REQUIRED, readOnly = true)
	public Customer getById(Long cust_id) {
		return customerDao.getById(cust_id);
	}

	@Override
	@Transactional(isolation = Isolation.READ_COMMITTED, propagation = Propagation.REQUIRED, readOnly = true)
	public List<Object[]> getIndustryCount() {
		return customerDao.getIndustryCount();
	}

	@Override
	public List<Object[]> getSourceCount() {
		return customerDao.getSourceCount();
	}

}
