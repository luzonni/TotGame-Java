package com.lzstudios.entities;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;

import com.lzstudios.main.Game;
import com.lzstudios.world.Camera;
import com.lzstudios.world.World;

public class Particle extends Entity{
	
	public int lifeTime = 20, curLife = 0;
	
	public int speed = 1;
	public double dx = 0, dy = 0;
	public double gravity = 0;
	public double angle;
	
	public BufferedImage[] Stone;
	public int SpriteNumber;
	

	public Particle(double x, double y, int width, int height, double speed, BufferedImage sprite) {
		super(x, y, width, height, speed, sprite);
		dx = Entity.rand.nextGaussian();
		dy = Entity.rand.nextGaussian();
		SpriteNumber = Entity.rand.nextInt(4);
		Stone = new BufferedImage[4];
		Stone[0] = Game.spritesheet.getSprite(12, 48, 4, 3);
		Stone[1] = Game.spritesheet.getSprite(12, 52, 4, 3);
		Stone[2] = Game.spritesheet.getSprite(12, 57, 4, 3);
		Stone[3] = Game.spritesheet.getSprite(12, 61, 4, 3);
		angle = Entity.rand.nextDouble();
		
	}
	
	public void tick() {
		if(!World.isFree(this.getX(), this.getY(),width*4,height*4)) {
			Game.entities.remove(this);
		}
		//Moviment
		x+= dx*speed;
		y+= dy*speed;
		//Remover
		curLife++;
		if(lifeTime == curLife) {
			Game.entities.remove(this);
		}
	}
	
	public void render(Graphics g) {
		Graphics2D g2 = (Graphics2D) g;
		g2.rotate(angle,(this.getX() - Camera.x) + 2*2,(this.getY() - Camera.y) + 1.5*2);
		g.setColor(Color.cyan);
		g.setColor(new Color(209,227,255));
		g.fillRect(this.getX(),this.getY(),1,1);
		//g.drawImage(Stone[SpriteNumber],this.getX() - Camera.x ,this.getY() - Camera.y, 4*2,3*2,null);
		g2.rotate(-angle,(this.getX() - Camera.x) + 2*2,(this.getY() - Camera.y) + 1.5*2);
		//g.setColor(Color.gray);
		//g.fillRect(this.getX() - Camera.x, this.getY() - Camera.y, width*3, height*3);
	}

}

