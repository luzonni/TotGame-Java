package com.lzstudios.entities;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.BufferedImage;

import com.lzstudios.main.Game;

public class Box extends Entity{
	

	public Box(double x, double y, int width, int height, double speed, BufferedImage sprite) {
		super(x, y, width, height, speed, sprite);
	}
	public void tick() {
		depth = 0;
		//SystemBox
		if(Game.StartLevel) {
		for(int i = 0; i < Game.entities.size(); i++) {
			Entity e = Game.entities.get(i);
			if(e instanceof Player) {
				if(Entity.isColidding(this, e)) {
					Player.boxCurrent++;
					Game.entities.remove(this);
				}
			}
		}
	   }
	}
	public void render(Graphics g) {
		g.setColor(Color.black);
		g.fillRect(this.getX(), this.getY(), this.getWidth(), this.getHeight());
	}

}
