package com.alisio.genesis.level.objects;

import com.alisio.genesis.graphics.Screen;
import com.alisio.genesis.graphics.Sprite;

public class TreeObject extends TileObject {
	
	public static Sprite sprite = new Sprite(SIZE,0,0,objects);
	public static int color = 0xff00ff00;
	public static TileObject object = new TreeObject(sprite,color);

	public TreeObject(Sprite sprite, int color) {
		super(sprite, color);
	}
	
	public void render(int x, int y, Screen screen){
		screen.renderObject(x << 4, y << 4, this);
	}
	
	public boolean walkable(){
		return false;
	}
	
	public boolean blocksShooting(){
		return true;
	}
}
