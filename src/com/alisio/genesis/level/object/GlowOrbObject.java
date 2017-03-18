package com.alisio.genesis.level.object;

import com.alisio.genesis.graphics.Screen;
import com.alisio.genesis.graphics.Sprite;
import com.alisio.genesis.level.Level;
import com.alisio.genesis.level.tile.Tile;

public class GlowOrbObject extends TileObject {
	
	public static Sprite sprite = new Sprite(16,0,2,objects);
	public static String name = "GlowOrbObject";
	public static GlowOrbObject object = new GlowOrbObject(sprite,name,4);

	public GlowOrbObject(Sprite sprite, String name, int baseSize) {
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
	
	public boolean emitsLight(){
		return true;
	}

	public boolean breakable() {
		return false;
	}
}
