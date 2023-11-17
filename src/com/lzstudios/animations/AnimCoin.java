package com.lzstudios.animations;

import java.awt.Graphics;
import java.awt.image.BufferedImage;

import com.lzstudios.entities.Entity;
import com.lzstudios.main.Game;

public class AnimCoin extends Entity{
	
    public BufferedImage AnimCoin[];
	
	public double Timer,curTimer;
	public int curAnim = 0 , LastAnim = 5;

	public AnimCoin(double x, double y, int width, int height, double speed, BufferedImage sprite) {
		super(x, y, width, height, speed, sprite);
		// TODO Auto-generated constructor stub
		AnimCoin = new BufferedImage[5];
		AnimCoin[0] = Game.AnimSpriteSheet.getSprite(64, 0, 16, 16);
		AnimCoin[1] = Game.AnimSpriteSheet.getSprite(64, 16, 16, 16);
		AnimCoin[2] = Game.AnimSpriteSheet.getSprite(64, 32, 16, 16);
		AnimCoin[3] = Game.AnimSpriteSheet.getSprite(64, 48, 16, 16);
		AnimCoin[3] = Game.AnimSpriteSheet.getSprite(64, 64, 16, 16);
	}
	public void tick() {
		Timer++;
		if(Timer >= 4) {
			curAnim++;
			Timer = 0;
			if(curAnim >= LastAnim) {
				curAnim = 0;
				Game.entities.remove(this);
			}
		}
	}
	
	public void render(Graphics g) {
		g.drawImage(AnimCoin[curAnim], this.getX(), this.getY(), 16, 16, null);
	}

}
