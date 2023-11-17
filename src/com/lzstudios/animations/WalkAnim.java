package com.lzstudios.animations;

import java.awt.Graphics;
import java.awt.image.BufferedImage;

import com.lzstudios.entities.Entity;
import com.lzstudios.main.Game;

public class WalkAnim extends Entity{

	
	public BufferedImage AnimDead[];
	
	public double Timer,curTimer;
	public int curAnim = 0 , LastAnim = 7;

	public WalkAnim(double x, double y, int width, int height, double speed, BufferedImage sprite) {
		super(x, y, 5, 5, speed, sprite);
		// TODO Auto-generated constructor stub
		AnimDead = new BufferedImage[7];
		AnimDead[0] = Game.AnimSpriteSheet.getSprite(16, 0, 5, 5);
		AnimDead[1] = Game.AnimSpriteSheet.getSprite(16, 16, 5, 5);
		AnimDead[2] = Game.AnimSpriteSheet.getSprite(16, 32, 5, 5);
		AnimDead[3] = Game.AnimSpriteSheet.getSprite(16, 48, 5, 5);
		AnimDead[4] = Game.AnimSpriteSheet.getSprite(16, 64, 5, 5);
		AnimDead[5] = Game.AnimSpriteSheet.getSprite(16, 80, 5, 5);
		AnimDead[6] = Game.AnimSpriteSheet.getSprite(16, 96, 5, 5);
		depth = 2;
	}
	
	public void tick() {
		
		Timer++;
		if(Timer >= 2) {
			curAnim++;
			Timer = 0;
			if(curAnim >= LastAnim) {
				curAnim = 0;
				Game.entities.remove(this);
			}
		}
	}
	
	public void render(Graphics g) {
		g.drawImage(AnimDead[curAnim], this.getX() + 5, this.getY() + 5, 6, 6, null);
	}

}
