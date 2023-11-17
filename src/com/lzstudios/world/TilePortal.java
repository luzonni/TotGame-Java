package com.lzstudios.world;

import java.awt.Graphics;
import java.awt.image.BufferedImage;

import com.lzstudios.graficos.Spritesheet;
import com.lzstudios.main.Game;

public class TilePortal extends Tile{
	
	public BufferedImage Sprite[];
	
	public static Spritesheet spritePortal;
	
	public static int CurrentAnim = 0,LastAnim = 10;
	public static int TimeAnim;
	public int X,Y;

	public TilePortal(int x, int y, BufferedImage sprite) {
		super(x, y, sprite);
		if(Game.RandModel > 1) {
	    	Game.RandModel = 0;
	    }
		spritePortal = new Spritesheet("/spritePortal" + Game.RandModel + ".png");
		X = x;
		Y = y;
		Sprite = new BufferedImage[10 ];
		Sprite[0] = spritePortal.getSprite(0, 0, 16, 16);
		Sprite[1] = spritePortal.getSprite(16, 0, 16, 16);
		Sprite[2] = spritePortal.getSprite(32, 0, 16, 16);
		Sprite[3] = spritePortal.getSprite(48, 0, 16, 16);
		Sprite[4] = spritePortal.getSprite(64, 0, 16, 16);
		Sprite[5] = spritePortal.getSprite(80, 0, 16, 16);
		Sprite[6] = spritePortal.getSprite(64, 0, 16, 16);
		Sprite[7] = spritePortal.getSprite(48, 0, 16, 16);
		Sprite[8] = spritePortal.getSprite(32, 0, 16, 16);
		Sprite[9] = spritePortal.getSprite(16, 0, 16, 16);
	}
	public static void tick() {
		TimeAnim++;
		if(TimeAnim == 6) {
		CurrentAnim++;
		TimeAnim = 0;
		}
		if(CurrentAnim == LastAnim) {
			CurrentAnim = 0;
		}
	}
	
	public void render(Graphics g) {
		g.drawImage(Sprite[CurrentAnim], X, Y, 16, 16, null);
	}

}
