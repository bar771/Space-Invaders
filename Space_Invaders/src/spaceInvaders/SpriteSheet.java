package spaceInvaders;

import java.awt.image.BufferedImage;

import javax.imageio.ImageIO;

public class SpriteSheet {
	
	
	public static SpriteSheet enemy = new SpriteSheet("enemy.png", 56, 21);
	public static SpriteSheet spaceship = new SpriteSheet("player.png", 56, 21);
	
	public static SpriteSheet bShot = new SpriteSheet("bullet.png", 10, 14);
	public static SpriteSheet particles = new SpriteSheet("particles.png", 75, 20);
	public static SpriteSheet bMissile = new SpriteSheet("missile.png", 10, 24);
	
	public int width, height;
	public int[] pixels;
	private String path;
	
	public SpriteSheet(String path, int width, int height) {
		this.path = path;
		this.width = width;
		this.height = height;
		pixels = new int[width * height];
		
		load();
	}
	
	private void load() { 
		try { 
			BufferedImage image = ImageIO.read(Game.class.getResource("/" + path));
			
			pixels = image.getRGB(0, 0, width, height, null, 0, width);
			//for (int i=0; i<pixels.length; i++) pixels[i] = (pixels[i] & 0xff) >> 64;
		} catch(Exception e) { new RuntimeException(e); }
	}
	
	
}
