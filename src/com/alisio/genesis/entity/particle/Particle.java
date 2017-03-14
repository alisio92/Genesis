package com.alisio.genesis.entity.particle;

import com.alisio.genesis.entity.Entity;
import com.alisio.genesis.graphics.Screen;
import com.alisio.genesis.graphics.Sprite;

public class Particle extends Entity {
	
	public static Sprite particle = new Sprite(3,0xAAAAA);
	
	private Sprite sprite;
	private int life;
	
	protected double xx, yy,xa,ya;
	
	public Particle(int x, int y, int life) {
		this.x = x;
		this.y = y;
		this.xx = x;
		this.yy = y;
		this.sprite = particle;
		this.life = life;
		this.xa = random.nextGaussian();
		this.ya = random.nextGaussian();
	}
	
	public void update() {
		this.xx += xa;
		this.yy += ya;
	}
	
	public void render(Screen screen) {
		screen.renderSprite((int)xx,(int)yy,sprite,false);
	}
}
