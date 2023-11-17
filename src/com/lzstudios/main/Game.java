package com.lzstudios.main;

import java.awt.Canvas;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.awt.image.BufferStrategy;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import javax.swing.JFrame;

import com.lzstudios.animations.AnimDead;
import com.lzstudios.entities.Box;
import com.lzstudios.entities.DoorBox;
import com.lzstudios.entities.Enemy;
import com.lzstudios.entities.Entity;
import com.lzstudios.entities.Player;
import com.lzstudios.graficos.Config;
import com.lzstudios.graficos.SetColor;
import com.lzstudios.graficos.Spritesheet;
import com.lzstudios.graficos.UI;
import com.lzstudios.world.TileArrow;
import com.lzstudios.world.TileEnemy;
import com.lzstudios.world.TilePortal;
import com.lzstudios.world.World;

public class Game extends Canvas implements Runnable,KeyListener,MouseListener,MouseMotionListener{

	private static final long serialVersionUID = 1L;
	public static JFrame frame;
	private Thread thread;
	private boolean isRunning = true;
	public static final int WIDTH = 160*3;
	public static final int HEIGHT = 160*3;
	public static final int SCALE = 2;
	
	private BufferedImage image;
	//Projeto alterado!
	
    public static Player player;
    public static boolean playerDead = false;
    public static int xP,yP;
	public static World world;
	public Box boxSystem;
	public static List<Entity> entities;
	public static Spritesheet spritesheet,MenuSprite,ButtonsSprite,AnimSpriteSheet,SpriteTiles,PlayerSpriteSheet;
	public static int CUR_LEVEL = 10, MAX_LEVEL = 17;
	public int timeLevel;

	public UI ui;
	public SetColor setcolor;
	public static int R,G,B;
	public boolean upLight;
	public static int Darkness;
	public static int RandModel = 0;
	
	public static boolean MenuGameOver = false;
	public static boolean GameOver = false;
	public static boolean restart = false;
	public static boolean Menu = false;
	
	public static boolean StartLevel = false;
	
	//Keys
	public static boolean KeyUp = true, KeyDown = true, KeyRight = true, KeyLeft = true;
	//UiKeys
	public static boolean KeyW_ui,KeyA_ui,KeyS_ui,KeyD_ui,KeyUp_ui,KeyDown_ui,KeyLeft_ui,KeyRight_ui;
	
	public Game(){
		addKeyListener(this);
		addMouseListener(this);
		addMouseMotionListener(this);
		setPreferredSize(new Dimension(WIDTH,HEIGHT));
		initFrame();
		image = new BufferedImage(WIDTH,HEIGHT,BufferedImage.TYPE_INT_RGB);
		//Inicializando objetos.
		spritesheet = new Spritesheet("/spritesheet.png");
		MenuSprite = new Spritesheet("/Menu.png");
		ButtonsSprite = new Spritesheet("/Buttons.png");
		AnimSpriteSheet = new Spritesheet("/AnimSpriteSheet.png");
		PlayerSpriteSheet = new Spritesheet("/PlayerSpriteSheet.png");
		//LoadSpriteTiles
		RandModel = Entity.rand.nextInt(4);
		SpriteTiles = new Spritesheet("/SpriteTiles" + RandModel +".png");
		//====
		entities = new ArrayList<Entity>();
		boxSystem = new Box(0,0,0,0,0,null);
		world = new World("/level" + CUR_LEVEL + ".png");
		player = new Player(xP,yP,16,16,0,null);
		entities.add(player);
		setcolor = new SetColor();
		setcolor.SetColor();
		ui = new UI();
		ui.Ui();
		R = Entity.rand.nextInt(100) + 100;
		G = Entity.rand.nextInt(100) + 100;
		B = Entity.rand.nextInt(100) + 100;
	}
	
	public void initFrame(){
		frame = new JFrame("Tot");
		frame.add(this);
		frame.setResizable(false);
		frame.pack();
		frame.setLocationRelativeTo(null);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setVisible(true);
	}
	
	public synchronized void start(){
		thread = new Thread(this);
		isRunning = true;
		thread.start();
	}
	
	public synchronized void stop(){
		isRunning = false;
		try {
			thread.join();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
	
	public static void main(String args[]){
		Game game = new Game();
		game.start();
	}
	
	public void tick(){
		//===========
		if(GameOver) {
			//System
			if(restart) {
				String newWorld = "level" + CUR_LEVEL + ".png";
				World.restartGame(newWorld);
			}
			//Inteface
			Darkness+=5;
			if(Darkness >= 250) {
				Darkness = 250;
			}
			if(Darkness == 250) {
				MenuGameOver = true;
			}
		}
		//NextLevel
		if(world.BoxMap == Player.boxCurrent) {
			entities.remove(player);
			timeLevel++;
			Darkness+=8;
			if(timeLevel == 30) {
			CUR_LEVEL++;
			 if(CUR_LEVEL > MAX_LEVEL) {
					CUR_LEVEL = 1;
			 	 }
		    String newWorld = "level" + CUR_LEVEL + ".png";
			World.restartGame(newWorld);
			timeLevel = 0;
			Darkness = 0;
			}
	 	}
		//Entities
		for(int i = 0; i < entities.size(); i++) {
			Entity e = entities.get(i);
			e.tick();
		}
		world.tick();
		ui.tick();
		setcolor.tick();
		//AnimTiles
		TileEnemy.tick();
		TileArrow.tick();
		TilePortal.tick();
	}
	


	
	public void render(){
		BufferStrategy bs = this.getBufferStrategy();
		if(bs == null){
			this.createBufferStrategy(3);
			return;
		}
		Graphics g = image.getGraphics();
		//Light
		g.setColor(new Color(R,G,B));
		g.fillRect(0, 0,WIDTH,HEIGHT);
		
		/*Renderiza��o do jogo*/
		Graphics2D g2 = (Graphics2D) g;
		//Tiles
		world.render(g);
		//SystemBoxRender
		boxSystem.render(g);
		//Entity
		Collections.sort(entities,Entity.nodeSorter);
		for(int i = 0; i < entities.size(); i++) {
			Entity e = entities.get(i);
			e.render(g);
		}
		//Darkness
		g.setColor(new Color(0,0,0,Darkness));
		g.fillRect(0, 0, WIDTH, HEIGHT);
		//Renderiza��o
		ui.render(g);
		setcolor.render(g);
		g.dispose();
		g = bs.getDrawGraphics();
		g.drawImage(image, 0, 0,WIDTH*2,HEIGHT*2,null);
		bs.show();
	}
	
	public void run() {
		long lastTime = System.nanoTime();
		double amountOfTicks = 60.0;
		double ns = 1000000000 / amountOfTicks;
		double delta = 0;
		int frames = 0;
		double timer = System.currentTimeMillis();
		requestFocus();
		while(isRunning){
			long now = System.nanoTime();
			delta+= (now - lastTime) / ns;
			lastTime = now;
			if(delta >= 1) {
				tick();
				render();
				frames++;
				delta--;
			}
			
			if(System.currentTimeMillis() - timer >= 1000){
				System.out.println("FPS: "+ frames);
				ui.frames = frames;
				frames = 0;
				timer+=1000;
			}
			
		}
		
		stop();
	}
	@Override
	public void keyPressed(KeyEvent e) {
		if(!ui.Store) {
		if(timeLevel == 0) {
		if((e.getKeyCode() == KeyEvent.VK_S || e.getKeyCode() == KeyEvent.VK_DOWN)) {
			KeyDown_ui = true;
			if(KeyDown == true) {
			if(player.isRun == false && playerDead == false) {
				StartLevel = true;
				KeyRight = false;
				KeyUp = false;
				KeyLeft = false;
			player.Down = true;
			}
			}
		}
		if((e.getKeyCode() == KeyEvent.VK_D || e.getKeyCode() == KeyEvent.VK_RIGHT)) {
			KeyRight_ui = true;
			if(KeyRight == true) {
			if(player.isRun == false && playerDead == false) {
				StartLevel = true;
				KeyDown = false;
				KeyUp = false;
				KeyLeft = false;
			player.Right = true;
			}
			}
		}
		if((e.getKeyCode() == KeyEvent.VK_W || e.getKeyCode() == KeyEvent.VK_UP)) {
			KeyUp_ui = true;
			if(KeyUp == true) {
			if(player.isRun == false && playerDead == false) {
				StartLevel = true;
				KeyDown = false;
				KeyRight = false;
				KeyLeft = false;
			player.Up = true;
			}
			}
		}
		if((e.getKeyCode() == KeyEvent.VK_A || e.getKeyCode() == KeyEvent.VK_LEFT)) {
			KeyLeft_ui = true;
			if(KeyLeft == true) {
			if(player.isRun == false && playerDead == false) {
				StartLevel = true;
				KeyDown = false;
				KeyRight = false;
				KeyUp = false;
			player.Left = true;
			}
		}
		}
	}
	}
	}

	@Override
	public void keyReleased(KeyEvent e) {
		if((e.getKeyCode() == KeyEvent.VK_S || e.getKeyCode() == KeyEvent.VK_DOWN)) {
			KeyDown_ui = false;
			if(KeyDown == true) {
				KeyRight = true;
				KeyUp = true;
				KeyLeft = true;
			}
		}
		if((e.getKeyCode() == KeyEvent.VK_D || e.getKeyCode() == KeyEvent.VK_RIGHT)) {
			KeyRight_ui = false;
			if(KeyRight == true) {
				KeyDown = true;
				KeyUp = true;
				KeyLeft = true;
			}
		}
		if((e.getKeyCode() == KeyEvent.VK_W || e.getKeyCode() == KeyEvent.VK_UP)) {
			KeyUp_ui = false;
			if(KeyUp == true) {
				KeyDown = true;
				KeyRight = true;
				KeyLeft = true;
			}
		}
		if((e.getKeyCode() == KeyEvent.VK_A || e.getKeyCode() == KeyEvent.VK_LEFT)) {
			KeyLeft_ui = false;
			if(KeyLeft == true) {
				KeyDown = true;
				KeyRight = true;
				KeyUp = true;
			}
		}
	}

	@Override
	public void keyTyped(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseClicked(MouseEvent e) {
	}

	@Override
	public void mouseEntered(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseExited(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mousePressed(MouseEvent e) {
		ui.pressed = true;
		if(e.getButton() == MouseEvent.BUTTON1) {
			setcolor.isPressed = true;
			}
		Config.isPressed = true;
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		ui.pressed = false;
		Config.isPressed = false;
	}

	@Override
	public void mouseDragged(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseMoved(MouseEvent e) {
		UI.mouseX = e.getX()/2;
		UI.mouseY = e.getY()/2;
		setcolor.xMouse = e.getX()/2;
		setcolor.yMouse = e.getY()/2;
		Config.xMouse = e.getX()/2;
		Config.yMouse = e.getY()/2;
	}

	
}
