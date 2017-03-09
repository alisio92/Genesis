package com.alisio.genesis.level;

import java.awt.image.BufferedImage;
import java.io.IOException;
import javax.imageio.ImageIO;

public class BasicLevel extends Level {

	public BasicLevel(String pathTiles, String pathObjects, String name) {
		super(pathTiles,pathObjects,name);
	}
	
	protected void loadLevel(String pathTiles, String pathObjects) {
		try {
			BufferedImage imageTiles = ImageIO.read(BasicLevel.class.getResource(pathTiles));
			int w = imageTiles.getWidth();
			this.width = w;
			int h = imageTiles.getHeight();
			this.height = h;
			this.tiles = new int[w*h];
			imageTiles.getRGB(0, 0,w,h,tiles,0,w);
		} catch (IOException e) {
			e.printStackTrace();
			System.out.println("Could not load level tiles file from " + pathTiles + "!");
		}
		
		try {
			if(pathObjects != null){
				BufferedImage imageObjects = ImageIO.read(BasicLevel.class.getResource(pathObjects));
				int w = imageObjects.getWidth();
				int h = imageObjects.getHeight();
				this.objects = new int[w*h];
				imageObjects.getRGB(0, 0,w,h,objects,0,w);
			}else{
				this.objects = new int[this.width * this.height];
			}			
		} catch (IOException e) {
			e.printStackTrace();
			System.out.println("Could not load level objects file from " + pathObjects + "!");
		}		
	}
	
	protected void generateLevel() {		

	}
}
