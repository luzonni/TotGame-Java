package com.lzstudios.entities;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.BufferedImage;

import com.lzstudios.animations.AnimDead;
import com.lzstudios.main.Game;
import com.lzstudios.world.World;

public class Arrow extends Entity{
	
	private BufferedImage Arrow1,Arrow2;
	private int dir;
	private int TimerShot;

	public Arrow(double x, double y, int width, int height, double speed, BufferedImage sprite) {
		super(x, y - 2, 10, 5, speed, sprite);
		Arrow1 = Game.spritesheet.getSprite(48, 64, 10, 5);
		Arrow2 = Game.spritesheet.getSprite(48, 69, 10, 5);
	}
	public void tick() {
		if(speed == 4) {
			dir = 1;
		}else {
			dir = 2;
		}
		TimerShot++;
		if(TimerShot >= 60){
			x+=speed;	
		}
		
		if(!World.isFree(this.getX(), this.getY(), 10, 5)) {
			Game.entities.remove(this);
		}
		//KillPlayer
		for(int i = 0; i < Game.entities.size(); i++) {
			Entity e = Game.entities.get(i);
			if(e instanceof Player) {
				if(Entity.isColidding(this, e)) {
					Game.entities.remove(i);
					Game.entities.remove(this);
					AnimDead dead = new AnimDead(Game.player.getX(),Game.player.getY(),16,16,0,null);
					Game.entities.add(dead);
					Game.playerDead = true;
				}
			}
	    }
	}
	public void render(Graphics g) {
		if(dir == 1) {
		    g.drawImage(Arrow1, this.getX(), this.getY(), this.getWidth(), this.getHeight(),null);
		}else if(dir == 2) {
			g.drawImage(Arrow2, this.getX(), this.getY(), this.getWidth(), this.getHeight(),null);
		}
	}

}
