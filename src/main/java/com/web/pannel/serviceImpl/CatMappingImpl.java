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
import com.web.pannel.service.ICatMapping;
import com.web.pannel.util.MyObjectMapper;
import com.web.pannel.util.ResponseModel;
import com.web.pannel.util.Util;
import com.web.pannel.util.Validation;

@Service
public class CatMappingImpl implements ICatMapping, IAppConstant, IErrorConstant, IDatabaseConstant {

	@Autowired
	private JdbcTemplate jdbcTemplate;

	@Override
	public ResponseModel accessControl(String input, String userdetails) {
		ResponseModel responseModel = ResponseModel.getInstance();

		try {

			String menucode;
			String catcode;
			String createdby;

			ObjectMapper objectMapper = MyObjectMapper.getInstance();

			Map<String, Object> inputMap = objectMapper.readValue(input, HashMap.class);

			menucode = inputMap.containsKey("menucode") ? String.valueOf(inputMap.get("menucode")) : "";
			catcode = inputMap.containsKey("cat_code") ? String.valueOf(inputMap.get("cat_code")) : "";

			String decodeToken = Util.decodeToken(userdetails);
			createdby = (String) MyObjectMapper.getInstance().readValue(decodeToken, HashMap.class).get("sub");

			// Validate request

			if (Validation.IsEmpty(menucode)) {
				responseModel.setStatus(ERROR);
				responseModel.setData("Invalid menu code");
				return responseModel;
			}
			if (Validation.IsEmpty(catcode)) {
				responseModel.setStatus(ERROR);
				responseModel.setData("Invalid Category");
				return responseModel;
			}

			AppDTO appDTO_ = AppDTO.getInstance();
			inputMap = new HashMap<>();

			inputMap.put("process", "update access control");
			inputMap.put("menucode", menucode);
			inputMap.put("cat_code", catcode);
			inputMap.put("user", createdby);
			Map<String, Object> out = appDTO_.manageDTO(inputMap, jdbcTemplate, MENUMAPPING);

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
	public ResponseModel loadCatMappWith(String input, String userdetails) {
		ResponseModel responseModel = ResponseModel.getInstance();

		try {
			AppDTO appDTO_ = AppDTO.getInstance();
			
			ObjectMapper objectMapper = MyObjectMapper.getInstance();

			Map<String, Object> inputMap = objectMapper.readValue(input, HashMap.class);

			String menucode = inputMap.containsKey("menucode") ? String.valueOf(inputMap.get("menucode")) : "";
			
			if (Validation.IsEmpty(menucode)) {
				responseModel.setStatus(ERROR);
				responseModel.setData("Invalid menu code");
				return responseModel;
			}

			inputMap.put("process", "load category map with");
			inputMap.put("menucode", menucode);
			
		
			List<Map<String, Object>> out = appDTO_.getData(inputMap, jdbcTemplate, MENUMAPPING);

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
	public ResponseModel getByMenu(String input, String userdetails) {
		ResponseModel responseModel = ResponseModel.getInstance();

		try {
			AppDTO appDTO_ = AppDTO.getInstance();
			
			ObjectMapper objectMapper = MyObjectMapper.getInstance();

			Map<String, Object> inputMap = objectMapper.readValue(input, HashMap.class);

			String menucode = inputMap.containsKey("menucode") ? String.valueOf(inputMap.get("menucode")) : "";
			
			if (Validation.IsEmpty(menucode)) {
				responseModel.setStatus(ERROR);
				responseModel.setData("Invalid menu code");
				return responseModel;
			}

			inputMap.put("process", "get category by menu code");
			inputMap.put("menucode", menucode);
			
		
			List<Map<String, Object>> out = appDTO_.getData(inputMap, jdbcTemplate, MENUMAPPING);

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
	public ResponseModel getByCatCode(String input, String userdetails) {
		ResponseModel responseModel = ResponseModel.getInstance();

		try {
			AppDTO appDTO_ = AppDTO.getInstance();
			
			ObjectMapper objectMapper = MyObjectMapper.getInstance();

			Map<String, Object> inputMap = objectMapper.readValue(input, HashMap.class);

			String cat_code = inputMap.containsKey("catcode") ? String.valueOf(inputMap.get("catcode")) : "";
			
			if (Validation.IsEmpty(cat_code)) {
				responseModel.setStatus(ERROR);
				responseModel.setData("Invalid cat code");
				return responseModel;
			}

			inputMap.put("process", "get access control");
			inputMap.put("cat_code", cat_code);
			
		
			List<Map<String, Object>> out = appDTO_.getData(inputMap, jdbcTemplate, MENUMAPPING);

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
