package com.lzstudios.graficos;


import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.image.BufferedImage;

import com.lzstudios.animations.AnimDead;
import com.lzstudios.entities.Player;
import com.lzstudios.main.Game;

public class UI {
	
	public static int frames;
	
	public Config config;
	
	//Coin
	public BufferedImage Coin[];
	public BufferedImage SkinChanger[];
	//AnimCoin
	public int CurrentAnimCoin = 0,LastAnimCoin = 6;
	public int TimeAnimCoin;
	//AnimSkin
	public int CurrentAnimSkinPlayer = 0,LastAnimSkinPlayer = 4;
	public int TimeAnimSkinPlayer;
	//ButtonsSprites
	public BufferedImage ButtonPlay,ButtonConfig,ButtonConfigOnMouse,ButtonContinue,ButtonCancel,ButtonCancelOnMouse,
	menuImage,ButtonStore,ButtonStoreOnMouse,ButtonContinueOnMouse,CoinOnMouse;
	//System
	public boolean ShowKeys = true;
	//MenuStore
	public static boolean Store;
	public Spritesheet spriteStore;
	public static int SkinPlayer = 0;
	public boolean DontBuy = false;
	//MouseSystem
	public static int mouseX,mouseY;
	public static boolean pressed = false;
	//ButtonStore
	private int xButtonStore = 10,yButtonStore = 10,widthButtoStore = 13,heightButtonStore = 11;
	public boolean OnMouseStore = false;
	//ButtonDie
	private int xButtonDie = 28,yButtonDie = 10,widthButtoDie = 11,heightButtonDie = 11;
	public boolean OnMouseDie = false;
	//ButtonConfig
	private int xButtonConfig = 215,yButtonConfig = 9,widthButtonConfig = 13,heightButtonConfig = 13;
	public boolean OnMouseConfig = false;
	//ButtonContinue
	private int xButtonContinue = 122,yButtonContinue = 120,widthButtonContinue = 32,heightButtonContinue = 16;
	public boolean OnMouseContinue = false;
	//ButtonCancel
	private int xButtonCancel = 85,yButtonCancel = 120,widthButtonCancel = 32,heightButtonCancel = 16;
	public boolean OnMouseCancel = false;
	//ButtonCloseStore
	private int xButtonCloseStore = 194,yButtonCloseStore = 37,widthButtonCloseStore = 10,heightButtonCloseStore = 10;
	public boolean OnMouseCloseStore = false;
	//ButtonChangeRight
	private int xButtonRightStore = 91,yButtonRightStore = 102,widthButtonRightStore = 3*2,heightButtonRightStore = 6*2;
	public boolean OnMouseRightStore = false;
	//ButtonChangeLeft
	private int xButtonLeftStore = 47,yButtonLeftStore = 102,widthButtonLeftStore = 3*2,heightButtonLeftStore = 6*2;
	public boolean OnMouseLeftStore = false;
	//ButtonBuySkin
	private int xButtonBuySkinStore = 56,yButtonBuySkinStore = 100,widthButtonBuySkinStore = 16*2,heightButtonBuySkinStore = 8*2;
	public boolean OnMouseBuySkinStore = false;
	//BuyCoin
	private int xButtonBuyCoin100 = 56,yButtonBuyCoin100 = 127,widthButtonBuyCoin100 = 16*2,heightButtonBuyCoin100 = 8*2;
	public boolean OnMouseBuyCoin100 = false;
	
	public void Ui() {
		//ConfigMenu
		config = new Config();
		config.Config();
		//SkinChage
		SkinChanger = new BufferedImage[4];
		//Coin
		Coin = new BufferedImage[6];
		Coin[0] = Game.spritesheet.getSprite(144,0,16,16);
		Coin[1] = Game.spritesheet.getSprite(144,16,16,16);
		Coin[2] = Game.spritesheet.getSprite(144,32,16,16);
		Coin[3] = Game.spritesheet.getSprite(144,48,16,16);
		Coin[4] = Game.spritesheet.getSprite(144,64,16,16);
		Coin[5] = Game.spritesheet.getSprite(144,80,16,16);
		CoinOnMouse = Game.ButtonsSprite.getSprite(80,0,16,16);
		//Buttons
		ButtonPlay = Game.ButtonsSprite.getSprite(0, 0, 6, 9);
		ButtonConfig = Game.ButtonsSprite.getSprite(98, 3, 13, 13);
		ButtonConfigOnMouse = Game.ButtonsSprite.getSprite(98, 19, 13, 13);
		ButtonStore = Game.ButtonsSprite.getSprite(114, 5, widthButtoStore, heightButtonStore);
		ButtonStoreOnMouse = Game.ButtonsSprite.getSprite(114, 17, widthButtoStore, heightButtonStore);
		ButtonContinue = Game.ButtonsSprite.getSprite(16, 0, 32, 16);
		ButtonContinueOnMouse = Game.ButtonsSprite.getSprite(48, 0, 32, 16);
		ButtonCancel = Game.ButtonsSprite.getSprite(16, 16, 32, 16);
		ButtonCancelOnMouse = Game.ButtonsSprite.getSprite(48, 16, 32, 16);
		//MenuLayout
		menuImage = Game.MenuSprite.getSprite(0, 0, 48, 48);
		//MenuStore
		spriteStore = new Spritesheet("/MenuStore.png");
	}
	
	
	public void tick() {
		//AnimCoin
		TimeAnimCoin++;
		if(TimeAnimCoin == 5) {
		CurrentAnimCoin++;
		TimeAnimCoin = 0;
		}
		if(CurrentAnimCoin == LastAnimCoin) {
			CurrentAnimCoin = 0;
		}
		//AnimSkinPlayer
		TimeAnimSkinPlayer++;
		if(TimeAnimSkinPlayer == 5) {
		CurrentAnimSkinPlayer++;
		TimeAnimSkinPlayer = 0;
		}
		if(CurrentAnimSkinPlayer == LastAnimSkinPlayer) {
			CurrentAnimSkinPlayer = 0;
		}
		SkinChanger[0] = Game.PlayerSpriteSheet.getSprite(SkinPlayer*16, 0, 16, 16);
		SkinChanger[1] = Game.PlayerSpriteSheet.getSprite(SkinPlayer*16, 16, 16, 16);
		SkinChanger[2] = Game.PlayerSpriteSheet.getSprite(SkinPlayer*16, 32, 16, 16);
		SkinChanger[3] = Game.PlayerSpriteSheet.getSprite(SkinPlayer*16, 48, 16, 16);
		//ButtonsStore ========================================================
		if(mouseX >= xButtonStore && mouseX < (xButtonStore+widthButtoStore) &&
		   mouseY >= yButtonStore && mouseY < (yButtonStore+heightButtonStore)) {
					//ButtonStore
				    OnMouseStore = true;
					if(pressed) {
					//MenuStore
					Store = true;
					SkinPlayer = Game.player.Personagem;
		    }
		}else {
			OnMouseStore = false;
		}
		//ButtonDie
		if(mouseX >= xButtonDie && mouseX < (xButtonDie+widthButtoDie) &&
				   mouseY >= yButtonStore && mouseY < (yButtonStore+heightButtonDie)) {
							//ButtonDie
						    OnMouseDie = true;
							if(pressed && !Game.playerDead && Game.CUR_LEVEL != 0) {
							//Die
								Game.entities.remove(Game.player);
								AnimDead dead = new AnimDead(Game.player.getX(),Game.player.getY(),16,16,0,null);
								Game.entities.add(dead);
								Game.playerDead = true;
							pressed = false;
				    }
				}else {
					OnMouseDie = false;
				}
		//====================================================================
		if(!Store) {//SystemOfStore /\
		//SystemaButtons;
		
		//ButtonsContinue
		if(mouseX >= xButtonContinue && mouseX < (xButtonContinue+widthButtonContinue) &&
		   mouseY >= yButtonContinue && mouseY < (yButtonContinue+heightButtonContinue)) {
				OnMouseContinue = true;
				if(pressed) {
				if(Game.GameOver && Game.MenuGameOver && Player.Coins >=10) {
						Game.GameOver = false;
						Game.MenuGameOver = false;
						Player.Coins-=10;
						Game.entities.add(Game.player);
						Game.playerDead = false;
						Game.Darkness = 0;
			    }
		    }
		}else {
			OnMouseContinue = false;
		}
		//ButtonsCancel
		if(mouseX >= xButtonCancel && mouseX < (xButtonCancel+widthButtonCancel) && 
		   mouseY >= yButtonCancel && mouseY < (yButtonCancel+heightButtonCancel)) {
		           OnMouseCancel = true;
					if(pressed) {
					if(Game.GameOver) {
						Game.restart = true;
					}
				}
		}else {
			OnMouseCancel = false;
		}
		//ExitConfig
		if(mouseX >= xButtonConfig && mouseX < (xButtonConfig+widthButtonConfig) && 
		   mouseY >= yButtonConfig && mouseY < (yButtonConfig+heightButtonConfig)) {
					//OnMouse
					OnMouseConfig = true;
					if(pressed) {
					//Systema de save
					config.Actived = true;
					}
			}else {
				OnMouseConfig = false;
		}
		}else {
			//SystemButtonsStore
			//ExitStore
			if(mouseX >= xButtonCloseStore && mouseX < (xButtonCloseStore+widthButtonCloseStore) && 
			   mouseY >= yButtonCloseStore && mouseY < (yButtonCloseStore+heightButtonCloseStore)) {
						//OnMouse
						OnMouseCloseStore = true;
						if(pressed) {
						//FecharLoja
						Store = false;
					}
				}else {
					OnMouseCloseStore = false;
			}
			//ButtonRightChage
			if(mouseX >= xButtonRightStore && mouseX < (xButtonRightStore+widthButtonRightStore) && 
					   mouseY >= yButtonRightStore && mouseY < (yButtonRightStore+heightButtonRightStore)) {
								//OnMouse
								OnMouseRightStore = true;
								if(pressed) {
								//ChangeSkin++
									SkinPlayer++;
						            if(SkinPlayer > 9) {
						                SkinPlayer = 0;
						            }
						        pressed = false;
								}
						}else {
							OnMouseRightStore = false;
					}
			//ButtonLeftChange
			if(mouseX >= xButtonLeftStore && mouseX < (xButtonLeftStore+widthButtonLeftStore) && 
					   mouseY >= yButtonLeftStore && mouseY < (yButtonRightStore+heightButtonLeftStore)) {
								//OnMouse
								OnMouseLeftStore = true;
								if(pressed) {
								//ChangeSkin++
									SkinPlayer--;
						            if(SkinPlayer <= -1) {
						                SkinPlayer = 9;
						            }
						        pressed = false;
								}
						}else {
							OnMouseLeftStore = false;
					}
			//BuyPersonAndSetPerson
			if(mouseX >= xButtonBuySkinStore && mouseX < (xButtonBuySkinStore+widthButtonBuySkinStore) && 
					   mouseY >= yButtonBuySkinStore && mouseY < (yButtonBuySkinStore+heightButtonBuySkinStore)) {
								//OnMouse
								OnMouseBuySkinStore = true;
								if(pressed) {
									if(Player.Diamond >= 5) {
								//ChangeSkin++
								Game.player.Personagem = SkinPlayer;
								Player.Diamond-=5;
								}else {
									DontBuy = true;
								}
						        pressed = false;
								}
						}else {
							OnMouseBuySkinStore = false;
					}
			//BuyCoins
			if(mouseX >= xButtonBuyCoin100 && mouseX < (xButtonBuyCoin100+widthButtonBuyCoin100) && 
					   mouseY >= yButtonBuyCoin100 && mouseY < (yButtonBuyCoin100+heightButtonBuyCoin100)) {
								//OnMouse
								OnMouseBuyCoin100 = true;
								if(pressed) {
									if(Player.Diamond >= 2) {
								//ChangeSkin++
								Player.Coins+=50;
								Player.Diamond-=2;
								}else {
									DontBuy = true;
								}
						        pressed = false;
								}
						}else {
							OnMouseBuyCoin100 = false;
					}
		}
		config.tick();
		
	}

	public void render(Graphics g) {
		//ButtonsTuto
		if(ShowKeys && !Store && Game.CUR_LEVEL != 0) {
			int x = -87;
			int y = 10;
			if(Game.player.Up) {
			g.drawImage(Game.ButtonsSprite.getSprite(56, 73, 13, 13),200 + x,201 + y,13,13,null);
			}else {
			g.drawImage(Game.ButtonsSprite.getSprite(14, 73, 13, 13),200 + x,200 + y,13,13,null);
			}
			if(Game.player.Down) {
			g.drawImage(Game.ButtonsSprite.getSprite(56, 87, 13, 13),200 + x,216 + y,13,13,null);
			}else {
			g.drawImage(Game.ButtonsSprite.getSprite(14, 87, 13, 13),200 + x,215 + y,13,13,null);
			}
			if(Game.player.Right) {
			g.drawImage(Game.ButtonsSprite.getSprite(70, 87, 13, 13),215 + x,216 + y,13,13,null);
			}else {
			g.drawImage(Game.ButtonsSprite.getSprite(28, 87, 13, 13),215 + x,215 + y,13,13,null);
			}
			if(Game.player.Left) {
			g.drawImage(Game.ButtonsSprite.getSprite(42, 87, 13, 13),185 + x,216 + y,13,13,null);
			}else {
			g.drawImage(Game.ButtonsSprite.getSprite(0, 87, 13, 13),185 + x,215 + y,13,13,null);
			}
		}
		//ButtonsStore
		if(!Store) {
		if(OnMouseStore) {
			g.drawImage(ButtonStoreOnMouse, xButtonStore, yButtonStore + 1,widthButtoStore,heightButtonStore,null);
		}else {
			g.drawImage(ButtonStore, xButtonStore, yButtonStore,widthButtoStore,heightButtonStore,null);
		}
		if(Game.CUR_LEVEL != 0) {
		if(OnMouseDie) {
			g.drawImage(Game.ButtonsSprite.getSprite(13, 48, 11, 11), xButtonDie, yButtonDie + 1,widthButtoDie,heightButtonDie,null);
		}else {
			g.drawImage(Game.ButtonsSprite.getSprite(0, 48, 11, 11), xButtonDie, yButtonDie,widthButtoDie,heightButtonDie,null);
		}
		}
		//ButtonExit
		if(OnMouseConfig) {
		g.drawImage(ButtonConfigOnMouse, 215, 10, 13, 13, null);
		}else {
		g.drawImage(ButtonConfig, 215, 9, 13, 13, null);
		}
		//Iniciu
		if(Game.CUR_LEVEL == 0) {
			g.drawImage(ButtonPlay, 134, 115, 6, 9, null);
			g.drawImage(Game.ButtonsSprite.getSprite(0, 10, 9, 9), 99, 115, 9, 9, null);
		}else {
		if(!Game.GameOver) {
		//Ui Gaming
		g.setColor(new Color(255,255,0));
		g.setFont(new Font("Arial",Font.BOLD,10));
		g.drawString("Level " + "[" + Game.CUR_LEVEL + "]" , 100, 10);
		g.drawString("= [" + Player.Coins + "]" , 115, 23);
		//Coin
		g.drawImage(Coin[CurrentAnimCoin], 100, 12, 16, 16, null);
		}else if(Game.MenuGameOver){
			//SystemGameOver
			//Coin
			g.drawImage(Coin[CurrentAnimCoin], 80, 73, 16*2, 16*2, null);
			g.setColor(new Color(255,255,0));
			g.setFont(new Font("Arial",Font.BOLD,12));
			g.drawString("= " + Player.Coins, 110, 93);
			//ButtonsContinue
			if(OnMouseContinue) {
				g.drawImage(ButtonContinueOnMouse, 122, 121, 32, 16, null);
				g.setFont(new Font("Arial",Font.BOLD,9));
				g.setColor(new Color(0,255,255));
				g.drawImage(CoinOnMouse, 124, 121, 15, 15, null);
				g.drawString("10", 139, 132);
			}else {
				g.drawImage(ButtonContinue, 122, 120, 32, 16, null);
				g.setFont(new Font("Arial",Font.BOLD,9));
				g.drawImage(Coin[0], 124, 120, 15, 15, null);
				g.drawString("10", 139, 131);
			}
			//ButtonsCancel
			if(OnMouseCancel) {
			    g.drawImage(ButtonCancelOnMouse, 85, 121, 32, 16, null);
			}else {
			    g.drawImage(ButtonCancel, 85, 120, 32, 16, null);
			}
			//MenuGameOver
			g.drawImage(menuImage,Game.WIDTH/4 - 48,Game.HEIGHT/4  - 48,48*2,48*2,null);
			g.setColor(new Color(255,255,0));
			g.setFont(new Font("Arial",Font.BOLD,10));
			//LevelCurrentBar
			g.drawRect((Game.WIDTH/4 - 27),Game.HEIGHT/4  + 24, 54, 4);
			int level = (Game.CUR_LEVEL);
			g.drawLine((Game.WIDTH/4 - 25) + level/2,Game.HEIGHT/4  + 26, (Game.WIDTH/4 + 25),Game.HEIGHT/4  + 26);
			g.setColor(new Color(255,255,0));
			g.setFont(new Font("Arial",Font.BOLD,10));
			g.drawString("" + level + "/" + "100", Game.WIDTH/4 - 10,Game.HEIGHT/4 + 39);
		}
	 }
	}else {
		//SystemStore
		g.setColor(Color.black);
		g.fillRect(30, 30, 180, 184);
		g.setColor(new Color(Game.R,Game.G,Game.B));
		g.fillRect(51, 51, 42, 42);
		g.setFont(new Font("Arial",Font.BOLD,13));
		g.setColor(Color.yellow);
		g.fillRect(30, 30, 179, 3);
		g.fillRect(27, 33, 3, 181);
		g.fillRect(209, 33, 3, 181);
		g.fillRect(30, 214, 179, 3);
		g.fillRect(30, 211, 3, 3);
		g.fillRect(30, 33, 3, 3);
		g.fillRect(206, 33, 3, 3);
		g.fillRect(206, 211, 3, 3);
		g.fillRect(43, 120, 58, 3);
		g.drawImage(spriteStore.getSprite(40, 1, 9, 9), 110,48,18,18,null);
		g.drawImage(Game.spritesheet.getSprite(148, 4, 8, 8), 115,75,8,8,null);
		g.drawString("" + Player.Diamond, 135, 62);
		g.drawString("" + Player.Coins, 135, 83);
		g.drawImage(spriteStore.getSprite(40, 1, 9, 9), 110,70,18,18,null);
		g.drawImage(spriteStore.getSprite(0, 0, 18, 18), 45, 45, 18*3,18*3,null);
		g.drawImage(SkinChanger[CurrentAnimSkinPlayer], 56, 55,16*2,16*2,null);
		//CloseStore
		if(OnMouseCloseStore) {
			g.drawImage(spriteStore.getSprite(68, 1, 5, 5),xButtonCloseStore,yButtonCloseStore + 1,widthButtonCloseStore,heightButtonCloseStore,null);
		}else {
			g.drawImage(spriteStore.getSprite(62, 1, 5, 5),xButtonCloseStore,yButtonCloseStore,widthButtonCloseStore,heightButtonCloseStore,null);
		}
		//ButtonRight
		if(OnMouseRightStore) {
			g.drawImage(spriteStore.getSprite(9, 26, 3, 6),xButtonRightStore,yButtonRightStore + 1,widthButtonRightStore,heightButtonRightStore,null);
		}else {
			g.drawImage(spriteStore.getSprite(9, 19, 3, 6),xButtonRightStore,yButtonRightStore,widthButtonRightStore,heightButtonRightStore,null);
		}
		//ButtonLeft
		if(OnMouseLeftStore) {
			g.drawImage(spriteStore.getSprite(5, 26, 3, 6),xButtonLeftStore,yButtonLeftStore + 1,widthButtonLeftStore,heightButtonLeftStore,null);
		}else {
			g.drawImage(spriteStore.getSprite(5, 19, 3, 6),xButtonLeftStore,yButtonLeftStore,widthButtonLeftStore,heightButtonLeftStore,null);
		}
		//ButtonBuySkin
		if(OnMouseBuySkinStore) {
			g.setColor(new Color(0,255,255));
			g.setFont(new Font("Arial",Font.BOLD,10));
			g.drawString("5", 75, 112);
			g.drawImage(Game.ButtonsSprite.getSprite(84, 25, 8, 8), 60,103,8*2,8*2,null);
			g.drawImage(spriteStore.getSprite(21, 19, 16, 8),xButtonBuySkinStore,yButtonBuySkinStore + 1,widthButtonBuySkinStore,heightButtonBuySkinStore,null);
		}else {
			g.setFont(new Font("Arial",Font.BOLD,10));
			g.drawString("5", 75, 111);
			g.drawImage(Game.ButtonsSprite.getSprite(84, 17, 8, 8), 60,102,8*2,8*2,null);
		    g.drawImage(spriteStore.getSprite(21, 1, 16, 8),xButtonBuySkinStore,yButtonBuySkinStore,widthButtonBuySkinStore,heightButtonBuySkinStore,null);
		}
		if(DontBuy) {
			g.drawImage(Game.ButtonsSprite.getSprite(84, 17, 7, 6),112,51 + 1,7*2,6*2,null);
			 for(int i = 0; i < 2; i++) {
				 if(i == 1) {
				 DontBuy = false;
				 }
			 }
		}else {
			g.drawImage(Game.ButtonsSprite.getSprite(84, 17, 7, 6),112,51,7*2,6*2,null);

		}
		if(OnMouseBuyCoin100) {
			g.setFont(new Font("Arial",Font.BOLD,10));
			g.setColor(new Color(0,255,255));
			g.drawString("2", 75, 139);
			g.drawImage(Game.ButtonsSprite.getSprite(84, 25, 8, 8), 59,130,8*2,8*2,null);
			g.drawImage(spriteStore.getSprite(21, 19, 16, 8),xButtonBuyCoin100,yButtonBuyCoin100 + 1,widthButtonBuyCoin100,heightButtonBuyCoin100,null);
		}else {
			g.setFont(new Font("Arial",Font.BOLD,9));
			g.setColor(new Color(255,255,0));
			g.drawString("50", 72, 138);
			g.drawImage(Game.spritesheet.getSprite(148, 4, 8, 8), 61,131,8,8,null);
			g.drawImage(spriteStore.getSprite(21, 1, 16, 8),xButtonBuyCoin100,yButtonBuyCoin100,widthButtonBuyCoin100,heightButtonBuyCoin100,null);

		}
		
		//========================
	}
		g.setFont(new Font("arial",Font.BOLD,10));
		if(frames >= 60) {
			g.setColor(Color.yellow);
		}else {
			g.setColor(Color.red);
		}
		g.drawString("" + frames , 5, 235);
		//ConfigMenu
		config.render(g);
	}
}