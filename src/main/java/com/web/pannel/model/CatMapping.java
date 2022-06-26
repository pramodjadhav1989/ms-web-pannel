package com.web.pannel.model;

public class CatMapping {
	private int id;
	private String menu_code;
	private String cat_code;
	private String mappedby;
	private String mappedon;
	private int isactive;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getMenu_code() {
		return menu_code;
	}
	public void setMenu_code(String menu_code) {
		this.menu_code = menu_code;
	}
	public String getCat_code() {
		return cat_code;
	}
	public void setCat_code(String cat_code) {
		this.cat_code = cat_code;
	}
	public String getMappedby() {
		return mappedby;
	}
	public void setMappedby(String mappedby) {
		this.mappedby = mappedby;
	}
	public String getMappedon() {
		return mappedon;
	}
	public void setMappedon(String mappedon) {
		this.mappedon = mappedon;
	}
	public int getIsactive() {
		return isactive;
	}
	public void setIsactive(int isactive) {
		this.isactive = isactive;
	}
	public CatMapping() {
		super();
	}
	
	
}
