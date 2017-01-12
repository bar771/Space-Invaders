package spaceInvaders;

public class Missile extends Shot {
	
	private java.util.Random rnd = new java.util.Random();
	private java.util.ArrayList<Particle> particles = Game.particles;
	
	public Missile(int x, int y, Sprite sprite) {
		super(x, y, sprite);
	}
	
	public boolean tick() {
		y ++;
		
		for (int i=0; i<5; i++) {
			double xPos = (((x-5) + rnd.nextInt(5)) + i );
			double yPos = (((y-17) + rnd.nextInt(20)) + i);
			
			Particle p = new Particle((int)xPos, (int)yPos, Sprite.patit);
			p.life = 7;
			particles.add(p);
		}
		
		return false; 
	}
	
	public void draw(Bitmap bitmap) { 
		super.draw(bitmap);
	}

}
