package com.alisio.genesis.graphics;

public class Environment {
	
	public static int changeBrightness(int col, int amount) {
		int r = (col & 0xff0000) >> 16;
		int g = (col & 0xff00) >> 8;
		int b = (col & 0xff);
		
		if(amount > 0) amount = 0;
		if(amount < -150) amount = -150;
		
		r += amount;
		g += amount;
		b += amount;		
		
		if(r < 0) r = 0;
		if(g < 0) g = 0;
		if(b < 0) b = 0;
		
		if(r > 255) r = 255;
		if(g > 255) g = 255;
		if(b > 255) b = 255;
		
		return r << 16 | g << 8 | b;
	}
}
