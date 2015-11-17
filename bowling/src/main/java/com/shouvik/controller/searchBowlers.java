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

@RestController
@RequestMapping(value = "/Bowlers")
public class searchBowlers {

	@RequestMapping(value = "/getBowlers",method = RequestMethod.GET)
	public ResponseEntity<List<Bowler>> bowlerList(){
		List<Bowler> listofBowlers=new ArrayList<Bowler>(BowlingClub.bowlers);
		return new ResponseEntity<>(listofBowlers, HttpStatus.OK);
	}
	
	@RequestMapping(value = "/getBowler/{id}", method = RequestMethod.GET)  
	 public ResponseEntity<String> getBowlerById(@PathVariable int id)  
	 {  
		  List<Bowler> listOfBowlers = new ArrayList<Bowler>(BowlingClub.bowlers);  
		  	  
		  for (Bowler b: listOfBowlers) {  
			  if(b.getID()==id) { 
				  Gson gson = new Gson();
				  String response = gson.toJson(b);
				  return new ResponseEntity<>(response, HttpStatus.OK); 
			  }
		  }  
		  CustomError e = new CustomError("Invalid Bowler ID.");
		  Gson gson = new Gson();
		  String response = gson.toJson(e);
		  return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);  
	 }  
}
