package com.alisio.genesis.level.tile;

import com.alisio.genesis.graphics.Screen;
import com.alisio.genesis.graphics.Sprite;

public class FlowerTile extends Tile {
	
	public static Sprite sprite = new Sprite(SIZE,1,0,tiles);
	public static int color = 0xffffff00;
	public static Tile tile = new FlowerTile(sprite,color);
	
	public FlowerTile(Sprite sprite, int color) {
		super(sprite,color);
	}
	
	public void render(int x, int y, Screen screen){
		screen.renderTile(x << BASE_SIZE, y << BASE_SIZE, this);
	}
	
	public boolean walkable(){
		return true;
	}
	
	public boolean blocksShooting(){
		return false;
	}
}
