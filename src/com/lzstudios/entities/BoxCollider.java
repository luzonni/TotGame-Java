package com.lzstudios.entities;

import java.awt.Graphics;
import java.awt.image.BufferedImage;

import com.lzstudios.main.Game;
import com.lzstudios.world.BoxTile;
import com.lzstudios.world.FloorTile;
import com.lzstudios.world.Tile;
import com.lzstudios.world.TileArrow;
import com.lzstudios.world.TileBoxColliding;
import com.lzstudios.world.WallTile;
import com.lzstudios.world.World;

public class BoxCollider extends Entity{
	
	public BufferedImage Sprite[];
	
	public boolean Actived = false;
	public int xx,yy;
	public int timer;
	public boolean ActivedTimer = false;
	public boolean Colliding = false;
	
	public int CurrentAnim = 0,LastAnim = 2;
	public int TimeAnim;
	
	public World world;
	public static boolean BoxLiving = false;

	public BoxCollider(double x, double y, int width, int height, double speed, BufferedImage sprite) {
		super(x, y, width, height, speed, sprite);
		Sprite = new BufferedImage[2];
		Sprite[0] = Game.spritesheet.getSprite(16, 48, 16, 16); 
		Sprite[1] = Game.spritesheet.getSprite(16, 64, 16, 16); 
		xx = this.getX();
		yy = this.getY();
	}
	public void tick() {
		//Anim
		TimeAnim++;
		if(TimeAnim == 10) {
		CurrentAnim++;
		TimeAnim = 0;
		}
		if(CurrentAnim == LastAnim) {
			CurrentAnim = 0;
		}
		//SeForDesativado
		
		
		//Ativar
		for(int i = 0; i < Game.entities.size(); i++) {
			Entity e = Game.entities.get(i);
			if(e instanceof Player) {
				if(Entity.isColidding(this, e)) {
					ActivedTimer = true;
					Colliding = true;
				}else {
					Colliding = false;
				}
			}
	    }
		if(ActivedTimer && Colliding == false) {
			timer++;
			if(timer >= 10) {
				Actived = true;
				if(Colliding) {
					timer = 100;
				}
			}
			if(timer >= 250) {
				Actived = false;
				timer = 0;
				ActivedTimer = false;
			}
		}
		
		if(BoxLiving) {
			 if(Actived) {
				World.tiles[(xx/16) + ((yy/16) * world.WIDTH)] = new BoxTile(xx,yy,Tile.TILE_Box);
			 }else {
				World.tiles[(xx/16) + ((yy/16) * world.WIDTH)] = new TileBoxColliding(xx,yy,Tile.TILE_FLOOR);
				if(Game.playerDead == false &&
				   Entity.calculateDistance(this.getX() + 8, this.getY() + 8, Game.player.getX() + 8, Game.player.getY() + 8) < 17) {
					if(Game.player.dir == 2 && !isFreeBox(Game.player.getX(), Game.player.getY() + 16, 16, 16)) {
						Game.player.Down = true;
					}else if(Game.player.dir == 1 && !isFreeBox(Game.player.getX(), Game.player.getY() - 16, 16, 16)) {
						Game.player.Up = true;
					}else if(Game.player.dir == 3 && !isFreeBox(Game.player.getX() + 16, Game.player.getY(), 16, 16)) {
						Game.player.Right = true;
					}else if(Game.player.dir == 4 && !isFreeBox(Game.player.getX() - 16, Game.player.getY(), 16, 16)) {
						Game.player.Left = true;
					}
				}
			 }
			}
	}
	
	public static boolean isFreeBox(int xnext,int ynext, int width, int height){
		
		int x1 = xnext / 16;
		int y1 = ynext / 16;
		
		int x2 = (xnext + width - 1) / 16;
		int y2 = ynext / 16;
		
		int x3 = xnext / 16;
		int y3 = (ynext + height - 1) / 16;
		
		int x4 = (xnext + width - 1) / 16;
		int y4 = (ynext + height - 1) / 16;
		
		return !(
				//Colisores
				((World.tiles[x1 + (y1*World.WIDTH)] instanceof TileBoxColliding) ||
				(World.tiles[x2 + (y2*World.WIDTH)] instanceof TileBoxColliding) ||
				(World.tiles[x3 + (y3*World.WIDTH)] instanceof TileBoxColliding) ||
				(World.tiles[x4 + (y4*World.WIDTH)] instanceof TileBoxColliding)));
	}
	public void render(Graphics g) {
		if(!Actived) {
		g.drawImage(Sprite[CurrentAnim], this.getX(),this.getY(),16,16, null);
		}
	}

}
