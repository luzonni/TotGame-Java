package com.lzstudios.animations;

import java.awt.Graphics;
import java.awt.image.BufferedImage;

import com.lzstudios.entities.Entity;
import com.lzstudios.main.Game;

public class AnimDead extends Entity{
	
	public BufferedImage AnimDead[];
	
	public double Timer,curTimer;
	public int curAnim = 0 , LastAnim = 8;

	public AnimDead(double x, double y, int width, int height, double speed, BufferedImage sprite) {
		super(x, y, width, height, speed, sprite);
		// TODO Auto-generated constructor stub
		AnimDead = new BufferedImage[8];
		AnimDead[0] = Game.AnimSpriteSheet.getSprite(0, 0, 16, 16);
		AnimDead[1] = Game.AnimSpriteSheet.getSprite(0, 16, 16, 16);
		AnimDead[2] = Game.AnimSpriteSheet.getSprite(0, 32, 16, 16);
		AnimDead[3] = Game.AnimSpriteSheet.getSprite(0, 48, 16, 16);
		AnimDead[4] = Game.AnimSpriteSheet.getSprite(0, 64, 16, 16);
		AnimDead[5] = Game.AnimSpriteSheet.getSprite(0, 80, 16, 16);
		AnimDead[6] = Game.AnimSpriteSheet.getSprite(0, 96, 16, 16);
		AnimDead[7] = Game.AnimSpriteSheet.getSprite(0, 112, 16, 16);
	}
	
	public void tick() {
		Timer++;
		if(Timer >= 3) {
			curAnim++;
			Timer = 0;
			if(curAnim >= LastAnim) {
				curAnim = 0;
				Game.GameOver = true;
				Game.entities.remove(this);
			}
		}
	}
	
	public void render(Graphics g) {
		g.drawImage(AnimDead[curAnim], this.getX(), this.getY(), 16, 16, null);
	}

}
