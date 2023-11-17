package com.lzstudios.entities;

import java.awt.Graphics;
import java.awt.image.BufferedImage;

import com.lzstudios.main.Game;

public class Key extends Entity{
	
public BufferedImage Key[];
	
	public int CurrentAnim = 0,LastAnim = 4;
	public int TimeAnim;

	public Key(double x, double y, int width, int height, double speed, BufferedImage sprite) {
		super(x, y, width, height, speed, sprite);
		Key = new BufferedImage[6];
		Key[0] = Game.spritesheet.getSprite(128,0,16,16);
		Key[1] = Game.spritesheet.getSprite(128,16,16,16);
		Key[2] = Game.spritesheet.getSprite(128,32,16,16);
		Key[3] = Game.spritesheet.getSprite(128,48,16,16);
		depth = 3;
	}
	public void tick() {
		TimeAnim++;
		if(TimeAnim == 25) {
		CurrentAnim++;
		TimeAnim = 0;
		}
		if(CurrentAnim == LastAnim) {
			CurrentAnim = 0;
		}
		for(int i = 0; i < Game.entities.size(); i++) {
			Entity e = Game.entities.get(i);
			if(e instanceof Player) {
				if(Entity.isColidding(this, e)) {
					DoorBox.ActivedAnim = true;
					Game.entities.remove(this);
				}
			}
	    }
	}
	public void render(Graphics g) {
		g.drawImage(Key[CurrentAnim], this.getX(), this.getY(), 16, 16, null);
	}

}
