package com.lzstudios.entities;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.BufferedImage;

import com.lzstudios.main.Game;

public class QuitGame extends Entity{

	public QuitGame(double x, double y, int width, int height, double speed, BufferedImage sprite) {
		super(x, y, width, height, speed, sprite);
		// TODO Auto-generated constructor stub
	}
	public void tick() {
		for(int i = 0; i < Game.entities.size(); i++) {
			Entity e = Game.entities.get(i);
			if(e instanceof Player) {
				if(Entity.isColidding(this, e)) {
						System.exit(1);
				}
			}
	    }
	}

	public void render(Graphics g) {
		g.setColor(Color.black);
		g.fillRect(this.getX(),this.getY(), width, height);
	}
}
