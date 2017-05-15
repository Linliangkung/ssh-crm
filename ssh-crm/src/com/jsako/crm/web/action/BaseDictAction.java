package com.jsako.crm.web.action;

import java.util.List;

import javax.servlet.Servlet;

import org.apache.struts2.ServletActionContext;

import com.jsako.crm.domain.BaseDict;
import com.jsako.crm.service.BaseDictService;
import com.opensymphony.xwork2.ActionSupport;

import net.sf.json.JSONArray;

public class BaseDictAction extends ActionSupport {

	private String dict_type_code;
	
	private BaseDictService baseDictService;

	

	@Override
	public String execute() throws Exception {
		// 1.调用service获得字典list
		List<BaseDict> baseDicts = baseDictService.getListByTypeCode(dict_type_code);
		// 2.将list集合转换为json格式
		String json = JSONArray.fromObject(baseDicts).toString();
		System.out.println(json);
		// 3.将json发送给客户端
		ServletActionContext.getResponse().setContentType("application/json;charset=utf-8");
		ServletActionContext.getResponse().getWriter().write(json);;
		//告诉struts2不需要进行结果处理
		return null;
	}

	public void setDict_type_code(String dict_type_code) {
		this.dict_type_code = dict_type_code;
	}
	
	public void setBaseDictService(BaseDictService baseDictService) {
		this.baseDictService = baseDictService;
	}

}
