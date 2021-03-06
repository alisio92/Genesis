package com.alisio.genesis.entity.projectile;

import com.alisio.genesis.Game;
import com.alisio.genesis.entity.spawner.ParticleSpawner;
import com.alisio.genesis.graphics.Screen;
import com.alisio.genesis.graphics.Sprite;
import com.alisio.genesis.level.tile.Tile;
import com.alisio.genesis.util.debug.Debug;

public class ArrowProjectile extends Projectile {

	public static Sprite projectile = new Sprite(Tile.SIZE, 1, 0, projectiles);
	public static final int FIRE_RATE = (int) Game.updates / 1;

	public ArrowProjectile(double x, double y, double direction) {
		super(x, y, direction);
		this.range = random.nextInt(100) + 150;
		this.speed = 4;
		this.damage = 20;
		this.sprite = Sprite.rotate(projectile,angle);

		this.nx = speed * Math.cos(angle);
		this.ny = speed * Math.sin(angle);
	}

	public void update() {
		//xoffset = pink pixels x-as to object
		//yoffset = pink pixels y-as to object
		if(level.tileCollision((int)(x + nx), (int)(y + ny), 7,5,4) || level.objectCollision((int)(x + nx), (int)(y + ny), 7,5,4)) {
			level.add(new ParticleSpawner((int)x,(int)y, 50, 50, level));
			remove();
		}
		move();
	}

	protected void move() {
		this.x += nx;
		this.y += ny;
		if (distance() > range) 
			remove();
	}

	private double distance() {
		double distance = 0;
		distance = Math.sqrt(Math.abs((xStart - x) * (xStart - x) + (yStart - y) * (yStart - y)));
		return distance;
	}

	public void render(Screen screen) {
		if(Debug.visible) Debug.drawRect(screen,(int) x - 8, (int) y - 10, sprite.getWidth(),sprite.getHeight(), false);
		screen.renderProjectile((int) x - 8, (int) y - 10, this);
	}
}
