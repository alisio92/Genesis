package com.alisio.genesis.graphics;

import java.awt.Graphics2D;
import java.awt.Image;
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
	
	public SpriteSheet(String path, double scale){
		this.path = path;
		this.scale = scale;
		load(scale);
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
	
	private void load(double scale){
		try {
			BufferedImage image = ImageIO.read(SpriteSheet.class.getResource(path));
			int w = image.getWidth();
			int h = image.getHeight();
			image = resize(image,(int)(w / scale), (int)(h / scale));
			this.Width = image.getWidth();
			this.Height = image.getHeight();
			this.pixels = new int[Width * Height];
			image.getRGB(0, 0,Width,Height,pixels,0,Width);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public static BufferedImage resize(BufferedImage img, int newW, int newH) { 
	    Image tmp = img.getScaledInstance(newW, newH, Image.SCALE_SMOOTH);
	    BufferedImage image = new BufferedImage(newW, newH, BufferedImage.TYPE_INT_ARGB);

	    Graphics2D g2d = image.createGraphics();
	    g2d.drawImage(tmp, 0, 0, null);
	    g2d.dispose();

	    return image;
	} 
}
