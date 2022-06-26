package com.web.pannel.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.web.pannel.service.ICatMapping;
import com.web.pannel.util.ResponseModel;

@RestController
public class CatMappingController {

	@Autowired
	ICatMapping mappingService  ;

	@RequestMapping(value = "/mapping/loadCatMappWith", method = RequestMethod.POST)
	public ResponseModel loadCatMappWith(@RequestBody String input ,@RequestHeader(name="Authorization") String token) throws Exception {
		return mappingService.loadCatMappWith(input,token);
	}
	
	@RequestMapping(value = "/mapping/getByMenu", method = RequestMethod.POST)
	public ResponseModel getByMenu(@RequestBody String input ,@RequestHeader(name="Authorization") String token) throws Exception {
		return mappingService.getByMenu(input,token);
	}
	
	@RequestMapping(value = "/mapping/getByCatCode", method = RequestMethod.POST)
	public ResponseModel getByCatCode(@RequestBody String input ,@RequestHeader(name="Authorization") String token) throws Exception {
		return mappingService.getByCatCode(input,token);
	}
	
	@RequestMapping(value = "/mapping/accessControl", method = RequestMethod.POST)
	public ResponseModel accessControl(@RequestBody String input ,@RequestHeader(name="Authorization") String token) throws Exception {
		return mappingService.accessControl(input,token);
	}
}
