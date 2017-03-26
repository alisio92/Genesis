package com.alisio.genesis.level.tile;

import com.alisio.genesis.graphics.Screen;
import com.alisio.genesis.graphics.Sprite;
import com.alisio.genesis.util.debug.Debug;

public class StoneTile extends Tile {

	public static Sprite sprite = new Sprite(SIZE,2,0,tiles);
	public static int color = 0xffff0000;
	public static Tile tile = new StoneTile(sprite,color);

	public StoneTile(Sprite sprite, int color) {
		super(sprite,color);
	}
	
	public void render(int x, int y, Screen screen){
		screen.renderTile(x << BASE_SIZE, y << BASE_SIZE, this);
		if(Debug.visible) Debug.drawRect(screen, x << BASE_SIZE, y << BASE_SIZE, sprite.getWidth()-1,sprite.getHeight()-1, false);
	}
	
	public boolean walkable(){
		return false;
	}
	
	public boolean blocksShooting(){
		return true;
	}

	public boolean breakable() {
		return false;
	}
}
