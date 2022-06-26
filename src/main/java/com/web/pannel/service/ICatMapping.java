package com.web.pannel.service;

import com.web.pannel.util.ResponseModel;

public interface ICatMapping {
	public ResponseModel accessControl(String input, String userdetails);
	public ResponseModel loadCatMappWith(String input, String userdetails);
	public ResponseModel getByMenu(String input, String userdetails);
	public ResponseModel getByCatCode(String input, String userdetails);

}
