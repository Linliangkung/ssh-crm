package com.jsako.crm.web.action;

import javax.servlet.ServletContext;

import org.apache.commons.lang3.StringUtils;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Restrictions;

import com.jsako.crm.domain.Customer;
import com.jsako.crm.domain.LinkMan;
import com.jsako.crm.service.LinkmanService;
import com.jsako.crm.utils.PageBean;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;
import com.sun.media.sound.ModelDirectedPlayer;

public class LinkmanAction extends ActionSupport implements ModelDriven<LinkMan>{
	
	private LinkMan linkMan=new LinkMan();
	
	private LinkmanService linkmanService;
	
	private Integer currentPage;
	private Integer pageSize;

	

	public String add(){
		linkmanService.save(linkMan);
		return "toList";
	}
	
	public String list() throws Exception{
		//封装离线查询对象
		
		System.out.println("pagesize:"+pageSize);
		
		DetachedCriteria criteria=DetachedCriteria.forClass(LinkMan.class);
		if(StringUtils.isNotBlank(linkMan.getLkm_name())){
			criteria.add(Restrictions.like("lkm_name", "%"+linkMan.getLkm_name()+"%"));
		}
		
		if(linkMan.getCustomer()!=null&&linkMan.getCustomer().getCust_id()!=null){
			if(StringUtils.isNotBlank(linkMan.getCustomer().getCust_name())){
				criteria.add(Restrictions.eq("customer.cust_id", linkMan.getCustomer().getCust_id()));
			}
		}
		
		//1.调用customerService查询分页数据(PageBean)
		PageBean linkmanPageBean=linkmanService.getLinkmanPageBean(criteria,currentPage,pageSize);
		//2.将pageBean保存到ActionContext域中，转发到列表页面显示 
		ActionContext.getContext().put("linkmanPageBean", linkmanPageBean);
		return "list";
	}
	
	
	public String toEdit(){
		
		LinkMan linkman=linkmanService.getById(linkMan.getLkm_id());
		ActionContext.getContext().put("linkman", linkman);
		return "toEdit";
	}
	
	
	public LinkMan getLinkMan() {
		return linkMan;
	}

	public void setLinkMan(LinkMan linkMan) {
		this.linkMan = linkMan;
	}


	public void setLinkmanService(LinkmanService linkmanService) {
		this.linkmanService = linkmanService;
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

	@Override
	public LinkMan getModel() {
		return linkMan;
	}
}
