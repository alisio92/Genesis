package com.alisio.genesis.level;

import java.awt.image.BufferedImage;
import java.io.IOException;
import javax.imageio.ImageIO;
import com.alisio.genesis.entity.mob.*;
import com.alisio.genesis.reader.XMLObject;
import com.alisio.genesis.reader.XMLReader;

public class BasicLevel extends Level {

	public BasicLevel(String path, String name) {
		super(path,name);
	}
	
	protected void loadLevel(String path) {
		try {
			BufferedImage imageTiles = ImageIO.read(BasicLevel.class.getResource(path));
			int w = imageTiles.getWidth();
			this.width = w;
			int h = imageTiles.getHeight();
			this.height = h;
			this.tiles = new int[w*h];
			imageTiles.getRGB(0, 0,w,h,tiles,0,w);
			new XMLReader("/maps/" + name + "/objects.xml", this);
		} catch (IOException e) {
			e.printStackTrace();
			System.out.println("Could not load level file from " + path + "!");
		}	
		TileLocation location = new TileLocation(22,12);
		add(new Chaser(location.getX(),location.getY()));
		
		for(int i = 0; i < getXMLObjects().size();i++){
			XMLObject o = getXMLObjects().get(i);
			if(o.name.equals("ShopObject")){
				TileLocation locationTrader = new TileLocation(o.x + 5,o.y + 4);
				add(new Trader(locationTrader.getX(),locationTrader.getY()));
			}
		}
	}
	
	protected void generateLevel() {		

	}
}
