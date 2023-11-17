package com.lzstudios.entities;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;

import com.lzstudios.animations.AnimDead;
import com.lzstudios.main.Game;
import com.lzstudios.world.World;

public class Thorns extends Entity{
	
	public BufferedImage ThornsSprite[];
	
	public boolean Actived = false;
	public boolean ActivedDamage = false;
	
	private double rotate;
	
	public int LastAnim = 5, CurrentAnim;
	public int TimeAnim,currentTimer;
	public int dir = 1;

	public Thorns(double x, double y, int width, int height, double speed, BufferedImage sprite) {
		super(x, y, width, height, speed, sprite);
		ThornsSprite = new BufferedImage[5];
		ThornsSprite[0] = Game.spritesheet.getSprite(48, 48, 16, 16);
		ThornsSprite[1] = Game.spritesheet.getSprite(64, 48, 16, 16);
		ThornsSprite[2] = Game.spritesheet.getSprite(80, 48, 16, 16);
		ThornsSprite[3] = Game.spritesheet.getSprite(96, 48, 16, 16);
		ThornsSprite[4] = Game.spritesheet.getSprite(112, 48, 16, 16);
		
		depth = 4;
	}
	
	public void tick() {
		if(!World.isFreePlayer(this.getX(), this.getY() + 16, width, height)) {
			dir = 1;
		}else if(!World.isFreePlayer(this.getX(), this.getY() - 16, width, height)) {
			dir = 2;
		}else if(!World.isFreePlayer(this.getX() + 16, this.getY(), width, height)) {
			dir = 3;
		}else if(!World.isFreePlayer(this.getX() - 16, this.getY(), width, height)) {
			dir = 4;
		}
		
		
		for(int i = 0; i < Game.entities.size(); i++) {
			Entity e = Game.entities.get(i);
			if(e instanceof Player) {
				if(Entity.isColidding(this, e)) {
					Actived = true;
				}
			}
	    }
		if(Actived) {
			TimeAnim++;
			if(TimeAnim == 6) {
			CurrentAnim++;
			TimeAnim = 0;
			if(CurrentAnim == LastAnim) {
				CurrentAnim = 4;
				ActivedDamage = true;
			}
			}
			
		}
		if(ActivedDamage) {
			for(int i = 0; i < Game.entities.size(); i++) {
				Entity e = Game.entities.get(i);
				if(e instanceof Player) {
					if(Entity.isColidding(this, e)) {
						Game.entities.remove(i);
						Game.entities.remove(this);
						AnimDead dead = new AnimDead(this.getX(),this.getY(),16,16,0,null);
						Game.entities.add(dead);
						Game.playerDead = true;
					}
				}
		    }
		}
		if(dir == 1) {
			   rotate = 0;
			}else if(dir == 2) {
				rotate = 3.14;
			}else if(dir == 3) {
				rotate = 4.71239;
			}else if(dir == 4) {
				rotate = 1.5708;
			}
	}
	
	public void render(Graphics g) {
		Graphics2D g2 = (Graphics2D) g;
		if(dir == 2) {
		g2.rotate(rotate,this.getX() + 8,this.getY() + 8);
		g.drawImage(ThornsSprite[CurrentAnim], this.getX(), this.getY(),16,16,null);
		g2.rotate(-rotate,this.getX() + 8,this.getY() + 8);
		}else if(dir == 1){
		g2.rotate(rotate,this.getX() + 8,this.getY() + 8);
		g.drawImage(ThornsSprite[CurrentAnim], this.getX(), this.getY(),16,16,null);
		g2.rotate(-rotate,this.getX() + 8,this.getY() + 8);
		}else if(dir == 4){
		g2.rotate(rotate,this.getX() + 8,this.getY() + 8);
		g.drawImage(ThornsSprite[CurrentAnim], this.getX(), this.getY(),16,16,null);
		g2.rotate(-rotate,this.getX() + 8,this.getY() + 8);
		}else if(dir == 3){
		g2.rotate(rotate,this.getX() + 8,this.getY() + 8);
		g.drawImage(ThornsSprite[CurrentAnim], this.getX(), this.getY(),16,16,null);
		g2.rotate(-rotate,this.getX() + 8,this.getY() + 8);
		}
	}

}
