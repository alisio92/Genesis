package com.alisio.genesis.level.objects;

import com.alisio.genesis.graphics.Screen;
import com.alisio.genesis.graphics.Sprite;

public class VoidObject extends TileObject {
	
	public static Sprite sprite = new Sprite(SIZE,0xffff00ff);
	public static int color = 0;
	public static TileObject object = new VoidObject(sprite,color);

	public VoidObject(Sprite sprite, int color) {
		super(sprite,color);
	}
	
	public void render(int x, int y, Screen screen){
		screen.renderObject(x << BASE_SIZE, y << BASE_SIZE, this);
	}
	
	public boolean walkable(){
		return false;
	}
	
	public boolean blocksShooting(){
		return false;
	}
}
