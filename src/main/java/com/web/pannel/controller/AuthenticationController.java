package com.web.pannel.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.web.pannel.service.IAuthenticationService;
import com.web.pannel.util.ResponseModel;


@RestController
@CrossOrigin
public class AuthenticationController {

	@Autowired
	IAuthenticationService authenticationService;

	@RequestMapping(value = "/authenticate", method = RequestMethod.POST)
	public ResponseModel createAuthenticationToken(@RequestBody String input) throws Exception {
		return authenticationService.login(input);

		// return ResponseEntity.ok(new JwtResponse(token));
	}

	@RequestMapping(value = "/userDetails", method = RequestMethod.POST)
	public ResponseModel getUser(@RequestBody String input) throws Exception {
		return authenticationService.getUser(input);
	}

//	@RequestMapping(value = "/sendNotification", method = RequestMethod.POST)
//	public ResponseModel Notification(@RequestBody String input) throws Exception {
//		return notificationService.sendEmail(input);
//	}

	// ******************************************************************************
	// Forget Pwd logic
	// ******************************************************************************

	

}
