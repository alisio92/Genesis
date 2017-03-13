package com.alisio.genesis.level.object;

import com.alisio.genesis.graphics.Screen;
import com.alisio.genesis.level.Level;
import com.alisio.genesis.level.tile.Tile;

public class LightObject {
	
	public int x, y;
	public int red,green,blue;
	private Tile tile;
	public int radius = 3;
	
	public int getX() {		
		return (this.x * 16);
	}	
	
	public int getY() {
		return (this.y * 16);
	}
	
	public int getRadius() {
		return this.radius * 16;
	}
	
	public LightObject(int x, int y) {
		this.x = x;
		this.y = y;
		this.red = 35;
		this.green = 35;
		this.blue = 35;
	}

	public void render(Screen screen, Level level) {
		int tb = Level.brightness;
		if(tb < -240) tb = -240;
		if(tb > 0) tb = 0;
		for (int yy = -radius; yy <= radius; yy++) {
			for (int xx = -radius; xx <= radius; xx++) {
				for (int i = 0; i < 4; i++) {
					int xt = ((x << Tile.BASE_SIZE) + (i % 2 * 2 - 1) * 7) >> Tile.BASE_SIZE;
					int yt = ((y << Tile.BASE_SIZE) + (i / 2 * 2 - 1) * 7) >> Tile.BASE_SIZE;
					tile = level.getTile(xt + xx, yt + yy);
				}

				int xTile = x * Tile.SIZE + xx * Tile.SIZE;
				int yTile = y * Tile.SIZE + yy * Tile.SIZE;
				double intensity = Math.abs((Math.pow(xx * Math.PI,2) + Math.pow(yy * Math.PI,2))) * tb * 0.00018;
				
				double r = Math.abs((Math.pow(xx * Math.PI,2) + Math.pow(yy * Math.PI,2)));
				if(r < 100) screen.renderLight(xTile, yTile, tile, red, green, blue, intensity);
			}
		}
	}
}
