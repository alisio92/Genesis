package com.alisio.genesis.level.object;

import com.alisio.genesis.graphics.Screen;
import com.alisio.genesis.graphics.Sprite;
import com.alisio.genesis.level.tile.Tile;

public class PalmTreeObject extends TileObject {
	
	public static Sprite sprite = new Sprite(SIZE * 2,1,0,objects);
	public static String name = "PalmTreeObject";
	public static TileObject object = new PalmTreeObject(sprite,name);

	public PalmTreeObject(Sprite sprite, String name) {
		super(sprite, name);
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
