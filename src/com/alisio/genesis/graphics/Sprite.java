package com.alisio.genesis.graphics;

public class Sprite {
	private int width, height;
	private int startX, startY;
	private int x,y;
	public int[] pixels;
	protected SpriteSheet sheet;
	public int xOffset, yOffset;
	private int scale = 1;
	
	protected Sprite(SpriteSheet sheet, int width, int height) {
		this.width = width;
		this.height = height;
		this.sheet = sheet;
	}
	
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

	public Sprite(int[] pixels, int width, int height) {
		this.width = width;
		this.height = height;
		this.pixels = pixels;
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
				this.pixels[(x * scale) + (y * scale) * width * scale] = sheet.pixels[(x + this.x + xOffset) + (y  + this.y + yOffset) * sheet.getWidth()];
								
				for (int i = 1; i < scale;i++){
					this.pixels[((x * scale) + i) + ((y * scale)) * width * scale] = sheet.pixels[(x + this.x + xOffset) + (y  + this.y + yOffset) * sheet.getWidth()];
					this.pixels[((x * scale) + i) + ((y * scale) + i) * width * scale] = sheet.pixels[(x + this.x + xOffset) + (y  + this.y + yOffset) * sheet.getWidth()];
					this.pixels[((x * scale)) + ((y * scale) + i) * width * scale] = sheet.pixels[(x + this.x + xOffset) + (y  + this.y + yOffset) * sheet.getWidth()];
				}
			}
		}
	}
	
	public static Sprite[] split(SpriteSheet sheet) {
	    int amount = sheet.getWidth() * sheet.getHeight() / (sheet.SPRITE_WIDTH * sheet.SPRITE_HEIGHT);
	    Sprite[] sprites = new Sprite[amount];
	    int current = 0;
	    
	    int[] pixels = new int[sheet.SPRITE_HEIGHT * sheet.SPRITE_WIDTH];
	    
	    for (int yp = 0; yp < sheet.getHeight() / sheet.SPRITE_HEIGHT; yp++) {
	      for (int xp = 0; xp < sheet.getWidth() / sheet.SPRITE_WIDTH; xp++)
	      {
	        for (int y = 0; y < sheet.SPRITE_HEIGHT; y++) {
	          for (int x = 0; x < sheet.SPRITE_WIDTH; x++) {
	            int x0 = x + xp * sheet.SPRITE_WIDTH;
	            int y0 = y + yp * sheet.SPRITE_HEIGHT;
	            pixels[(x + y * sheet.SPRITE_WIDTH)] = sheet.pixels[(x0 + y0 * sheet.getWidth())];
	          }
	        }
	        sprites[(current++)] = new Sprite(pixels, sheet.SPRITE_WIDTH, sheet.SPRITE_HEIGHT);
	      }
	    }
	    return sprites;
	  }
}
