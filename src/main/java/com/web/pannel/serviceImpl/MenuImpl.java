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
import com.web.pannel.service.IMenu;
import com.web.pannel.util.MyObjectMapper;
import com.web.pannel.util.ResponseModel;
import com.web.pannel.util.Util;
import com.web.pannel.util.Validation;


@Service
public class MenuImpl implements IMenu, IAppConstant, IErrorConstant, IDatabaseConstant  {

	@Autowired
	private JdbcTemplate jdbcTemplate;
	
	@Override
	public ResponseModel manage_menu(String input, String userdetails) {
		ResponseModel responseModel = ResponseModel.getInstance();

		try {

			 int id;
			 String menucode;
			 String head;
			 String subhead;
			 String menuname;
			 String location;
			 int seq_no;
			 String icon;
			 int isactive;
			 String createdby;

			ObjectMapper objectMapper = MyObjectMapper.getInstance();

			Map<String, Object> inputMap = objectMapper.readValue(input, HashMap.class);
			
			id = inputMap.containsKey("menuId") ? Integer.parseInt(String.valueOf(inputMap.get("menuId"))) : 0;
			menucode = inputMap.containsKey("menucode") ? String.valueOf(inputMap.get("menucode")) : "";
			menuname = inputMap.containsKey("menuname") ? String.valueOf(inputMap.get("menuname")) : "";
			head = inputMap.containsKey("head") ? String.valueOf(inputMap.get("head")) : "";
			subhead = inputMap.containsKey("subhead") ? String.valueOf(inputMap.get("subhead")) : "";
			location = inputMap.containsKey("location") ? String.valueOf(inputMap.get("location")) : "";
			icon = inputMap.containsKey("icon") ? String.valueOf(inputMap.get("icon")) : "";
			seq_no = inputMap.containsKey("seq_no") ? Integer.parseInt(String.valueOf(inputMap.get("seq_no")))
					: 1;
			isactive = inputMap.containsKey("isactive") ? Integer.parseInt(String.valueOf(inputMap.get("isactive")))
					: 1;

			String decodeToken = Util.decodeToken(userdetails);
			createdby = (String) MyObjectMapper.getInstance().readValue(decodeToken, HashMap.class).get("sub");

			// Validate request

			if (!Validation.IsNumeric(id)) {
				responseModel.setStatus(ERROR);
				responseModel.setData(INVALID_ID);
				return responseModel;

			}
			
			if (Validation.IsEmpty(menuname)) {
				responseModel.setStatus(ERROR);
				responseModel.setData("Invalid menu name");
				return responseModel;
			}
			if (Validation.IsEmpty(head)) {
				responseModel.setStatus(ERROR);
				responseModel.setData("Invalid head");
				return responseModel;
			}
			if (Validation.IsEmpty(subhead)) {
				responseModel.setStatus(ERROR);
				responseModel.setData("Invalid sub head");
				return responseModel;
			}
			if (Validation.IsEmpty(location)) {
				responseModel.setStatus(ERROR);
				responseModel.setData("Invalid location");
				return responseModel;
			}
			if (Validation.IsEmpty(seq_no)) {
				responseModel.setStatus(ERROR);
				responseModel.setData("Invalid Sequence");
				return responseModel;
			}
			

			AppDTO appDTO_ = AppDTO.getInstance();
			inputMap = new HashMap<>();

			inputMap.put("process", "manage_nenu");
			inputMap.put("id", id);
			inputMap.put("menucode", menucode);
			inputMap.put("head", head);
			inputMap.put("subhead", subhead);
			inputMap.put("menuname", menuname);
			inputMap.put("location", location);
			inputMap.put("seq_no", seq_no);
			inputMap.put("icon", icon);
			inputMap.put("user", createdby);
			inputMap.put("isactive", isactive);

			Map<String, Object> out = appDTO_.manageDTO(inputMap, jdbcTemplate, MENU);

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

			inputMap.put("process", "get_menus");
			
		
			List<Map<String, Object>> out = appDTO_.getData(inputMap, jdbcTemplate, MENU);

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
			id = inputMap.containsKey("menuId") ? Integer.parseInt(String.valueOf(inputMap.get("menuId"))) : 0;


			if (!Validation.IsNumeric(id)) {
				responseModel.setStatus(ERROR);
				responseModel.setData(INVALID_ID);
				return responseModel;

			}
			
			AppDTO appDTO_ = AppDTO.getInstance();
			inputMap = new HashMap<>();

			inputMap.put("process", "get_menuById");
			inputMap.put("id", id);
			

			List<Map<String, Object>> out = appDTO_.getData(inputMap, jdbcTemplate, MENU);

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

			String menu;
	
			ObjectMapper objectMapper = MyObjectMapper.getInstance();

			Map<String, Object> inputMap = objectMapper.readValue(input, HashMap.class);
			menu = inputMap.containsKey("menuname") ? String.valueOf(inputMap.get("menuname")) : "";
		
			AppDTO appDTO_ = AppDTO.getInstance();
			inputMap = new HashMap<>();

			inputMap.put("process", "get_menusearch");
			inputMap.put("menuname", menu);
			

			List<Map<String, Object>> out = appDTO_.getData(inputMap, jdbcTemplate, MENU);

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
	public ResponseModel getByCatcode(String input, String userdetails) {
		ResponseModel responseModel = ResponseModel.getInstance();

		try {

			String category;
	
			ObjectMapper objectMapper = MyObjectMapper.getInstance();

			Map<String, Object> inputMap = objectMapper.readValue(input, HashMap.class);
			category = inputMap.containsKey("cat_code") ? String.valueOf(inputMap.get("cat_code")) : "";
		
			AppDTO appDTO_ = AppDTO.getInstance();
			inputMap = new HashMap<>();

			inputMap.put("process", "get access control");
			inputMap.put("cat_code", category);
			

			List<Map<String, Object>> out = appDTO_.getData(inputMap, jdbcTemplate, MENUMAPPING);

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
}
