package com.shouvik.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.shouvik.bean.Bowler;
import com.shouvik.bean.BowlingClub;
import com.shouvik.bean.League;

@RestController
@RequestMapping(value = "/Leagues")
public class searchBowlersbyLeague {

	@RequestMapping(value = "{leagueid}/getBowlers",method = RequestMethod.GET)
	public ResponseEntity<List<Bowler>> bowlerList(@PathVariable int leagueid){
		List<Bowler> listofBowlers=new ArrayList<Bowler>(BowlingClub.bowlers);
		List<League> listofLeagues = new ArrayList<League>(BowlingClub.leagues);
		
		for(League l : listofLeagues){
			if(l.getID() == leagueid){
				ArrayList<Integer> bowlers = l.getBowlers();
				List<Bowler> result = new ArrayList<>();
				for(Bowler b: listofBowlers){
					if(bowlers.contains(b.getID())){
						result.add(b);
					}
				}
				return new ResponseEntity<>(result, HttpStatus.OK);
			}
		}
		return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
	}
}
