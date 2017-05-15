package com.jsako.crm.test;

import static org.junit.Assert.*;

import javax.annotation.Resource;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.junit.After;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.jsako.crm.dao.UserDao;
import com.jsako.crm.domain.BaseDict;
import com.jsako.crm.domain.Customer;
import com.jsako.crm.domain.LinkMan;
import com.jsako.crm.domain.SaleVisit;
import com.jsako.crm.domain.User;
import com.jsako.crm.service.UserService;
import com.mysql.jdbc.jdbc2.optional.SuspendableXAConnection;
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:applicationContext.xml")
public class HibernateTest {
	@Resource(name="sessionFactory")
	private SessionFactory sessionFactory;
	@Resource(name="userDao")
	private UserDao userDao;
	@Resource(name="userService")
	private UserService userService;
	
	@Test
	public void testHibernate() {
		Session session=sessionFactory.openSession();
		Transaction transaction=session.beginTransaction();
		User user=new User();
		user.setUser_code("13143350142");
		user.setUser_name("蔡佳欣");
		user.setUser_password("123");
		session.save(user);
		
		transaction.commit();
		session.close();
		
	}
	
	@Test
	public void testHibernateTemplate(){
		
		User user = userDao.getUserByCode("359270069");
		System.out.println(user.getUser_name());
	}
	
	@Test
	public void testHibernateAopTransaction(){
		User user=new User();
		user.setUser_code("13143350142");
		user.setUser_name("蔡佳欣");
		user.setUser_password("123");
		userService.saveUser(user);
		
	}
	
	@Test
	public void testSaveCustomer(){
		Session session = sessionFactory.openSession();
		Transaction transaction = session.beginTransaction();
		
		Customer customer=new Customer();
		BaseDict baseDict1=new BaseDict();
		baseDict1.setDict_id("1");
		BaseDict baseDict2=new BaseDict();
		baseDict2.setDict_id("7");
		BaseDict baseDict3=new BaseDict();
		baseDict3.setDict_id("23");
		
		customer.setCust_industry(baseDict1);
		customer.setCust_source(baseDict2);
		customer.setCust_level(baseDict3);
		
		session.save(customer);
		
		
		transaction.commit();
		session.close();
	}
	
	
	@Test
	public void testSaleVisit(){
		Session session = sessionFactory.openSession();
		Transaction transaction = session.beginTransaction();
			
		SaleVisit saleVisit=new SaleVisit();
		Customer customer=new Customer();
		customer.setCust_id(2l);
		User user=new User();
		user.setUser_id(2l);
		
		saleVisit.setCustomer(customer);
		saleVisit.setUser(user);
		
		session.saveOrUpdate(saleVisit);
		
		
		transaction.commit();
		session.close();
	}
}
