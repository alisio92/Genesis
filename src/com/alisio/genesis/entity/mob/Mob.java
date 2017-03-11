package com.alisio.genesis.entity.mob;

import com.alisio.genesis.entity.Entity;
import com.alisio.genesis.entity.Projectile;
import com.alisio.genesis.entity.SphereProjectile;
import com.alisio.genesis.graphics.Screen;
import com.alisio.genesis.graphics.Sprite;
import com.alisio.genesis.level.object.TileObject;
import com.alisio.genesis.level.tile.Tile;

public abstract class Mob extends Entity {

	protected Sprite sprite;
	protected int direction = 0;
	protected int timer = 0;

	protected int animation = 0;
	private int animationSpeed = 0;
	protected int maxAnimationSpeed = 100;
	
	protected int firerate = 0;

	protected boolean moving = false;
	protected int movingSpeed = 0;

	public void move(int xTo, int yTo) {
		if(xTo != 0 && yTo != 0){
			move(xTo,0);
			move(0,yTo);
			return;
		}
		
		if (xTo > 0) direction = 1;
		if (xTo < 0) direction = 3;
		if (yTo > 0) direction = 2;
		if (yTo < 0) direction = 0;

		if (!collision(xTo, yTo) && !collisionOverlay(xTo, yTo)) {
			this.x += xTo;
			this.y += yTo;
		}
	}

	public void update() {
	}
	
	protected void shoot(int x, int y, double direction) {
		Projectile p = new SphereProjectile(x, y, direction);
		level.addProjectile(p);
	}

	public void render(Screen screen) {

	}

	private boolean collision(int xTo, int yTo) {
		boolean collision = false;
		
		for(int i = 0;i<Tile.BASE_SIZE;i++){
			double xt = ((x + xTo) + i % 2 * 14 - 8) / Tile.SIZE;
			double yt = ((y + yTo) + i / 2 * 12 + 3) / Tile.SIZE;
			if (!level.getTile((int)xt,(int)yt).walkable()) collision = true;
		}
				
		return collision;
	}
	
	private boolean collisionOverlay(int xTo, int yTo) {
		boolean collision = false;
		
		for(int i = 0;i<Tile.BASE_SIZE;i++){
			double xt = ((x + xTo)) / Tile.SIZE;
			double yt = ((y + yTo)) / Tile.SIZE;
			TileObject o = getObjectCollision((int)xt,(int)yt); //level.getObject((int)xt,(int)yt);
			if (o != null && !o.walkable()) 
				collision = true;
		}
				
		return collision;
	}
	
	private TileObject getObjectCollision(int x, int y){
		int radius = 4;
		for(int yy = 0; yy < radius;yy++){
			for(int xx = 0; xx < radius;xx++){
				TileObject o = level.getObject(x-xx,y-yy);
				if (o != null) return o;
			}
		}
		return null;
	}

	public void setAnimationSpeed(int animationSpeed) {
		if (animationSpeed <= maxAnimationSpeed) this.animationSpeed = animationSpeed;
		else this.animationSpeed = maxAnimationSpeed;
	}

	public int getAnimationSpeed() {
		return maxAnimationSpeed - animationSpeed;
	}
}
