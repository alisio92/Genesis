package com.alisio.genesis.level.object;

import java.util.ArrayList;
import java.util.List;
import com.alisio.genesis.graphics.Screen;
import com.alisio.genesis.graphics.Sprite;
import com.alisio.genesis.graphics.SpriteSheet;
import com.alisio.genesis.level.tile.Tile;

public class TileObject {	
	public int x,y;
	public int baseSize, size;
	public Sprite sprite;
	public String name;
	
	public static List<TileObject> listObjects = new ArrayList<TileObject>();
	
	public static SpriteSheet objects = new SpriteSheet("/" + Tile.SIZE + "/textures/objects.png");
	
	public TileObject(Sprite sprite, String name, int baseSize){
		this.sprite = sprite;
		this.name = name;
		this.baseSize = baseSize;
		this.size = (int)Math.pow(2, baseSize);
	}
	
	public void render(int x, int y, Screen screen){
		
	}
	
	public boolean breakable(){
		return false;
	}
	
	public boolean walkable(){
		return false;
	}
	
	public boolean blocksShooting(){
		return true;
	}
}
