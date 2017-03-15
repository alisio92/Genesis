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
	
	public SpriteSheet(String path){
		this.path = path;
		load();
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
