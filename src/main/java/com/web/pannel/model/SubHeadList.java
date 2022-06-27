package com.web.pannel.model;

import java.util.ArrayList;
import java.util.List;

public class SubHeadList {

	private String subhead;
List<Menus> menus=new ArrayList<Menus>();
public String getSubhead() {
	return subhead;
}
public void setSubhead(String subhead) {
	this.subhead = subhead;
}
public List<Menus> getMenus() {
	return menus;
}
public void setMenus(List<Menus> menus) {
	this.menus = menus;
}


}
