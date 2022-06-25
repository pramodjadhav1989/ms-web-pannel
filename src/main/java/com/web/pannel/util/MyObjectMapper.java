package com.web.pannel.util;

import com.fasterxml.jackson.databind.ObjectMapper;

public class MyObjectMapper extends ObjectMapper {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private static MyObjectMapper myObjectMapper;
	
	public static MyObjectMapper getInstance() {
		if(myObjectMapper==null)
			myObjectMapper= new MyObjectMapper();
		
		return myObjectMapper;
	}

}
