package com.alisio.genesis.graphics;

public class Sprite {
	private int width, height;
	private int startX, startY;
	private int x,y;
	public int[] pixels;
	private SpriteSheet sheet;
	public int xOffset, yOffset;
	private int scale = 1;
	
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
	
	public Sprite(int size, int x, int y, SpriteSheet sheet, int scale){
		this.width = size;
		this.height = size;
		this.x = x * size;
		this.y = y * size;
		this.sheet = sheet;		
		this.xOffset = 0;
		this.yOffset = 0;
		this.scale = scale;
		this.pixels = new int[size * scale * size * scale];
		load();
	}
	
	public Sprite(int size, int x, int y, int startX, int startY,SpriteSheet sheet, int scale){
		this.width = size;
		this.height = size;
		this.x = x * size;
		this.y = y * size;
		this.sheet = sheet;		
		this.xOffset = 0;
		this.yOffset = 0;
		this.scale = scale;
		this.startX = startX;
		this.startY = startY;
		this.pixels = new int[size * scale * size * scale];
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
		return height * scale;
	}
	
	public int getWidth() {
		return width * scale;
	}
	
	public int getStartX() {
		return startX;
	}
	
	public int getStartY() {
		return startY;
	}

	private void load() {
		for(int y = 0; y < height;y++){
			for(int x = 0; x < width;x++){
				this.pixels[(x * scale) + (y * scale) * width * scale] = sheet.pixels[(x + this.x + xOffset) + (y  + this.y + yOffset) * sheet.Width];
								
				for (int i = 1; i < scale;i++){
					this.pixels[((x * scale) + i) + ((y * scale)) * width * scale] = sheet.pixels[(x + this.x + xOffset) + (y  + this.y + yOffset) * sheet.Width];
					this.pixels[((x * scale) + i) + ((y * scale) + i) * width * scale] = sheet.pixels[(x + this.x + xOffset) + (y  + this.y + yOffset) * sheet.Width];
					this.pixels[((x * scale)) + ((y * scale) + i) * width * scale] = sheet.pixels[(x + this.x + xOffset) + (y  + this.y + yOffset) * sheet.Width];
				}
			}
		}
	}
}
