package com.web.pannel.model;



public class Users {

	private static Users users;

	public static Users getInstance() {
		if(users==null)
			users=new Users();
		
		return users;
	}
	
	private int id;
	private String userid; 
	private String name; 
	private String email; 
	private String password; 
	private String senior; 
	private String cat_code; 
	private String lastlogin; 
	private String lastpwdchanged; 
	private String login_count; 
	private String createdby; 
	private String createdon; 
	private String modifyby;
	private String modifyon; 
	private int isactive; 
	private String division;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getUserid() {
		return userid;
	}
	public void setUserid(String userid) {
		this.userid = userid;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getSenior() {
		return senior;
	}
	public void setSenior(String senior) {
		this.senior = senior;
	}
	public String getCat_code() {
		return cat_code;
	}
	public void setCat_code(String cat_code) {
		this.cat_code = cat_code;
	}
	public String getLastlogin() {
		return lastlogin;
	}
	public void setLastlogin(String lastlogin) {
		this.lastlogin = lastlogin;
	}
	public String getLastpwdchanged() {
		return lastpwdchanged;
	}
	public void setLastpwdchanged(String lastpwdchanged) {
		this.lastpwdchanged = lastpwdchanged;
	}
	public String getLogin_count() {
		return login_count;
	}
	public void setLogin_count(String login_count) {
		this.login_count = login_count;
	}
	public String getCreatedby() {
		return createdby;
	}
	public void setCreatedby(String createdby) {
		this.createdby = createdby;
	}
	public String getCreatedon() {
		return createdon;
	}
	public void setCreatedon(String createdon) {
		this.createdon = createdon;
	}
	public String getModifyby() {
		return modifyby;
	}
	public void setModifyby(String modifyby) {
		this.modifyby = modifyby;
	}
	public String getModifyon() {
		return modifyon;
	}
	public void setModifyon(String modifyon) {
		this.modifyon = modifyon;
	}
	public int getIsactive() {
		return isactive;
	}
	public void setIsactive(int isactive) {
		this.isactive = isactive;
	}
	public String getDivision() {
		return division;
	}
	public void setDivision(String division) {
		this.division = division;
	}
	
	
	public Users() {
		super();
	}

}
