package com.alisio.genesis.level.tile;

import java.util.ArrayList;
import java.util.List;
import com.alisio.genesis.graphics.Screen;
import com.alisio.genesis.graphics.Sprite;
import com.alisio.genesis.graphics.SpriteSheet;

public class Tile {
	public int x,y;
	public static final int BASE_SIZE = 4;
	public static final int SIZE = (int)Math.pow(2, BASE_SIZE);
	public Sprite sprite;
	public int color = 0;
	
	public static List<Tile> listTiles = new ArrayList<Tile>();
	
	public static SpriteSheet tiles = new SpriteSheet("/" + Tile.SIZE + "/textures/tiles.png");
	
	public Tile(Sprite sprite, int color){
		this.sprite = sprite;
		this.color = color;
	}
	
	public void render(int x, int y, Screen screen){
		
	}
	
	public boolean breakable(){
		return false;
	}
	
	public boolean walkable(){
		return true;
	}
	
	public boolean blocksShooting(){
		return false;
	}
}
