package com.lzstudios.world;

import java.awt.Graphics;
import java.awt.image.BufferedImage;

import com.lzstudios.entities.Enemy;
import com.lzstudios.graficos.Spritesheet;
import com.lzstudios.main.Game;

public class TileArrow extends Tile{
	
	public static Spritesheet spriteGeradorArrow;
	
	public BufferedImage Sprite[];

	public static int CurrentAnim = 0,LastAnim = 8;
	public static int TimeAnim;

	public int X,Y;
	
	public static int Dir = 0;
	
	public TileArrow(int x, int y, BufferedImage sprite) {
		super(x, y, sprite);
	    if(Game.RandModel > 1) {
	    	Game.RandModel = 0;
	    }
		spriteGeradorArrow = new Spritesheet("/spriteGeradorArrow" + Game.RandModel + ".png");
		X = x;
		Y = y;
		Sprite = new BufferedImage[8];
		Sprite[0] = spriteGeradorArrow.getSprite(0, Dir*16, 16, 16);
		Sprite[1] = spriteGeradorArrow.getSprite(16, Dir*16, 16, 16);
		Sprite[2] = spriteGeradorArrow.getSprite(32, Dir*16, 16, 16);
		Sprite[3] = spriteGeradorArrow.getSprite(48, Dir*16, 16, 16);
		Sprite[4] = spriteGeradorArrow.getSprite(64, Dir*16, 16, 16);
		Sprite[5] = spriteGeradorArrow.getSprite(80, Dir*16, 16, 16);
		Sprite[6] = spriteGeradorArrow.getSprite(96, Dir*16, 16, 16);
		Sprite[7] = spriteGeradorArrow.getSprite(112, Dir*16, 16, 16);
	}
	public static void tick() {
		TimeAnim++;
		if(TimeAnim == 4) {
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
