package com.alisio.genesis.level;

import java.awt.image.BufferedImage;
import java.io.IOException;
import javax.imageio.ImageIO;

public class BasicLevel extends Level {

	public BasicLevel(String path, String name) {
		super(path,name);
	}
	
	protected void loadLevel(String path) {
		try {
			BufferedImage image = ImageIO.read(BasicLevel.class.getResource(path));
			int w = image.getWidth();
			this.width = w;
			int h = image.getHeight();
			this.height = h;
			this.tiles = new int[w*h];
			image.getRGB(0, 0,w,h,tiles,0,w);
		} catch (IOException e) {
			e.printStackTrace();
			System.out.println("Could not load level file from " + path + "!");
		}
	}
	
	protected void generateLevel() {		

	}
}
