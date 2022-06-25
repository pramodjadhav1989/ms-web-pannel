package com.web.pannel.serviceImpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.stereotype.Service;

import com.web.pannel.constant.IAppConstant;
import com.web.pannel.service.ICategory;
import com.web.pannel.util.JwtTokenUtil;
import com.web.pannel.util.ResponseModel;
import com.web.pannel.util.Security;


@Service
public class CategoryImpl implements ICategory,IAppConstant {

	@Autowired
	private JdbcTemplate jdbcTemplate;

	@Autowired
	private AuthenticationManager authenticationManager;
	@Autowired
	private JwtTokenUtil jwtTokenUtil;

	@Autowired
	private Security security;

	@Override
	public ResponseModel manage_category() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ResponseModel getAll() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ResponseModel getById() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ResponseModel getByName() {
		// TODO Auto-generated method stub
		return null;
	}

}
