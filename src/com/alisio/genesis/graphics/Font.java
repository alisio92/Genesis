package com.alisio.genesis.graphics;

import com.alisio.genesis.level.tile.Tile;

public class Font {

	private static SpriteSheet font = new SpriteSheet("/fonts/arial.png",16,16);
	private static Sprite[] characters = Sprite.split(font);
	
	private static String lowerHalfChars = "gjpqy,";
	
	private static String charIndex = //
			"ABCDEFGHIJKLM" + //
			"NOPQRSTUVWQYZ" + //
			"abcdefghijklm" + //
			"nopqrstuvwxyz" + //
			"0123456789.,'" + //
			"'\"\";:!@$%()-+";
	
	public Font() {
		
	}
	
	public void render(int x, int y, String text,Screen screen) {
		render(x,y,0,0,text,screen);
	}
	
	public void render(int x, int y, int spacing, int color,String text,Screen screen) {
		int xOffset = 0;
		int line = 0;
		for(int i = 0; i < text.length();i++) {
			xOffset += Tile.SIZE + spacing;
			int yOffset = 0;
			char currentChar = text.charAt(i);
			if(lowerHalfChars.indexOf(currentChar) > -1) yOffset = 4;
			if(currentChar == '\n') {
				xOffset = 0;
				line++;
			}
			int index = charIndex.indexOf(currentChar);
			if(index == -1) continue;
			screen.renderText(x + xOffset, y + yOffset + (line * 20), characters[index],color,true);
		}
	}
}
