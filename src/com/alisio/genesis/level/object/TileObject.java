package com.alisio.genesis.level.object;

import java.util.ArrayList;
import java.util.List;
import com.alisio.genesis.graphics.Screen;
import com.alisio.genesis.graphics.Sprite;
import com.alisio.genesis.graphics.SpriteSheet;
import com.alisio.genesis.level.tile.Tile;

public class TileObject {	
	public int x,y;
	public static final int BASE_SIZE = 5;
	public static final int SIZE = (int)Math.pow(2, BASE_SIZE);
	public Sprite sprite;
	public String name;
	
	public static List<TileObject> listObjects = new ArrayList<TileObject>();
	
	public static SpriteSheet objects = new SpriteSheet("/" + Tile.SIZE + "/textures/objects.png",2);
	
	public TileObject(Sprite sprite, String name){
		this.sprite = sprite;
		this.name = name;
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
