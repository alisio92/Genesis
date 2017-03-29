package com.alisio.genesis.graphics;

import java.util.List;
import com.alisio.genesis.entity.mob.Mob;
import com.alisio.genesis.entity.projectile.*;
import com.alisio.genesis.level.Level;
import com.alisio.genesis.level.object.*;
import com.alisio.genesis.level.tile.Tile;
import com.alisio.genesis.util.*;

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

	public void renderObject(int xLoc, int yLoc, TileObject object, Level level) {
		xLoc -= XOffset;
		yLoc -= yOffset;
		
		int tb = Level.brightness;
		if(tb < -240) tb = -240;
		if(tb > 0) tb = 0;
		
		for (int y = 0; y < object.getSprite().getHeight(); y++) {
			int yy = y + yLoc;
			for (int x = 0; x < object.getSprite().getWidth(); x++) {
				int xx = x + xLoc;
				if (xx < -object.getSprite().getWidth() || xx >= width || yy < 0 || yy >= height) break;
				if (xx < 0) xx = 0;
				int col = object.getSprite().pixels[x + y * object.getSprite().getWidth()];
				if (col != 0xffff00ff) {
					if (!object.emitsLight()) {
						List<LightObject> objects = level.getLightObjects();
						if (objects != null && objects.size() > 0) {
							boolean pointswithinborder = false;
							LightObject lo = null;
							int distanceX = 100, distanceY = 100;
							for (int i = 0; i < objects.size(); i++) {
								LightObject o = objects.get(i);

								int xLeft = o.getX() - XOffset;
								int xRight = xLeft + Tile.SIZE - 1;
								int yTop = o.getY() - yOffset;
								int yBottom = yTop + Tile.SIZE - 1;						

								if (xx < (xLeft - o.getRadius()) || xx > (xRight + o.getRadius())
										|| yy < (yTop - o.getRadius()) || yy > (yBottom + o.getRadius())) {
								} else {
									pointswithinborder = true;
									lo = o;
									int xLight = o.getX() - (XOffset);
									int xObject = xx;
			
									int yLight = o.getY() - (yOffset);
									int yObject = yy;
									
									int xDelta = Math.abs(xLight - xObject);
									int yDelta = Math.abs(yLight - yObject);
			
									if(xDelta < distanceX) distanceX = xDelta;
									if(yDelta < distanceY) distanceY = yDelta;
								}
							}

							if (!pointswithinborder) col = ColorManipulation.changeBrightness(col, Level.brightness);
							else {							
								int xxx = distanceX >> Tile.BASE_SIZE;
								int yyy = distanceY >> Tile.BASE_SIZE;
								double intensity = Math.abs((Math.pow(xxx * Math.PI,2) + Math.pow(yyy * Math.PI,2))) * tb * 0.00018;	
								col = ColorManipulation.tint(col, lo.red * intensity, lo.green * intensity, lo.blue * intensity);
							}

						} else col = ColorManipulation.changeBrightness(col, Level.brightness);
					}
					this.pixels[xx + yy * width] = col;
					//Debug.drawRect(this,xx + XOffset,yy + yOffset,1,1,0xff0000ff,false);
				}
			}
		}
	}

	public void renderTile(int xLoc, int yLoc, Tile tile) {
		xLoc -= XOffset;
		yLoc -= yOffset;
		for (int y = 0; y < tile.sprite.getHeight(); y++) {
			int yy = y + yLoc;
			for (int x = 0; x < tile.sprite.getWidth(); x++) {
				int xx = x + xLoc;
				if (xx < -tile.sprite.getWidth() || xx >= width || yy < 0 || yy >= height) break;
				if (xx < 0) xx = 0;
				int col = tile.sprite.pixels[x + y * tile.sprite.getWidth()];
				if (col != 0xffff00ff) {
					col = ColorManipulation.changeBrightness(col, Level.brightness);
					this.pixels[xx + yy * width] = col;
				}
			}
		}
	}
	
	public void renderText(int xLoc, int yLoc, Sprite sprite,int color, boolean fixed) {
		if (!fixed) {
			xLoc -= XOffset;
			yLoc -= yOffset;
		}
		for (int y = 0; y < sprite.getHeight(); y++) {
			int yy = y + yLoc;
			for (int x = 0; x < sprite.getWidth(); x++) {
				int xx = x + xLoc;
				if (xx < 0 || xx >= width || yy < 0 || yy >= height) continue;
				int col = sprite.pixels[x + y * sprite.getWidth()];
				if (col != 0xffff00ff && col != 0xff7F007F) this.pixels[xx + yy * width] = color;
			}
		}
	}

	public void renderSprite(int xLoc, int yLoc, Sprite sprite, boolean fixed) {
		if (!fixed) {
			xLoc -= XOffset;
			yLoc -= yOffset;
		}
		for (int y = 0; y < sprite.getHeight(); y++) {
			int yy = y + yLoc;
			for (int x = 0; x < sprite.getWidth(); x++) {
				int xx = x + xLoc;
				if (xx < 0 || xx >= width || yy < 0 || yy >= height) continue;
				int col = sprite.pixels[x + y * sprite.getWidth()];
				if (col != 0xffff00ff && col != 0xff7F007F) this.pixels[xx + yy * width] = col;
			}
		}
	}

	public void renderSprite(int xLoc, int yLoc, Sprite sprite) {
		xLoc -= XOffset;
		yLoc -= yOffset;
		for (int y = 0; y < sprite.getHeight(); y++) {
			int yy = y + yLoc;
			for (int x = 0; x < sprite.getWidth(); x++) {
				int xx = x + xLoc;
				if (xx < -sprite.getWidth() || xx >= width || yy < 0 || yy >= height) break;
				if (xx < 0) xx = 0;
				int col = sprite.pixels[x + y * sprite.getWidth()];
				if (col != 0xffff00ff) this.pixels[xx + yy * width] = col;
			}
		}
	}
	
	public void renderProjectile(int xLoc, int yLoc, Projectile p) {
		xLoc -= XOffset;
		yLoc -= yOffset;
		for (int y = 0; y < p.getSprite().getHeight(); y++) {
			int yy = y + yLoc;
			for (int x = 0; x < p.getSprite().getWidth(); x++) {
				int xx = x + xLoc;
				if (xx < -p.getSprite().getWidth() || xx >= width || yy < 0 || yy >= height) break;
				if (xx < 0) xx = 0;
				int col = p.getSprite().pixels[x + y * p.getSprite().getWidth()];
				if (col != 0xffff00ff) this.pixels[xx + yy * width] = col;
			}
		}
	}

	public void renderMob(int xLoc, int yLoc, Sprite sprite) {
		xLoc -= XOffset;
		yLoc -= yOffset;
		for (int y = 0; y < sprite.getHeight(); y++) {
			int yy = y + yLoc;
			for (int x = 0; x < sprite.getWidth(); x++) {
				int xx = x + xLoc;
				if (xx < -sprite.getWidth() || xx >= width || yy < 0 || yy >= height) break;
				if (xx < 0) xx = 0;
				int col = sprite.pixels[x + y * sprite.getWidth()];
				if (col != 0xff78C380) {
					col = ColorManipulation.changeBrightness(col, Level.brightness);
					this.pixels[xx + yy * width] = col;
				}
			}
		}
	}
	
	public void renderMob(int xLoc, int yLoc, Mob mob) {
		xLoc -= XOffset;
		yLoc -= yOffset;
		for (int y = 0; y < mob.getSprite().getHeight(); y++) {
			int yy = y + yLoc;
			for (int x = 0; x < mob.getSprite().getWidth(); x++) {
				int xx = x + xLoc;
				if (xx < -mob.getSprite().getWidth() || xx >= width || yy < 0 || yy >= height) break;
				if (xx < 0) xx = 0;
				int col = mob.getSprite().pixels[x + y * mob.getSprite().getWidth()];
				if (col != 0xff78C380) {
					col = ColorManipulation.changeBrightness(col, Level.brightness);
					this.pixels[xx + yy * width] = col;
				}
			}
		}
	}

	public void renderLight(int xLoc, int yLoc, Tile tile, int r, int g, int b, double intensity) {
		xLoc -= XOffset;
		yLoc -= yOffset;
		for (int y = 0; y < tile.sprite.getHeight(); y++) {
			int yy = y + yLoc;
			for (int x = 0; x < tile.sprite.getWidth(); x++) {
				int xx = x + xLoc;
				if (xx < -tile.sprite.getWidth() || xx >= width || yy < 0 || yy >= height) break;
				if (xx < 0) xx = 0;
				int col = tile.sprite.pixels[x + y * tile.sprite.getWidth()];
				col = ColorManipulation.tint(col, r * intensity, g * intensity, b * intensity);
				this.pixels[xx + yy * width] = col;
			}
		}
	}
	
	public void drawRect(int xLoc, int yLoc, int width, int height, int color, boolean fixed) {
		if (!fixed) {
			xLoc -= XOffset;
			yLoc -= yOffset;
		}
		
		for(int x = xLoc; x < xLoc + width;x++) {
			if(x < 0 || x >= this.width || yLoc >= this.height) continue;
			if(yLoc > 0) pixels[x + yLoc * this.width] = color;
			if(yLoc + height >= this.height) continue;
			if(yLoc + height > 0) pixels[x + (yLoc + height) * this.width] = color;
		}
		for(int y = yLoc; y <= yLoc + height;y++) {
			if(xLoc >= this.width || y < 0 || y >= this.height) continue;
			if(xLoc > 0) pixels[xLoc + y * this.width] = color;
			if(xLoc + width >= this.width) continue;
			if(xLoc + width > 0) pixels[(xLoc + width) + y * this.width] = color;
		}
	}

	public void setOffset(int xOffset, int yOffset) {
		this.XOffset = xOffset;
		this.yOffset = yOffset;
	}
}
