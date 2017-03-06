package com.alisio.genesis.console;

import java.awt.Color;
import java.awt.Font;

public class Console {
	public int x, y;
	public Font font;
	public Color color;
	public int size;
	public String message;
	public Boolean visible = false;
	
	public Console() {
		this.x = 0;
		this.y = 0;
		this.size = 16;
		this.font = new Font("Verdena",0,size);
		this.color = Color.WHITE;
	}
	
	public Console(int x, int y) {
		this.x = x;
		this.y = y;
		this.size = 16;
		this.font = new Font("Verdena",0,size);
		this.color = Color.WHITE;
	}
	
	public Console(int x, int y, int size, String font, Color color) {
		this.x = x;
		this.y = y;
		this.size = size;
		this.font = new Font(font,0,size);
		this.color = color;
	}
}
