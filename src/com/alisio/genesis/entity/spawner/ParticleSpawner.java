package com.alisio.genesis.entity.spawner;

import com.alisio.genesis.entity.particle.Particle;
import com.alisio.genesis.level.Level;

public class ParticleSpawner extends EntitySpawner {

	private int life;

	public ParticleSpawner(int x, int y, int life, int amount, Level level) {
		super(x, y, Type.PARTICLE, amount, level);
		this.life = life;
		for (int i = 0; i < amount; i++) {
			level.add(new Particle(x, y, 50));
		}
	}
}
