package com.web.pannel.serviceImpl;

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
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.web.pannel.constant.IAppConstant;
import com.web.pannel.constant.IDatabaseConstant;
import com.web.pannel.constant.IErrorConstant;
import com.web.pannel.dto.AppDTO;
import com.web.pannel.model.Users;
import com.web.pannel.service.IAuthenticationService;
import com.web.pannel.util.JwtRequest;
import com.web.pannel.util.JwtTokenUtil;
import com.web.pannel.util.MyObjectMapper;
import com.web.pannel.util.ResponseModel;
import com.web.pannel.util.Security;

@Service
public class AuthenticationServiceImpl
		implements UserDetailsService, IAuthenticationService, IAppConstant, IDatabaseConstant, IErrorConstant {
//Fetch users details from database 

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

	public org.springframework.security.core.userdetails.UserDetails loadUserByUsername(String username) {

		// UserDetails userdetails=UserDetails.getInstance();
		boolean validate = false;
		Users user = getUserObject(username);
		if (user != null) {

			validate = true;
			return new org.springframework.security.core.userdetails.User(user.getUserid(), user.getPassword(),
					new ArrayList<>());

		}
		if (!validate) {
			throw new UsernameNotFoundException("User not found with username: " + username);
		}
		return null;

	}

	public Users getUserObject(String username) {
		try {

			Map<String, Object> inputMap = new HashMap<>();
			inputMap.put("process", "getuser");
			inputMap.put("userid", username);
			AppDTO appDTO_ = AppDTO.getInstance();
			List<Map<String, Object>> out = appDTO_.getData(inputMap, jdbcTemplate, USERS);
			if (out != null) {
				if (out.size() > 0) {
					ObjectMapper mapper = MyObjectMapper.getInstance();
					return mapper.convertValue(out.get(0), Users.class);
				}
			}
			return null;
		} catch (Exception ex) {
			return null;
		}
	}

	@Override
	public ResponseModel getUser(String input) {
		// TODO Auto-generated method stub
		// logger.info("MasterController: |GetAllApllication Entry :input " + input);
		ResponseModel responseModel = ResponseModel.getInstance();
		try {
			String userid = null;

			ObjectMapper objectMapper = MyObjectMapper.getInstance();
			Map<String, Object> result = objectMapper.readValue(input, HashMap.class);
			userid = result.containsKey("userId") ? result.get("userId").toString() : null;
			Users out = getUserObject(userid);
			try {
				// String arr[] = out.getPhoto().split("/");
				// String f = arr[arr.length - 1];
				// String p_ = storageService.getS3File(f, out.getPhoto());
				// out.setPhoto_(p_);
			} catch (Exception ex) {
				System.out.println(ex.getMessage());
			}

			if (out == null) {
				responseModel.setStatus(ERROR);
				responseModel.setData(WENTWRONG);
			} else {
				responseModel.setStatus(SUCCESS);
				responseModel.setData(out);
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

	private ResponseModel authenticate(String username, String password) throws Exception {
		ResponseModel responseModel = ResponseModel.getInstance();
		try {
			authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(username, password));
			responseModel.setStatus(SUCCESS);
			responseModel.setData("Authentication done.");
		} catch (DisabledException e) {
			responseModel.setStatus(ERROR);
			responseModel.setData(e.getMessage());
			// throw new Exception("USER_DISABLED", e);
		} catch (BadCredentialsException e) {
			responseModel.setStatus(ERROR);
			responseModel.setData(e.getMessage());
		}
		return responseModel;
	}

	@Override
	public ResponseModel userValidate(String input) {
		ResponseModel responseModel = ResponseModel.getInstance();
		try {
			String userid = null;

			ObjectMapper objectMapper = MyObjectMapper.getInstance();
			Map<String, Object> result = objectMapper.readValue(input, HashMap.class);
			userid = result.containsKey("data") ? result.get("data").toString() : null;

			if (userid == null) {
				responseModel.setStatus(ERROR);
				responseModel.setData(INVALID_INPUT);
				return responseModel;
			}

			try {
				userid = security.decrypt(userid);
			} catch (Exception ex) {
				responseModel.setStatus(ERROR);
				responseModel.setData(INVALID_INPUT);
				return responseModel;
			}

			Users user = getUserObject(userid);

			if (user == null) {
				responseModel.setStatus(ERROR);
				responseModel.setData(INVALID_USER_ID);
				return responseModel;
			}

			// Load Template
			Map<String, Object> resultMap = new HashMap<>();
			resultMap.put("name", user.getName());
			resultMap.put("userid", user.getUserid());

			responseModel.setStatus(SUCCESS);
			responseModel.setData(resultMap);
			// user.setBirthdate(dob.toString());

		} catch (Exception ex) {
			responseModel.setStatus(ERROR);
			responseModel.setData(WENTWRONG);
			// logger.error("MasterController: |GetAllApllication Entry :input " + input + "
			// Error" + ex.getMessage());
		}
		// TODO Auto-generated method stub
		return responseModel;
	}

	@Override
	public ResponseModel login(String input) {
		ResponseModel responseModel = ResponseModel.getInstance();
		try {
			MyObjectMapper objectMapper = MyObjectMapper.getInstance();
			// UserLoginLog userlog = UserLoginLog.getInstance();
			Map<String, Object> result = objectMapper.readValue(input, HashMap.class);
			String userid = result.containsKey("userId") ? result.get("userId").toString() : null;
			String password = result.containsKey("password") ? result.get("password").toString() : null;
			// String deviceId = result.containsKey("deviceId") ?
			// result.get("deviceId").toString() : null;
			String application = result.containsKey("application") ? result.get("application").toString() : null;

			JwtRequest authenticationRequest = JwtRequest.getInstance();
			authenticationRequest.setPassword(password);
			authenticationRequest.setUsername(userid);
			responseModel = authenticate(authenticationRequest.getUsername(), authenticationRequest.getPassword());
			if (responseModel.getStatus().equals(ERROR)) {
				return responseModel;
			}
			final UserDetails userDetails = loadUserByUsername(authenticationRequest.getUsername());

//			TokenMap.put("token", token);
//			TokenMap.put("issueAt", issueAt);
//			TokenMap.put("expiry", expiry);	

			final Map<String, Object> token = jwtTokenUtil.generateToken(userDetails);

			responseModel.setData(token.get("token"));
			responseModel.setStatus(SUCCESS);

			// Login Log
			AppDTO appDTO_ = AppDTO.getInstance();
//			userlog.setUsername(userid);
//			userlog.setDevice(deviceId);
//			userlog.setLastlogin(token.get("issueAt").toString());
//			userlog.setValidupto(token.get("expiry").toString());
//			userlog.setApplication(application);
//			appDTO_.userLog(userlog, "login", jdbcTemplate);
		} catch (Exception ex) {
			responseModel.setStatus(ERROR);
			responseModel.setData(WENTWRONG);
		}
		return responseModel;
	}

}
