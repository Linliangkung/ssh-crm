package com.jsako.crm.dao.impl;

import java.util.List;

import javax.management.Query;

import org.hibernate.HibernateException;
import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.springframework.orm.hibernate5.HibernateCallback;

import com.jsako.crm.dao.CustomerDao;
import com.jsako.crm.domain.Customer;

public class CustomerDaoImpl extends BaseDaoImpl<Customer> implements CustomerDao {

	@Override
	public List<Object[]> getIndustryCount() {
		// 原生sql查询
		return getHibernateTemplate().execute(new HibernateCallback<List<Object[]>>() {

			@Override
			public List<Object[]> doInHibernate(Session session) throws HibernateException {
				String sql = "SELECT bd.`dict_item_name`,COUNT(*) total FROM cst_customer c,base_dict bd WHERE c.`cust_industry`=bd.`dict_id` GROUP BY c.`cust_industry`";
				SQLQuery sqlQuery = session.createSQLQuery(sql);
				return sqlQuery.list();
			}

		});
	}

	@Override
	public List<Object[]> getSourceCount() {
		// 原生sql查询
				return getHibernateTemplate().execute(new HibernateCallback<List<Object[]>>() {

					@Override
					public List<Object[]> doInHibernate(Session session) throws HibernateException {
						String sql = "SELECT bd.`dict_item_name`,COUNT(*) total FROM cst_customer c,base_dict bd WHERE c.`cust_source`=bd.`dict_id` GROUP BY c.`cust_source`";
						SQLQuery sqlQuery = session.createSQLQuery(sql);
						return sqlQuery.list();
					}

				});
	}

}
