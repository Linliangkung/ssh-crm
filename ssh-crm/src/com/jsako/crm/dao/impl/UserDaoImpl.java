package com.jsako.crm.dao.impl;

import java.util.List;

import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.orm.hibernate5.support.HibernateDaoSupport;

import com.jsako.crm.dao.UserDao;
import com.jsako.crm.domain.User;

public class UserDaoImpl extends BaseDaoImpl<User> implements UserDao {

	@Override
	public User getUserByCode(String code) {
		/*
		 * return getHibernateTemplate().execute(new HibernateCallback<User>() {
		 * 
		 * @Override public User doInHibernate(Session session) throws
		 * HibernateException { String hql="FROM User u WHERE u.user_code=?";
		 * Query query = session.createQuery(hql); query.setString(0, code);
		 * return (User) query.uniqueResult(); }
		 * 
		 * });
		 */
		DetachedCriteria criteria = DetachedCriteria.forClass(User.class);
		criteria.add(Restrictions.eq("user_code", code));
		List<User> list = (List<User>) getHibernateTemplate().findByCriteria(criteria);
		if (list != null && list.size() > 0) {

			return list.get(0);
		} else {
			return null;
		}
	}


}
