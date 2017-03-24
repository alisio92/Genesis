package com.alisio.genesis.entity.mob;

import java.util.List;
import com.alisio.genesis.graphics.*;

public class Chaser extends Mob {
	
	public static final int WIDTH = 32;
	public static final int HEIGHT = 32;
	
	public static SpriteSheet chaser = new SpriteSheet("/player/player.png");
	public static SpriteSheet chaserDown = new SpriteSheet(chaser,0,0,1,3,32);
	public static SpriteSheet chaserUp = new SpriteSheet(chaser,1,0,1,3,32);
	public static SpriteSheet chaserRight = new SpriteSheet(chaser,3,0,1,3,32);
	public static SpriteSheet chaserLeft = new SpriteSheet(chaser,2,0,1,3,32);
	
	private AnimatedSprite down = new AnimatedSprite(chaserDown,32,32,3);
	private AnimatedSprite up = new AnimatedSprite(chaserUp,32,32,3);
	private AnimatedSprite right = new AnimatedSprite(chaserRight,32,32,3);
	private AnimatedSprite left = new AnimatedSprite(chaserLeft,32,32,3);
	
	private AnimatedSprite animSprite = null;
	private int time = 0;
	private double xx = 0;
	private double	yy = 0;
	private double speed = 1.0;

	public Chaser(int x, int y) {
		super(x, y);
		this.movingSpeed = 1;
		this.animSprite = up;
	}
	
	private void move(){
		xx = 0;
		yy = 0;
		List<Player> players = level.getPlayers(this, 50);
		if(players.size() > 0) {
			Player player = level.getPlayer(0);
			if((int)x < (int)player.getX()) xx+= speed;
			if((int)x > (int)player.getX()) xx-= speed;
			if((int)y < (int)player.getY()) yy+= speed;
			if((int)y > (int)player.getY()) yy-= speed;
		}
		
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
	}

	public void update() {	
		move();
		time++;
		if(time >= 7500) time = 0;
		/*if(time % (random.nextInt(50) + 30) == 0) {
			xx = random.nextInt(3) - 1;
			yy = random.nextInt(3) - 1;
			if(random.nextInt(3) == 0) {
				xx = 0;
				yy = 0;
			}
		}*/
	}

	public void render(Screen screen) {
		sprite = animSprite.getSprite();
		screen.renderMob((int)(x - (WIDTH / 2)), (int)(y - (HEIGHT / 2)), this);
	}
}
