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
	protected double movingSpeed = 0;

	protected enum Direction {
		UP, DOWN, LEFT, RIGHT
	}

	public Mob(double x, double y) {
		super(x, y);
	}

	protected void move(double xTo, double yTo) {
		if (xTo != 0 && yTo != 0) {
			move(xTo, 0);
			move(0, yTo);
			return;
		}

		if (xTo > 0) direction = Direction.RIGHT;
		if (xTo < 0) direction = Direction.LEFT;
		if (yTo > 0) direction = Direction.DOWN;
		if (yTo < 0) direction = Direction.UP;
		
		while(xTo != 0) {
			if(Math.abs(xTo) > 1) {
				if (!collision(abs(xTo), yTo) && !collisionOverlay(abs(xTo), yTo)) this.x += abs(xTo);
				xTo -= abs(xTo);
			} else {
				if (!collision(abs(xTo), yTo) && !collisionOverlay(abs(xTo), yTo)) this.x += xTo;
				xTo = 0;
			}
		}

		while(yTo != 0) {
			if(Math.abs(yTo) > 1) {
				if (!collision(xTo, abs(yTo)) && !collisionOverlay(xTo, abs(yTo))) this.y += abs(yTo);
				yTo -= abs(yTo);
			} else {
				if (!collision(xTo, abs(yTo)) && !collisionOverlay(xTo, abs(yTo))) this.y += yTo;
				yTo = 0;
			}
		}
	}

	private int abs(double value) {
		if (value < 0) return -1;
		else return 1;
	}

	protected void shoot(double x, double y, double direction) {
		Projectile p = new SphereProjectile(x, y, direction);
		level.add(p);
	}

	/*
	 * private boolean collision(double xTo, double yTo) { boolean collision =
	 * false;
	 * 
	 * for (int i = 0; i < Tile.BASE_SIZE; i++) { double xt = ((x + xTo) + i % 2
	 * * 14 - 8) / Tile.SIZE; double yt = ((y + yTo) + i / 2 * 12 + 3) /
	 * Tile.SIZE; if (!level.getTile((int) xt, (int) yt).walkable()) collision =
	 * true; }
	 * 
	 * return collision; }
	 */

	private boolean collision(double xTo, double yTo) {
		boolean collision = false;

		for (int i = 0; i < Tile.BASE_SIZE; i++) {
			double xt = ((x + xTo) - i % 2 * Tile.SIZE) / Tile.SIZE;
			double yt = ((y + yTo) - i / 2 * Tile.SIZE) / Tile.SIZE;
			int xx = (int) Math.ceil(xt);
			int yy = (int) Math.ceil(yt);
			if (i % 2 == 0) xx = (int) Math.floor(xt);
			if (i / 2 == 0) yy = (int) Math.floor(yt);
			if (!level.getTile(xx, yy).walkable()) collision = true;
		}

		return collision;
	}
	
	private boolean collisionOverlay(double xTo, double yTo) {
		boolean collision = false;

		for (int i = 0; i < Tile.BASE_SIZE; i++) {
			double xt = ((x + xTo)) / Tile.SIZE;
			double yt = ((y + yTo)) / Tile.SIZE;
			int xx = (int) Math.ceil(xt);
			int yy = (int) Math.ceil(yt);
			if (i % 2 == 0) xx = (int) Math.floor(xt);
			if (i / 2 == 0) yy = (int) Math.floor(yt);
			TileObject o = level.getObjectCollision(xx,yy); // level.getObject((int)xt,(int)yt);
			if (o != null && !o.walkable()) collision = true;
		}

		return collision;
	}

	/*private boolean collisionOverlay(double xTo, double yTo) {
		boolean collision = false;

		for (int i = 0; i < Tile.BASE_SIZE; i++) {
			double xt = ((x + xTo)) / Tile.SIZE;
			double yt = ((y + yTo)) / Tile.SIZE;
			TileObject o = level.getObjectCollision((int) xt, (int) yt); // level.getObject((int)xt,(int)yt);
			if (o != null && !o.walkable()) collision = true;
		}

		return collision;
	}*/

	// interface
	public abstract void update();

	public abstract void render(Screen screen);
}
