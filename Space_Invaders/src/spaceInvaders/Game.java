package spaceInvaders;

import java.awt.Canvas;
import java.awt.Graphics;
import java.awt.Dimension;
import java.awt.BorderLayout;
import java.awt.Point;
import java.awt.image.BufferedImage;
import java.awt.image.BufferStrategy;
import java.awt.image.DataBufferInt;
import java.net.InetAddress;
import java.util.ArrayList;

import javax.swing.JFrame;

// The Cherno Project : 2D Game Programming
// https://www.youtube.com/watch?v=GFYT7Lqt1h8&list=PLlrATfBNZ98eOOCk2fOFg7Qg5yoQfFAdf
public class Game extends Canvas implements Runnable {
	
	public static int wWindow = 520, hWindow = wWindow * 9 / 12;
	private boolean isRunning = false;
	public BufferedImage image = new BufferedImage(wWindow, hWindow, BufferedImage.TYPE_INT_RGB);
	public int[] pixels = ((DataBufferInt) image.getRaster().getDataBuffer()).getData();
	
	public static ArrayList<Shot> shots = new ArrayList<Shot>();
	public static ArrayList<Missile> missiles = new ArrayList<Missile>();
	public static ArrayList<Mob> enemies = new ArrayList<Mob>();
	public static ArrayList<Particle> particles = new ArrayList<Particle>();
	public static ArrayList<Player> netPlayers = new ArrayList<Player>();
	
	static Bitmap screen;
	public Control control;
	
	static Sprite[] playerAnim = { Sprite.playerAnimO, Sprite.playerAnimT };
	static Sprite[] enemyAnim = { Sprite.enemyAnimO, Sprite.enemyAnimT };
	
	
	public Level level;
	
	public Game() {
		control = new Control();
		screen = new Bitmap(wWindow, hWindow);
		
		enemies.add(new Player((wWindow / 2) - (28/2), hWindow - 46, Sprite.playerAnimO, playerAnim)
		);
		level = new Level(screen.width, screen.height);
		
		JFrame f = new JFrame();
		Dimension size = new Dimension(wWindow, hWindow);
		setPreferredSize(size);
		setMaximumSize(size);
		setMinimumSize(size);
		
		f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		f.setLayout(new BorderLayout());
		f.add(this, BorderLayout.CENTER);
		f.pack();
		f.setVisible(true);
		f.setResizable(false);
		f.setLocationRelativeTo(null);
		addKeyListener(control);
		addMouseMotionListener(control);
		addMouseListener(control);
		
		start();
	}
	
	public int getColour(int colour) { 
		int r = colour >> 16;
		int g = colour >> 8;
		int b = colour;
		
		return (r & 16) << 16 | (g & 8) << 8 | (b);
	}
	
	public void draw(Bitmap bitmap) {
		
		level.draw(bitmap);
		
		for (int i=0; i<shots.size(); i++) { 
				shots.get(i).draw(bitmap);
		}
		
		for (int i=0; i<missiles.size(); i++) { 
			missiles.get(i).draw(bitmap);
		}
		
		for (int i=0; i<particles.size(); i++) {
			particles.get(i).draw(bitmap);
		}
		
	}
	
	int i =0;
	public void tick() {
		
		boolean left = control.getKey(java.awt.event.KeyEvent.VK_A);
		boolean right = control.getKey(java.awt.event.KeyEvent.VK_D);
		boolean isShooting = control.mPressed[1];
		
		level.tick(right, left, isShooting, control.xMouse);
		
		for (int i=0; i<shots.size(); i++) {
			Shot e = shots.get(i);
			if (e.tick() || e.life <= 0) {
				shots.remove(i);
			}
		}
		
		for (int i=0; i<missiles.size(); i++) {
			Missile e = missiles.get(i);
			if (e.tick() || e.life <= 0) {
				missiles.remove(i);
			}
		}
		
		for (int i=0; i<particles.size(); i++) {
			Particle p = particles.get(i);
			if (p.tick() || p.life <= 0) { 
				particles.remove(i);
			}
		}
		
		
	}
	
	public static void main(String[] args) { 
		Game game = new Game();
	}
	
	
	public synchronized void start() {
		isRunning = true;
		Thread t = new Thread(this);
		t.start();
	}
	
	public synchronized void stop() { 
		isRunning = false;
	}
	
	
	public void run() {
		long lastTime = System.nanoTime();
		long timer = System.currentTimeMillis();
		final double ns = 1000000000.0 / 60.0;
		double delta = 0;
		int frames = 0;
		int updates = 0;
		while (isRunning) {
			long now = System.nanoTime();
			delta += (now - lastTime) / ns;
			lastTime = now;
			while (delta >= 1) { 
				tick();
				updates ++;
				delta --;
			}
			render();
			frames ++;
			
			if (System.currentTimeMillis() - timer > 1000) {
				timer += 1000;
				System.out.println(frames + " FPS " + " " + updates +" UPS");
				updates = 0;
				frames = 0;
			}
			
		}
		stop();
		
	}
	
	public void render() { 
		
		BufferStrategy bs = getBufferStrategy();
		if (bs == null) { 
			createBufferStrategy(3);					
			requestFocus();
			return;
		}
		
		screen.clear();
		draw(screen);
		for (int i=0; i<wWindow*hWindow; i++)  
			pixels[i] = screen.pixels[i];
		
		
		Graphics g = bs.getDrawGraphics();
		g.drawImage(image, 0, 0, wWindow + 10, hWindow + 10, null);
		g.dispose();
		bs.show();
		
	}
	
	
	public boolean isCollid(Point p, java.awt.Rectangle r) {
		int xP = p.x, yP = p.y;
		int xR = r.x, yR = r.y, wR = r.width, hR = r.height;
		if (xP > xR && xP <= wR && yP > yR && yP <= hR) { 
			return true;
		}
		return false;
	}
	
	public int yFollow(int xBall, int yBall, int xPaddle, int yPaddle) {
		
		int xDiff = xBall - xPaddle;
		int yDiff = yBall - yPaddle;
		
		int m = (yDiff - yBall) / (xDiff - xBall);
		
		return (m * (xDiff - xBall) + yBall);
	}
	
	public float Lerp(float from, float to, float by) { 
		return from + (from - to) * by;
	}
	
}
