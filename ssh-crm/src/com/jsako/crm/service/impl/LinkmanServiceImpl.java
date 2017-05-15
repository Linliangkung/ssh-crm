package com.jsako.crm.service.impl;

import java.util.List;

import org.hibernate.criterion.DetachedCriteria;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.jsako.crm.dao.CustomerDao;
import com.jsako.crm.dao.LinkmanDao;
import com.jsako.crm.domain.Customer;
import com.jsako.crm.domain.LinkMan;
import com.jsako.crm.service.LinkmanService;
import com.jsako.crm.utils.PageBean;

public class LinkmanServiceImpl implements LinkmanService {
	private CustomerDao customerDao;
	private LinkmanDao linkmanDao;
	
	
	public void setCustomerDao(CustomerDao customerDao) {
		this.customerDao = customerDao;
	}


	public void setLinkmanDao(LinkmanDao linkmanDao) {
		this.linkmanDao = linkmanDao;
	}


	@Override
	@Transactional(isolation = Isolation.REPEATABLE_READ, propagation = Propagation.REQUIRED, readOnly = false)
	public void save(LinkMan linkMan) {
		Long cus_id=linkMan.getCust_id();
		Customer customer = customerDao.getById(cus_id);
		linkMan.setCustomer(customer);
		linkmanDao.saveOrUpdate(linkMan);
	}
	
	
	@Override
	@Transactional(isolation = Isolation.READ_COMMITTED, propagation = Propagation.REQUIRED, readOnly = true)
	public PageBean getLinkmanPageBean(DetachedCriteria criteria, Integer currentPage, Integer pageSize) {
		// 1.调用Dao查询总记录数
		Integer totalCount = linkmanDao.getTotalCount(criteria);
		// 2.创建PageBean
		PageBean linkmanPageBean = new PageBean(currentPage, totalCount, pageSize);
		
		// 3.根据当前页数和当前页显示条数调用Dao查询客户列表
		List<LinkMan> customers = linkmanDao.getPageList(criteria, 
				linkmanPageBean.getStart(),
				linkmanPageBean.getPageSize());
		// 4.给PageBean设置客户列表
		linkmanPageBean.setList(customers);
		// 5.返回PageBean
		return linkmanPageBean;
	}


	@Override
	public LinkMan getById(Long lkm_id) {
		return linkmanDao.getById(lkm_id);
	}

}
