package com.alisio.genesis.level.object;

import com.alisio.genesis.graphics.Screen;
import com.alisio.genesis.graphics.Sprite;
import com.alisio.genesis.level.tile.Tile;

public class TreeObject extends TileObject {
	
	public static Sprite sprite = new Sprite(32,0,0,objects,2);
	public static String name = "TreeObject";
	public static TileObject object = new TreeObject(sprite,name,6);

	public TreeObject(Sprite sprite, String name, int baseSize) {
		super(sprite, name,baseSize);
	}
	
	public void render(int x, int y, Screen screen){
		screen.renderObject(x << Tile.BASE_SIZE, y << Tile.BASE_SIZE, this);
	}
	
	public boolean walkable(){
		return false;
	}
	
	public boolean blocksShooting(){
		return true;
	}
}
