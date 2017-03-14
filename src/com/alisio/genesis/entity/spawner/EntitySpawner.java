package com.alisio.genesis.entity.spawner;

import com.alisio.genesis.entity.Entity;
import com.alisio.genesis.level.Level;

public class EntitySpawner extends Entity {
	
	public enum Type {
		PARTICLE
	}
	
	private Type type;
	
	public EntitySpawner(int x, int y, Type type, int amount, Level level) {
		init(level);
		this.x = x;
		this.y = y;
		this.type = type;
	}
}
