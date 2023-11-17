package com.lzstudios.entities;

import java.awt.Graphics;
import java.awt.image.BufferedImage;

import com.lzstudios.main.Game;
import com.lzstudios.world.BoxTile;
import com.lzstudios.world.FloorTile;
import com.lzstudios.world.Tile;
import com.lzstudios.world.TileBoxColliding;
import com.lzstudios.world.World;

public class DoorBox extends Entity{
	
	public BufferedImage Sprite[];
	public BufferedImage DoorClosed[];
	public int xx,yy;
	public int timer;
	
	public int CurrentAnim = 0,LastAnim = 2;
	public int TimeAnim;
	
	public int CurrentAnimOpening = 0,LastAnimOpening = 10;
	public int TimeAnimOpening;
	
	public World world;
	public static boolean DoorLiving = false;
	
	public static boolean Actived = false;
	public static boolean ActivedAnim = false;
	public boolean Collected = true;
	public int CollectedTimer = 0;

	public DoorBox(double x, double y, int width, int height, double speed, BufferedImage sprite) {
		super(x, y, width, height, speed, sprite);
		Sprite = new BufferedImage[2];
		Sprite[0] = Game.spritesheet.getSprite(16, 80, 16, 16); 
		Sprite[1] = Game.spritesheet.getSprite(16, 96, 16, 16); 
		
		DoorClosed = new BufferedImage[10];
		DoorClosed[0] = Game.spritesheet.getSprite(0, 144, 16, 16);
		DoorClosed[1] = Game.spritesheet.getSprite(16, 144, 16, 16);
		DoorClosed[2] = Game.spritesheet.getSprite(32, 144, 16, 16);
		DoorClosed[3] = Game.spritesheet.getSprite(48, 144, 16, 16);
		DoorClosed[4] = Game.spritesheet.getSprite(64, 144, 16, 16);
		DoorClosed[5] = Game.spritesheet.getSprite(80, 144, 16, 16);
		DoorClosed[6] = Game.spritesheet.getSprite(96, 144, 16, 16);
		DoorClosed[7] = Game.spritesheet.getSprite(112, 144, 16, 16);
		DoorClosed[8] = Game.spritesheet.getSprite(128, 144, 16, 16);
		DoorClosed[9] = Game.spritesheet.getSprite(144, 144, 16, 16);
		
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
		//OpeningAnim
		if(ActivedAnim) {
		TimeAnimOpening++;
		if(TimeAnimOpening == 4) {
			CurrentAnimOpening++;
			TimeAnimOpening = 0;
			}
		if(CurrentAnimOpening == LastAnimOpening) {
			CurrentAnimOpening = 0;
			ActivedAnim = false;
			Actived = true;
		}
		}
		//SeForDesativado
		if(DoorLiving) {
			 if(Actived) {
				World.tiles[(xx/16) + ((yy/16) * World.WIDTH)] = new TileBoxColliding(xx,yy,Tile.TILE_FLOOR);
				if(Game.playerDead == false &&
				   Entity.calculateDistance(this.getX() + 8, this.getY() + 8, Game.player.getX() + 8, Game.player.getY() + 8) < 17) {
					if(Game.player.dir == 2 && !isFreeDoor(Game.player.getX(), Game.player.getY() + 16, 16, 16)) {
						Game.player.Down = true;
					}else if(Game.player.dir == 1 && !isFreeDoor(Game.player.getX(), Game.player.getY() - 16, 16, 16)) {
						Game.player.Up = true;
					}else if(Game.player.dir == 3 && !isFreeDoor(Game.player.getX() + 16, Game.player.getY(), 16, 16)) {
						Game.player.Right = true;
					}else if(Game.player.dir == 4 && !isFreeDoor(Game.player.getX() - 16, Game.player.getY(), 16, 16)) {
						Game.player.Left = true;
					}
				}
				//SystemBox
		        if(Collected == true) {
		            Box box = new Box(this.getX(), this.getY(),16,16,0,null);
		            Game.entities.add(box);
		            Collected = false;
		        }
			 }else {
				World.tiles[(xx/16) + ((yy/16) * World.WIDTH)] = new BoxTile(xx,yy,Tile.TILE_FLOOR);
			 }
		}
	}
    public static boolean isFreeDoor(int xnext,int ynext, int width, int height){
		
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
		if(Actived) {
		g.drawImage(Sprite[CurrentAnim], this.getX(),this.getY(),16,16, null);
		}else {
		g.drawImage(DoorClosed[CurrentAnimOpening], this.getX(),this.getY(),16,16, null);
		}
	}

}
