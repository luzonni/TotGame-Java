package com.lzstudios.graficos;

import java.awt.Color;
import java.awt.Graphics;

import com.lzstudios.main.Game;

public class Config {
	
	public static boolean Actived = false;
	
	public static int xMouse,yMouse;
	public static boolean isPressed = false;
	
	//Buttons
	//ButtonClose
	private int xCloseConfig = 200,yCloseConfig = 40,widthCloseConfig = 10,heightCloseConfig = 10;
	private boolean OnMouseClose = false;
	//ButtonQuitGame
	private int xQuitGame = 108,yQuitGame = 108,widthQuitGame = 9*2,heightQuitGame = 9*2;
	private boolean OnMouseQuitGame = false;

	public void Config() {
		
	}
	
	public void tick() {
		if(Actived) {
		//ButtonCloseConfig
		if((xMouse >= xCloseConfig && xMouse < xCloseConfig+widthCloseConfig) &&
			(yMouse >= yCloseConfig && yMouse < yCloseConfig+heightCloseConfig)) {
			OnMouseClose = true;
			if(isPressed) {
				Actived = false;
			}
		}else {
			OnMouseClose = false;
		}
		//ButtonQuitGame
		if((xMouse >= xQuitGame && xMouse < xQuitGame+widthQuitGame) &&
			(yMouse >= yQuitGame && yMouse < yQuitGame+heightQuitGame)) {
			OnMouseQuitGame = true; 
			if(isPressed) {
				System.exit(1);
			}
		}else {
			OnMouseQuitGame = false;
		}
		}
	}
	
	public void render(Graphics g) {
		if(Actived) {
			//BackGround
			g.setColor(Color.black);
			g.fillRect(0, 0, 240, 240);
			//MenuSetColors
			g.setColor(Color.yellow);
			g.fillRect(40, 135, 160, 3);
			g.fillRect(37, 138, 3, 87);
			g.fillRect(200, 138, 3, 87);
			g.fillRect(40, 225, 160, 3);
			g.fillRect(40, 222, 3, 3);
			g.fillRect(40, 138, 3, 3);
			g.fillRect(197, 138, 3, 3);
			g.fillRect(197, 222, 3, 3);
			//DrawTile
			g.setColor(new Color((int)Game.R,(int)Game.G,(int)Game.B));
			g.fillRect(109, 145, 16, 16);
			g.drawImage(Game.SpriteTiles.getSprite(0, 48, 16, 16), 109, 145, 16, 16,null);
			//ButtonClose
			if(OnMouseClose) {
				g.drawImage(Game.ButtonsSprite.getSprite(6, 32, 5, 5),xCloseConfig,yCloseConfig + 1,widthCloseConfig,heightCloseConfig,null);
			}else {
				g.drawImage(Game.ButtonsSprite.getSprite(0, 32, 5, 5),xCloseConfig,yCloseConfig,widthCloseConfig,heightCloseConfig,null);
			}
			//ButtonQuitGame
			if(OnMouseQuitGame) {
				g.drawImage(Game.ButtonsSprite.getSprite(7, 3, 9, 9),xQuitGame,yQuitGame + 1,widthQuitGame,heightQuitGame,null);
			}else {
				g.drawImage(Game.ButtonsSprite.getSprite(0, 10, 9, 9),xQuitGame,yQuitGame,widthQuitGame,heightQuitGame,null);
			}
		}
	}
	
	
	
}
