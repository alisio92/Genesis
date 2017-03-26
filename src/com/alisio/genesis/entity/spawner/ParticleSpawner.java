package com.alisio.genesis.entity.spawner;

import com.alisio.genesis.entity.particle.Particle;
import com.alisio.genesis.level.Level;

public class ParticleSpawner extends EntitySpawner {

	private int life;
	private int time = 0;

	public ParticleSpawner(int x, int y, int life, int amount, Level level) {
		super(x, y, Type.PARTICLE, amount, level);
		this.life = life;
		for (int i = 0; i < amount; i++) {
			level.add(new Particle(x, y, life));
		}
	}
	
	public void update() {
		time++;
		if(time >= 7500) time = 0;
		if(time > life * 2) remove();
	}
}
