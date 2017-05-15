package com.jsako.crm.service;

import org.hibernate.criterion.DetachedCriteria;

import com.jsako.crm.domain.LinkMan;
import com.jsako.crm.utils.PageBean;

public interface LinkmanService {

	void save(LinkMan linkMan);
	
	PageBean getLinkmanPageBean(DetachedCriteria criteria, Integer currentPage, Integer pageSize);

	LinkMan getById(Long lkm_id);
}
