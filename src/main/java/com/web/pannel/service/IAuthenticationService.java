package com.web.pannel.service;

import com.web.pannel.util.ResponseModel;

public interface IAuthenticationService {
	public ResponseModel getUser(String input);
	
	public ResponseModel userValidate(String input);
	
	public ResponseModel login(String input);
	

}
