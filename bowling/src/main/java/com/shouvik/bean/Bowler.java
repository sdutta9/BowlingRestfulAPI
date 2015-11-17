package com.shouvik.bean;

import java.util.Date;

public class Bowler {
	private String name;
	private int ID;
	private String DOJ;
	
	public Bowler(String n){
		name = n;
		this.ID = IdGenerator.newbowlerID();
		this.DOJ = new Date().toString();
	}
	
	public Bowler(){
		this.setName("shouvik");
		this.ID = IdGenerator.newbowlerID();
		this.setDOJ(new Date().toString());
	}

	public String getName() {
		return name;
	}
	
	public int getID() {
		return ID;
	}

	public void setName(String name) {
		this.name = name;
	}
	public void updateInfo(String name){
		this.name = name;
	}

	public String getDOJ() {
		return DOJ;
	}

	public void setDOJ(String dOJ) {
		DOJ = dOJ;
	}
}
