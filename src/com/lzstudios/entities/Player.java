package com.lzstudios.entities;


import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;

import com.lzstudios.animations.AnimCollider;
import com.lzstudios.animations.WalkAnim;
import com.lzstudios.graficos.UI;
import com.lzstudios.main.Game;
import com.lzstudios.world.World;


public class Player extends Entity{
	
	public static int Coins = 1000000000;
	public static int Diamond = 10;
	
	public boolean Right = false,Left = false,Up = false,Down = false;
	public boolean isRunR,isRunL,isRunU,isRunD,isRun;
	
	public static int boxCurrent;
	
	public BufferedImage PlayerDefault[],PlayerWalking[];
	public int dir = 0;
	public static int DirX = 2,DirY = 2;
	public static boolean StopedRight = false,StopedLeft = false,StopedUp = false,StopedDown = false;
	private double rotate;
	
	public int CurrentAnim = 0,LastAnim = 4;
	public int TimeAnim;
	
	public int Personagem = UI.SkinPlayer;
	
	public Player(int x, int y, int width, int height,double speed,BufferedImage sprite) {
		super(x, y , 16, 16,8,sprite);
		//Static
		depth = 5;
		//Walking
		PlayerWalking = new BufferedImage[4];
		PlayerWalking[0] = Game.AnimSpriteSheet.getSprite(48, 0, 16, 16);
		PlayerWalking[1] = Game.AnimSpriteSheet.getSprite(48, 16, 16, 16);
		PlayerWalking[2] = Game.AnimSpriteSheet.getSprite(48, 32, 16, 16);
		PlayerWalking[3] = Game.AnimSpriteSheet.getSprite(48, 48, 16, 16);
		
		if(!World.isFree(this.getX(), this.getY() + 1, width, height)) {
			dir = 2;
		}else if(!World.isFree(this.getX(), this.getY() - 1, width, height)) {
			dir = 1;
		}else if(!World.isFree(this.getX() + 1, this.getY(), width, height)) {
			dir = 3;
		}else if(!World.isFree(this.getX() - 1, this.getY(), width, height)) {
			dir = 4;
		}
		
	}
	
	public void tick(){
		//Skin
		PlayerDefault = new BufferedImage[4];
		PlayerDefault[0] = Game.PlayerSpriteSheet.getSprite(Personagem*16, 0, 16, 16);
		PlayerDefault[1] = Game.PlayerSpriteSheet.getSprite(Personagem*16, 16, 16, 16);
		PlayerDefault[2] = Game.PlayerSpriteSheet.getSprite(Personagem*16, 32, 16, 16);
		PlayerDefault[3] = Game.PlayerSpriteSheet.getSprite(Personagem*16, 48, 16, 16);
		//====
		DirY+=10;
		depth = 1;
		//Direita
		if(World.isFreePlayer((int)(this.getX()+speed), this.getY(),this.getWidth(),this.getHeight())) {
			if(Right == true) {
			x+=speed;
			dir = 3;
			isRunR = true;
			WalkAnim collider = new WalkAnim(this.getX(),this.getY(),16,16,0,null);
			Game.entities.add(collider);
			StopedRight = true;
			}
		}else {
			Right = false;
			isRunR = false;
			if(StopedRight == true) {
				AnimCollider ac1 = new AnimCollider(this.getX(),this.getY(),16,16,0,null);
				Game.entities.add(ac1);
				StopedRight = false;
			}
		}
		//Esquerda
		if(World.isFreePlayer((int)(this.getX()-speed), this.getY(),this.getWidth(),this.getHeight())) {
			if(Left == true) {
			x-=speed;
			dir = 4;
			isRunL = true;
			WalkAnim collider = new WalkAnim(this.getX(),this.getY(),16,16,0,null);
			Game.entities.add(collider);
			StopedLeft = true;
			}
		}else {
			Left = false;
			isRunL = false;
			if(StopedLeft == true) {
				AnimCollider ac2 = new AnimCollider(this.getX(),this.getY(),16,16,0,null);
				Game.entities.add(ac2);
				StopedLeft = false;
			}
		}
		//Cima
		if(World.isFreePlayer(this.getX(), (int)(this.getY() - speed),this.getWidth(),this.getHeight())) {
			if(Up == true) {
			y-=speed;
			dir = 1;
			isRunU = true;
			WalkAnim collider = new WalkAnim(this.getX(),this.getY(),16,16,0,null);
			Game.entities.add(collider);
			StopedUp = true;
			}
		}else {
			Up = false;
			isRunU = false;
			if(StopedUp == true) {
				AnimCollider ac3 = new AnimCollider(this.getX(),this.getY(),16,16,0,null);
				Game.entities.add(ac3);
				StopedUp = false;
			}
		}
		//Baixo
		if(World.isFreePlayer(this.getX(), (int)(this.getY() + speed),this.getWidth(),this.getHeight())) {
			if(Down == true) {
			y+=speed;
			dir = 2;
			isRunD = true;
			WalkAnim collider = new WalkAnim(this.getX(),this.getY(),16,16,0,null);
			Game.entities.add(collider);
			StopedDown = true;
			}
		}else {
			Down = false;
			isRunD = false;
			if(StopedDown == true) {
				AnimCollider ac4 = new AnimCollider(this.getX(),this.getY(),16,16,0,null);
				Game.entities.add(ac4);
				StopedDown = false;
			}
		}
		//Verificação
		if(isRunR == true || isRunL == true || isRunU == true || isRunD == true) {
			isRun = true;
		}else {
			isRun = false;
			Right = false;
			Left = false;
			Up = false;
			Down = false;
		}
		//Direction
		if(Game.player.dir == 2) {
			   rotate = 0;
			}else if(Game.player.dir == 1) {
				rotate = 3.14;
			}else if(Game.player.dir == 3) {
				rotate = 4.71239;
			}else if(Game.player.dir == 4) {
				rotate = 1.5708;
			}
		//Anim
		TimeAnim++;
		if(TimeAnim == 5) {
		CurrentAnim++;
		TimeAnim = 0;
		}
		if(CurrentAnim == LastAnim) {
			CurrentAnim = 0;
		}
	}
	public void render(Graphics g) {
		Graphics2D g2 = (Graphics2D) g;
		if(isRun == false) {
			if(dir == 2) {
				g2.rotate(rotate,this.getX() + 8,this.getY() + 8);
				g.drawImage(PlayerDefault[CurrentAnim], this.getX(), this.getY(),16,16,null);
				g2.rotate(-rotate,this.getX() + 8,this.getY() + 8);
			}else if(dir == 1){
				g2.rotate(rotate,this.getX() + 8,this.getY() + 8);
				g.drawImage(PlayerDefault[CurrentAnim], this.getX(), this.getY(),16,16,null);
				g2.rotate(-rotate,this.getX() + 8,this.getY() + 8);
			}else if(dir == 3) {
				g2.rotate(rotate,this.getX() + 8,this.getY() + 8);
				g.drawImage(PlayerDefault[CurrentAnim], this.getX(), this.getY(),16,16,null);
				g2.rotate(-rotate,this.getX() + 8,this.getY() + 8);
			}else if(dir == 4) {
				g2.rotate(rotate,this.getX() + 8,this.getY() + 8);
				g.drawImage(PlayerDefault[CurrentAnim], this.getX(), this.getY(),16,16,null);
				g2.rotate(-rotate,this.getX() + 8,this.getY() + 8);
			}
		}else {
			g2.rotate(rotate,this.getX() + 8,this.getY() + 8);
			g.drawImage(PlayerWalking[CurrentAnim], this.getX(), this.getY(),16,16,null);
			g2.rotate(-rotate,this.getX() + 8,this.getY() + 8);
		}
	}
	
}
