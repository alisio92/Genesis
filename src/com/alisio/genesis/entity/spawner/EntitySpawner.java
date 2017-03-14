package com.alisio.genesis.entity.spawner;

import com.alisio.genesis.entity.Entity;
import com.alisio.genesis.entity.particle.Particle;
import com.alisio.genesis.level.Level;

public class EntitySpawner extends Entity {
	
	public enum Type {
		MOB, PARTICLE
	}
	
	private Type type;
	
	public EntitySpawner(int x, int y, Type type, int amount, Level level) {
		init(level);
		this.x = x;
		this.y = y;
		this.type = type;
		for(int i = 0; i < amount;i++){
			if(type == Type.PARTICLE) {
				level.add(new Particle(x,y,50));
			}
		}
	}
}
