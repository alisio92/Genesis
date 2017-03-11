package com.alisio.genesis.level;

import java.time.Instant;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;

public class GameTime {
	private int maxTime = 86400; // 24 uur
	private int timeSpeed = 1;
	
	public static int time = 28800; // 8 uur
	public int turnDark = 72000; // 20 uur
	public int turnLight = 25200; // 7 uur
	public boolean day, night;
	
	
	public GameTime() {
	}
	
	public GameTime(int timeSpeed, int turnDark, int turnLight) {
		this.timeSpeed = timeSpeed;
		this.turnDark = turnDark;
		this.turnLight = turnLight;
	}

	public void update() {
		if (time < maxTime) time += timeSpeed;
		else time = 0;
		
		if((time >= turnDark && time <= maxTime) || (time > 0 && time <= turnLight)) {
			day = false;
			night = true;
		}
		if(time >= turnLight && time < turnDark) {
			day = true;
			night = false;
		}
	}

	public void render() {
	}

	public static String getTime() {
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HH:mm").withZone(ZoneId.of("UTC"));
		Instant instant = Instant.ofEpochMilli((long) (time * 1000));
		return formatter.format(instant);
	}
}
