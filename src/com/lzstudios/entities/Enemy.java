package com.lzstudios.entities;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.BufferedImage;

import com.lzstudios.animations.AnimDead;
import com.lzstudios.main.Game;
import com.lzstudios.world.World;

public class Enemy extends Entity{
	
	public boolean right = true,left = false;
	private int dir = 2;
	
	private BufferedImage SpriteLeft[];
	private BufferedImage SpriteRight[];
	
	public int CurrentAnim = 0,LastAnim = 4;
	public int TimeAnim;

	public Enemy(double x, double y, int width, int height, double speed, BufferedImage sprite) {
		super(x + 2, y + 2, 12, 12, 0.75, sprite);
		SpriteLeft = new BufferedImage[6];
		SpriteLeft[0] = Game.spritesheet.getSprite(50, 82, 12, 12);
		SpriteLeft[1] = Game.spritesheet.getSprite(66, 82, 12, 12);
		SpriteLeft[2] = Game.spritesheet.getSprite(82, 82, 12, 12);
		SpriteLeft[3] = Game.spritesheet.getSprite(98, 82, 12, 12);
		SpriteLeft[4] = Game.spritesheet.getSprite(114, 82, 12, 12);
		SpriteLeft[5] = Game.spritesheet.getSprite(130, 82, 12, 12);
		
		SpriteRight = new BufferedImage[6];
		SpriteRight[0] = Game.spritesheet.getSprite(50, 98, 12, 12);
		SpriteRight[1] = Game.spritesheet.getSprite(66, 98, 12, 12);
		SpriteRight[2] = Game.spritesheet.getSprite(82, 98, 12, 12);
		SpriteRight[3] = Game.spritesheet.getSprite(98, 98, 12, 12);
		SpriteRight[4] = Game.spritesheet.getSprite(114, 98, 12, 12);
		SpriteRight[5] = Game.spritesheet.getSprite(130, 98, 12, 12);
	}
	
	public void tick() {
		depth = 2;
		if(!World.isFree(getX() - 2, getY(),this.getWidth(),this.getHeight())) {
			right = false;
			left = true;
			dir = 2;
		}else if(!World.isFree(getX() + 2, getY(),this.getWidth(),this.getHeight())) {
			right = true;
			left = false;
			dir = 1;
		}
		if(right) {
			x-=speed;
			dir = 2;
		}else if(left) {
			x+=speed;
			dir = 1;
		}
		
		
		//Anim
		TimeAnim++;
		if(TimeAnim == 5) {
		CurrentAnim++;
		TimeAnim = 0;
		}
		if(CurrentAnim == LastAnim) {
			CurrentAnim = 0;
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
			g.drawImage(SpriteLeft[CurrentAnim], this.getX(), this.getY(), this.getWidth(), this.getHeight(),null);
		}else if(dir == 2) {
			g.drawImage(SpriteRight[CurrentAnim], this.getX(), this.getY(), this.getWidth(), this.getHeight(),null);
		}
		
	}

}
