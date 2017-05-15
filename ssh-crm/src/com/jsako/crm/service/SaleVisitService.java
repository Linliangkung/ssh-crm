package com.jsako.crm.service;

import org.hibernate.criterion.DetachedCriteria;

import com.jsako.crm.domain.SaleVisit;
import com.jsako.crm.utils.PageBean;

public interface SaleVisitService {

	void save(SaleVisit saleVisit);
	
	PageBean getSaleVisitPageBean(DetachedCriteria criteria, Integer currentPage, Integer pageSize);

	SaleVisit getSaleVisitById(String visit_id);
}
