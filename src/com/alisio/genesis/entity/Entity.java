
package com.alisio.genesis.entity;

import java.util.Random;
import com.alisio.genesis.graphics.*;
import com.alisio.genesis.level.Level;

public class Entity {
    public double x, y;
    protected Sprite sprite;
    private boolean removed = false;
    protected Level level;
    protected final Random random = new Random();
    
    public Entity(int x, int y, Sprite sprite) {
    	this.x = x;
    	this.y = y;
    	this.sprite = sprite;
    }
    
    public Entity(int x, int y) {
    	this.x = x;
    	this.y = y;
    }
    
    public void update(){
        
    }
    
    public void render(Screen screen){
    	if(sprite != null) screen.renderSprite((int)x, (int)y, sprite);
    }
    
    public void remove(){
        this.removed = true;
    }
    
    public boolean isRemoved(){
        return removed;
    }
    
    public void init(Level level) {
    	this.level = level;
    }
    
    public Sprite getSprite(){
    	return sprite;
    }
}
