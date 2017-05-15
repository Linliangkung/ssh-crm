package com.jsako.crm.web.action;

import javax.servlet.ServletContext;

import org.apache.commons.lang3.StringUtils;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.transaction.interceptor.TransactionAttributeEditor;

import com.jsako.crm.domain.Customer;
import com.jsako.crm.domain.SaleVisit;
import com.jsako.crm.domain.User;
import com.jsako.crm.service.SaleVisitService;
import com.jsako.crm.utils.PageBean;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;

public class SaleVisitAction extends ActionSupport implements ModelDriven<SaleVisit> {
	private SaleVisit saleVisit = new SaleVisit();
	private SaleVisitService saleVisitService;
	
	private Integer currentPage;
	private Integer pageSize;
	
	public String add() {
		User user = (User) ActionContext.getContext().getSession().get("user");
		saleVisit.setUser(user);
		saleVisitService.save(saleVisit);
		return "toList";
	}

	public String list() {
		// 封装离线查询对象
		DetachedCriteria criteria = DetachedCriteria.forClass(SaleVisit.class);
		if(saleVisit.getCustomer()!=null&&saleVisit.getCustomer().getCust_id()!=null){
			if(StringUtils.isNotBlank(saleVisit.getCustomer().getCust_name())){
				criteria.add(Restrictions.eq("customer.cust_id", saleVisit.getCustomer().getCust_id()));
			}
		}
		// 1.调用customerService查询分页数据(PageBean)
		PageBean saleVisitPageBean = saleVisitService.getSaleVisitPageBean(criteria, currentPage, pageSize);
		// 2.将pageBean保存到ActionContext域中，转发到列表页面显示
		ActionContext.getContext().put("saleVisitPageBean", saleVisitPageBean);

		return "list";
	}

	
	public String toEdit(){
		
		SaleVisit qSaleVisit=saleVisitService.getSaleVisitById(saleVisit.getVisit_id());
		ActionContext.getContext().put("saleVisit", qSaleVisit);
		return "toEdit";
	}
	@Override
	public SaleVisit getModel() {
		return saleVisit;
	}

	public void setSaleVisitService(SaleVisitService saleVisitService) {
		this.saleVisitService = saleVisitService;
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
