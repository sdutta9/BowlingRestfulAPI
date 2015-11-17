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
import com.shouvik.bean.BowlingClub;
import com.shouvik.bean.League;

@RestController
public class createLeague {

	private League l;
	
	@RequestMapping(value = "/createLeague",method = RequestMethod.POST)
	public @ResponseBody ResponseEntity<String> addLeague(@RequestBody String json) {
		
		JSONObject obj = new JSONObject(json);
		String Name = obj.getString("name");
		l = new League(Name);
		BowlingClub.leagues.add(l);

		Gson gson = new Gson();
		String response = gson.toJson(l);
		return new ResponseEntity<>(response,HttpStatus.OK);
	}
}
