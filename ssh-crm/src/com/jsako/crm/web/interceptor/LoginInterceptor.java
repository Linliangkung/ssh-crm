package com.jsako.crm.web.interceptor;

import org.hibernate.procedure.internal.Util.ResultClassesResolutionContext;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionInvocation;
import com.opensymphony.xwork2.interceptor.MethodFilterInterceptor;

public class LoginInterceptor extends MethodFilterInterceptor {

	@Override
	protected String doIntercept(ActionInvocation invocation) throws Exception {
		Object user=ActionContext.getContext().getSession().get("user");
		if(user==null){
			//未登录
			return "toLogin";
		}else{
			//已经登录
			return invocation.invoke();
		}
	}

}
