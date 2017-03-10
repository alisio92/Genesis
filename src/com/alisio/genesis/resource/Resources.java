package com.alisio.genesis.resource;

import com.alisio.genesis.level.object.*;
import com.alisio.genesis.level.tile.*;

public class Resources {
	
	//grass = 0xff00ff00
	//flower = 0xffffff00
	//sand = 0xff00ffff
	//stone = 0xffff0000
	//water = 0xffffffff
	//dirt = 0xff7F0000
	public Resources() {		
		Tile.listTiles.add(VoidTile.tile);
		Tile.listTiles.add(GrassTile.tile);
		Tile.listTiles.add(FlowerTile.tile);
		Tile.listTiles.add(StoneTile.tile);
		Tile.listTiles.add(SandTile.tile);
		Tile.listTiles.add(WaterTile.tile);
		Tile.listTiles.add(DirtTile.tile);
		
		TileObject.listObjects.add(VoidObject.object);
		TileObject.listObjects.add(TreeObject.object);
		TileObject.listObjects.add(PalmTreeObject.object);
	}
}
