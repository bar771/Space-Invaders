package spaceInvaders;

public class Shot extends Particle {
	
	public Shot(int x, int y, Sprite sprite) {
		super(x, y, sprite);
	}
	
	public boolean tick() { 
		super.tick();
		
		y --;
		
		return false;
	}
	
	public void draw(Bitmap bitmap) { 
		if (life > 0)
			super.draw(bitmap);
	}
	
	
}
