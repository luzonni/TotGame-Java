package com.lzstudios.animations;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;

import com.lzstudios.entities.Entity;
import com.lzstudios.main.Game;

public class AnimCollider extends Entity{
	
	public BufferedImage AnimJump[];
	
	public double Timer,curTimer;
	public int curAnim = 0 , LastAnim = 7;
	
	private double rotate;
	
	private int LocateX, LocateY;

	public AnimCollider(double x, double y, int width, int height, double speed, BufferedImage sprite) {
		super(x, y, width, height, speed, sprite);
		AnimJump = new BufferedImage[7];
		AnimJump[0] = Game.AnimSpriteSheet.getSprite(32, 0, 16, 16);
		AnimJump[1] = Game.AnimSpriteSheet.getSprite(32, 16, 16, 16);
		AnimJump[2] = Game.AnimSpriteSheet.getSprite(32, 32, 16, 16);
		AnimJump[3] = Game.AnimSpriteSheet.getSprite(32, 48, 16, 16);
		AnimJump[4] = Game.AnimSpriteSheet.getSprite(32, 64, 16, 16);
		AnimJump[5] = Game.AnimSpriteSheet.getSprite(32, 80, 16, 16);
		AnimJump[6] = Game.AnimSpriteSheet.getSprite(32, 96, 16, 16);
		//Localize
		LocateX = Game.player.getX();
		LocateY = Game.player.getY();
		//Rotate
		if(Game.player.dir == 2) {
			   rotate = 6.28319;
			}else if(Game.player.dir == 1) {
				rotate = 3.14;
			}else if(Game.player.dir == 3) {
				rotate = 4.71239;
			}else if(Game.player.dir == 4) {
				rotate = 1.5708;
			}
	}
	
	public void tick() {
		depth = 5;
		Timer++;
		if(Timer >= 2) {
			curAnim++;
			Timer = 0;
			if(curAnim >= LastAnim) {
				curAnim = 0;
				Game.entities.remove(this);
			}
		}
	}
	
	public void render(Graphics g) {
		Graphics2D g2 = (Graphics2D) g;
		if(Game.player.dir == 2) {
		g2.rotate(rotate,LocateX + 8, LocateY + 8);
		g.drawImage(AnimJump[curAnim], LocateX, LocateY, 16, 16, null);
		g2.rotate(-rotate,LocateX + 8, LocateY + 8);
		}else if(Game.player.dir == 1) {
		g2.rotate(rotate,LocateX + 8, LocateY + 8);
		g.drawImage(AnimJump[curAnim], LocateX, LocateY, 16, 16, null);
		g2.rotate(-rotate,LocateX + 8, LocateY + 8);
		}else if(Game.player.dir == 3) {
		g2.rotate(rotate,LocateX + 8, LocateY + 8);
		g.drawImage(AnimJump[curAnim], LocateX, LocateY, 16, 16, null);
		g2.rotate(-rotate,LocateX + 8, LocateY + 8);
		}else if(Game.player.dir == 4) {
		g2.rotate(rotate,LocateX + 8, LocateY + 8);
		g.drawImage(AnimJump[curAnim], LocateX, LocateY, 16, 16, null);
		g2.rotate(-rotate,LocateX + 8, LocateY + 8);
		}
	}

}
