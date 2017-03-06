
package com.alisio.genesis.entity;

import java.util.Random;
import com.alisio.genesis.graphics.Screen;
import com.alisio.genesis.level.Level;

public class Entity {
    public double x, y;
    private boolean removed = false;
    protected Level level;
    protected final Random random = new Random();
    
    
    public void update(){
        
    }
    
    public void render(Screen screen){
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
}
