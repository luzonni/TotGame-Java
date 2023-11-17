package com.lzstudios.entities;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;

import com.lzstudios.main.Game;

public class Teleporter1 extends Entity {
	
	public static int tx1, ty1;
	public static boolean Actived1 = true;
	public static boolean Activing1 = true;
	public int Timer;
	public boolean Colliding;
	public static boolean Coll1 = true;
	
	private double rotate;
	
	public static Teleporter2 T2;

	public Teleporter1(double x, double y, int width, int height, double speed, BufferedImage sprite) {
		super(x, y, width, height, speed, sprite);
		this.tx1 = this.getX();
		this.ty1 = this.getY();
	}
	
	public void tick() {
		if(T2.Actived2 == false && Activing1 == true) {
			Timer++;
			if(Timer >= 0) {
				Actived1 = true;
				Timer = 0;
			}
		}
		//Verificação de Teleporter
		for(int i = 0; i < Game.entities.size(); i++) {
			Entity e = Game.entities.get(i);
			if(e instanceof Player) {
				if(Entity.isColidding(this, e)) {
					if(Coll1 == true) {
					Colliding = true;
					}
				}else {
					Colliding = false;
					Coll1 = false;
				}
			}
		}
		//Teleporting
		for(int i = 0; i < Game.entities.size(); i++) {
			Entity e = Game.entities.get(i);
			if(e instanceof Player) {
				if(Entity.isColidding(this, e)) {
					if(Actived1 && Colliding == false) {
					Game.player.setX(Teleporter2.tx2);
					Game.player.setY(Teleporter2.ty2);
					Actived1 = false;
					Activing1 = false;
					T2.Activing2 = true;
					T2.Coll2 = true;
					}
				}
			}
		}
		if(!Game.GameOver) {
		rotate+=0.1;
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
		if(Actived1) {
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
