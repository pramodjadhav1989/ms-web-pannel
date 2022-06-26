package com.web.pannel.serviceImpl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.web.pannel.constant.IAppConstant;
import com.web.pannel.constant.IDatabaseConstant;
import com.web.pannel.constant.IErrorConstant;
import com.web.pannel.dto.AppDTO;
import com.web.pannel.service.ICategory;
import com.web.pannel.service.IMenuHeader;
import com.web.pannel.util.JwtTokenUtil;
import com.web.pannel.util.MyObjectMapper;
import com.web.pannel.util.ResponseModel;
import com.web.pannel.util.Security;
import com.web.pannel.util.Util;
import com.web.pannel.util.Validation;


@Service
public class MenuHeaderImpl implements IMenuHeader, IAppConstant, IErrorConstant, IDatabaseConstant  {
	@Autowired
	private JdbcTemplate jdbcTemplate;

	@Override
	public ResponseModel manage_header(String input, String userdetails) {
		ResponseModel responseModel = ResponseModel.getInstance();

		try {

			int id;
			int isactive;
			String head;
			String icon;
			String createdBy;

			ObjectMapper objectMapper = MyObjectMapper.getInstance();

			Map<String, Object> inputMap = objectMapper.readValue(input, HashMap.class);
			id = inputMap.containsKey("headerId") ? Integer.parseInt(String.valueOf(inputMap.get("headerId"))) : 0;
			isactive = inputMap.containsKey("isactive") ? Integer.parseInt(String.valueOf(inputMap.get("isactive")))
					: 1;
			head = inputMap.containsKey("head") ? String.valueOf(inputMap.get("head")) : "";
			icon = inputMap.containsKey("icon") ? String.valueOf(inputMap.get("icon")) : "";
			

			String decodeToken = Util.decodeToken(userdetails);
			createdBy = (String) MyObjectMapper.getInstance().readValue(decodeToken, HashMap.class).get("sub");

			// Validate request

			if (!Validation.IsNumeric(id)) {
				responseModel.setStatus(ERROR);
				responseModel.setData(INVALID_ID);
				return responseModel;

			}
			if (Validation.IsEmpty(head)) {
				responseModel.setStatus(ERROR);
				responseModel.setData("Invalid Head");
				return responseModel;
			}
			
			AppDTO appDTO_ = AppDTO.getInstance();
			inputMap = new HashMap<>();

			inputMap.put("process", "manage_head");
			inputMap.put("id", id);
			inputMap.put("head", head);
			inputMap.put("icon", icon);
			inputMap.put("user", createdBy);
			inputMap.put("isactive", isactive);

			Map<String, Object> out = appDTO_.manageDTO(inputMap, jdbcTemplate, MENUHEADER);

			if (out == null) {
				responseModel.setStatus(ERROR);
				responseModel.setData(WENTWRONG);
			} else {

				responseModel.setData(out.get("message"));
				responseModel.setStatus((String) out.get("status"));

				return responseModel;

			}

		} catch (Exception ex) {
			System.out.println(ex.getMessage());
			responseModel.setStatus(ERROR);
			responseModel.setData(WENTWRONG);
		}
		return responseModel;
	}

	@Override
	public ResponseModel getAll(String userdetails) {
		ResponseModel responseModel = ResponseModel.getInstance();

		try {
			AppDTO appDTO_ = AppDTO.getInstance();
			Map<String,Object>inputMap = new HashMap<>();

			inputMap.put("process", "get_head");
			
		
			List<Map<String, Object>> out = appDTO_.getData(inputMap, jdbcTemplate, MENUHEADER);

			if (out == null) {
				responseModel.setStatus(ERROR);
				responseModel.setData(DATA_NOT_FOUND);
			} else {

				responseModel.setData(out);
				responseModel.setStatus(SUCCESS);

				return responseModel;

			}

		} catch (Exception ex) {
			System.out.println(ex.getMessage());
			responseModel.setStatus(ERROR);
			responseModel.setData(WENTWRONG);
		}
		return responseModel;
	}

	@Override
	public ResponseModel getById(String input, String userdetails) {
		ResponseModel responseModel = ResponseModel.getInstance();

		try {

			int id;
	
			ObjectMapper objectMapper = MyObjectMapper.getInstance();

			Map<String, Object> inputMap = objectMapper.readValue(input, HashMap.class);
			id = inputMap.containsKey("headerId") ? Integer.parseInt(String.valueOf(inputMap.get("headerId"))) : 0;


			if (!Validation.IsNumeric(id)) {
				responseModel.setStatus(ERROR);
				responseModel.setData(INVALID_ID);
				return responseModel;

			}
			
			AppDTO appDTO_ = AppDTO.getInstance();
			inputMap = new HashMap<>();

			inputMap.put("process", "get_headById");
			inputMap.put("id", id);
			

			List<Map<String, Object>> out = appDTO_.getData(inputMap, jdbcTemplate, MENUHEADER);

			if (out == null) {
				responseModel.setStatus(ERROR);
				responseModel.setData(DATA_NOT_FOUND);
			} else {

				responseModel.setData(out);
				responseModel.setStatus(SUCCESS);

				return responseModel;

			}

		} catch (Exception ex) {
			System.out.println(ex.getMessage());
			responseModel.setStatus(ERROR);
			responseModel.setData(WENTWRONG);
		}
		return responseModel;
	}

	@Override
	public ResponseModel getByName(String input, String userdetails) {
		ResponseModel responseModel = ResponseModel.getInstance();

		try {

			String head;
	
			ObjectMapper objectMapper = MyObjectMapper.getInstance();

			Map<String, Object> inputMap = objectMapper.readValue(input, HashMap.class);
			head = inputMap.containsKey("head") ? String.valueOf(inputMap.get("head")) : "";
		
			AppDTO appDTO_ = AppDTO.getInstance();
			inputMap = new HashMap<>();

			inputMap.put("process", "get_headByName");
			inputMap.put("head", head);
			

			List<Map<String, Object>> out = appDTO_.getData(inputMap, jdbcTemplate, MENUHEADER);

			if (out == null) {
				responseModel.setStatus(ERROR);
				responseModel.setData(DATA_NOT_FOUND);
			} else {

				responseModel.setData(out);
				responseModel.setStatus(SUCCESS);

				return responseModel;

			}

		} catch (Exception ex) {
			System.out.println(ex.getMessage());
			responseModel.setStatus(ERROR);
			responseModel.setData(WENTWRONG);
		}
		return responseModel;
	}


}
