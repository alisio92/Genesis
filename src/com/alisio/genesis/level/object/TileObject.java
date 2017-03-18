package com.alisio.genesis.level.object;

import java.util.ArrayList;
import java.util.List;
import com.alisio.genesis.entity.Entity;
import com.alisio.genesis.graphics.Screen;
import com.alisio.genesis.graphics.Sprite;
import com.alisio.genesis.graphics.SpriteSheet;
import com.alisio.genesis.level.Level;
import com.alisio.genesis.level.tile.Tile;

public abstract class TileObject extends Entity {	
	public int baseSize, size;
	public String name;
	
	public static List<TileObject> listObjects = new ArrayList<TileObject>();
	
	public static SpriteSheet objects = new SpriteSheet("/" + Tile.SIZE + "/textures/objects.png");
	
	public TileObject(Sprite sprite, String name, int baseSize){
		super(0,0,sprite);
		this.name = name;
		this.baseSize = baseSize;
		this.size = (int)Math.pow(2, baseSize);
	}
	
	//Interface
	public abstract void render(int x, int y, Screen screen, Level level);
	public abstract boolean breakable();
	public abstract boolean walkable();
	public abstract boolean blocksShooting();
	public abstract boolean emitsLight();
}
