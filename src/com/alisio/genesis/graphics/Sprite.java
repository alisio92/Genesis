package com.alisio.genesis.graphics;

public class Sprite {
	private int width, height;
	private int x,y;
	public int[] pixels;
	private SpriteSheet sheet;
	public int xOffset, yOffset;
	
	public Sprite(int size, int x, int y, SpriteSheet sheet){
		this.width = size;
		this.height = size;
		this.x = x * size;
		this.y = y * size;
		this.sheet = sheet;		
		this.xOffset = 0;
		this.yOffset = 0;
		this.pixels = new int[size*size];
		load();
	}
	
	public Sprite(int width, int height, int x, int y, SpriteSheet sheet){
		this.width = width;
		this.height = height;
		this.x = x * width;
		this.y = y * height;
		this.sheet = sheet;		
		this.xOffset = 0;
		this.yOffset = 0;
		this.pixels = new int[width*height];
		load();
	}
	
	public Sprite(int width, int height, int x, int y,int xOffset, int yOffset, SpriteSheet sheet){
		this.width = width;
		this.height = height;
		this.x = x * width;
		this.y = y * height;
		this.sheet = sheet;		
		this.xOffset = xOffset;
		this.yOffset = yOffset;
		this.pixels = new int[width*height];
		load();
	}
	
	public Sprite(int size, int color){
		this.width = size;
		this.height = size;
		this.xOffset = 0;
		this.yOffset = 0;
		this.pixels = new int[size*size];
		setColor(color);
	}
	
	public Sprite(int width, int height, int color){
		this.width = width;
		this.height = height;
		this.xOffset = 0;
		this.yOffset = 0;
		this.pixels = new int[width*height];
		setColor(color);
	}

	private void setColor(int color) {
		for(int i = 0; i < width * height;i++){
			this.pixels[i] = color;
		}
	}
	
	public int getHeight() {
		return height;
	}
	
	public int getWidth() {
		return width;
	}

	private void load() {
		for(int y = 0; y < height;y++){
			for(int x = 0; x < width;x++){
				this.pixels[x+y*width] = sheet.pixels[(x + this.x + xOffset) + (y  + this.y + yOffset) * sheet.Width];
			}
		}
	}
}
