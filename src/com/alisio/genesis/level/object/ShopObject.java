package com.alisio.genesis.level.object;

import com.alisio.genesis.graphics.Screen;
import com.alisio.genesis.graphics.Sprite;
import com.alisio.genesis.level.Level;
import com.alisio.genesis.level.tile.Tile;

public class ShopObject extends TileObject {

	public static Sprite sprite = new Sprite(128,0,1,objects,1);
	public static String name = "ShopObject";
	public static TileObject object = new ShopObject(sprite,name,7);

	public ShopObject(Sprite sprite, String name, int baseSize) {
		super(sprite, name,baseSize);
	}
	
	public void render(int x, int y, Screen screen, Level level){
		screen.renderObject(x << Tile.BASE_SIZE, y << Tile.BASE_SIZE, this, level);
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

	public boolean emitsLight() {
		return false;
	}
}
