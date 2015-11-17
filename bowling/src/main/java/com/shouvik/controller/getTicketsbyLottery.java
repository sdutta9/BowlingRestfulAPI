package com.shouvik.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.google.gson.Gson;
import com.shouvik.bean.BowlingClub;
import com.shouvik.bean.CustomError;
import com.shouvik.bean.League;
import com.shouvik.bean.Lottery;

@RestController
@RequestMapping(value = "/Leagues")
public class getTicketsbyLottery {

	@RequestMapping(value = "{leagueid}/Lottery/{lotteryid}/getPurchasedTickets", method = RequestMethod.GET)  
	 public ResponseEntity<String> getPurchasedTickets(@PathVariable int leagueid, @PathVariable int lotteryid)  
	 {  
		List<League> listOfLeagues = new ArrayList<League>(BowlingClub.leagues);  
		  	  
		for (League l: listOfLeagues) {  
			if(l.getID()==leagueid){
				ArrayList<Lottery> listOfLotteries = l.getLotteries();
				for (Lottery temp: listOfLotteries){
					if(temp.getID()==lotteryid){
						HashMap<Integer, Integer> result = temp.allTickets();
						Gson gson = new Gson();
						String response = gson.toJson(result);
						return new ResponseEntity<>(response, HttpStatus.OK);
					}
				}
				CustomError e = new CustomError("Invalid Lottery ID.");
				Gson gson = new Gson();
				String response = gson.toJson(e);
				return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
		   	}
			     
		}  
		CustomError e = new CustomError("Invalid League ID.");
		Gson gson = new Gson();
		String response = gson.toJson(e);
		return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST); 
	 }  
	
	@RequestMapping(value = "{leagueid}/Lottery/{lotteryid}/getPurchasedTicket/{bowlerid}", method = RequestMethod.GET)  
	 public ResponseEntity<String> getPurchasedTicketsByBowlerID(@PathVariable int leagueid, @PathVariable int lotteryid, @PathVariable int bowlerid)  
	 {  
		if(!BowlingClub.validBowler(bowlerid)){
			CustomError e = new CustomError("Invalid Bowler ID.");
			Gson gson = new Gson();
			String response = gson.toJson(e);
			return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
	    }
		List<League> listOfLeagues = new ArrayList<League>(BowlingClub.leagues);  
		  	  
		for (League l: listOfLeagues) {  
			if(l.getID()==leagueid){
				ArrayList<Lottery> listOfLotteries = l.getLotteries();
				for (Lottery temp: listOfLotteries){
					if(temp.getID()==lotteryid){
						if(!temp.isBowlerParticipant(bowlerid)){
							CustomError e = new CustomError("Bowler has not participated in the lottery for this week.");
							Gson gson = new Gson();
							String response = gson.toJson(e);
							return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
						}
						int result = temp.totalTickets(bowlerid);
						Gson gson = new Gson();
						String response = gson.toJson(result);
						return new ResponseEntity<>(response, HttpStatus.OK);
					}
				}
				CustomError e = new CustomError("Invalid Lottery ID.");
				Gson gson = new Gson();
				String response = gson.toJson(e);
				return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
		   	}
			     
		}  
		CustomError e = new CustomError("Invalid League ID.");
		Gson gson = new Gson();
		String response = gson.toJson(e);
		return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST); 
	 } 
}

