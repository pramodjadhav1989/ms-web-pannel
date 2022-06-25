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
	public ResponseModel createUser(@RequestBody String input) throws Exception {
		return authenticationService.register(input);
	}

}

