package com.web.pannel.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.web.pannel.service.IMenu;
import com.web.pannel.util.ResponseModel;

@RestController
public class MenuController {
	@Autowired
	IMenu menuService ;



	@RequestMapping(value = "/menu/manage", method = RequestMethod.POST)
	public ResponseModel createUser(@RequestBody String input ,@RequestHeader(name="Authorization") String token) throws Exception {
		return menuService.manage_menu(input,token);
	}
	
	@RequestMapping(value = "/menu/getAll", method = RequestMethod.GET)
	public ResponseModel getAll(@RequestHeader(name="Authorization") String token) throws Exception {
		return menuService.getAll(token);
	}
	
	@RequestMapping(value = "/menu/getById", method = RequestMethod.POST)
	public ResponseModel getById(@RequestBody String input ,@RequestHeader(name="Authorization") String token) throws Exception {
		return menuService.getById(input,token);
	}
	
	@RequestMapping(value = "/menu/getByName", method = RequestMethod.POST)
	public ResponseModel getByName(@RequestBody String input ,@RequestHeader(name="Authorization") String token) throws Exception {
		return menuService.getByName(input,token);
	}
	
	@RequestMapping(value = "/menu/getMenu", method = RequestMethod.GET)
	public ResponseModel getMenu(@RequestHeader(name="Authorization") String token) throws Exception {
		return menuService.getMenu(token);
	}
	
	@RequestMapping(value = "/menu/getByCatcode", method = RequestMethod.POST)
	public ResponseModel getByCatCode(@RequestBody String input ,@RequestHeader(name="Authorization") String token) throws Exception {
		return menuService.getByCatcode(input,token);
	}
}
