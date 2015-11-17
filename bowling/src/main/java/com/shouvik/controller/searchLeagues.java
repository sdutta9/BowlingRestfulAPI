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

@RestController
@RequestMapping(value = "/Leagues")
public class searchLeagues {

	@RequestMapping(value = "/getLeagues",method = RequestMethod.GET)
	public ResponseEntity<List<League>> bowlerList(){
		List<League> listofLeagues=new ArrayList<League>(BowlingClub.leagues);
		
		return new ResponseEntity<>(listofLeagues, HttpStatus.OK);
	}
	
	@RequestMapping(value = "/getLeague/{id}", method = RequestMethod.GET)  
	 public ResponseEntity<String> getLeagueById(@PathVariable int id)  
	 {  
		  List<League> listOfLeagues = new ArrayList<League>(BowlingClub.leagues);  
		  	  
		  for (League l: listOfLeagues) {  
			  if(l.getID()==id){
				  Gson gson = new Gson();
				  String response = gson.toJson(l);
				  return new ResponseEntity<>(response, HttpStatus.OK);
			  }   
		  }  
		  CustomError e = new CustomError("Invalid League ID.");
		  Gson gson = new Gson();
		  String response = gson.toJson(e);
		  return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);    
	 }  
}
