package com.web.pannel.dto;


import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.simple.SimpleJdbcCall;
import com.web.pannel.constant.IAppConstant;
import com.web.pannel.constant.IDatabaseConstant;
import com.web.pannel.constant.IErrorConstant;
import com.web.pannel.model.Users;
import com.web.pannel.util.MyObjectMapper;
import com.web.pannel.util.Validation;

import org.postgresql.util.PGobject;

public class AppDTO implements IDatabaseConstant,IErrorConstant,IAppConstant {

	// static Logger logger = //logger.getLogger(AppDTO.class);

	
	private static AppDTO appDTO;

	public static AppDTO getInstance() {
		if (appDTO == null)
			appDTO = new AppDTO();
		return appDTO;

	}

	private String getFunction(String process) {
		try {
			switch(process) {
			case CATEGORY:
				return SP_CATEGORY;
			case USERS:
				return SP_USERS;
			case MENU:
				return SP_MENU;
			case MENUHEADER:
				return SP_MENUHEADER;
			case MENUMAPPING:
				return SP_MENUMAPPING;
				
			
			}
		}catch(Exception e) {
			
		}
		return "";
	}
	
	public List<Map<String, Object>> getData(Map<String, Object> input, JdbcTemplate jdbcTemplate,String process) {
		try {
			Map<String, Object> result= new HashMap<>();
			String sp_name =getFunction(process);
			if(Validation.IsEmpty(sp_name))
			{
				result.put(ERROR, "Invalid process");
				return null;
			}
			
			MyObjectMapper objectMapper = MyObjectMapper.getInstance();

			SimpleJdbcCall jdbc_ = new SimpleJdbcCall(jdbcTemplate).withFunctionName(sp_name).withSchemaName("wissen");
			Map<String, Object> params = new HashMap<>();

			String json = objectMapper.writeValueAsString(input);

			params.put("p_json", json);

			 result = jdbc_.execute(params);
				if (result.get("returnvalue") != null) {
					
					return Arrays.asList(objectMapper.readValue(((String) result.get("returnvalue")), HashMap[].class));
					

				}
			
		} catch (Exception ex) {
			System.out.println(ex.getMessage());
			return null;
		}
		return null;
	}
	public Map<String, Object> manageDTO(Map<String, Object> input, JdbcTemplate jdbcTemplate,String process) {
		try {
			Map<String, Object> result= new HashMap<>();
			String sp_name =getFunction(process);
			if(Validation.IsEmpty(sp_name))
			{
				result.put(ERROR, "Invalid process");
				return result;
			}
			
			MyObjectMapper objectMapper = MyObjectMapper.getInstance();

			SimpleJdbcCall jdbc_ = new SimpleJdbcCall(jdbcTemplate).withFunctionName(sp_name).withSchemaName("wissen");
			Map<String, Object> params = new HashMap<>();

			String json = objectMapper.writeValueAsString(input);

			params.put("p_json", json);
			// params.put("p_json", json);

			 result = jdbc_.execute(params);
				if (result.get("returnvalue") != null) {
					
					//Arrays.asList(objectMapper.readValue(((String) result.get("returnvalue")), HashMap[].class));
					result = objectMapper.readValue(((String) result.get("returnvalue")), HashMap.class);

					// logger.info("ManageApplication end ");
					// logger.info("ManageApplication end ");

				}
			return result;

		} catch (Exception ex) {
			System.out.println(ex.getMessage());
			// logger.error("Error : ManageApplication" + ex.getMessage());
			return null;
		}
	}
	
}
