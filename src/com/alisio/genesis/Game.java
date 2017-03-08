package com.alisio.genesis;

import java.awt.*;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.image.*;
import javax.swing.JFrame;
import com.alisio.genesis.console.Console;
import com.alisio.genesis.debug.Debug;
import com.alisio.genesis.entity.mob.Player;
import com.alisio.genesis.graphics.*;
import com.alisio.genesis.input.*;
import com.alisio.genesis.level.*;
import com.alisio.genesis.resources.Resources;

public class Game extends Canvas implements Runnable{
	private static final long serialVersionUID = 1L;
	private static int width = 300;
	private static int height = width/ 16*9;
	private static int scale = 3;
	public static String Title = "Genesis";
	public static double updates = 60.0;
	
	private Thread thread;
	private JFrame frame;
	private KeyBoard key;
	private Level level;
	private Player player;
	private Console console;
	private Debug debug;
	private boolean running = false;
	
	private Screen screen;
	private BufferedImage image = new BufferedImage(width,height,BufferedImage.TYPE_INT_RGB);
	//private BufferedImage overlay = new BufferedImage(width,height,BufferedImage.TYPE_INT_ARGB);
	private int[] pixels = ((DataBufferInt)image.getRaster().getDataBuffer()).getData();
	
	public Game(){
		Init();
	}
	
	public synchronized void start(){
		running = true;
		thread = new Thread(this,"Display");
		thread.start();
	}
	
	public synchronized void stop(){
		running = false;
		try {
			thread.join();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

	public void run() {
		long lastTime = System.nanoTime();
		long timer = System.currentTimeMillis();
		final double ns = 1000000000.0 / updates;
		double delta = 0;
		int frames = 0;
		int updates = 0;
		requestFocus();
		while (running) {
			long now = System.nanoTime();
			delta+= (now-lastTime) / ns;
			lastTime = now;
			while (delta >= 1){
				update();
				updates++;
				delta--;
			}
			render();
			frames++;
			
			if(System.currentTimeMillis() - timer > 1000){
				timer += 1000;
				System.out.println(updates + " ups, " + frames + " fps");
				frame.setTitle(Title + "  |  " + updates + " ups, " + frames + " fps");
				updates = 0;
				frames = 0;
			}
		}
		stop();
	}
	
	private void render() {
		BufferStrategy bs = getBufferStrategy();
		if(bs == null) {
			createBufferStrategy(3);
			return;
		}
		
		screen.clear();
		double xScroll = player.x - screen.width / 2;
		double yScroll = player.y - screen.height / 2;
		level.render((int)xScroll, (int)yScroll, screen);		
		player.render(screen);
		
		//Sprite sprite = new Sprite(80,80,0x10ff00ff);
		//screen.renderSprite(0, 0, sprite, true);
		
		for(int i = 0; i < screen.pixels.length;i++){
			pixels[i] = screen.pixels[i];
		}
		
		/*for (int x = 0; x < 100; x++) {
			for (int y = 0; y < 100; y++) {
				overlay.setRGB(x, y, 0x64ff00ff);
			}
		}	*/

		Graphics g = bs.getDrawGraphics();
		g.drawImage(image, 0,0,getWidth(),getHeight(),null);
		//g.drawImage(overlay, 0,0,getWidth(),getHeight(),null);
		g.setColor(Color.WHITE);
		g.setFont(new Font("Verdena",0,16));
		//g.drawString("X: " + player.x + ", Y: " + player.y,10,20);
		
		if(console.visible){
			console.message = "X: " + player.x + ", Y: " + player.y;
			g.setFont(console.font);
			g.drawString(console.message, console.x, console.y);
		}
		
		if(debug.visible){
			g.setColor(new Color(100,100,100,100));
			g.fillRect(0, 0, getWindowWidth(), getWindowHeight());
			for(int i = 0; i < Debug.data.size();i++){	
				g.setColor(debug.color);
				g.drawString(Debug.data.get(i).text, Debug.data.get(i).x, Debug.data.get(i).y);
			}
		}
		
		g.dispose();
		bs.show();
	}

	private void update() {
		key.update();
		player.update();
		level.update();
		
		if(key.space) NewLevel();		
		if(key.console) console.visible = !console.visible;
		if(key.debug) debug.visible = !debug.visible;
		if(debug.visible) debug.update(player, level);
	}
	
	private void NewLevel() {
		TileLocation location;
		if(level.name == "island2"){
 			level = new BasicLevel("/maps/test3.png", "test3");
			location = new TileLocation(12,8);
		}else{
			level = new BasicLevel("/maps/island2.png", "island2");
			location = new TileLocation(20,12);
		}
		player = new Player(location.getX(),location.getY(),key);
		player.init(level);
	}
	
	private void Init() {
		Dimension size = new Dimension(width * scale, height * scale);
		setPreferredSize(size);
		
		new Resources();
		screen = new Screen(width,height);		
		frame = new JFrame();
		key = new KeyBoard();
		level = new BasicLevel("/maps/island2.png", "island2");
		
		TileLocation location = new TileLocation(20,12);
		player = new Player(location.getX(),location.getY(),key);
		player.init(level);
		
		console = new Console(10,400);
		debug = new Debug();
		
		addKeyListener(key);
		
		Mouse mouse = new Mouse();
		addMouseListener(mouse);
		addMouseMotionListener(mouse);
	}
	
	public static int getWindowWidth() {
		return width * scale;
	}
	
	public static int getWindowHeight() {
		return height * scale;
	}

	public static void main(String[] args){
		Game game = new Game();
		game.frame.setResizable(false);
		game.frame.setTitle(Game.Title);
		game.frame.add(game);
		game.frame.pack();
		game.frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		game.frame.setLocationRelativeTo(null);
		game.frame.setVisible(true);
		game.start();
	}
}
