package com.alisio.genesis.graphics;

import com.alisio.genesis.level.Level;
import com.alisio.genesis.level.object.TileObject;
import com.alisio.genesis.level.tile.Tile;

public class Screen {
	public int width, height;
	public int[] pixels;
	public final int MAP_SIZE = 512;
	public final int MAP_SIZE_MASK = MAP_SIZE - 1;
	public int XOffset, yOffset;

	public int[] tiles = new int[MAP_SIZE * MAP_SIZE];

	public Screen(int width, int height) {
		this.width = width;
		this.height = height;
		this.pixels = new int[width * height];
	}

	public void clear() {
		for (int i = 0; i < pixels.length; i++) {
			this.pixels[i] = 0;
		}
	}
	
	public void renderObject(int xLoc, int yLoc, TileObject object){
		xLoc -= XOffset;
		yLoc -= yOffset;
		for(int y = 0;y < object.sprite.getHeight();y++){
			int yy = y + yLoc;
			for(int x = 0;x < object.sprite.getWidth();x++){
				int xx = x + xLoc;
				if(xx < -object.sprite.getWidth() || xx >= width || yy < 0 || yy >= height) break;
				if(xx < 0) xx = 0;
				int col = object.sprite.pixels[x+y*object.sprite.getWidth()];				
				if(col != 0xffff00ff) {
					col = Environment.changeBrightness(col, Level.brightness);
					this.pixels[xx+yy*width] = col;
				}
			}
		}
	}
	
	public void renderTile(int xLoc, int yLoc, Tile tile){
		xLoc -= XOffset;
		yLoc -= yOffset;
		for(int y = 0;y < tile.sprite.getHeight();y++){
			int yy = y + yLoc;
			for(int x = 0;x < tile.sprite.getWidth();x++){
				int xx = x + xLoc;
				if(xx < -tile.sprite.getWidth() || xx >= width || yy < 0 || yy >= height) break;
				if(xx < 0) xx = 0;
				int col = tile.sprite.pixels[x+y*tile.sprite.getWidth()];
				if(col != 0xffff00ff) {
					col = Environment.changeBrightness(col, Level.brightness);
					this.pixels[xx+yy*width] = col;
				}
			}
		}
	}
	
	public void renderSprite(int xLoc, int yLoc, Sprite sprite, boolean fixed) {
		if(!fixed) {
			xLoc -= XOffset;
			yLoc -= yOffset;
		}
		for(int y = 0;y < sprite.getHeight();y++){
			int yy = y + yLoc;
			for(int x = 0;x < sprite.getWidth();x++){
				int xx = x + xLoc;
				if(xx < 0 || xx >= width || yy < 0 || yy >= height) continue;
				int col = sprite.pixels[x+y*sprite.getWidth()];
				if(col != 0xffff00ff) this.pixels[xx+yy*width] = col;
			}
		}
	}
	
	public void renderSprite(int xLoc, int yLoc, Sprite sprite) {
		xLoc -= XOffset;
		yLoc -= yOffset;
		for(int y = 0;y < sprite.getHeight();y++){
			int yy = y + yLoc;
			for(int x = 0;x < sprite.getWidth();x++){
				int xx = x + xLoc;
				if(xx < -sprite.getWidth() || xx >= width || yy < 0 || yy >= height) break;
				if(xx < 0) xx = 0;
				int col = sprite.pixels[x+y*sprite.getWidth()];
				if(col != 0xffff00ff) this.pixels[xx+yy*width] = col;
			}
		}
	}
	
	public void renderPlayer(int xLoc, int yLoc, Sprite sprite) {
		xLoc -= XOffset;
		yLoc -= yOffset;
		for(int y = 0;y < sprite.getHeight();y++){
			int yy = y + yLoc;
			for(int x = 0;x < sprite.getWidth();x++){
				int xx = x + xLoc;
				if(xx < -sprite.getWidth() || xx >= width || yy < 0 || yy >= height) break;
				if(xx < 0) xx = 0;
				int col = sprite.pixels[x+y*sprite.getWidth()];
				if(col != 0xff78C380) {
					col = Environment.changeBrightness(col, Level.brightness);
					this.pixels[xx+yy*width] = col;
				}
			}
		}
	}
	
	public void setOffset(int xOffset, int yOffset){
		this.XOffset = xOffset;
		this.yOffset = yOffset;
	}
}
