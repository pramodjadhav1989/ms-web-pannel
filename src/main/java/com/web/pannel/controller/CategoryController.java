package com.web.pannel.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.web.pannel.service.ICategory;
import com.web.pannel.util.ResponseModel;

@RestController
public class CategoryController {
	@Autowired
	ICategory catService;



	@RequestMapping(value = "/category/manage", method = RequestMethod.POST)
	public ResponseModel manageCategory(@RequestBody String input ,@RequestHeader(name="Authorization") String token) throws Exception {
		return catService.manage_category(input,token);
	}
	
	@RequestMapping(value = "/category/getAll", method = RequestMethod.GET)
	public ResponseModel getAll(@RequestHeader(name="Authorization") String token) throws Exception {
		return catService.getAll(token);
	}
	
	@RequestMapping(value = "/category/getById", method = RequestMethod.POST)
	public ResponseModel getById(@RequestBody String input ,@RequestHeader(name="Authorization") String token) throws Exception {
		return catService.getById(input,token);
	}
	
	@RequestMapping(value = "/category/getByName", method = RequestMethod.POST)
	public ResponseModel getByName(@RequestBody String input ,@RequestHeader(name="Authorization") String token) throws Exception {
		return catService.getByName(input,token);
	}
}
