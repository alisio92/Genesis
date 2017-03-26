package com.alisio.genesis.graphics;

import java.awt.image.BufferedImage;
import java.io.IOException;
import javax.imageio.ImageIO;

public class SpriteSheet {
	private String path;
	private int width, height;
	public int[] pixels;
	public double scale;
	public int SPRITE_WIDTH, SPRITE_HEIGHT;
	
	private Sprite[] sprites;
	
	public SpriteSheet(String path){
		this.path = path;
		load();
	}
	
	/*public SpriteSheet(String path, int width, int height){
		this.path = path;
		this.SPRITE_WIDTH = width;
		this.SPRITE_HEIGHT = height;
		load();
	}*/

	public SpriteSheet(SpriteSheet sheet, int xLoc, int yLoc, int width, int height, int spriteSize) {
		int xx = xLoc * spriteSize;
		int yy = yLoc * spriteSize;
		int w = width * spriteSize;
		int h = height * spriteSize;
		this.width = w;
		this.height = h;
		this.pixels = new int[w * h];
		for (int y = 0; y < h; y++) {
			int yp = yy + y;
			for (int x = 0; x < w; x++) {
				int xp = xx + x;
				pixels[x + y * w] = sheet.pixels[xp + yp * sheet.getWidth()];
			}
		}

		int frame = 0;
		sprites = new Sprite[width * height];
		for (int ya = 0; ya < height; ya++) {
			for (int xa = 0; xa < width; xa++) {
				int[] spritePixels = new int[spriteSize*spriteSize];
				for (int y = 0; y < spriteSize; y++) {
					for (int x = 0; x < spriteSize; x++) {
						spritePixels[x+y*spriteSize] = pixels[(x+xa*spriteSize) + (y + ya * spriteSize) * width];
					}
				}
				Sprite sprite = new Sprite(spritePixels,spriteSize,spriteSize);
				sprites[frame++] = sprite;
			}
		}
	}
	
	public Sprite[] getSprites() {
		return sprites;
	}
	
	private void load(){
		try {
			System.out.println("Trying to load: " + path);
			BufferedImage image = ImageIO.read(SpriteSheet.class.getResource(path));
			this.width = image.getWidth();
			this.height = image.getHeight();
			this.pixels = new int[width * height];
			image.getRGB(0, 0,width,height,pixels,0,width);
		} catch (IOException e) {
			e.printStackTrace();
			System.err.println("Could not load resource file from " + path + "!");
		}
	}
	
	public int getWidth() {
		return width;
	}
	
	public int getHeight() {
		return height;
	}
}
