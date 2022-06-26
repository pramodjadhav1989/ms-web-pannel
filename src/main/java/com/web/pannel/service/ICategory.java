package com.web.pannel.service;

import com.web.pannel.util.ResponseModel;

public interface ICategory {
public ResponseModel manage_category(String input, String userdetails);
public ResponseModel getAll(String userdetails);
public ResponseModel getById(String input, String userdetails);
public ResponseModel getByName(String input, String userdetails);

}
