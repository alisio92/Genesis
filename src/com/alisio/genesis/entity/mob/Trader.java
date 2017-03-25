package com.alisio.genesis.entity.mob;

import com.alisio.genesis.graphics.AnimatedSprite;
import com.alisio.genesis.graphics.Screen;
import com.alisio.genesis.graphics.SpriteSheet;

public class Trader extends Mob {
	
	public static final int WIDTH = 32;
	public static final int HEIGHT = 32;
	
	public static SpriteSheet chaser = new SpriteSheet("/player/player.png");
	public static SpriteSheet chaserDown = new SpriteSheet(chaser,0,0,1,3,32);
	public static SpriteSheet chaserUp = new SpriteSheet(chaser,1,0,1,3,32);
	public static SpriteSheet chaserRight = new SpriteSheet(chaser,3,0,1,3,32);
	public static SpriteSheet chaserLeft = new SpriteSheet(chaser,2,0,1,3,32);
	
	private AnimatedSprite down = new AnimatedSprite(chaserDown,32,32,3);
	/*private AnimatedSprite up = new AnimatedSprite(chaserUp,32,32,3);
	private AnimatedSprite right = new AnimatedSprite(chaserRight,32,32,3);
	private AnimatedSprite left = new AnimatedSprite(chaserLeft,32,32,3);*/
	
	private AnimatedSprite animSprite = null;

	public Trader(int x, int y) {
		super(x, y);
		this.animSprite = down;
	}

	public void update() {
	}

	public void render(Screen screen) {
		sprite = animSprite.getSprite();
		screen.renderMob((int)(x - (WIDTH / 2)), (int)(y - (HEIGHT / 2)), this);
	}
}
