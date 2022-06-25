package com.web.pannel.serviceImpl;

import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.web.pannel.constant.IAppConstant;
import com.web.pannel.constant.IDatabaseConstant;
import com.web.pannel.constant.IErrorConstant;
import com.web.pannel.dto.AppDTO;
import com.web.pannel.service.ICategory;
import com.web.pannel.service.IUser;
import com.web.pannel.util.JwtTokenUtil;
import com.web.pannel.util.MyObjectMapper;
import com.web.pannel.util.ResponseModel;
import com.web.pannel.util.Security;
import com.web.pannel.util.Validation;
import com.fasterxml.jackson.databind.ObjectMapper;


@Service
public class UserServiceImpl implements IAppConstant, IErrorConstant, IUser, IDatabaseConstant {
	@Autowired
	private PasswordEncoder bEncryptor;

	@Autowired
	private JdbcTemplate jdbcTemplate;

	@Autowired
	private AuthenticationManager authenticationManager;
	@Autowired
	private JwtTokenUtil jwtTokenUtil;

	@Autowired
	private Security security;

	
	

	
	@Override
	public ResponseModel register(String input) {
		ResponseModel responseModel = ResponseModel.getInstance();
		try {
			String userid = null;
			String name = null;
			String password = null;
			String email = null;
			String createdBy = null;
			String senior = null;
			String cat_code = null;
			String division = null;
			int id = 0;
			int isactive = 0;

			String confirmpwd = null;

			ObjectMapper objectMapper = MyObjectMapper.getInstance();
			Map<String, Object> result = objectMapper.readValue(input, HashMap.class);
			id = result.containsKey("id") ? Integer.parseInt(result.get("id").toString()) : 0;
			userid = result.containsKey("userId") ? result.get("userId").toString() : null;
			password = result.containsKey("password") ? result.get("password").toString() : null;
			name = result.containsKey("name") ? result.get("name").toString() : null;
			email = result.containsKey("email") ? result.get("email").toString() : "";
			createdBy = result.containsKey("createdBy") ? result.get("createdBy").toString() : "";
			senior = result.containsKey("senior") ? result.get("senior").toString() : "";
			cat_code = result.containsKey("cat_code") ? result.get("cat_code").toString() : "";
			division = result.containsKey("division") ? result.get("division").toString() : "";
			confirmpwd = result.containsKey("confirmpwd") ? result.get("confirmpwd").toString() : null;
			isactive = result.containsKey("isactive") ? Integer.parseInt(result.get("isactive").toString()) : 0;

			// Validate request
			if (Validation.IsEmpty(userid)) {
				responseModel.setStatus(ERROR);
				responseModel.setData(INVALID_USER_ID);
				return responseModel;
			}
			if (Validation.IsEmpty(name)) {
				responseModel.setStatus(ERROR);
				responseModel.setData(INVALID_NAME);
				return responseModel;
			}
			if (Validation.IsEmpty(email)) {
				responseModel.setStatus(ERROR);
				responseModel.setData(INVALID_EMAIL);
				return responseModel;
			}
			if (Validation.IsEmpty(senior)) {
				responseModel.setStatus(ERROR);
				responseModel.setData(INVALID_SENIOR);
				return responseModel;
			}
			if (Validation.IsEmpty(cat_code)) {
				responseModel.setStatus(ERROR);
				responseModel.setData(INVALID_CATCODE);
				return responseModel;
			}
			if(id==0) {
				if (Validation.IsEmpty(password)) {
					responseModel.setStatus(ERROR);
					responseModel.setData(INVALID_PWD);
					return responseModel;
				}
				if (!password.equals(confirmpwd)) {
					responseModel.setStatus(ERROR);
					responseModel.setData(INVALID_REPWD);
					return responseModel;
				}	
			}
			

			//

			AppDTO appDTO_ = AppDTO.getInstance();
			Map<String, Object> inputMap = new HashMap<>();

			inputMap.put("process", "manage_user");
			inputMap.put("userid", userid);
			inputMap.put("id", id);
			inputMap.put("name", name);
			inputMap.put("email", email);
			if(id==0) {
			inputMap.put("password", bEncryptor.encode(password));
			}
			inputMap.put("senior", senior);
			inputMap.put("cat_code", cat_code);
			inputMap.put("user", createdBy);
			inputMap.put("division", division);
			inputMap.put("isactive", isactive);

			Map<String, Object> out = appDTO_.manageDTO(inputMap, jdbcTemplate, USERS);

			if (out == null) {
				responseModel.setStatus(ERROR);
				responseModel.setData(WENTWRONG);
			} else {

				responseModel.setData(out.get("message"));
				responseModel.setStatus((String) out.get("status"));

				return responseModel;

			}

		} catch (Exception ex) {
			responseModel.setStatus(ERROR);
			responseModel.setData(WENTWRONG);
			// logger.error("MasterController: |GetAllApllication Entry :input " + input + "
			// Error" + ex.getMessage());
		}
		// TODO Auto-generated method stub
		return responseModel;
	}

	

}
