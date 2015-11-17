package com.shouvik.bean;

import java.util.concurrent.atomic.AtomicInteger;

public class IdGenerator {
	private static AtomicInteger bowlerID = new AtomicInteger(1);
	private static AtomicInteger leagueID = new AtomicInteger(1);
	public static int newbowlerID() {
		return bowlerID.getAndIncrement();
	}
	public static int newLeagueID() {
		return leagueID.getAndIncrement();
	}

}
