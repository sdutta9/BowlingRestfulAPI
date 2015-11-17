package com.shouvik.controller;

import org.json.JSONObject;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.google.gson.Gson;
import com.shouvik.bean.Bowler;
import com.shouvik.bean.BowlingClub;

@RestController
public class createBowler {

	private Bowler b;
	
	@RequestMapping(value = "/createBowler", method = RequestMethod.POST)
	public @ResponseBody ResponseEntity<String> addBowler(@RequestBody String json) {
		
		JSONObject obj = new JSONObject(json);
		String Name = obj.getString("name");
		b= new Bowler(Name);
		BowlingClub.bowlers.add(b);
		Gson gson = new Gson();
		String response = gson.toJson(b);
		return new ResponseEntity<>(response,HttpStatus.OK);
	}
}
