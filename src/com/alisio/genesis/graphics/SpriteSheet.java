package com.alisio.genesis.graphics;

import java.awt.image.BufferedImage;
import java.io.IOException;
import javax.imageio.ImageIO;

public class SpriteSheet {
	private String path;
	public int Width;
	public int Height;
	public int[] pixels;
	public double scale;
	
	private Sprite[] sprites;
	
	public SpriteSheet(String path){
		this.path = path;
		load();
	}

	public SpriteSheet(SpriteSheet sheet, int xLoc, int yLoc, int width, int height, int spriteSize) {
		int xx = xLoc * spriteSize;
		int yy = yLoc * spriteSize;
		int w = width * spriteSize;
		int h = height * spriteSize;
		this.Width = w;
		this.Height = h;
		this.pixels = new int[w * h];
		for (int y = 0; y < h; y++) {
			int yp = yy + y;
			for (int x = 0; x < w; x++) {
				int xp = xx + x;
				pixels[x + y * w] = sheet.pixels[xp + yp * sheet.Width];
			}
		}

		int frame = 0;
		sprites = new Sprite[width * height];
		for (int ya = 0; ya < height; ya++) {
			for (int xa = 0; xa < width; xa++) {
				int[] spritePixels = new int[spriteSize*spriteSize];
				for (int y = 0; y < spriteSize; y++) {
					for (int x = 0; x < spriteSize; x++) {
						spritePixels[x+y*spriteSize] = pixels[(x+xa*spriteSize) + (y + ya * spriteSize) * Width];
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
			BufferedImage image = ImageIO.read(SpriteSheet.class.getResource(path));
			this.Width = image.getWidth();
			this.Height = image.getHeight();
			this.pixels = new int[Width * Height];
			image.getRGB(0, 0,Width,Height,pixels,0,Width);
		} catch (IOException e) {
			e.printStackTrace();
			System.out.println("Could not load resource file from " + path + "!");
		}
	}
}
