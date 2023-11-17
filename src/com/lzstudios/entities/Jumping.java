package com.lzstudios.entities;

import java.awt.Graphics;
import java.awt.image.BufferedImage;

import com.lzstudios.graficos.Spritesheet;
import com.lzstudios.main.Game;
import com.lzstudios.world.World;

public class Jumping extends Entity{
	
	public int Dir;
	
	public Spritesheet SpriteJumping;
	
	public BufferedImage Sprite[];
	private int SpriteCurrent = 0;
	

	public Jumping(double x, double y, int width, int height, double speed, BufferedImage sprite) {
		super(x, y, width, height, speed, sprite);
		// TODO Auto-generated constructor stub
		SpriteJumping = new Spritesheet("/SpritesheetJumping.png");
		Sprite = new BufferedImage[9];
		Sprite[0] = SpriteJumping.getSprite(0, 0, 16, 16);
		Sprite[1] = SpriteJumping.getSprite(16, 0, 16, 16);
		Sprite[2] = SpriteJumping.getSprite(32, 0, 16, 16);
		Sprite[3] = SpriteJumping.getSprite(48, 0, 16, 16);
		Sprite[4] = SpriteJumping.getSprite(0, 16, 16, 16);
		Sprite[5] = SpriteJumping.getSprite(16, 16, 16, 16);
		Sprite[6] = SpriteJumping.getSprite(32, 16, 16, 16);
		Sprite[7] = SpriteJumping.getSprite(48, 16, 16, 16);
		Sprite[8] = SpriteJumping.getSprite(64, 0, 16, 16);
		depth = 1;
	}
	public void tick() {
		//Verificação de animação
		for(int i = 0; i < Game.entities.size(); i++) {
			Entity e = Game.entities.get(i);
			if(e instanceof Player) {
				if(Entity.isColidding(this, e)) {
					Player.StopedDown = false;
					Player.StopedUp = false;
					Player.StopedRight = false;
					Player.StopedLeft = false;
				}
			}
		}
		//Ativar
		if(!World.isFreePlayer(this.getX() - 1, this.getY(), 16, 16) &&
		   !World.isFreePlayer(this.getX(), this.getY() + 1, 16, 16)) {
			Dir = 1;
			for(int i = 0; i < Game.entities.size(); i++) {
				Entity e = Game.entities.get(i);
				if(e instanceof Player) {
					if(Entity.isColidding(this, e)) {
						SpriteCurrent = 4;
						if(Game.player.isRun) {
									if(Game.player.dir == 4 && 
									   !World.isFreePlayer(Game.player.getX() - 1, Game.player.getY(), 16, 16)) {
										Game.player.Up = true;
									}else if(Game.player.dir == 2 && 
											 !World.isFreePlayer(Game.player.getX(), Game.player.getY() + 1, 16, 16)) {
										Game.player.Right = true;
									}
								}
							 }else {
								 SpriteCurrent = 0;
							 }
					}
			}
		}else if(!World.isFreePlayer(this.getX() - 1, this.getY(), 16, 16) &&
				 !World.isFreePlayer(this.getX(), this.getY() - 1, 16, 16)) {
			Dir = 2;
			for(int i = 0; i < Game.entities.size(); i++) {
				Entity e = Game.entities.get(i);
				if(e instanceof Player) {
					if(Entity.isColidding(this, e)) {
						SpriteCurrent = 5;
						if(Game.player.isRun) {
									if(Game.player.dir == 1 && 
									   !World.isFreePlayer(Game.player.getX(), Game.player.getY() - 1, 16, 16)) {
										Game.player.Right = true;
									}else if(Game.player.dir == 4 && 
											 !World.isFreePlayer(Game.player.getX() - 1, Game.player.getY(), 16, 16)) {
										Game.player.Down = true;
									}
								}
							 }else {
								 SpriteCurrent = 1;
							 }
					}
			}
		}else if(!World.isFreePlayer(this.getX() + 1, this.getY(), 16, 16) &&
				 !World.isFreePlayer(this.getX(), this.getY() - 1, 16, 16)) {
			Dir = 3;
			for(int i = 0; i < Game.entities.size(); i++) {
				Entity e = Game.entities.get(i);
				if(e instanceof Player) {
					if(Entity.isColidding(this, e)) {
						SpriteCurrent = 6;
						if(Game.player.isRun) {
									if(Game.player.dir == 1 && 
									   !World.isFreePlayer(Game.player.getX(), Game.player.getY() - 1, 16, 16)) {
										Game.player.Left = true;
									}else if(Game.player.dir == 3 && 
											 !World.isFreePlayer(Game.player.getX() + 1, Game.player.getY(), 16, 16)) {
										Game.player.Down = true;
									}
								}
							 }else {
								 SpriteCurrent = 2;
							 }
					}
			}
		}else if(!World.isFreePlayer(this.getX() + 1, this.getY(), 16, 16) &&
				 !World.isFreePlayer(this.getX(), this.getY() + 1, 16, 16)) {
			Dir = 4;
			for(int i = 0; i < Game.entities.size(); i++) {
				Entity e = Game.entities.get(i);
				if(e instanceof Player) {
					if(Entity.isColidding(this, e)) {
						SpriteCurrent = 7;
						if(Game.player.isRun) {
									if(Game.player.dir == 2 && 
									   !World.isFreePlayer(Game.player.getX(), Game.player.getY() + 1, 16, 16)) {
										Game.player.Left = true;
									}else if(Game.player.dir == 3 && 
											 !World.isFreePlayer(Game.player.getX() + 1, Game.player.getY(), 16, 16)) {
										Game.player.Up = true;
									}
								}
							 }else {
								 SpriteCurrent = 3;
							 }
					}
			}
		}else {
			Dir = 5;
		}
		
	}
	public void render(Graphics g) {
		if(Dir == 1) {
		g.drawImage(Sprite[SpriteCurrent],this.getX(), this.getY(), 16, 16, null);
		}else if(Dir == 2) {
		g.drawImage(Sprite[SpriteCurrent],this.getX(), this.getY(), 16, 16, null);
		}else if(Dir == 3) {
		g.drawImage(Sprite[SpriteCurrent],this.getX(), this.getY(), 16, 16, null);
		}else if(Dir == 4) {
		g.drawImage(Sprite[SpriteCurrent],this.getX(), this.getY(), 16, 16, null);	
		}else if(Dir == 5) {
		g.drawImage(Sprite[8],this.getX(), this.getY(), 16, 16, null);	
		}
	}

}
