package com.web.pannel.model;

public class UserLoginLog {
	private String userid;
	private String ip;
	private String lastlogin;
	private String validupto;
	private int isLogin;
	private static UserLoginLog userLoginLog;
	
	public static UserLoginLog getInstance() {
		if(userLoginLog==null)
			userLoginLog=new UserLoginLog();
		
		return userLoginLog;
	}
	
	public UserLoginLog() {
		super();
	}

	public String getUserid() {
		return userid;
	}

	public void setUserid(String userid) {
		this.userid = userid;
	}


	public String getIp() {
		return ip;
	}


	public void setIp(String ip) {
		this.ip = ip;
	}

	public String getLastlogin() {
		return lastlogin;
	}
	public void setLastlogin(String lastlogin) {
		this.lastlogin = lastlogin;
	}
	public String getValidupto() {
		return validupto;
	}
	public void setValidupto(String validupto) {
		this.validupto = validupto;
	}
	public int getIsLogin() {
		return isLogin;
	}
	public void setIsLogin(int isLogin) {
		this.isLogin = isLogin;
	}

		
	
	
}
