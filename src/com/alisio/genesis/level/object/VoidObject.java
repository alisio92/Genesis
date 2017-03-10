package com.alisio.genesis.level.object;

import com.alisio.genesis.graphics.Screen;
import com.alisio.genesis.graphics.Sprite;
import com.alisio.genesis.level.tile.Tile;

public class VoidObject extends TileObject {
	
	public static Sprite sprite = new Sprite(SIZE,0xffff00ff);
	public static String name = "VoidObject";
	public static TileObject object = new VoidObject(sprite,name);

	public VoidObject(Sprite sprite, String name) {
		super(sprite,name);
	}
	
	public void render(int x, int y, Screen screen){
		screen.renderObject(x << Tile.BASE_SIZE, y << Tile.BASE_SIZE, this);
	}
	
	public boolean walkable(){
		return false;
	}
	
	public boolean blocksShooting(){
		return false;
	}
}
