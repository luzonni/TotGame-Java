package com.lzstudios.world;

import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.imageio.ImageIO;

import com.lzstudios.entities.Box;
import com.lzstudios.entities.BoxCollider;
import com.lzstudios.entities.Coin;
import com.lzstudios.entities.Diamond;
import com.lzstudios.entities.DoorBox;
import com.lzstudios.entities.Enemy;
import com.lzstudios.entities.Entity;
import com.lzstudios.entities.GeradorArrow;
import com.lzstudios.entities.Jumping;
import com.lzstudios.entities.Key;
import com.lzstudios.entities.LazerShot;
import com.lzstudios.entities.Particle;
import com.lzstudios.entities.Player;
import com.lzstudios.entities.QuitGame;
import com.lzstudios.entities.Teleporter1;
import com.lzstudios.entities.Teleporter2;
import com.lzstudios.entities.Thorns;
import com.lzstudios.graficos.Spritesheet;
import com.lzstudios.main.Game;

public class World {
	
	public static BoxCollider bc;

	public static Tile[] tiles;
	public static List<Tile> tilesL; 
	public static int WIDTH,HEIGHT;
	public static final int TILE_SIZE = 16;
	public int SpriteSize = 16;
	
	public int BoxMap;
	
	public boolean BoxLiving = false;
	
	public TileArrow TA;
	public Jumping jumping;
	
	public World(String path){
		jumping = new Jumping(0,0,0,0,0,null);
		try {
			BufferedImage map = ImageIO.read(getClass().getResource(path));
			int[] pixels = new int[map.getWidth() * map.getHeight()];
			WIDTH = map.getWidth();
			HEIGHT = map.getHeight();
			tiles = new Tile[map.getWidth() * map.getHeight()];
			map.getRGB(0, 0, map.getWidth(), map.getHeight(),pixels, 0, map.getWidth());
			for(int xx = 0; xx < map.getWidth(); xx++){
				for(int yy = 0; yy < map.getHeight(); yy++){
					int pixelAtual = pixels[xx + (yy * map.getWidth())];
					tiles[xx + (yy * WIDTH)] = new FloorTile(xx*SpriteSize,yy*SpriteSize,Tile.TILE_FLOOR);
					if(pixelAtual == 0xff0f00ff) {
						Game.xP = xx*16;
						Game.yP = yy*16;
						Box box = new Box(xx*SpriteSize,yy*SpriteSize,16,16,0,null);
						Game.entities.add(box);
						BoxMap++;
					}else if(pixelAtual == 0xFF000000) {
						//Tile = ========================================================================= =
						if(xx+1 >= 0 && pixels[(xx+1)+((yy) * map.getWidth())] != 0xFF000000 &&
						   xx-1 >= 0 && pixels[(xx-1)+((yy) * map.getWidth())] != 0xFF000000 &&
						   yy+1 >= 0 && pixels[(xx)+((yy+1) * map.getWidth())] != 0xFF000000 &&
						   yy-1 >= 0 && pixels[(xx)+((yy-1) * map.getWidth())] != 0xFF000000) {
							//Defaul
							tiles[xx + (yy * WIDTH)] = new WallTile(xx*SpriteSize,yy*SpriteSize,Tile.TILE_WALLDefaul);
						}else if(xx-1 >= 0 && pixels[(xx-1)+((yy) * map.getWidth())] != 0xFF000000 &&
						   xx+1 >= 0 && pixels[(xx+1)+((yy) * map.getWidth())] != 0xFF000000 &&
						   yy-1 >= 0 && pixels[(xx)+((yy-1) * map.getWidth())] != 0xFF000000 &&
						   yy+1 >= 0 && pixels[(xx)+((yy+1) * map.getWidth())] == 0xFF000000) {
							tiles[xx + (yy * WIDTH)] = new WallTile(xx*SpriteSize,yy*SpriteSize,Tile.TILE_Wall1);
						}else if(xx-1 >= 0 && pixels[(xx-1)+((yy) * map.getWidth())] != 0xFF000000 &&
								xx+1 >= 0 && pixels[(xx+1)+((yy) * map.getWidth())] != 0xFF000000 && 
								yy+1 >= 0 && pixels[(xx)+((yy+1) * map.getWidth())] == 0xFF000000 &&
								yy-1 >= 0 && pixels[(xx)+((yy-1) * map.getWidth())] == 0xFF000000) {
							tiles[xx + (yy * WIDTH)] = new WallTile(xx*SpriteSize,yy*SpriteSize,Tile.TILE_Wall2);
						}else if(xx-1 >= 0 && pixels[(xx-1)+((yy) * map.getWidth())] != 0xFF000000 &&
								xx+1 >= 0 && pixels[(xx+1)+((yy) * map.getWidth())] != 0xFF000000 && 
								yy-1 >= 0 && pixels[(xx)+((yy-1) * map.getWidth())] == 0xFF000000 &&
								yy+1 >= 0 && pixels[(xx)+((yy+1) * map.getWidth())] != 0xFF000000) {
							tiles[xx + (yy * WIDTH)] = new WallTile(xx*SpriteSize,yy*SpriteSize,Tile.TILE_Wall3);
						}else if(xx-1 >= 0 && pixels[(xx-1)+((yy) * map.getWidth())] == 0xFF000000 &&
								 xx+1 >= 0 && pixels[(xx+1)+((yy) * map.getWidth())] == 0xFF000000 &&
								 yy+1 >= 0 && pixels[(xx)+((yy+1) * map.getWidth())] != 0xFF000000 &&
								 yy-1 >= 0 && pixels[(xx)+((yy-1) * map.getWidth())] != 0xFF000000) {
							tiles[xx + (yy * WIDTH)] = new WallTile(xx*SpriteSize,yy*SpriteSize,Tile.TILE_Wall5);
						}else if(xx+1 >= 0 && pixels[(xx+1)+((yy) * map.getWidth())] == 0xFF000000 &&
								 xx-1 >= 0 && pixels[(xx-1)+((yy) * map.getWidth())] != 0xFF000000 &&
								 yy-1 >= 0 && pixels[(xx)+((yy-1) * map.getWidth())] != 0xFF000000 &&
								 xx+1 >= 0 && pixels[(xx)+((yy+1) * map.getWidth())] == 0xFF000000) {
							tiles[xx + (yy * WIDTH)] = new WallTile(xx*SpriteSize,yy*SpriteSize,Tile.TILE_Wall7);
						}else if(xx-1 >= 0 && pixels[(xx-1)+((yy) * map.getWidth())] == 0xFF000000 &&
								 xx+1 >= 0 && pixels[(xx+1)+((yy) * map.getWidth())] != 0xFF000000 &&
								 yy+1 >= 0 && pixels[(xx)+((yy+1) * map.getWidth())] == 0xFF000000 &&
								 yy-1 >= 0 && pixels[(xx)+((yy-1) * map.getWidth())] != 0xFF000000) {
							tiles[xx + (yy * WIDTH)] = new WallTile(xx*SpriteSize,yy*SpriteSize,Tile.TILE_Wall8);
						}else if(xx+1 >= 0 && pixels[(xx+1)+((yy) * map.getWidth())] == 0xFF000000 &&
								 xx-1 >= 0 && pixels[(xx-1)+((yy) * map.getWidth())] != 0xFF000000 &&
								 yy-1 >= 0 && pixels[(xx)+((yy-1) * map.getWidth())] == 0xFF000000 &&
								 xx+1 >= 0 && pixels[(xx)+((yy+1) * map.getWidth())] != 0xFF000000) {
							tiles[xx + (yy * WIDTH)] = new WallTile(xx*SpriteSize,yy*SpriteSize,Tile.TILE_Wall9);
						}else if(xx+1 >= 0 && pixels[(xx+1)+((yy) * map.getWidth())] != 0xFF000000 &&
								 xx-1 >= 0 && pixels[(xx-1)+((yy) * map.getWidth())] == 0xFF000000 &&
								 yy-1 >= 0 && pixels[(xx)+((yy-1) * map.getWidth())] == 0xFF000000 &&
								 xx+1 >= 0 && pixels[(xx)+((yy+1) * map.getWidth())] != 0xFF000000) {
							tiles[xx + (yy * WIDTH)] = new WallTile(xx*SpriteSize,yy*SpriteSize,Tile.TILE_Wall10);
						}else if(xx+1 >= 0 && pixels[(xx+1)+((yy) * map.getWidth())] != 0xFF000000 &&
								 xx-1 >= 0 && pixels[(xx-1)+((yy) * map.getWidth())] == 0xFF000000 &&
								 yy-1 >= 0 && pixels[(xx)+((yy-1) * map.getWidth())] != 0xFF000000 &&
								 xx+1 >= 0 && pixels[(xx)+((yy+1) * map.getWidth())] != 0xFF000000) {
							tiles[xx + (yy * WIDTH)] = new WallTile(xx*SpriteSize,yy*SpriteSize,Tile.TILE_Wall6);
						}else if(xx+1 >= 0 && pixels[(xx+1)+((yy) * map.getWidth())] == 0xFF000000 &&
								 xx-1 >= 0 && pixels[(xx-1)+((yy) * map.getWidth())] != 0xFF000000 &&
								 yy-1 >= 0 && pixels[(xx)+((yy-1) * map.getWidth())] != 0xFF000000 &&
								 xx+1 >= 0 && pixels[(xx)+((yy+1) * map.getWidth())] != 0xFF000000) {
							tiles[xx + (yy * WIDTH)] = new WallTile(xx*SpriteSize,yy*SpriteSize,Tile.TILE_Wall4);
						}else if(xx+1 >= 0 && pixels[(xx+1)+((yy) * map.getWidth())] == 0xFF000000 &&
								 xx-1 >= 0 && pixels[(xx-1)+((yy) * map.getWidth())] == 0xFF000000 &&
								 yy-1 >= 0 && pixels[(xx)+((yy-1) * map.getWidth())] == 0xFF000000 &&
								 xx+1 >= 0 && pixels[(xx)+((yy+1) * map.getWidth())] == 0xFF000000) {
							tiles[xx + (yy * WIDTH)] = new WallTile(xx*SpriteSize,yy*SpriteSize,Tile.TILE_Wall11);
						}else if(xx+1 >= 0 && pixels[(xx+1)+((yy) * map.getWidth())] != 0xFF000000 &&
								 xx-1 >= 0 && pixels[(xx-1)+((yy) * map.getWidth())] == 0xFF000000 &&
								 yy-1 >= 0 && pixels[(xx)+((yy-1) * map.getWidth())] == 0xFF000000 &&
								 xx+1 >= 0 && pixels[(xx)+((yy+1) * map.getWidth())] == 0xFF000000) {
							tiles[xx + (yy * WIDTH)] = new WallTile(xx*SpriteSize,yy*SpriteSize,Tile.TILE_Wall12);
						}else if(xx+1 >= 0 && pixels[(xx+1)+((yy) * map.getWidth())] == 0xFF000000 &&
								 xx-1 >= 0 && pixels[(xx-1)+((yy) * map.getWidth())] == 0xFF000000 &&
								 yy-1 >= 0 && pixels[(xx)+((yy-1) * map.getWidth())] != 0xFF000000 &&
								 xx+1 >= 0 && pixels[(xx)+((yy+1) * map.getWidth())] == 0xFF000000) {
							tiles[xx + (yy * WIDTH)] = new WallTile(xx*SpriteSize,yy*SpriteSize,Tile.TILE_Wall14);
						}else if(xx+1 >= 0 && pixels[(xx+1)+((yy) * map.getWidth())] == 0xFF000000 &&
								 xx-1 >= 0 && pixels[(xx-1)+((yy) * map.getWidth())] == 0xFF000000 &&
								 yy-1 >= 0 && pixels[(xx)+((yy-1) * map.getWidth())] == 0xFF000000 &&
								 xx+1 >= 0 && pixels[(xx)+((yy+1) * map.getWidth())] != 0xFF000000) {
							tiles[xx + (yy * WIDTH)] = new WallTile(xx*SpriteSize,yy*SpriteSize,Tile.TILE_Wall15);
						}else if(xx+1 >= 0 && pixels[(xx+1)+((yy) * map.getWidth())] == 0xFF000000 &&
								 xx-1 >= 0 && pixels[(xx-1)+((yy) * map.getWidth())] != 0xFF000000 &&
								 yy-1 >= 0 && pixels[(xx)+((yy-1) * map.getWidth())] == 0xFF000000 &&
								 xx+1 >= 0 && pixels[(xx)+((yy+1) * map.getWidth())] == 0xFF000000) {
							tiles[xx + (yy * WIDTH)] = new WallTile(xx*SpriteSize,yy*SpriteSize,Tile.TILE_Wall13);
						}
						// = ============================================================================= =
					}else if(pixelAtual == 0xFFffffff) {
						tiles[xx + (yy * WIDTH)] = new FloorTile(xx*SpriteSize,yy*SpriteSize,Tile.TILE_FLOOR);
						//SystemBox
						Box box = new Box(xx*SpriteSize,yy*SpriteSize,16,16,0,null);
						Game.entities.add(box);
						BoxMap++;
						int coinRand = Entity.rand.nextInt(1000);
						if(coinRand > 900 && coinRand < 903) {
							Diamond diamond = new Diamond(xx*16,yy*16,16,16,0,null);
							Game.entities.add(diamond);
						}else if(coinRand <= 50) {
							Coin coin = new Coin(xx*16,yy*16,16,16,0,null);
							if(Game.CUR_LEVEL != 0) {
							Game.entities.add(coin);
							}
						}
					}else if(pixelAtual == 0xFF4f493a) {
						QuitGame quit = new QuitGame(xx*16,yy*16,16,16,0,null);
						Game.entities.add(quit);
					}else if(pixelAtual == 0xffff0000) {
						//Enemy
						Enemy enemy = new Enemy(xx*SpriteSize,yy*SpriteSize,16,16,0,null);
						Game.entities.add(enemy);;
						//SystemBox
						Box box = new Box(xx*SpriteSize,yy*SpriteSize,16,16,0,null);
						Game.entities.add(box);
						BoxMap++;
					}else if(pixelAtual == 0xffff00f8) {
						//Enemy²
						Enemy enemy = new Enemy(xx*SpriteSize,yy*SpriteSize,16,16,0,null);
						Game.entities.add(enemy);
						tiles[xx + (yy * WIDTH)] = new TileEnemy(xx*SpriteSize,yy*SpriteSize,null);
					}else if(pixelAtual == 0xff323c39) {
						//TileBlack
						tiles[xx + (yy * WIDTH)] = new FloorTile(xx*SpriteSize,yy*SpriteSize,Tile.TILE_BLACK);
					}else if(pixelAtual == 0xff353535) {
						//TileNull
						tiles[xx + (yy * WIDTH)] = new FloorTile(xx*SpriteSize,yy*SpriteSize,Tile.TILE_Null);
					}else if(pixelAtual == 0xff8000ff) {
						//ShotterArrow
						if((xx+1 >= 0 && pixels[(xx+1)+((yy) * map.getWidth())] == 0xFFffffff &&
							xx-1 >= 0 && pixels[(xx-1)+((yy) * map.getWidth())] == 0xFFffffff)) {
							TA.Dir = 2;
						}else if((xx+1 >= 0 && pixels[(xx+1)+((yy) * map.getWidth())] == 0xFFffffff &&
								xx-1 >= 0 && pixels[(xx-1)+((yy) * map.getWidth())] != 0xFFffffff)) {
							TA.Dir = 1;
						}else if((xx+1 >= 0 && pixels[(xx+1)+((yy) * map.getWidth())] != 0xFFffffff &&
								xx-1 >= 0 && pixels[(xx-1)+((yy) * map.getWidth())] == 0xFFffffff)) {
							TA.Dir = 0;
						}
						tiles[xx + (yy * WIDTH)] = new TileArrow(xx*SpriteSize,yy*SpriteSize,null);
						GeradorArrow Geradorshotter = new GeradorArrow(xx*16,yy*16,1,1,0,null);
						Game.entities.add(Geradorshotter);
					}else if(pixelAtual == 0xffff8700) {
						//Portal1
						tiles[xx + (yy * WIDTH)] = new TilePortal(xx*SpriteSize,yy*SpriteSize,null);
						Teleporter1 teleport1 = new Teleporter1(xx*16,yy*16,4,4,0,null);
						Game.entities.add(teleport1);
					}else if(pixelAtual == 0xff8d4a00) {
						//Portal2
						tiles[xx + (yy * WIDTH)] = new TilePortal(xx*SpriteSize,yy*SpriteSize,null);
						Teleporter2 teleport2 = new Teleporter2(xx*16,yy*16,4,4,0,null);
						Game.entities.add(teleport2);
					}else if(pixelAtual == 0xff00d1e5) {
						//Thron
						int randed = Entity.rand.nextInt(100);
						if(randed <= 30) {
							Thorns catchau = new Thorns(xx*16,yy*16,16,16,0,null);
							Game.entities.add(catchau);
						}
						//SystemBox
						Box box = new Box(xx*SpriteSize,yy*SpriteSize,16,16,0,null);
						Game.entities.add(box);
						BoxMap++;
					}else if(pixelAtual == 0xff951616) {
						BoxCollider bx = new BoxCollider(xx*16,yy*16,16,16,0,null);
						Game.entities.add(bx);
						bc.BoxLiving = true;
					}else if(pixelAtual == 0xffff009e) {
						//Door
						DoorBox db = new DoorBox(xx*16,yy*16,16,16,0,null);
						Game.entities.add(db);
						DoorBox.DoorLiving = true;
						BoxMap++;
					}else if(pixelAtual == 0xffff5a94) {
						//Key
						Key key = new Key(xx*16,yy*16,16,16,0,null);
						Game.entities.add(key);
						//SystemBox
						Box box = new Box(xx*SpriteSize,yy*SpriteSize,16,16,0,null);
						Game.entities.add(box);
						BoxMap++;
					}else if(pixelAtual == 0xffc3ff00) {
						//LazerShoter
						LazerShot lz = new LazerShot(xx*16,yy*16,16,16,0,null);
						Game.entities.add(lz);
					}else if(pixelAtual == 0xff0fff00) {
						//Jumping
						Jumping cw = new Jumping(xx*16,yy*16,16,16,0,null);
						Game.entities.add(cw);
						//BoxSystem
						Box box = new Box(xx*SpriteSize,yy*SpriteSize,16,16,0,null);
						Game.entities.add(box);
						BoxMap++;
					}
					
				}
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public void tick() {
	}
	
	public static void generateParticle(int amount, int x,int y) {
		for(int i = 0; i < amount; i++) {
			Game.entities.add(new Particle(x,y,1,1,1,null));
		}
	}
	
    public static boolean isFree(int xnext,int ynext, int width, int height){
		
		int x1 = xnext / TILE_SIZE;
		int y1 = ynext / TILE_SIZE;
		
		int x2 = (xnext + width - 1) / TILE_SIZE;
		int y2 = ynext / TILE_SIZE;
		
		int x3 = xnext / TILE_SIZE;
		int y3 = (ynext + height - 1) / TILE_SIZE;
		
		int x4 = (xnext + width - 1) / TILE_SIZE;
		int y4 = (ynext + height - 1) / TILE_SIZE;
		
		return !(
				//Colisores
				(tiles[x1 + (y1*World.WIDTH)] instanceof WallTile) ||
				(tiles[x2 + (y2*World.WIDTH)] instanceof WallTile) ||
				(tiles[x3 + (y3*World.WIDTH)] instanceof WallTile) ||
				(tiles[x4 + (y4*World.WIDTH)] instanceof WallTile) ||
				(tiles[x1 + (y1*World.WIDTH)] instanceof BoxTile) ||
				(tiles[x2 + (y2*World.WIDTH)] instanceof BoxTile) ||
				(tiles[x3 + (y3*World.WIDTH)] instanceof BoxTile) ||
				(tiles[x4 + (y4*World.WIDTH)] instanceof BoxTile));
	}
    public static boolean isFreePlayer(int xnext,int ynext, int width, int height){
		
		int x1 = xnext / TILE_SIZE;
		int y1 = ynext / TILE_SIZE;
		
		int x2 = (xnext + width - 1) / TILE_SIZE;
		int y2 = ynext / TILE_SIZE;
		
		int x3 = xnext / TILE_SIZE;
		int y3 = (ynext + height - 1) / TILE_SIZE;
		
		int x4 = (xnext + width - 1) / TILE_SIZE;
		int y4 = (ynext + height - 1) / TILE_SIZE;
		
		return !(
				//Colisores
				(tiles[x1 + (y1*World.WIDTH)] instanceof WallTile) ||
				(tiles[x2 + (y2*World.WIDTH)] instanceof WallTile) ||
				(tiles[x3 + (y3*World.WIDTH)] instanceof WallTile) ||
				(tiles[x4 + (y4*World.WIDTH)] instanceof WallTile) ||
				(tiles[x1 + (y1*World.WIDTH)] instanceof TileArrow) ||
				(tiles[x2 + (y2*World.WIDTH)] instanceof TileArrow) ||
				(tiles[x3 + (y3*World.WIDTH)] instanceof TileArrow) ||
				(tiles[x4 + (y4*World.WIDTH)] instanceof TileArrow) ||
				(tiles[x1 + (y1*World.WIDTH)] instanceof BoxTile) ||
				(tiles[x2 + (y2*World.WIDTH)] instanceof BoxTile) ||
				(tiles[x3 + (y3*World.WIDTH)] instanceof BoxTile) ||
				(tiles[x4 + (y4*World.WIDTH)] instanceof BoxTile));
	}
	
	public static void restartGame(String level){
		Game.spritesheet = new Spritesheet("/spritesheet.png");
		Game.entities = new ArrayList<Entity>();
		//LoadWorld
		if(Game.CUR_LEVEL == 1 || Game.CUR_LEVEL == 2 || Game.CUR_LEVEL == 3) {
		Game.world = new World("/" + level);
		}else if(!Game.GameOver){
			Game.world = new World("/" + level);
		}else if(Game.GameOver){
			Game.CUR_LEVEL = Game.CUR_LEVEL - 1;
			Game.world = new World("/level"+Game.CUR_LEVEL+".png");
		}
		Game.StartLevel = false;
		//====
		Game.player = new Player(Game.xP,Game.yP,16,16,0,null);
		Game.entities.add(Game.player);
		Game.playerDead = false;
		Player.boxCurrent = 0;
		Game.Darkness = 0;
		Game.restart = false;
		Game.GameOver = false;
		Game.MenuGameOver = false;
		//Teleporter
		Teleporter1.Actived1 = true;
		Teleporter1.Activing1 = true;
		Teleporter2.Actived2 = false;
		Teleporter2.Activing2 = false;
		//Door
		DoorBox.Actived = false;
		return;
	}
	
	public void render(Graphics g){
		int xstart = Camera.x >> 4;
		int ystart = Camera.y >> 4;
		
		int xfinal = xstart + (Game.WIDTH >> 4);
		int yfinal = ystart + (Game.HEIGHT >> 4);
		
		for(int xx = xstart; xx <= xfinal; xx++) {
			for(int yy = ystart; yy <= yfinal; yy++) {
				if(xx < 0 || yy < 0 || xx >= WIDTH || yy >= HEIGHT)
					continue;
				Tile tile = tiles[xx + (yy*WIDTH)];
				tile.render(g);
			}
		}
	}
	
}
