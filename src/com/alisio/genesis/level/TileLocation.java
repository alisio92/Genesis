package com.alisio.genesis.level;

import com.alisio.genesis.entity.mob.Player;
import com.alisio.genesis.level.tile.Tile;

public class TileLocation {
	
	private int x, y;
	
	public TileLocation(int x, int y) {
		//yCenter = 8 * Tile.SIZE + ((Tile.SIZE - Player.HEIGHT) / 2);
		this.x = x * Tile.SIZE + ((Tile.SIZE - Player.WIDTH) / 2);
		this.y = y * Tile.SIZE + ((Tile.SIZE - Player.HEIGHT) / 2);
	}
	
	public int getX() {
		return x;
	}
	
	public int getY() {
		return y;
	}
	
	public int[] location() {
		int[] r = new int[2];
		r[0] = x;
		r[1] = y;
		return r;
	}
}
