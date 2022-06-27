package com.web.pannel.model;

import java.util.ArrayList;
import java.util.List;

public class HeadList {
private String head;
List<SubHeadList> subHeadList=new ArrayList<SubHeadList>();
public String getHead() {
	return head;
}
public void setHead(String head) {
	this.head = head;
}
public List<SubHeadList> getSubHeadList() {
	return subHeadList;
}
public void setSubHeadList(List<SubHeadList> subHeadList) {
	this.subHeadList = subHeadList;
}


}
