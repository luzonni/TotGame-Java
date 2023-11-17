package com.lzstudios.world;

import java.awt.Graphics;
import java.awt.image.BufferedImage;

import com.lzstudios.main.Game;

public class TileEnemy extends Tile{
	
	public BufferedImage Sprite[];
	
	public static int CurrentAnim = 0,LastAnim = 3;
	public static int TimeAnim;
	
	private int X,Y;

	public TileEnemy(int x, int y, BufferedImage sprite) {
		super(x, y, sprite);
		X = x;
	    Y = y;
		Sprite = new BufferedImage[3];
		Sprite[0] = Game.spritesheet.getSprite(0, 64, 16, 16);
		Sprite[1] = Game.spritesheet.getSprite(0, 80, 16, 16);
		Sprite[2] = Game.spritesheet.getSprite(0, 96, 16, 16);
	}
	public static void tick() {
		TimeAnim++;
		if(TimeAnim == 10) {
			CurrentAnim++;
			TimeAnim = 0;
		}
		if(CurrentAnim == LastAnim) {
			CurrentAnim = 0;
		}
	}
	public void render(Graphics g) {
		g.drawImage(Sprite[CurrentAnim],X,Y,16,16,null);
	}

}
