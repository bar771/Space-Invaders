package spaceInvaders;

public class Particle {
	
	public int x, y;
	public Sprite sprite;
	public int life = 330;
	
	public Particle(int x, int y, Sprite sprite) { 
		this.x = x;
		this.y = y;
		this.sprite = sprite;
	}
	
	public boolean tick() { 
		if (life <= 0) {
			return true;
		}
		life --;
		
		return false;
	}
	
	public void draw(Bitmap bitmap) { 
		bitmap.drawSprite(sprite, x, y);
	}
	
	
}
