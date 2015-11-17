package com.shouvik.controller;

import java.util.ArrayList;
import java.util.List;

import org.json.JSONObject;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.google.gson.Gson;
import com.shouvik.bean.BowlingClub;
import com.shouvik.bean.CustomError;
import com.shouvik.bean.League;
import com.shouvik.bean.Lottery;

@RestController
public class createLottery {

	@RequestMapping(value = "/createLottery",method = RequestMethod.POST)
	public @ResponseBody ResponseEntity<String> addLottery(@RequestBody String json) {
		
		JSONObject obj = new JSONObject(json);
		int lid = obj.getInt("leagueID");
		String Name = obj.getString("name");
		List<League> listofLeagues = new ArrayList<League>(BowlingClub.leagues);
		for(League l: listofLeagues){
			if(l.getID() == lid ){
				Lottery temp = l.addLottery(Name);
				Gson gson = new Gson();
				String response = gson.toJson(temp);
				return new ResponseEntity<>(response,HttpStatus.OK);
			}
		}
		CustomError e = new CustomError("Invalid League ID.");
		Gson gson = new Gson();
		String response = gson.toJson(e);
		return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
	}
}
