package com.lzstudios.entities;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;

import com.lzstudios.animations.AnimDead;
import com.lzstudios.main.Game;

public class LazerShot extends Entity {
	
	public BufferedImage LazerShot[];
	public int anim = 0;
	
	public int xTarget,yTarget;
	public boolean shoting;
	
	public int timerShot;

	public LazerShot(double x, double y, int width, int height, double speed, BufferedImage sprite) {
		super(x, y, width, height, speed, sprite);
		// TODO Auto-generated constructor stub
		LazerShot = new BufferedImage[5];
		LazerShot[0] = Game.spritesheet.getSprite(32,48,16,16);
		LazerShot[1] = Game.spritesheet.getSprite(32,64,16,16);
		LazerShot[2] = Game.spritesheet.getSprite(32,80,16,16);
		LazerShot[3] = Game.spritesheet.getSprite(32,96,16,16);
		LazerShot[4] = Game.spritesheet.getSprite(32,112,16,16);
		depth = 2;
	}
	
	public void tick() {
		if(Entity.calculateDistance((int)x, (int)y, Game.player.getX(), Game.player.getY()) < 64) {
			shoting = true;
		}else {
			shoting = false;
		}
		if(shoting && !Game.GameOver) {
			timerShot++;
			if(timerShot == 90) {
				AnimDead dead = new AnimDead(Game.player.getX(),Game.player.getY(),16,16,0,null);
				Game.entities.add(dead);
				Game.entities.remove(Game.player);
				timerShot = 0;
				Game.playerDead = true;
			}
		}else {
			timerShot--;
			if(timerShot <= 0) {
				timerShot = 0;
			}
		}
		if(timerShot == 0) {
			anim = 0;
		}
		if(timerShot >= 1) {
			anim = 1;
		}
		if(timerShot >= 21) {
			anim = 2;
		}
		if(timerShot >= 42) {
			anim = 3;
		}
		if(timerShot >= 72) {
			anim = 4;
		}
		if(timerShot >= 90) {
			anim = 5;
		}
	}
	
	public void render(Graphics g) {
		g.drawImage(LazerShot[anim],(int)x,(int)y,16,16,null);
		if(shoting) {
			Graphics2D g2 = (Graphics2D) g;
			g2.setColor(Color.black);
			g2.drawLine((int)x + 8, (int)y + 7, Game.player.getX() + 8, Game.player.getY() + 7);
			g2.setColor(Color.cyan);
			g2.drawLine((int)x + 8, (int)y + 8, Game.player.getX() + 8, Game.player.getY() + 8);
			g2.setColor(Color.black);
			g2.drawLine((int)x + 8, (int)y + 9, Game.player.getX() + 8, Game.player.getY() + 9);
		}else {
			
		}
	}

}
