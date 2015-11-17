package com.shouvik.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.google.gson.Gson;
import com.shouvik.bean.Bowler;
import com.shouvik.bean.BowlingClub;
import com.shouvik.bean.CustomError;
import com.shouvik.bean.League;
import com.shouvik.bean.Lottery;
import com.shouvik.bean.Payment;

@RestController
@RequestMapping(value = "/Leagues")
public class lotteryWinner {

	@RequestMapping(value = "{leagueid}/Lottery/{lotteryid}/drawWinner", method = RequestMethod.GET)  
	 public ResponseEntity<String> drawWinner(@PathVariable int leagueid, @PathVariable int lotteryid)  
	 {  
		List<League> listOfLeagues = new ArrayList<League>(BowlingClub.leagues);  
		  	  
		for (League l: listOfLeagues) {  
			if(l.getID()==leagueid){
				ArrayList<Lottery> listOfLotteries = l.getLotteries();
				for (Lottery temp: listOfLotteries){
					if(temp.getID()==lotteryid){
						Bowler winner = temp.drawWinner();
						Gson gson = new Gson();
						String response = gson.toJson(winner);
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
	
	@RequestMapping(value = "{leagueid}/Lottery/{lotteryid}/throwStrike/{bowlerid}", method = RequestMethod.GET)
	public ResponseEntity<String> throwStrike(@PathVariable int leagueid, @PathVariable int lotteryid, @PathVariable int bowlerid)  
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
				for (Lottery lottery: listOfLotteries){
					if(lottery.getID()==lotteryid){
						if(!lottery.isWinner(bowlerid)){
							CustomError e = new CustomError("You cannot throw strike as you are not this weeks winner.");
							Gson gson = new Gson();
							String response = gson.toJson(e);
							return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
						}
						
						Payment p = lottery.throwStrike(leagueid);
						Gson gson = new Gson();
						String response = gson.toJson(p);
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

