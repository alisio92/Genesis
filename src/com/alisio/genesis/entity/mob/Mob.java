package com.alisio.genesis.entity.mob;

import com.alisio.genesis.entity.Entity;
import com.alisio.genesis.entity.projectile.*;
import com.alisio.genesis.graphics.*;
import com.alisio.genesis.level.object.*;
import com.alisio.genesis.level.tile.Tile;

public abstract class Mob extends Entity {

	protected Direction direction = Direction.DOWN;
	protected int firerate = 0;
	protected boolean moving = false;
	protected int movingSpeed = 0;
	
	protected enum Direction {
		UP,DOWN,LEFT,RIGHT
	}
	
	public Mob(int x, int y){
		super(x,y);
	}

	protected void move(int xTo, int yTo) {
		if (xTo != 0 && yTo != 0) {
			move(xTo, 0);
			move(0, yTo);
			return;
		}

		if (xTo > 0) direction = Direction.RIGHT;
		if (xTo < 0) direction = Direction.LEFT;
		if (yTo > 0) direction = Direction.DOWN;
		if (yTo < 0) direction = Direction.UP;

		if (!collision(xTo, yTo) && !collisionOverlay(xTo, yTo)) {
			this.x += xTo;
			this.y += yTo;
		}
	}
	
	protected void shoot(int x, int y, double direction) {
		Projectile p = new SphereProjectile(x, y, direction);
		level.add(p);
	}

	private boolean collision(int xTo, int yTo) {
		boolean collision = false;

		for (int i = 0; i < Tile.BASE_SIZE; i++) {
			double xt = ((x + xTo) + i % 2 * 14 - 8) / Tile.SIZE;
			double yt = ((y + yTo) + i / 2 * 12 + 3) / Tile.SIZE;
			if (!level.getTile((int) xt, (int) yt).walkable()) collision = true;
		}

		return collision;
	}

	private boolean collisionOverlay(int xTo, int yTo) {
		boolean collision = false;

		for (int i = 0; i < Tile.BASE_SIZE; i++) {
			double xt = ((x + xTo)) / Tile.SIZE;
			double yt = ((y + yTo)) / Tile.SIZE;
			TileObject o = level.getObjectCollision((int) xt, (int) yt); // level.getObject((int)xt,(int)yt);
			if (o != null && !o.walkable()) collision = true;
		}

		return collision;
	}
	
	//interface
	public abstract void update();
	public abstract void render(Screen screen);
}
