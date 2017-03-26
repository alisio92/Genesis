package com.alisio.genesis.entity.mob;

import com.alisio.genesis.Game;
import com.alisio.genesis.entity.projectile.*;
import com.alisio.genesis.graphics.AnimatedSprite;
import com.alisio.genesis.graphics.*;
import com.alisio.genesis.input.*;
import com.alisio.genesis.util.debug.Debug;

public class Player extends Mob {

	private KeyBoard input;
	private Sprite sprite;
	private AnimatedSprite animSprite = null;

	public static final int WIDTH = 32;
	public static final int HEIGHT = 32;

	public static SpriteSheet player = new SpriteSheet("/player/player.png");
	public static SpriteSheet playerDown = new SpriteSheet(player,0,0,1,3,32);
	public static SpriteSheet playerUp = new SpriteSheet(player,1,0,1,3,32);
	public static SpriteSheet playerRight = new SpriteSheet(player,3,0,1,3,32);
	public static SpriteSheet playerLeft = new SpriteSheet(player,2,0,1,3,32);
	
	private AnimatedSprite down = new AnimatedSprite(playerDown,32,32,3);
	private AnimatedSprite up = new AnimatedSprite(playerUp,32,32,3);
	private AnimatedSprite right = new AnimatedSprite(playerRight,32,32,3);
	private AnimatedSprite left = new AnimatedSprite(playerLeft,32,32,3);
	private double speed = 1.2;

	public Player(double x, double y, KeyBoard input) {
		super(x,y);
		this.input = input;
		this.movingSpeed = speed;
		this.firerate = SphereProjectile.FIRE_RATE;
		this.animSprite = up;
	}

	public Player(KeyBoard input) {
		super(0,0);
		this.input = input;
		this.movingSpeed = speed;
		this.firerate = SphereProjectile.FIRE_RATE;
		this.animSprite = up;
	}

	public void update() {
		if(firerate > 0) firerate--;		
		double xx = 0, yy = 0;

		if (input.up) yy -= movingSpeed;
		if (input.down) yy += movingSpeed;
		if (input.left) xx -= movingSpeed;
		if (input.right) xx += movingSpeed;

		if (xx != 0 || yy != 0) {
			move(xx, yy);
			moving = true;
			animSprite.setAnimationSpeed(90);
			animSprite.update();
		
			if (direction == Direction.UP) animSprite = up;
			if (direction == Direction.RIGHT) animSprite = right;
			if (direction == Direction.DOWN) animSprite = down;
			if (direction == Direction.LEFT) animSprite = left;			
		} else {
			moving = false;
			animSprite.setSprite(0);
		}
		clear();
		shooting();
	}

	private void clear() {
		for(int i = 0; i < level.getProjectiles().size();i++){
			Projectile p = level.getProjectiles().get(i);
			if(p.isRemoved()) level.getProjectiles().remove(i);
		}
	}

	private void shooting() {
		if(Mouse.getButton() == 1 && firerate <= 0) {
			double dx = Mouse.getX() - Game.getWindowWidth() / 2;
			double dy = Mouse.getY() - Game.getWindowHeight() / 2;
			double dir = Math.atan2(dy, dx);
			shoot(x,y,dir);
			firerate = SphereProjectile.FIRE_RATE;
		}
	}

	public void render(Screen screen) {
	    sprite = animSprite.getSprite();
	    if(Debug.visible) Debug.drawRect(screen,(int)(x - (WIDTH / 2)), (int)(y - (HEIGHT / 2)), sprite.getWidth(),sprite.getHeight(), false);
		screen.renderMob((int)(x - (WIDTH / 2)), (int)(y - (HEIGHT / 2)), sprite);
		
		/*for(int y2 = -(screen.height / 2); y2 < screen.height / 2;y2++) {
			for(int x2 = -(screen.width / 2); x2 < screen.width / 2;x2++) {
				if(collisionOverlay(x2,y2)) Debug.drawRect(screen,(int)x + x2,(int)y + y2,1,1,0xff0000ff,false);
			}
		}*/
	}
}
