package com.alisio.genesis.level.tile;

import com.alisio.genesis.graphics.Screen;
import com.alisio.genesis.graphics.Sprite;

public class VoidTile extends Tile {
	
	public static Sprite sprite = new Sprite(SIZE,0xffff00ff);
	public static int color = 0;
	public static Tile tile = new VoidTile(sprite,color);

	public VoidTile(Sprite sprite, int color) {
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
}
