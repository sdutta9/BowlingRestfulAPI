package com.shouvik.controller;

import java.util.ArrayList;
import java.util.List;

import org.json.JSONObject;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.google.gson.Gson;
import com.shouvik.bean.BowlingClub;
import com.shouvik.bean.CustomError;
import com.shouvik.bean.League;
import com.shouvik.bean.Lottery;

@RestController
public class purchaseTicket {

	@RequestMapping(value = "/purchaseTicket",method = RequestMethod.POST)
	public ResponseEntity<String> buyTicket(@RequestBody String json){
		
		JSONObject obj = new JSONObject(json);
		
		int bid = obj.getInt("bowlerID");
		int lid = obj.getInt("leagueID");
		int lotid = obj.getInt("lotteryID");
		
		if(!BowlingClub.validBowler(bid)){
			CustomError e = new CustomError("Invalid Bowler ID.");
			Gson gson = new Gson();
			String response = gson.toJson(e);
			return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
		}
		
		List<League> listofLeagues = new ArrayList<League>(BowlingClub.leagues);
		
		for(League l : listofLeagues){
			if(l.getID() == lid){
				if(!l.isRegisteredBowler(bid)){
					CustomError e = new CustomError("Bowler is not registered to this league");
					Gson gson = new Gson();
					String response = gson.toJson(e);
					return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
				}
				
				ArrayList<Lottery> lotteries = l.getLotteries();
				for(Lottery temp: lotteries){
					if(temp.getID() == lotid){
						temp.purchaseTicket(bid);
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
		CustomError e = new CustomError("Invalid League ID.");
		Gson gson = new Gson();
		String response = gson.toJson(e);
		return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
	}
}
