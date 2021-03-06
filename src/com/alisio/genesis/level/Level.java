package com.alisio.genesis.level;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import com.alisio.genesis.entity.Entity;
import com.alisio.genesis.entity.mob.*;
import com.alisio.genesis.entity.particle.*;
import com.alisio.genesis.entity.projectile.*;
import com.alisio.genesis.graphics.Screen;
import com.alisio.genesis.level.object.*;
import com.alisio.genesis.level.tile.*;
import com.alisio.genesis.util.*;
import com.alisio.genesis.util.reader.*;

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
	private List<XMLObject> objects = new ArrayList<XMLObject>();
	private List<Player> players = new ArrayList<Player>();
	private NodeComparator nodeSorter = new NodeComparator();

	// cto
	public Level(int width, int height) {
		this.width = width;
		this.height = height;
		this.tiles = new int[width * height];
		generateLevel();
		gameTime = new GameTime(startTime, timeSpeed, 72000, 25200);
	}

	public Level(String path, String name) {
		this.name = name;
		loadLevel(path);
		generateLevel();
		gameTime = new GameTime(startTime, timeSpeed, 72000, 25200);
	}

	protected void loadLevel(String path) {
	}

	protected void generateLevel() {
	}

	public void update() {
		time();

		time++;
		if (time > lengthChangeNightDay + 1) time = 0;

		for (int i = 0; i < entities.size(); i++) {
			entities.get(i).update();
		}
		for (int i = 0; i < projectiles.size(); i++) {
			projectiles.get(i).update();
		}
		for (int i = 0; i < particles.size(); i++) {
			particles.get(i).update();
		}
		for (int i = 0; i < players.size(); i++) {
			players.get(i).update();
		}

		gameTime.update();
		remove();
	}

	private void remove() {
		if (time > lengthChangeNightDay + 1) time = 0;

		for (int i = 0; i < entities.size(); i++) {
			if (entities.get(i).isRemoved()) entities.remove(i);
		}
		for (int i = 0; i < projectiles.size(); i++) {
			if (projectiles.get(i).isRemoved()) projectiles.remove(i);
		}
		for (int i = 0; i < particles.size(); i++) {
			if (particles.get(i).isRemoved()) particles.remove(i);
		}
		for (int i = 0; i < players.size(); i++) {
			if (players.get(i).isRemoved()) players.remove(i);
		}
	}

	public void time() {
		if (brightness > 0) brightness = 0;
		if (brightness < -150) brightness = -150;

		if (gameTime.night) {
			if (time % lengthChangeNightDay == 0) brightness--;
			return;
		}
		if (gameTime.day) {
			if (time % lengthChangeNightDay == 0) brightness++;
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

		// lighting
		bottom = bottom + 3;
		right = right + 3;

		for (int y = top; y < bottom; y++) {
			for (int x = left; x < right; x++) {
				TileObject o = getObject(x, y);
				if (o != null) if (o.emitsLight()) lightObjects.add(new LightObject(x, y));
			}
		}

		for (int i = 0; i < lightObjects.size(); i++) {
			lightObjects.get(i).render(screen, this);
		}

		for (int y = top; y < bottom; y++) {
			for (int x = left; x < right; x++) {
				TileObject o = getObject(x, y);
				if (o != null) o.render(x, y, screen, this);
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
		for (int i = 0; i < players.size(); i++) {
			players.get(i).render(screen);
		}
		gameTime.render();
		lightObjects.clear();
	}

	public void add(Entity e) {
		e.init(this);
		if (e instanceof Particle) {
			particles.add((Particle) e);
		} else if (e instanceof Projectile) {
			projectiles.add((Projectile) e);
		} else if (e instanceof Player) {
			players.add((Player) e);
		} else {
			entities.add(e);
		}
	}

	public void add(XMLObject e) {
		objects.add(e);
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
			for (int j = 0; j < objects.size(); j++) {
				XMLObject o = objects.get(j);
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

			if (temp != null) {
				xt = (x - i % 2 * size + xOffset - temp.getSprite().getStartX()) >> Tile.BASE_SIZE;
				yt = (y - i / 2 * size + yOffset - temp.getSprite().getStartY()) >> Tile.BASE_SIZE;
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
		int radius = 8;
		for (int yy = 0; yy < radius; yy++) {
			for (int xx = 0; xx < radius; xx++) {
				TileObject o = getObject(x - xx, y - yy);
				if (o != null) {
					int newradius = o.getSprite().getWidth() / 16;
					if (newradius == radius) {
						return o;
					} else {
						for (int yyNew = 0; yyNew < newradius; yyNew++) {
							for (int xxNew = 0; xxNew < newradius; xxNew++) {
								TileObject oNew = getObject(x - xxNew, y - yyNew);
								if (oNew != null) return oNew;
							}
						}
					}
				}
			}
		}
		return null;
	}

	public List<Node> findPath(Vector2i start, Vector2i goal) {
		List<Node> openList = new ArrayList<Node>();
		List<Node> closedList = new ArrayList<Node>();
		Node current = new Node(start, null, 0, Vector2i.getDistance(start, goal));
		openList.add(current);
		while (openList.size() > 0) {
			Collections.sort(openList, nodeSorter);
			current = (Node) openList.get(0);
			if (current.tile.equals(goal)) {
				List<Node> path = new ArrayList<Node>();
				while (current.parent != null) {
					path.add(current);
					current = current.parent;
				}
				openList.clear();
				closedList.clear();
				return path;
			}
			openList.remove(current);
			closedList.add(current);
			for (int i = 0; i < 9; i++) {
				if (i == 4) continue;
				int x = current.tile.getX();
				int y = current.tile.getY();
				int xi = i % 3 - 1;
				int yi = i / 3 - 1;
				Tile at = getTile(x + xi, y + yi);
				if (at == null) continue;
				if (!at.walkable()) continue;
				Vector2i a = new Vector2i(x + xi, y + yi);
				double gCost = current.gCost + (Vector2i.getDistance(current.tile, a) == 1 ? 1 : 0.95);
				double hCost = Vector2i.getDistance(a, goal);
				Node node = new Node(a, current, gCost, hCost);
				if (vecInList(closedList, a) && gCost >= node.gCost) continue;
				if (!vecInList(openList, a) || gCost < node.gCost) openList.add(node);
			}
		}
		closedList.clear();
		return null;
	}

	private boolean vecInList(List<Node> list, Vector2i vector) {
		for (Node n : list) {
			if (n.tile.equals(vector)) return true;
		}
		return false;
	}

	// getters
	public List<Projectile> getProjectiles() {
		return projectiles;
	}

	public List<Entity> getEntities() {
		return entities;
	}

	public List<Entity> getEntities(Entity e, int radius) {
		List<Entity> result = new ArrayList<Entity>();
		double ex = e.getX();
		double ey = e.getTileY();
		for (int i = 0; i < entities.size(); i++) {
			Entity entity = entities.get(i);
			if(entity.equals(e)) continue;
			double x = entity.getX();
			double y = entity.getTileY();

			double dx = Math.abs(x - ex);
			double dy = Math.abs(y - ey);
			double distace = Math.sqrt((dx * dx) + (dy * dy));
			if (distace <= radius) result.add(entity);
		}
		return result;
	}

	public List<Player> getPlayers(Entity e, int radius) {
		List<Player> result = new ArrayList<Player>();
		double ex = e.getX();
		double ey = e.getTileY();
		for (int i = 0; i < players.size(); i++) {
			Player player = players.get(i);
			double x = player.getX();
			double y = player.getTileY();

			double dx = Math.abs(x - ex);
			double dy = Math.abs(y - ey);
			double distace = Math.sqrt((dx * dx) + (dy * dy));
			if (distace <= radius) result.add(player);
		}
		return result;
	}

	public List<LightObject> getLightObjects() {
		return lightObjects;
	}

	public List<XMLObject> getXMLObjects() {
		return objects;
	}

	public List<Player> getPlayers() {
		return players;
	}

	public Player getPlayer(int index) {
		return players.get(index);
	}
}
