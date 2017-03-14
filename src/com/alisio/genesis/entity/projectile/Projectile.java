package com.alisio.genesis.entity.projectile;

import java.util.Random;
import com.alisio.genesis.entity.Entity;
import com.alisio.genesis.graphics.Sprite;
import com.alisio.genesis.graphics.SpriteSheet;
import com.alisio.genesis.level.tile.Tile;

public abstract class Projectile extends Entity {
	
	protected final int xStart, yStart;
	protected double angle;
	protected Sprite sprite;
	protected double nx, ny;
	protected double distance;
	protected double speed, range, damage;
	
	protected final Random random = new Random();
	
	public static SpriteSheet projectiles = new SpriteSheet("/" + Tile.SIZE + "/textures/projectiles.png");

	public Projectile(int x, int y, double direction) {
		this.xStart = x;
		this.yStart = y;
		this.x = x;
		this.y = y;
		this.angle = direction;
	}
	
	protected void move() {
		
	}
}
