package com.alisio.genesis.entity.projectile;

import com.alisio.genesis.Game;
import com.alisio.genesis.entity.spawner.ParticleSpawner;
import com.alisio.genesis.graphics.Screen;
import com.alisio.genesis.graphics.Sprite;
import com.alisio.genesis.level.tile.Tile;

public class SphereProjectile extends Projectile {

	public static Sprite projectile = new Sprite(Tile.SIZE, 0, 0, projectiles);
	public static final int FIRE_RATE = (int) Game.updates / 1;

	public SphereProjectile(int x, int y, double direction) {
		super(x, y, direction);
		this.range = random.nextInt(100) + 150;
		this.speed = 4;
		this.damage = 20;
		this.sprite = projectile;

		this.nx = speed * Math.cos(angle);
		this.ny = speed * Math.sin(angle);
	}

	public void update() {
		if(level.tileCollision(x, y, nx, ny, 16)) {
			level.add(new ParticleSpawner((int)x,(int)y, 50, 50, level));
			remove();
		}
		move();
	}

	protected void move() {
		this.x += nx;
		this.y += ny;
		if (distance() > range) remove();
	}

	private double distance() {
		double distance = 0;
		distance = Math.sqrt(Math.abs((xStart - x) * (xStart - x) + (yStart - y) * (yStart - y)));
		return distance;
	}

	public void render(Screen screen) {
		screen.renderSprite((int) x - 8, (int) y - 10, sprite);
	}
}
