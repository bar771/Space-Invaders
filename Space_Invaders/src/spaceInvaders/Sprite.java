package spaceInvaders;

public class Sprite {
	
	public static Sprite enemyAnimO = new Sprite(0, 0, 28, 21, SpriteSheet.enemy);
	public static Sprite enemyAnimT = new Sprite(1, 0, 28, 21, SpriteSheet.enemy);
	
	public static Sprite playerAnimO = new Sprite(0, 0, 28, 21, SpriteSheet.spaceship);
	public static Sprite playerAnimT = new Sprite(1, 0, 28, 21, SpriteSheet.spaceship);
	
	public static Sprite bulletShot = new Sprite(0, 0, 10, 14, SpriteSheet.bShot);
	
	public static Sprite patit = new Sprite(0, 0, 10, 10, SpriteSheet.particles);
	public static Sprite patitSheleg = new Sprite(1, 0, 10, 10, SpriteSheet.particles);
	
	
	public int width, height;
	public int[] pixels;
	private int x, y;
	
	SpriteSheet sheet;
	
	public Sprite(int x, int y, int width, int height, SpriteSheet sheet) { 
		this.width = width;
		this.height = height;
		this.x = x * width;
		this.y = y * height;
		this.sheet = sheet;
		pixels = new int[width * height];
		
		extract();
	}
	
	private void extract() { 
		for (int x=0; x<width; x++) { 
			for (int y=0; y<height; y++) {
				pixels[x + y * width] = sheet.pixels[(x + this.x) + (y + this.y) * sheet.width];
			}
		}
	}
	
	public void getSprite(int xp, int yp) { 
		xp *= width;
		yp *= height;
		for (int x=0; x<width; x++) { 
			for (int y=0; y<height; y++) {
				pixels[x + y * width] = sheet.pixels[(x + xp) + (y + yp) * sheet.width];
			}
		}
	}
	
	
}

