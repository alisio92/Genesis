package com.alisio.genesis.level.object;

import com.alisio.genesis.graphics.Screen;
import com.alisio.genesis.level.Level;
import com.alisio.genesis.level.tile.Tile;

public class LightObject {
	
	public int x, y;
	private Tile tile;
	public int radius = 3;
	
	public int getTileX() {
		return (this.x * 16);
	}	
	
	public int getTileY() {
		return (this.y * 16);
	}
	
	public int getRadius() {
		return this.radius * 16;
	}
	
	public LightObject(int x, int y) {
		this.x = x;
		this.y = y;
	}

	public void render(Screen screen, Level level) {
		for (int yy = -radius; yy <= radius; yy++) {
			for (int xx = -radius; xx <= radius; xx++) {
				for (int i = 0; i < 4; i++) {
					int xt = ((x << Tile.BASE_SIZE) + (i % 2 * 2 - 1) * 7) >> Tile.BASE_SIZE;
					int yt = ((y << Tile.BASE_SIZE) + (i / 2 * 2 - 1) * 7) >> Tile.BASE_SIZE;
					tile = level.getTile(xt + xx, yt + yy);
				}

				int xTile = x * Tile.SIZE + xx * Tile.SIZE;
				int yTile = y * Tile.SIZE + yy * Tile.SIZE;
				screen.renderLight(xTile, yTile, tile, 35, 35, 35, 1.0);
			}
		}
	}
}
