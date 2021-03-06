package com.web.pannel.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.web.pannel.service.IUser;
import com.web.pannel.util.ResponseModel;


@RestController
public class UserController {
	@Autowired
	IUser authenticationService;



	@RequestMapping(value = "/manageUser", method = RequestMethod.POST)
	public ResponseModel createUser(@RequestBody String input ,@RequestHeader(name="Authorization") String token) throws Exception {
		return authenticationService.register(input,token);
	}
	
	@RequestMapping(value = "/getAllUser", method = RequestMethod.GET)
	public ResponseModel getAll(@RequestHeader(name="Authorization") String token) throws Exception {
		return authenticationService.getAll();
	}
	
	@RequestMapping(value = "/getById", method = RequestMethod.POST)
	public ResponseModel getById(@RequestBody String input ,@RequestHeader(name="Authorization") String token) throws Exception {
		return authenticationService.getById(input,token);
	}
	
	@RequestMapping(value = "/getByName", method = RequestMethod.POST)
	public ResponseModel getByName(@RequestBody String input ,@RequestHeader(name="Authorization") String token) throws Exception {
		return authenticationService.getByName(input,token);
	}

}

