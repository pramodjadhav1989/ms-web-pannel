package com.web.pannel.service;

import com.web.pannel.util.ResponseModel;

public interface IMenu {
	
	public ResponseModel manage_menu(String input, String userdetails);
	public ResponseModel getAll(String userdetails);
	public ResponseModel getById(String input, String userdetails);
	public ResponseModel getByName(String input, String userdetails);
	public ResponseModel getByCatcode(String input, String userdetails);
	public ResponseModel getMenu(String userdetails);
	
	
}
