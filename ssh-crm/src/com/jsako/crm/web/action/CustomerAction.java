package com.jsako.crm.web.action;

import java.awt.print.Pageable;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.apache.struts2.ServletActionContext;
import org.apache.struts2.interceptor.RequestAware;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Restrictions;

import com.jsako.crm.domain.Customer;
import com.jsako.crm.service.CustomerService;
import com.jsako.crm.utils.PageBean;
import com.opensymphony.xwork2.Action;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;

public class CustomerAction extends ActionSupport implements RequestAware, ModelDriven<Customer> {
	private Customer customer;

	private Integer currentPage;
	private Integer pageSize;

	private CustomerService customerService;
	private Map<String, Object> request;

	public String list() throws Exception{
		//封装离线查询对象
		DetachedCriteria criteria=DetachedCriteria.forClass(Customer.class);
		if(StringUtils.isNotBlank(customer.getCust_name())){
			criteria.add(Restrictions.like("cust_name", "%"+customer.getCust_name()+"%"));
		}
		
		//1.调用customerService查询分页数据(PageBean)
		PageBean customerPageBean=customerService.getCustomerPageBean(criteria,currentPage,pageSize);
		//2.将pageBean保存到ActionContext域中，转发到列表页面显示 
		ActionContext.getContext().put("customerPageBean", customerPageBean);
		return "list";
	}

	public String add() {
		customerService.save(customer);
		return "toList";
	}

	public String toEdit(){
		Customer editCustomer=customerService.getById(customer.getCust_id());
		ActionContext.getContext().put("customer", editCustomer);
		return "toEdit";
	}
	
	public String industryCount(){
		
		List<Object[]> list = customerService.getIndustryCount();
		
		ActionContext.getContext().put("list", list);
		ActionContext.getContext().put("type", "industry");
		return "count";
	}
	
	public String sourceCount(){
		
		List<Object[]> list = customerService.getSourceCount();
		
		ActionContext.getContext().put("list", list);
		ActionContext.getContext().put("type", "source");
		return "count";
	}
	
	@Override
	public void setRequest(Map<String, Object> request) {
		this.request = request;
	}

	@Override
	public Customer getModel() {
		customer = new Customer();
		return customer;
	}

	public void setCustomerService(CustomerService customerService) {
		this.customerService = customerService;
	}

	public Integer getCurrentPage() {
		return currentPage;
	}

	public void setCurrentPage(Integer currentPage) {
		this.currentPage = currentPage;
	}

	public Integer getPageSize() {
		return pageSize;
	}

	public void setPageSize(Integer pageSize) {
		this.pageSize = pageSize;
	}

}
