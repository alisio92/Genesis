package com.alisio.genesis.entity.mob;

import com.alisio.genesis.Game;
import com.alisio.genesis.entity.Projectile;
import com.alisio.genesis.entity.SphereProjectile;
import com.alisio.genesis.graphics.Screen;
import com.alisio.genesis.graphics.Sprite;
import com.alisio.genesis.graphics.SpriteSheet;
import com.alisio.genesis.input.KeyBoard;
import com.alisio.genesis.input.Mouse;

public class Player extends Mob {

	private KeyBoard input;
	private Sprite sprite;

	public static final int WIDTH = 32;
	public static final int HEIGHT = 32;

	public static SpriteSheet player = new SpriteSheet("/player/player.png");

	/*
	 * public static Sprite playerUp = new Sprite(WIDTH,HEIGHT,4,0,11,0,player);
	 * public static Sprite playerDown = new Sprite(WIDTH,HEIGHT,0,0,player);
	 * public static Sprite playerLeft = new
	 * Sprite(WIDTH,HEIGHT,0,3,0,-4,player); public static Sprite playerRight =
	 * new Sprite(WIDTH,HEIGHT,0,3,0,16,player);
	 */

	public static Sprite playerDown = new Sprite(WIDTH, HEIGHT, 0, 0, player);
	public static Sprite playerUp = new Sprite(WIDTH, HEIGHT, 1, 0, player);
	public static Sprite playerLeft = new Sprite(WIDTH, HEIGHT, 2, 0, player);
	public static Sprite playerRight = new Sprite(WIDTH, HEIGHT, 3, 0, player);

	public static Sprite playerDown1 = new Sprite(WIDTH, HEIGHT, 0, 1, player);
	public static Sprite playerUp1 = new Sprite(WIDTH, HEIGHT, 1, 1, player);
	public static Sprite playerLeft1 = new Sprite(WIDTH, HEIGHT, 2, 1, player);
	public static Sprite playerRight1 = new Sprite(WIDTH, HEIGHT, 3, 1, player);

	public static Sprite playerDown2 = new Sprite(WIDTH, HEIGHT, 0, 2, player);
	public static Sprite playerUp2 = new Sprite(WIDTH, HEIGHT, 1, 2, player);
	public static Sprite playerLeft2 = new Sprite(WIDTH, HEIGHT, 2, 2, player);
	public static Sprite playerRight2 = new Sprite(WIDTH, HEIGHT, 3, 2, player);

	public Player(int x, int y, KeyBoard input) {
		this.x = x;
		this.y = y;
		this.input = input;
		this.movingSpeed = 1;
		setAnimationSpeed(90);
		firerate = SphereProjectile.FIRE_RATE;
	}

	public Player(KeyBoard input) {
		this.input = input;
		this.x = 0;
		this.y = 0;
		this.movingSpeed = 1;
		setAnimationSpeed(90);
		firerate = SphereProjectile.FIRE_RATE;
	}

	public void update() {
		if(firerate > 0) firerate--;
		
		int xx = 0, yy = 0;

		if (timer < getAnimationSpeed()) timer++;
		else timer = 0;

		if (input.up) yy -= movingSpeed;
		if (input.down) yy += movingSpeed;
		if (input.left) xx -= movingSpeed;
		if (input.right) xx += movingSpeed;

		if (xx != 0 || yy != 0) {
			move(xx, yy);
			moving = true;

			if (timer % (getAnimationSpeed()+1) == getAnimationSpeed()) {
				if (animation >=2) animation = 0;
				else animation++;
			}
		} else moving = false;
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
			shoot((int)x,(int)y,dir);
			firerate = SphereProjectile.FIRE_RATE;
		}
	}

	public void render(Screen screen) {
		if (direction == 0) {
			sprite = playerUp;
			if (moving) {
				if (animation == 1) sprite = playerUp1;
				else if (animation == 2) sprite = playerUp2;
			}
		}
		if (direction == 2) {
			sprite = playerDown;
			if (moving) {
				if (animation == 1) sprite = playerDown1;
				else if (animation == 2) sprite = playerDown2;
			}
		}
		if (direction == 3) {
			sprite = playerLeft;
			if (moving) {
				if (animation == 1) sprite = playerLeft1;
				else if (animation == 2) sprite = playerLeft2;
			}
		}
		if (direction == 1) {
			sprite = playerRight;
			if (moving) {
				if (animation == 1) sprite = playerRight1;
				else if (animation == 2) sprite = playerRight2;
			}
		}

		screen.renderPlayer((int)(x - (WIDTH / 2)), (int)(y - (HEIGHT / 2)), sprite);
	}
}
