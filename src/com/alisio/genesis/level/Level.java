package com.alisio.genesis.level;

import java.util.ArrayList;
import java.util.List;
import com.alisio.genesis.entity.Entity;
import com.alisio.genesis.entity.particle.Particle;
import com.alisio.genesis.entity.projectile.Projectile;
import com.alisio.genesis.graphics.Screen;
import com.alisio.genesis.level.object.LightObject;
import com.alisio.genesis.level.object.TileObject;
import com.alisio.genesis.level.tile.Tile;
import com.alisio.genesis.level.tile.VoidTile;
import com.alisio.genesis.reader.XMLObject;
import com.alisio.genesis.reader.XMLReader;

public class Level {
	protected int width, height;
	protected int[] tiles;
	public String name;
	public static int brightness;
	private GameTime gameTime;
	private int time = 0;
	private int lengthChangeNightDay = 4;
	private int timeSpeed = 100;
	private int startTime = 28800; // 8 uur 28800

	private List<Entity> entities = new ArrayList<Entity>();
	private List<Projectile> projectiles = new ArrayList<Projectile>();
	private List<Particle> particles = new ArrayList<Particle>();
	private List<LightObject> lightObjects = new ArrayList<LightObject>();

	//cto
	public Level(int width, int height) {
		this.width = width;
		this.height = height;
		this.tiles = new int[width * height];
		generateLevel();
		gameTime = new GameTime(startTime,timeSpeed,72000,25200);
	}

	public Level(String path, String name) {
		this.name = name;
		loadLevel(path);
		generateLevel();
		gameTime = new GameTime(startTime,timeSpeed,72000,25200);
	}

	protected void loadLevel(String path) {
	}

	protected void generateLevel() {
	}

	public void update() {
		time();
		
		time++;
		if(time > lengthChangeNightDay + 1) time = 0;

		for (int i = 0; i < entities.size(); i++) {
			entities.get(i).update();
		}
		for (int i = 0; i < projectiles.size(); i++) {
			projectiles.get(i).update();
		}
		for (int i = 0; i < particles.size(); i++) {
			particles.get(i).update();
		}
		
		gameTime.update();
		remove();
	}
	
	private void remove(){
		if(time > lengthChangeNightDay + 1) time = 0;

		for (int i = 0; i < entities.size(); i++) {
			if(entities.get(i).isRemoved()) entities.remove(i);
		}
		for (int i = 0; i < projectiles.size(); i++) {
			if(projectiles.get(i).isRemoved()) projectiles.remove(i);
		}
		for (int i = 0; i < particles.size(); i++) {
			if(particles.get(i).isRemoved()) particles.remove(i);
		}
	}

	public void time() {		
		if(brightness > 0) brightness = 0;
		if(brightness < -150) brightness = -150;
		
		if (gameTime.night) {
			if(time % lengthChangeNightDay == 0) brightness--;			
			return;
		}
		if (gameTime.day) {
			if(time % lengthChangeNightDay == 0) brightness++;
			return;
		}
	}

	public void render(int xScroll, int yScroll, Screen screen) {
		screen.setOffset(xScroll, yScroll);
		int left = xScroll >> Tile.BASE_SIZE;
		int right = (xScroll + screen.width + Tile.SIZE) >> Tile.BASE_SIZE;
		int top = yScroll >> Tile.BASE_SIZE;
		int bottom = (yScroll + screen.height + Tile.SIZE) >> Tile.BASE_SIZE;

		for (int y = top; y < bottom; y++) {
			for (int x = left; x < right; x++) {
				getTile(x, y).render(x, y, screen);
			}
		}

		left = xScroll >> 5;
		top = yScroll >> 5;
		
		//lighting
		bottom = bottom + 3;
		right = right + 3;

		for (int y = top; y < bottom; y++) {
			for (int x = left; x < right; x++) {
				TileObject o = getObject(x, y);
				if (o != null) if(o.emitsLight()) lightObjects.add(new LightObject(x,y));
			}
		}
		
		for (int i = 0; i < lightObjects.size(); i++) {
			lightObjects.get(i).render(screen, this);
		}
		
		for (int y = top; y < bottom; y++) {
			for (int x = left; x < right; x++) {
				TileObject o = getObject(x, y);
				if (o != null) o.render(x, y, screen,this);
			}
		}

		for (int i = 0; i < entities.size(); i++) {
			entities.get(i).render(screen);
		}
		for (int i = 0; i < projectiles.size(); i++) {
			projectiles.get(i).render(screen);
		}
		for (int i = 0; i < particles.size(); i++) {
			particles.get(i).render(screen);
		}
		gameTime.render();	
		lightObjects.clear();
	}

	public void add(Entity e) {
		e.init(this);
		if(e instanceof Particle) {
			particles.add((Particle)e);
		} else if(e instanceof Projectile) {
			projectiles.add((Projectile)e);
		}
		else{
			entities.add(e);
		}
	}

	public Tile getTile(int x, int y) {
		if (x < 0 || y < 0 || x >= width || y >= height) return VoidTile.tile;
		for (int i = 0; i < Tile.listTiles.size(); i++) {
			if (tiles[x + y * width] == Tile.listTiles.get(i).color) return Tile.listTiles.get(i);
		}
		return VoidTile.tile;
	}

	public TileObject getObject(int x, int y) {
		if (x < 0 || y < 0 || x >= width || y >= height) return null;
		for (int i = 0; i < TileObject.listObjects.size(); i++) {
			for (int j = 0; j < XMLReader.objects.size(); j++) {
				XMLObject o = XMLReader.objects.get(j);
				if (x == o.x && y == o.y && TileObject.listObjects.get(i).name.equals(o.name))
					return TileObject.listObjects.get(i);
			}
		}
		return null;
	}
	
	public boolean objectCollision(int x, int y, int size, int xOffset, int yOffset) {
		boolean collision = false;

		for (int i = 0; i < Tile.BASE_SIZE; i++) {
			int xt = (x - i % 2 * size + xOffset) >> Tile.BASE_SIZE;
			int yt = (y - i / 2 * size + yOffset) >> Tile.BASE_SIZE;
			TileObject temp = getObjectCollision(xt, yt);
			
			if(temp != null) {
				xt = (x - i % 2 * size + xOffset - temp.sprite.getStartX()) >> Tile.BASE_SIZE;
				yt = (y - i / 2 * size + yOffset - temp.sprite.getStartY()) >> Tile.BASE_SIZE;			
			}
			
			TileObject o = getObjectCollision(xt, yt);
			if (o != null && !o.walkable()) collision = true;
		}

		return collision;
	}
	
	public boolean tileCollision(int x, int y, int size, int xOffset, int yOffset) {
		boolean collision = false;

		for (int i = 0; i < Tile.BASE_SIZE; i++) {
			int xt = (x - i % 2 * size + xOffset) >> Tile.BASE_SIZE;
			int yt = (y - i / 2 * size + yOffset) >> Tile.BASE_SIZE;
			if (getTile(xt, yt).blocksShooting()) collision = true;
		}

		return collision;
	}
	
	public TileObject getObjectCollision(int x, int y) {
		int radius = 4;
		for (int yy = 0; yy < radius; yy++) {
			for (int xx = 0; xx < radius; xx++) {
				TileObject o = getObject(x - xx, y - yy);
				if (o != null) {
					int newradius = o.sprite.getWidth() / 16;
					if (newradius == radius) return o;
					else {
						for (int yyNew = 0; yyNew < newradius; yyNew++) {
							for (int xxNew = 0; xxNew < newradius; xxNew++) {
								TileObject oNew = getObject(x - xxNew, y - yyNew);
								if (o != null) return oNew;
							}
						}
					}
				}
			}
		}
		return null;
	}
	
	//getters
	public List<Projectile> getProjectiles() {
		return projectiles;
	}

	public List<Entity> getEntities() {
		return entities;
	}
	
	public List<LightObject> getLightObjects() {
		return lightObjects;
	}
	
	//setters
}
