package com.alisio.genesis.entity.mob;

import java.util.List;
import com.alisio.genesis.graphics.*;
import com.alisio.genesis.level.Node;
import com.alisio.genesis.level.tile.Tile;
import com.alisio.genesis.util.Vector2i;
import com.alisio.genesis.util.debug.Debug;

public class Star extends Mob{

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
	private double speed = 0.8;
	private List<Node> path = null;

	public Star(int x, int y) {
		super(x, y);
		this.movingSpeed = 1;
		this.animSprite = up;
	}
	
	private void move(){
		xx = 0;
		yy = 0;
		
		int px = (int)level.getPlayer(0).getX();
		int py = (int)level.getPlayer(0).getY();
		Vector2i start = new Vector2i((int)(getX()) >> Tile.BASE_SIZE,(int)(getY()) >> Tile.BASE_SIZE);
		Vector2i destination = new Vector2i(px >> Tile.BASE_SIZE,py >> Tile.BASE_SIZE);
		if(time % 3 == 0) path = level.findPath(start, destination);
		if(path != null) {
			if(path.size() > 0) {
				Vector2i vec = path.get(path.size() - 1).tile;	
				if((int)x < (int)vec.getX() << Tile.BASE_SIZE) xx+= speed;
				if((int)x > (int)vec.getX() << Tile.BASE_SIZE) xx-= speed;
				if((int)y < (int)vec.getY() << Tile.BASE_SIZE) yy+= speed;
				if((int)y > (int)vec.getY() << Tile.BASE_SIZE) yy-= speed;
			}
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
	}

	public void render(Screen screen) {
		sprite = animSprite.getSprite();
		if(Debug.visible) Debug.drawRect(screen,(int)(x - (WIDTH / 2)), (int)(y - (HEIGHT / 2)), sprite.getWidth(),sprite.getHeight(), false);
		screen.renderMob((int)(x - (WIDTH / 2)), (int)(y - (HEIGHT / 2)), this);
	}
}
