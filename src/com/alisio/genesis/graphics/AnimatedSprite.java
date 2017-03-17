package com.alisio.genesis.graphics;

public class AnimatedSprite extends Sprite {
	
	private int frame = 0;
	private Sprite sprite;
	private int animationSpeed = 0;
	protected int maxAnimationSpeed = 100;
	private int length = -1;
	private int time = 0;

	public AnimatedSprite(SpriteSheet sheet, int width, int height, int lenght) {
		super(sheet, width, height);
		this.length = lenght;
		this.sprite = sheet.getSprites()[0];
		if(length > sheet.getSprites().length) System.err.println("Error! Lenght of animation is too long!");
	}
	
	public void update(){	
		if (time < getAnimationSpeed()) time++;
		else time = 0;
		
		if(time % (getAnimationSpeed()+1) == getAnimationSpeed()) {
			if(frame >= length - 1) frame = 0;
			else frame++;
			this.sprite = sheet.getSprites()[frame];
		}
	}
	
	public Sprite getSprite() {
		return sprite;
	}
	
	public void setSprite(int index) {
		if(index > sheet.getSprites().length - 1) {
			System.err.println("Index out of bounds!");
			return;
		}
		if(index == 0) time = 0;
		sprite = sheet.getSprites()[index];
	}
	
	public void setAnimationSpeed(int animationSpeed) {
		if (animationSpeed <= maxAnimationSpeed) this.animationSpeed = animationSpeed;
		else this.animationSpeed = maxAnimationSpeed;
	}

	public int getAnimationSpeed() {
		return maxAnimationSpeed - animationSpeed;
	}
}
