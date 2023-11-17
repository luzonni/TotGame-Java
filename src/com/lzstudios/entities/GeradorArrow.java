package com.lzstudios.entities;

import java.awt.Graphics;
import java.awt.image.BufferedImage;

import com.lzstudios.main.Game;
import com.lzstudios.world.World;

public class GeradorArrow extends Entity{
	
	
	public int CurrentTimeShot = 0;

	public GeradorArrow(double x, double y, int width, int height, double speed, BufferedImage sprite) {
		super(x, y, width, height, speed, sprite);
	}
	
	public void tick() {
		CurrentTimeShot++;
		if(CurrentTimeShot == 60 && World.isFree(this.getX() + 16, this.getY(), 16, 16) && 
				!World.isFree(this.getX() - 16, this.getY(), 16, 16)) {
			Arrow shotterR = new Arrow(this.getX() + 3,this.getY()+7,8,4,4,null);
			Game.entities.add(shotterR);
		}else if(CurrentTimeShot == 90 && World.isFree(this.getX() - 16, this.getY(), 16, 16) && 
				!World.isFree(this.getX() + 16, this.getY(), 16, 16)) {
			Arrow shotterL = new Arrow(this.getX() + 3,this.getY()+7,8,4,-4,null);
			Game.entities.add(shotterL);
		}else if(CurrentTimeShot == 60 && World.isFree(this.getX() + 16, this.getY(), 16, 16) && 
				World.isFree(this.getX() - 16, this.getY(), 16, 16)) {
			Arrow shotterR = new Arrow(this.getX() + 3,this.getY()+5,8,4,4,null);
			Game.entities.add(shotterR);
		}else if(CurrentTimeShot == 90 && World.isFree(this.getX() - 16, this.getY(), 16, 16) && 
				World.isFree(this.getX() + 16, this.getY(), 16, 16)) {
			Arrow shotterL = new Arrow(this.getX() + 3,this.getY()+10,8,4,-4,null);
			Game.entities.add(shotterL);
		}
		
		//ResetTime
		if(CurrentTimeShot == 90) {
			CurrentTimeShot = 0;
		}
	}
	public void render(Graphics g) {
	}

}
