package spaceInvaders;

public class Player extends Mob {
	
	private java.util.Random rnd = new java.util.Random();
	
	java.util.ArrayList<Shot> shots = Game.shots;
	java.util.ArrayList<Missile> missiles = Game.missiles;
	java.util.ArrayList<Particle> particles = Game.particles;
	
	public Player(int x, int y, Sprite sprite, Sprite[] animSprite) {
		super(x, y, sprite, animSprite);
	}
	
	
	public boolean tick(boolean right, boolean left, boolean shooting, int xMouse) {
		super.tick();
		generateSparks();
		
		if (left) 
			x --;
		else if (right) 
			x ++;
		
		
		if (shooting) {
			x = sprite.width/2-2; // x + (21/2-2)
			shots.add(new Shot(x, y, Sprite.bulletShot));
		}
		
		if (xMouse >= Game.wWindow) 
			x = Game.wWindow - 20;
		else if (xMouse < 0)
			x = 0;
		else
			x = xMouse;
		
		
		for (int i=0; i<missiles.size(); i++) { 
			Missile b = missiles.get(i);
			
			int xMin = x;
			int xMax = x + sprite.width;
			int yMin = y;
			int yMax = y + sprite.height;
			
			if (b.x <= xMax && b.x >= xMin && b.y <= yMax && b.y >= yMin) {
				missiles.remove(i);
				life = 0;
			}
			
		}
		
		
		return false;
	}
	
	public void draw(Bitmap bitmap) { 
		super.draw(bitmap);
	}
	
	public void generateSparks() { 
		
		for (int i=0; i<5; i++) {
			double xPos = (((x+5) + rnd.nextInt(10)) + i );
			double yPos = (((y+17) + rnd.nextInt(10)) + i * 7);
			
			Particle p = new Particle((int)xPos, (int)yPos, Sprite.patitSheleg);
			p.life = 7;
			particles.add(p);
		}
		
	}

}
