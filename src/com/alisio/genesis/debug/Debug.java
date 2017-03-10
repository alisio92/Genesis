package com.alisio.genesis.debug;

import java.awt.Color;
import java.util.ArrayList;
import java.util.List;
import com.alisio.genesis.entity.mob.Player;
import com.alisio.genesis.level.Level;
import com.alisio.genesis.level.tile.Tile;
import com.alisio.genesis.reader.XMLReader;

public class Debug {
	
	public static List<DataLocation> data;
	public Boolean visible = false;
	public Color color = Color.WHITE;
	
	public void update(Player player, Level level) {
		data = new ArrayList<DataLocation>();
		
		//player
		data.add(new DataLocation("Player",10,20));
		data.add(new DataLocation("X: " + player.x + ", Y: " + player.y,15,40));
		
		int tileX = ((int)(player.x + (Player.WIDTH / 2)) >> Tile.BASE_SIZE);
		int tileY = ((int)(player.y + (Player.HEIGHT / 2)) >> Tile.BASE_SIZE);
		data.add(new DataLocation("TileX: " + tileX + ", TileY: " + tileY,15,60));
		
		//level
		data.add(new DataLocation("Level",10,90));
		data.add(new DataLocation("Name: " + level.name,15,110));
		data.add(new DataLocation("Entities: " + level.getEntities().size(),15,130));
		data.add(new DataLocation("Projectiles: " + level.getProjectiles().size(),15,150));
		
		//Scenes
		data.add(new DataLocation("Scenes",10,180));
		data.add(new DataLocation("Trees: " + XMLReader.trees.size(),15,200));
	}
}
