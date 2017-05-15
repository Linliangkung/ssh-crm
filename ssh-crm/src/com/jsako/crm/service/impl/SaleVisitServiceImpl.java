package com.jsako.crm.service.impl;

import java.util.List;

import org.hibernate.criterion.DetachedCriteria;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.jsako.crm.dao.SaleVisitDao;
import com.jsako.crm.domain.Customer;
import com.jsako.crm.domain.SaleVisit;
import com.jsako.crm.service.SaleVisitService;
import com.jsako.crm.utils.PageBean;

public class SaleVisitServiceImpl implements SaleVisitService {
	private SaleVisitDao saleVisitDao ;

	@Override
	@Transactional(isolation = Isolation.READ_COMMITTED, propagation = Propagation.REQUIRED, readOnly = false)
	public void save(SaleVisit saleVisit) {
		
		saleVisitDao.saveOrUpdate(saleVisit);
		
	}
	
	
	@Override
	@Transactional(isolation = Isolation.READ_COMMITTED, propagation = Propagation.REQUIRED, readOnly = true)
	public PageBean getSaleVisitPageBean(DetachedCriteria criteria, Integer currentPage, Integer pageSize) {
		// 1.调用Dao查询总记录数
		Integer totalCount = saleVisitDao.getTotalCount(criteria);
		// 2.创建PageBean
		PageBean saleVisitPageBean = new PageBean(currentPage, totalCount, pageSize);
		// 3.根据当前页数和当前页显示条数调用Dao查询客户列表
		List<SaleVisit> saleVisits = saleVisitDao.getPageList(criteria, 
				saleVisitPageBean.getStart(),
				saleVisitPageBean.getPageSize());
		// 4.给PageBean设置客户列表
		saleVisitPageBean.setList(saleVisits);
		// 5.返回PageBean
		return saleVisitPageBean;
	}
	
	
	public void setSaleVisitDao(SaleVisitDao saleVisitDao) {
		this.saleVisitDao = saleVisitDao;
	}


	@Override
	public SaleVisit getSaleVisitById(String visit_id) {
		return saleVisitDao.getById(visit_id);
	}
}
