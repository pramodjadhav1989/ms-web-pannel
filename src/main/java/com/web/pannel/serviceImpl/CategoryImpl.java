package com.web.pannel.serviceImpl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.databind.ObjectMapper;

import com.web.pannel.constant.IAppConstant;
import com.web.pannel.constant.IDatabaseConstant;
import com.web.pannel.constant.IErrorConstant;
import com.web.pannel.dto.AppDTO;
import com.web.pannel.service.ICategory;
import com.web.pannel.util.MyObjectMapper;
import com.web.pannel.util.ResponseModel;
import com.web.pannel.util.Util;
import com.web.pannel.util.Validation;

@Service
public class CategoryImpl implements ICategory, IAppConstant, IErrorConstant, IDatabaseConstant {

	@Autowired
	private JdbcTemplate jdbcTemplate;


	@Override
	public ResponseModel manage_category(String input, String userdetails) {
		ResponseModel responseModel = ResponseModel.getInstance();

		try {

			int id;
			int isactive;
			String cat_code;
			String category;
			String description;
			String createdBy;

			ObjectMapper objectMapper = MyObjectMapper.getInstance();

			Map<String, Object> inputMap = objectMapper.readValue(input, HashMap.class);
			id = inputMap.containsKey("catId") ? Integer.parseInt(String.valueOf(inputMap.get("catId"))) : 0;
			isactive = inputMap.containsKey("isactive") ? Integer.parseInt(String.valueOf(inputMap.get("isactive")))
					: 1;
			cat_code = inputMap.containsKey("cat_code") ? String.valueOf(inputMap.get("cat_code")) : "";
			category = inputMap.containsKey("category") ? String.valueOf(inputMap.get("category")) : "";
			description = inputMap.containsKey("description") ? String.valueOf(inputMap.get("description")) : "";

			String decodeToken = Util.decodeToken(userdetails);
			createdBy = (String) MyObjectMapper.getInstance().readValue(decodeToken, HashMap.class).get("sub");

			// Validate request

			if (!Validation.IsNumeric(id)) {
				responseModel.setStatus(ERROR);
				responseModel.setData(INVALID_ID);
				return responseModel;

			}
			if (Validation.IsEmpty(cat_code)) {
				responseModel.setStatus(ERROR);
				responseModel.setData("Invalid Category code");
				return responseModel;
			}
			if (Validation.IsEmpty(category)) {
				responseModel.setStatus(ERROR);
				responseModel.setData("Invalid Category");
				return responseModel;
			}
			if (Validation.IsEmpty(description)) {
				responseModel.setStatus(ERROR);
				responseModel.setData("Invalid description");
				return responseModel;
			}

			AppDTO appDTO_ = AppDTO.getInstance();
			inputMap = new HashMap<>();

			inputMap.put("process", "manage_category");
			inputMap.put("id", id);
			inputMap.put("cat_code", cat_code);
			inputMap.put("category", category);
			inputMap.put("description", description);
			inputMap.put("cat_code", cat_code);
			inputMap.put("user", createdBy);
			inputMap.put("isactive", isactive);

			Map<String, Object> out = appDTO_.manageDTO(inputMap, jdbcTemplate, CATEGORY);

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

			inputMap.put("process", "get_category");
			
		
			List<Map<String, Object>> out = appDTO_.getData(inputMap, jdbcTemplate, CATEGORY);

			if (out == null) {
				responseModel.setStatus(ERROR);
				responseModel.setData(WENTWRONG);
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
			id = inputMap.containsKey("catId") ? Integer.parseInt(String.valueOf(inputMap.get("catId"))) : 0;


			if (!Validation.IsNumeric(id)) {
				responseModel.setStatus(ERROR);
				responseModel.setData(INVALID_ID);
				return responseModel;

			}
			
			AppDTO appDTO_ = AppDTO.getInstance();
			inputMap = new HashMap<>();

			inputMap.put("process", "get_categoryById");
			inputMap.put("id", id);
			

			List<Map<String, Object>> out = appDTO_.getData(inputMap, jdbcTemplate, CATEGORY);

			if (out == null) {
				responseModel.setStatus(ERROR);
				responseModel.setData(WENTWRONG);
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

			String category;
	
			ObjectMapper objectMapper = MyObjectMapper.getInstance();

			Map<String, Object> inputMap = objectMapper.readValue(input, HashMap.class);
			category = inputMap.containsKey("category") ? String.valueOf(inputMap.get("category")) : "";
		
			AppDTO appDTO_ = AppDTO.getInstance();
			inputMap = new HashMap<>();

			inputMap.put("process", "get_categoryByName");
			inputMap.put("category", category);
			

			List<Map<String, Object>> out = appDTO_.getData(inputMap, jdbcTemplate, CATEGORY);

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
