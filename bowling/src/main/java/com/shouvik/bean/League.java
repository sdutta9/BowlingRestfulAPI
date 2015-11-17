package com.shouvik.bean;

import java.util.ArrayList;

public class League {
	private int ID;
	private String name;	
	private ArrayList<Integer> bowlers;
	private ArrayList<Lottery> lotteries;
	
	public League(String n){
		this.ID = IdGenerator.newLeagueID();
		name = n;
		bowlers = new ArrayList<Integer>();
		lotteries = new ArrayList<Lottery>();
	}
	
	public League(){
		this.ID = IdGenerator.newLeagueID();
		this.setName("default league");
		bowlers = new ArrayList<Integer>();
		lotteries = new ArrayList<Lottery>();		
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
	
	public Lottery addLottery(String name){
		Lottery newlottery = new Lottery(lotteries.size()+1, name);
		lotteries.add(newlottery);
		return newlottery;
	}
	
	public ArrayList<Lottery> getLotteries(){
		return lotteries;
	}
	
	public void addBowler(int bowlerID){
		bowlers.add(bowlerID);
	}
	
	public ArrayList<Integer> getBowlers(){
		return bowlers;
	}	
	public boolean isRegisteredBowler(int bowlerID){
		for(int bid: bowlers){
			if(bid == bowlerID)
				return true;
		}
		return false;
	}
}
