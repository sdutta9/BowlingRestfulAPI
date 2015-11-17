package com.shouvik.bean;

import java.util.Date;

public class Payment {
	private int leagueID;
	private int lotteryID;
	private int bowlerID;
	private int roll;
	private double winning_amount;
	private double remaining_balance;
	private String DrawDate;
	
	public Payment(int lid, int lotid, int bid, int roll, double win_amt, double rem_bal){
		leagueID = lid;
		lotteryID = lotid;
		bowlerID = bid;
		this.roll = roll;
		winning_amount = win_amt;
		remaining_balance =  rem_bal;
		this.DrawDate = new Date().toString();
	}
	
	public Payment(){
		leagueID = 0;
		lotteryID = 0;
		bowlerID = 0;
		roll = 0;
		winning_amount = 0;
		remaining_balance =  0;
		this.DrawDate = new Date().toString();
	}

	public int getLeagueID() {
		return leagueID;
	}

	public int getLotteryID() {
		return lotteryID;
	}

	public int getBowlerID() {
		return bowlerID;
	}

	public double getWinning_amount() {
		return winning_amount;
	}

	public double getRemaining_balance() {
		return remaining_balance;
	}

	public String getDrawDate() {
		return DrawDate;
	}
	
	public int getRoll(){
		return roll;
	}
}
