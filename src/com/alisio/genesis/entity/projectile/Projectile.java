package com.alisio.genesis.entity.projectile;

import java.util.Random;
import com.alisio.genesis.entity.Entity;
import com.alisio.genesis.graphics.Screen;
import com.alisio.genesis.graphics.SpriteSheet;
import com.alisio.genesis.level.tile.Tile;

public abstract class Projectile extends Entity {
	
	protected final double xStart, yStart;
	protected double angle;
	protected double nx, ny;
	protected double distance;
	protected double speed, range, damage;
	
	protected final Random random = new Random();
	
	public static SpriteSheet projectiles = new SpriteSheet("/" + Tile.SIZE + "/textures/projectiles.png");

	public Projectile(double x, double y, double direction) {
		super(x,y);
		this.xStart = x;
		this.yStart = y;
		this.x = x;
		this.y = y;
		this.angle = direction;
	}
	
	protected void move() {
		
	}
	
	//interface
	public abstract void update();
	public abstract void render(Screen screen);
}
