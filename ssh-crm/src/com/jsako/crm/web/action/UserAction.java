package com.jsako.crm.web.action;


import com.jsako.crm.domain.User;
import com.jsako.crm.service.UserService;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;
import com.sun.media.sound.ModelDirectedPlayer;

public class UserAction extends ActionSupport implements ModelDriven<User>{
	private User user=new User();
	private UserService userService;
		

	public String login(){
		
		User queryUser = userService.getUserByCodeAndPassword(user);
		ActionContext.getContext().getSession().put("user",queryUser);
		
		return "toHome";
	}

	public String regist(){
		System.out.println(user.getUser_name());
		try{
			userService.saveUser(user);
		}catch(Exception e){
			ActionContext.getContext().put("error", e.getMessage());
			e.printStackTrace();
			return "regist";
		}
		return "toLogin";
	}
	
	
	@Override
	public User getModel() {
		return user;
	}
	
	public void setUserService(UserService userService) {
		this.userService = userService;
	}
}
