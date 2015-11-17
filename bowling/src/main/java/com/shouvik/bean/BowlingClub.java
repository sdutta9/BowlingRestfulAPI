package com.shouvik.bean;

import java.util.HashSet;

public class BowlingClub{

	public static HashSet<Bowler> bowlers = new HashSet<Bowler>();
	public static HashSet<League> leagues = new HashSet<League>();
	public static HashSet<Payment> payments = new HashSet<Payment>();
	
	public static boolean validBowler(int bowlerID){
		for (Bowler b: bowlers){
			if(b.getID() == bowlerID)
				return true;
		}
		return false;
	}
	
	public static boolean validLeague(int leagueID){
		for (League l: leagues){
			if(l.getID() == leagueID)
				return true;
		}
		return false;
	}

}
