package com.alisio.genesis.util.debug;

import java.awt.Color;
import java.util.ArrayList;
import java.util.List;
import com.alisio.genesis.entity.mob.Player;
import com.alisio.genesis.graphics.Screen;
import com.alisio.genesis.level.GameTime;
import com.alisio.genesis.level.Level;

public class Debug {
	
	public static List<DataLocation> data;
	public static Boolean visible = false;
	public static Color color = Color.WHITE;
	
	public static void update(Player player, Level level) {
		data = new ArrayList<DataLocation>();
		
		//player
		data.add(new DataLocation("Player",10,20));
		data.add(new DataLocation("X: " + player.getX() + ", Y: " + player.getY(),15,40));		
		data.add(new DataLocation("TileX: " + player.getTileX() + ", TileY: " + player.getTileY(),15,60));
		
		//level
		data.add(new DataLocation("Level",10,90));
		data.add(new DataLocation("Name: " + level.name,15,110));
		data.add(new DataLocation("Time: " + GameTime.getTime(),15,130));
		data.add(new DataLocation("Entities: " + level.getEntities().size(),15,150));
		data.add(new DataLocation("Projectiles: " + level.getProjectiles().size(),15,170));
		
		//Scenes
		data.add(new DataLocation("Scenes",10,200));
		data.add(new DataLocation("Trees: " + level.getXMLObjects().stream().filter(x -> x.name.equals("TreeObject") || x.name.equals("PalmTreeObject")).count(),15,220));
	}
	
	public static void drawRect(Screen screen, int x, int y, int width, int height, int color, boolean fixed) {
		screen.drawRect(x,y,width,height,color,fixed);
	}
	
	public static void drawRect(Screen screen, int x, int y, int width, int height, boolean fixed) {
		screen.drawRect(x,y,width,height,0xffff0000,fixed);
	}
}
