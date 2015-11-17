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
import com.shouvik.bean.BowlingClub;
import com.shouvik.bean.CustomError;
import com.shouvik.bean.League;
import com.shouvik.bean.Lottery;

@RestController
@RequestMapping(value = "/Leagues")
public class searchLotteriesbyLeague {

	@RequestMapping(value = "{leagueid}/getLotteries",method = RequestMethod.GET)
	public ResponseEntity<List<Lottery>> LotteryList(@PathVariable int leagueid){
		
		List<League> listofLeagues = new ArrayList<League>(BowlingClub.leagues);
		
		for(League l : listofLeagues){
			if(l.getID() == leagueid){
				ArrayList<Lottery> lotteries = l.getLotteries();
				
				return new ResponseEntity<>(lotteries, HttpStatus.OK);
			}
		}
		return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
	}
	
	@RequestMapping(value = "{leagueid}/getLottery/{id}", method = RequestMethod.GET)  
	 public ResponseEntity<String> getLotteryeById(@PathVariable int leagueid, @PathVariable int id)  
	 {  
		List<League> listOfLeagues = new ArrayList<League>(BowlingClub.leagues);  
		  	  
		for (League l: listOfLeagues) {  
			if(l.getID()==leagueid){
				ArrayList<Lottery> listOfLotteries = l.getLotteries();
				for (Lottery temp: listOfLotteries){
					if(temp.getID()==id){
						Gson gson = new Gson();
						String response = gson.toJson(temp);
						return new ResponseEntity<>(response, HttpStatus.OK);
					}
				}
				CustomError e = new CustomError("Invalid Lottery ID.");
				Gson gson = new Gson();
				String response = gson.toJson(e);
				return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
		   	}
			     
		}  
		CustomError e = new CustomError("Invalid Lottery ID.");
		Gson gson = new Gson();
		String response = gson.toJson(e);
		return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST); 
	 }  
}
