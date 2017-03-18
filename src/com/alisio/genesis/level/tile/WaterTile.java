package com.alisio.genesis.level.tile;

import com.alisio.genesis.graphics.Screen;
import com.alisio.genesis.graphics.Sprite;

public class WaterTile extends Tile {

	public static Sprite sprite = new Sprite(SIZE,4,0,tiles);
	public static int color = 0xffffffff;
	public static Tile tile = new WaterTile(sprite,color);

	public WaterTile(Sprite sprite, int color) {
		super(sprite,color);
	}
	
	public void render(int x, int y, Screen screen){
		screen.renderTile(x << BASE_SIZE, y << BASE_SIZE, this);
	}
	
	public boolean walkable(){
		return false;
	}
	
	public boolean blocksShooting(){
		return false;
	}

	public boolean breakable() {
		return false;
	}
}
