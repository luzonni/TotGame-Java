package com.lzstudios.world;

import java.awt.Graphics;
import java.awt.image.BufferedImage;

import com.lzstudios.main.Game;

public class Tile {
	
	public static BufferedImage TILE_FLOOR = Game.spritesheet.getSprite(0,0,16,16);
	public static BufferedImage TILE_WALL_Enemy = Game.spritesheet.getSprite(16,16,16,16);
	public static BufferedImage TILE_BLACK = Game.spritesheet.getSprite(0,16,16,16);
	public static BufferedImage TILE_Shotter = Game.spritesheet.getSprite(16,32,16,16);
	public static BufferedImage TILE_Teleporter = Game.spritesheet.getSprite(32,48,16,16);
	public static BufferedImage TILE_Null = Game.spritesheet.getSprite(0,32,16,16);
	public static BufferedImage TILE_Box = Game.spritesheet.getSprite(0,48,16,16);
	public static BufferedImage TILE_Door = Game.spritesheet.getSprite(0,112,16,16);
	//Walls
	public static BufferedImage TILE_WALLDefaul = Game.SpriteTiles.getSprite(0,48,16,16);
	public static BufferedImage TILE_Wall1 = Game.SpriteTiles.getSprite(0,0,16,16);
	public static BufferedImage TILE_Wall2 = Game.SpriteTiles.getSprite(0,16,16,16);
	public static BufferedImage TILE_Wall3 = Game.SpriteTiles.getSprite(0,32,16,16);
	public static BufferedImage TILE_Wall4 = Game.SpriteTiles.getSprite(16,32,16,16);
	public static BufferedImage TILE_Wall5 = Game.SpriteTiles.getSprite(32,32,16,16);
	public static BufferedImage TILE_Wall6 = Game.SpriteTiles.getSprite(48,32,16,16);
	public static BufferedImage TILE_Wall7 = Game.SpriteTiles.getSprite(64,16,16,16);
	public static BufferedImage TILE_Wall8 = Game.SpriteTiles.getSprite(80,16,16,16);
	public static BufferedImage TILE_Wall9 = Game.SpriteTiles.getSprite(64,32,16,16);
	public static BufferedImage TILE_Wall10 = Game.SpriteTiles.getSprite(80,32,16,16);
	public static BufferedImage TILE_Wall11 = Game.SpriteTiles.getSprite(16,16,16,16);
	public static BufferedImage TILE_Wall12 = Game.SpriteTiles.getSprite(32,16,16,16);
	public static BufferedImage TILE_Wall13 = Game.SpriteTiles.getSprite(48,16,16,16);
	public static BufferedImage TILE_Wall14 = Game.SpriteTiles.getSprite(16,0,16,16);
	public static BufferedImage TILE_Wall15 = Game.SpriteTiles.getSprite(32,0,16,16);
	

	private BufferedImage sprite;
	private int x,y;
	
	public Tile(int x,int y,BufferedImage sprite){
		this.x = x;
		this.y = y;
		this.sprite = sprite;
	}
	
	public void render(Graphics g){
		g.drawImage(sprite, x - Camera.x, y - Camera.y, null);
	}

}
