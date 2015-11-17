package com.shouvik.bean;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Random;
//import java.util.Date;


public class Lottery {
	private String name;
	private int ID;
	private double Jackpot_balance;
	private ArrayList<Integer> bowler_tickets;
	Bowler weekly_winner = null;
	//private Date drawDate;
	
	public Lottery(int ID, String n){
		this.ID = ID;
		name = n;		
		Jackpot_balance = 0;
		bowler_tickets=new ArrayList<Integer>();
	}
	
	public Lottery(){
		ID = -1;
		this.setName("Default Lottery");
		Jackpot_balance = 0;
		bowler_tickets=new ArrayList<Integer>();
		//drawDate = new Date();
	}
	
	public void purchaseTicket(int bowlerId){
		bowler_tickets.add(bowlerId);
		Jackpot_balance+=1;
	}
	public double currentJackpotAmount(){
		return Jackpot_balance;
	}
	public HashMap<Integer, Integer> allTickets(){
		
		HashMap<Integer, Integer> result = new HashMap<>();
		for(Integer b: bowler_tickets){
			if(result.containsKey(b))
				result.put(b, result.get(b)+1);
			else
				result.put(b,1);
		}
		return result;
	}
	public int totalTickets(int bowlerID){
		int result = 0;
		for(int b : bowler_tickets){
			if(b==bowlerID)
				result+=1;
		}
		return result;
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
	
	public boolean isBowlerParticipant(int bowlerID){
		for(int b : bowler_tickets){
			if(b == bowlerID)
				return true;
		}
		return false;
	}
	
	public Bowler drawWinner(){
		Random rand = new Random();
		int random_idx = rand.nextInt(bowler_tickets.size());
		int bID = bowler_tickets.get(random_idx);
		List<Bowler> listOfBowlers = new ArrayList<Bowler>(BowlingClub.bowlers);
		
		for(Bowler b: listOfBowlers){
			if(b.getID() == bID){
				weekly_winner = b;
				break;
			}
		}
		return weekly_winner;
	}
	public boolean isWinner(int bowlerID){
		if(weekly_winner.getID() == bowlerID)
			return true;
		else
			return false;
	}
	
	public Payment throwStrike(int leagueID){
		Random rand = new Random();
		int random_number = rand.nextInt(7)+1;
		
		double win_amt= 0;
		if(random_number == 7)
			win_amt=Jackpot_balance;
		else
			win_amt = 0.1 *Jackpot_balance;
		
		Jackpot_balance-=win_amt;
		Payment p =new Payment(leagueID,this.ID, weekly_winner.getID(),random_number,win_amt,Jackpot_balance);	
		BowlingClub.payments.add(p);
		
		bowler_tickets.clear();
		weekly_winner = null;
		
		
		
		return p;
	}
	
	
}
