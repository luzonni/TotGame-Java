package com.lzstudios.entities;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.BufferedImage;

import com.lzstudios.animations.AnimCoin;
import com.lzstudios.main.Game;

public class Coin extends Entity{
	
	public BufferedImage Coin[];
	
	public int CurrentAnim = 0,LastAnim = 6;
	public int TimeAnim;

	public Coin(double x, double y, int width, int height, double speed, BufferedImage sprite) {
		super(x, y, width, height, speed, sprite);
		Coin = new BufferedImage[6];
		Coin[0] = Game.spritesheet.getSprite(144,0,16,16);
		Coin[1] = Game.spritesheet.getSprite(144,16,16,16);
		Coin[2] = Game.spritesheet.getSprite(144,32,16,16);
		Coin[3] = Game.spritesheet.getSprite(144,48,16,16);
		Coin[4] = Game.spritesheet.getSprite(144,64,16,16);
		Coin[5] = Game.spritesheet.getSprite(144,80,16,16);
	}
	public void tick() {
		TimeAnim++;
		if(TimeAnim == 5) {
		CurrentAnim++;
		TimeAnim = 0;
		}
		if(CurrentAnim == LastAnim) {
			CurrentAnim = 0;
		}
		//PlayerScoreAdd
		for(int i = 0; i < Game.entities.size(); i++) {
			Entity e = Game.entities.get(i);
			if(e instanceof Player) {
				if(Entity.isColidding(this, e)) {
					Player.Coins++;
					AnimCoin anc = new AnimCoin(this.getX(),this.getY(),16,16,0,null);
					Game.entities.add(anc);
					Game.entities.remove(this);
				}
			}
	    }
	}
	public void render(Graphics g) {
		g.drawImage(Coin[CurrentAnim], this.getX(), this.getY(), 16, 16, null);
	}

}
