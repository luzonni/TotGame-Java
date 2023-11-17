package com.lzstudios.entities;

import java.awt.Graphics;
import java.awt.image.BufferedImage;

import com.lzstudios.main.Game;
import com.lzstudios.world.World;

public class Diamond extends Entity{
	
	public BufferedImage Sprite[] = new BufferedImage[6];
	public int CurrentAnim = 0,LastAnim = 6;
	public int TimeAnim;

	public Diamond(double x, double y, int width, int height, double speed, BufferedImage sprite) {
		super(x + 5, y + 5, 7, 6, speed, sprite);
		Sprite[0] = Game.spritesheet.getSprite(144, 96, 7, 6);
		Sprite[1] = Game.spritesheet.getSprite(153, 96, 7, 6);
		Sprite[2] = Game.spritesheet.getSprite(144, 104, 7, 6);
		Sprite[3] = Game.spritesheet.getSprite(153, 104, 7, 6);
		Sprite[4] = Game.spritesheet.getSprite(144, 112, 7, 6);
		Sprite[5] = Game.spritesheet.getSprite(153, 112, 7, 6);
	}
	public void tick() {
		TimeAnim++;
		if(TimeAnim == 5) {
		CurrentAnim++;
		TimeAnim = 0;
		}
		if(CurrentAnim == LastAnim) {
			CurrentAnim = 0;
		}
		//AddPlayerDiamond
		for(int i = 0; i < Game.entities.size(); i++) {
			Entity e = Game.entities.get(i);
			if(e instanceof Player) {
				if(Entity.isColidding(this, e)) {
					Player.Diamond++;
					Game.entities.remove(this);
					World.generateParticle(60, this.getX() + 8, this.getY() + 8);
				}
			}
		}
	}
	public void render(Graphics g) {
		g.drawImage(Sprite[CurrentAnim], this.getX(), this.getY(), 7, 6,null);
	}

}
