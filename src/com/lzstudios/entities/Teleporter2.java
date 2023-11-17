package com.lzstudios.entities;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;

import com.lzstudios.main.Game;

public class Teleporter2 extends Entity{
	
	public static int tx2,ty2;
	public static boolean Actived2 = false;
	public static boolean Activing2 = false;
	public int Timer;
	public static Teleporter1 T1;
	
	public boolean Colliding;
	public static boolean Coll2 = true;
	
	private double rotate;

	public Teleporter2(double x, double y, int width, int height, double speed, BufferedImage sprite) {
		super(x, y, width, height, speed, sprite);
		tx2 = this.getX();
		ty2 = this.getY();
	}
	public void tick() {
		if(T1.Actived1 == false && Activing2 == true) {
		   Timer++;
		   if(Timer >= 0) {
		      Actived2 = true;
		      Timer = 0;
		   }
		}
		for(int i = 0; i < Game.entities.size(); i++) {
			Entity e = Game.entities.get(i);
			if(e instanceof Player) {
				if(Entity.isColidding(this, e)) {
					if(Coll2 == true) {
					Colliding = true;
					}
				}else {
					Colliding = false;
					Coll2 = false;
				}
			}
		}
		for(int i = 0; i < Game.entities.size(); i++) {
			Entity e = Game.entities.get(i);
			if(e instanceof Player) {
				if(Entity.isColidding(this, e)) {
					if(Actived2 && Colliding == false) {
					Game.player.setX(T1.tx1);
					Game.player.setY(T1.ty1);
					Actived2 = false;
					Activing2 = false;
					T1.Activing1 = true;
					T1.Coll1 = true;
					}
				}
			}
		}
		if(!Game.GameOver) {
		rotate-=0.1;
		}
	}
	
	public void render(Graphics g) {
		Graphics2D g2 = (Graphics2D) g;
		//BackBlack
		g2.rotate(rotate,this.getX() + 8,this.getY() + 8);
		g.setColor(new Color(0,0,0,170));
		g.fillRect(this.getX() + 2, this.getY() + 2, 12, 12);
		g2.rotate(-rotate,this.getX() + 8,this.getY() +  8);
		//Colorized
		g2.rotate(rotate,this.getX() + 8,this.getY() + 8);
		g.setColor(new Color(Game.R,Game.G,Game.B,170));
		g.fillRect(this.getX() + 4, this.getY() + 4, 8, 8);
		g2.rotate(-rotate,this.getX() + 8,this.getY() + 8);
		if(Actived2) {
			//Black
			g2.rotate(rotate,this.getX() + 8,this.getY() + 8);
			g.setColor(new Color(0,0,0,190));
			g.fillRect(this.getX() + 6, this.getY() + 6, 4, 4);
			g2.rotate(-rotate,this.getX() + 8,this.getY() + 8);
		}else {
			//Wihte
			g2.rotate(rotate,this.getX() + 8,this.getY() + 8);
			g.setColor(new Color(Game.R,Game.G,Game.B,190));
			g.fillRect(this.getX() + 6, this.getY() + 6, 4, 4);
			g2.rotate(-rotate,this.getX() + 8,this.getY() + 8);
		}
		g2.rotate(0,this.getX() + 8,this.getY() + 8);
	}

}
