package com.shouvik.bean;

public class CustomError {

	private String desc;
	
	public CustomError(){
		desc="default desc";
	}
	
	public CustomError(String text){
		desc = text;
	}
	public String getDesc(){
		return desc;
	}
}
