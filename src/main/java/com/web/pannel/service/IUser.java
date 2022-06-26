package com.web.pannel.service;

import com.web.pannel.util.ResponseModel;

public interface IUser {

	
	public ResponseModel register(String input, String userdetails);
	public ResponseModel getAll();
	public ResponseModel getById(String input, String userdetails);
	public ResponseModel getByName(String input, String userdetails);
	

}
