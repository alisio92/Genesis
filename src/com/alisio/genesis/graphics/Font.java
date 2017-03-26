package com.alisio.genesis.graphics;

public class Font {

	private static SpriteSheet font = new SpriteSheet("/fonts/arial.png",16,16);
	private static Sprite[] characters = Sprite.split(font);
	
	public Font() {
		
	}
	
	public void render(Screen screen) {
		screen.renderSprite(50, 50, characters[1],true);
	}
}
