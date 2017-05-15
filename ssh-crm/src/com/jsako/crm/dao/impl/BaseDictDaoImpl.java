package com.jsako.crm.dao.impl;

import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.springframework.orm.hibernate5.HibernateCallback;
import org.springframework.orm.hibernate5.support.HibernateDaoSupport;

import com.jsako.crm.dao.BaseDictDao;
import com.jsako.crm.domain.BaseDict;

public class BaseDictDaoImpl extends BaseDaoImpl<BaseDict> implements BaseDictDao{

	@Override
	public List<BaseDict> getListByTypeCode(String dict_type_code) {
		
		return getHibernateTemplate().execute(new HibernateCallback<List<BaseDict>>() {

			@Override
			public List<BaseDict> doInHibernate(Session session) throws HibernateException {
				String hql="FROM BaseDict b WHERE b.dict_type_code=? ORDER BY b.dict_sort asc";
				Query query = session.createQuery(hql);
				query.setString(0, dict_type_code);
				
				return query.list();
			}
		
		
		});
	}
}
