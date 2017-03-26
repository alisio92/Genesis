package com.alisio.genesis.entity.spawner;

import com.alisio.genesis.entity.Entity;
import com.alisio.genesis.level.Level;

public class EntitySpawner extends Entity {
	
	public enum Type {
		PARTICLE
	}
	
	private Type type;
	
	public EntitySpawner(int x, int y, Type type, int amount, Level level) {
		super(x,y,null);
		init(level);
		this.type = type;
	}
	
	public Type getType() {
		return type;
	}
}
