package com.alisio.genesis.entity.particle;

import com.alisio.genesis.entity.Entity;
import com.alisio.genesis.graphics.Screen;
import com.alisio.genesis.graphics.Sprite;
import com.alisio.genesis.level.object.TileObject;
import com.alisio.genesis.level.tile.Tile;

public class Particle extends Entity {
	
	public static Sprite particle = new Sprite(3,0xAAAAA);
	
	private Sprite sprite;
	private int life;
	private int time = 0;
	
	protected double xx, yy,zz;
	protected double xa,ya,za;
	
	public Particle(int x, int y, int life) {
		this.x = x;
		this.y = y;
		this.xx = x;
		this.yy = y;
		this.sprite = particle;
		this.life = life + (random.nextInt(20) - 10);
		this.xa = random.nextGaussian();
		this.ya = random.nextGaussian();
		this.zz = random.nextFloat() + 2.0;
	}
	
	public void update() {
		time++;
		if(time >= 7500) time = 0;
		if(time > life) remove();
		za -= 0.1;
		
		if(zz < 0) {
			zz = 0;
			za *= -0.55;
			xa *= 0.4;
			ya *= 0.4;
		}
		
		move(xx+xa,(yy + ya) + (zz+za));
	}
	
	private void move(double x, double y) {
		if(collision(x,y) || collisionOverlay(x,y)) {
			this.xx *= -0.5;
			this.yy *= -0.5;
			this.zz *= -0.5;
		}
		this.xx += xa;
		this.yy += ya;
		this.zz += za;
	}
	
	public boolean collision(double x, double y) {
		boolean collision = false;

		for (int i = 0; i < Tile.BASE_SIZE; i++) {
			double xt = (x - i % 2 * Tile.SIZE) / Tile.SIZE;
			double yt = (y - i / 2 * Tile.SIZE) / Tile.SIZE;
			int xx = (int) Math.ceil(xt);
			int yy = (int) Math.ceil(yt);
			if(i % 2 == 0) xx = (int) Math.floor(xt);
			if(i / 2 == 0) yy = (int) Math.floor(yt);
			if (level.getTile(xx, yy).blocksShooting()) collision = true;
		}

		return collision;
	}
	
	private boolean collisionOverlay(double x, double y) {
		boolean collision = false;
		
		for (int i = 0; i < Tile.BASE_SIZE; i++) {
			double xt = (x - i % 2 * Tile.SIZE) / Tile.SIZE;
			double yt = (y - i / 2 * Tile.SIZE) / Tile.SIZE;
			int xx = (int) Math.ceil(xt);
			int yy = (int) Math.ceil(yt);
			if(i % 2 == 0) xx = (int) Math.floor(xt);
			if(i / 2 == 0) yy = (int) Math.floor(yt);
			TileObject temp = level.getObjectCollision(xx,yy);
			
			if(temp != null) {
				x -= temp.sprite.getStartX();
				y -= temp.sprite.getStartY();
			}
			
			xt = (x - i % 2 * Tile.SIZE) / Tile.SIZE;
			yt = (y - i / 2 * Tile.SIZE) / Tile.SIZE;
			xx = (int) Math.ceil(xt);
			yy = (int) Math.ceil(yt);
			if(i % 2 == 0) xx = (int) Math.floor(xt);
			if(i / 2 == 0) yy = (int) Math.floor(yt);
			TileObject o = level.getObjectCollision(xx,yy);			
			if (o != null && o.blocksShooting()) collision = true;
		}

		return collision;
	}

	public void render(Screen screen) {
		screen.renderSprite((int)xx-1,(int)yy - (int)zz - 1,sprite,false);
	}
}
