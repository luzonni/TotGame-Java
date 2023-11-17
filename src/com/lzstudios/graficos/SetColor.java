package com.lzstudios.graficos;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;

import com.lzstudios.main.Game;

public class SetColor {
	
	public static boolean isPressed;
	public static int xMouse,yMouse;
	
	public int altura = 10;
	
	//Red
	public int RedWidth = 124,RedHeight = 3;
	public double RedX = Game.WIDTH/4 - 62,RedY = 158 + altura;
	//Greem
	public int GreenWidth = 124,GreenHeight = 3;
	public double GreenX = Game.WIDTH/4 - 62,GreenY = 178 + altura;
	//Blue
	public int BlueWidth = 124,BlueHeight = 3;
	public double BlueX = Game.WIDTH/4 - 62,BlueY = 198 + altura;
	
	public void SetColor() {
		
	}
	
	public void tick() {
		if((Game.CUR_LEVEL == 0 || Config.Actived) && !UI.Store) {
		//ColectedValores
		Game.R = (int)((RedX - 58)*2.07);
		Game.G = (int)((GreenX - 58)*2.07);
		Game.B = (int)((BlueX - 58)*2.07);
		
		if((xMouse >= Game.WIDTH/4 - 62 && xMouse < (Game.WIDTH/4 - 62) + 124) &&
			yMouse >= 158 + altura && yMouse < 164 + altura) {
			//RedBar
		  if(isPressed) {
		  	  RedX = xMouse;
		  if(xMouse <= Game.WIDTH/4 - 62) {
			  RedX = Game.WIDTH/4 - 62;
		  }else if(xMouse >= (Game.WIDTH/4 - RedWidth/2) + RedWidth) {
			  RedX = (Game.WIDTH/4 - 62) + 124;
		  }  
		}
		}else if((xMouse >= Game.WIDTH/4 - 62 && xMouse < (Game.WIDTH/4 - 62) + 124) &&
			yMouse >= 174 + altura && yMouse < 184 + altura) {
			  if(isPressed) {
				//Green
				  GreenX = xMouse;
			  if(xMouse <= Game.WIDTH/4 - 62) {
				  GreenX = Game.WIDTH/4 - 62;
			  }else if(xMouse >= (Game.WIDTH/4 - GreenWidth/2) + GreenWidth) {
				  GreenX = (Game.WIDTH/4 - 62) + 124;
			  }  
			}
		}else if((xMouse >= Game.WIDTH/4 - 62 && xMouse < (Game.WIDTH/4 - 62) + 124) &&
			yMouse >= 198 + altura && yMouse < 204 + altura) {
			//Blue
				if(isPressed) {
					BlueX = xMouse;
				if(xMouse <= Game.WIDTH/4 - 62) {
					BlueX = Game.WIDTH/4 - 62;
			    }else if(xMouse >= (Game.WIDTH/4 - BlueWidth/2) + BlueWidth) {
			    	BlueX = (Game.WIDTH/4 - 62) + 124;
				}  
			}
		}else {
			isPressed = false;
		}
		}
	}
	
	public void render(Graphics g) {
		if((Game.CUR_LEVEL == 0 || Config.Actived) && !UI.Store) {
		//RedBar
		g.setColor(new Color((int)(RedX*1.41),(int)(GreenX*1.41),(int)(BlueX*1.41)));
		g.fillRect(Game.WIDTH/4 - 62, (int)RedY + 2, RedWidth, RedHeight);
		g.setColor(new Color((int)(RedX*1.41),0,0));
		g.fillRect((int)RedX, (int)RedY, 4, 6);
		g.setColor(new Color((int)(RedX*1.41),(int)(GreenX*1.41),(int)(BlueX*1.41)));
		g.drawRect((int)RedX, (int)RedY, 4, 6);
		//GreenBar
		g.setColor(new Color((int)(RedX*1.41),(int)(GreenX*1.41),(int)(BlueX*1.41)));
		g.fillRect(Game.WIDTH/4 - 62, (int)GreenY + 2, GreenWidth, GreenHeight);
		g.setColor(new Color(0,(int)(GreenX*1.41),0));
		g.fillRect((int)GreenX, (int)GreenY, 4, 6);
		g.setColor(new Color((int)(RedX*1.41),(int)(GreenX*1.41),(int)(BlueX*1.41)));
		g.drawRect((int)GreenX, (int)GreenY, 4, 6);
		//BlueBar
		g.setColor(new Color((int)(RedX*1.41),(int)(GreenX*1.41),(int)(BlueX*1.41)));
		g.fillRect(Game.WIDTH/4 - 62, (int)BlueY + 2, BlueWidth, BlueHeight);
		g.setColor(new Color(0,0,(int)(BlueX*1.41)));
		g.fillRect((int)BlueX, (int)BlueY, 4, 6);
		g.setColor(new Color((int)(RedX*1.41),(int)(GreenX*1.41),(int)(BlueX*1.41)));
		g.drawRect((int)BlueX, (int)BlueY, 4, 6);
		}
		//CorAnemyEqual
		if(RedX > 0 && RedX < 90 &&
		   GreenX > 150 && GreenX < 255 &&
		   BlueX > 150 && GreenX < 255) {
			g.setColor(new Color((int)(RedX*1.41),(int)(GreenX*1.41),(int)(BlueX*1.41)));
			g.fillRect((Game.WIDTH/4) - 1, 5, 2, 10);
			g.fillRect((Game.WIDTH/4) - 1, 17, 2, 2);
			g.setFont(new Font("arial",Font.BOLD,10));
			g.drawString("The enemies are similar to that color!", (Game.WIDTH/4) - 87, 38);
		}
		//CorBlack
		if(RedX < 24 && GreenX < 24 && BlueX < 24) {
			g.setColor(Color.white);
			g.fillRect((Game.WIDTH/4) - 1, 5, 2, 10);
			g.fillRect((Game.WIDTH/4) - 1, 17, 2, 2);
			g.setFont(new Font("arial",Font.BOLD,10));
			g.drawString("Do you know what you are doing?", (Game.WIDTH/4) - 87, 38);
		}
	}

}
