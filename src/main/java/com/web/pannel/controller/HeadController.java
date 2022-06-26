package com.web.pannel.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.web.pannel.service.IMenuHeader;
import com.web.pannel.util.ResponseModel;

@RestController
public class HeadController {
	@Autowired
	IMenuHeader headerService;



	@RequestMapping(value = "/head/manage", method = RequestMethod.POST)
	public ResponseModel manageHead(@RequestBody String input ,@RequestHeader(name="Authorization") String token) throws Exception {
		return headerService.manage_header(input,token);
	}
	
	@RequestMapping(value = "/head/getAll", method = RequestMethod.GET)
	public ResponseModel getAll(@RequestHeader(name="Authorization") String token) throws Exception {
		return headerService.getAll(token);
	}
	
	@RequestMapping(value = "/head/getById", method = RequestMethod.POST)
	public ResponseModel getById(@RequestBody String input ,@RequestHeader(name="Authorization") String token) throws Exception {
		return headerService.getById(input,token);
	}
	
	@RequestMapping(value = "/head/getByName", method = RequestMethod.POST)
	public ResponseModel getByName(@RequestBody String input ,@RequestHeader(name="Authorization") String token) throws Exception {
		return headerService.getByName(input,token);
	}
}
