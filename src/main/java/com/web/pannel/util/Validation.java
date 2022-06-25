package com.web.pannel.util;

import org.springframework.util.StringUtils;

import software.amazon.ion.Decimal;

public class Validation {

	private static Validation validations;
	
	public static Validation getInstance() {
		if(validations==null)
			validations= new Validation();
		
		return validations;
			
	}
	
	public static boolean IsEmpty(Object in) {
		try {
			return StringUtils.isEmpty(in);
			
		}catch(Exception ex){
			return true;
		}
	}
	public static boolean IsNumeric(Object in) {
		try {
			Integer.parseInt(in.toString());
			return true;
		}catch(Exception ex){
			return false;
		}
	}
	
	public static boolean IsDecimal(Object in) {
		try {
			Float.parseFloat((in.toString()));
			return true;
		}catch(Exception ex){
			return false;
		}
	}
	
	public static boolean isStringOnlyAlphabet(String str) 
	{
	    return ((!str.equals(""))
	            && (str != null)
	            && (str.matches("^[a-zA-Z]*$")));
	}
}
