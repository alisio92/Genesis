package com.alisio.genesis.level;

import java.util.ArrayList;
import java.util.List;
import com.alisio.genesis.entity.*;
import com.alisio.genesis.graphics.Screen;
import com.alisio.genesis.level.tile.*;

public class Level {
	protected int width,height;
	protected int[] tiles;
	public String name;
	
	private List<Entity> entities = new ArrayList<Entity>();
	private List<Projectile> projectiles = new ArrayList<Projectile>();
	
	public Level(int width, int height){
		this.width = width;
		this.height = height;
		this.tiles = new int[width * height];
		generateLevel();
	}
	
	public Level(String path, String name){
		this.name = name;
		loadLevel(path);
		generateLevel();
	}

	protected void loadLevel(String path) {
	}

	protected void generateLevel() {		
	}
	
	public void update() {		
		for(int i = 0; i < entities.size();i++){
			entities.get(i).update();
		}
		for(int i = 0; i < projectiles.size();i++){
			projectiles.get(i).update();
		}
	}
	
	public void render(int xScroll, int yScroll, Screen screen){
		screen.setOffset(xScroll, yScroll);
		int left = xScroll >> Tile.BASE_SIZE;
		int right = (xScroll + screen.width + Tile.SIZE) >> Tile.BASE_SIZE;
		int top = yScroll >> Tile.BASE_SIZE;
		int bottom = (yScroll + screen.height + Tile.SIZE) >> Tile.BASE_SIZE;
		
		for(int y = top; y < bottom;y++){
			for(int x = left; x < right;x++){
				getTile(x,y).render(x, y, screen);
			}
		}
		for(int i = 0; i < entities.size();i++){
			entities.get(i).render(screen);
		}
		for(int i = 0; i < projectiles.size();i++){
			projectiles.get(i).render(screen);
		}
	}
	
	public void addEntity(Entity e) {
		entities.add(e);
	}
	
	public void addProjectile(Projectile p) {
		p.init(this);
		projectiles.add(p);
	}
		
	public Tile getTile(int x, int y){
		if(x < 0 || y < 0 || x >= width || y >= height) return VoidTile.tile;		
		for(int i = 0; i < Tile.listTiles.size();i++){
			if(tiles[x+y*width] == Tile.listTiles.get(i).color) 
				return Tile.listTiles.get(i);
		}
		return VoidTile.tile;
	}
	
	public boolean tileCollision(double x, double y,double xTo, double yTo, double size) {
		boolean collision = false;
		
		for(int i = 0;i<Tile.BASE_SIZE;i++){
			double xt = (((int)x + (int)xTo) + i % 2 * size / 2 - 5) / Tile.SIZE;
			double yt = (((int)y + (int)yTo) + i / 2 * size / 2 - 8) / Tile.SIZE;
			if (getTile((int)xt,(int)yt).blocksShooting()) collision = true;
		}
				
		return collision;
	}
	
	public List<Projectile> getProjectiles() {
		return projectiles;
	}
	
	public List<Entity> getEntities() {
		return entities;
	}
}
