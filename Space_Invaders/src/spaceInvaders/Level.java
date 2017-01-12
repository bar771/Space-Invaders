package spaceInvaders;


public class Level {
	
	public static java.util.ArrayList<Mob> mobs = Game.enemies;
	protected java.util.Random rnd = new java.util.Random();
	
	public int width, height;
	
	public Level(int width, int height) {
		this.width = width;
		this.height = height;
		
		generateLevel();
	}
	
	public void generateLevel() {
		int xl = 150;
		int yl = 20;
		
		for (int i=0; i<10; i++) {
			int x = (rnd.nextInt(width) % 10) * xl / 2;
			int y = (rnd.nextInt(height) % 10) * yl;
			
			if (x > 0 && x <= width && y > 0 && y >= height && isFree(x, y)) {
				mobs.add(new Enemy(x, y, Game.enemyAnim[0], Game.enemyAnim));
			}else {
				do {
					x = (rnd.nextInt(width) % 10) * xl / 2;
					y = (rnd.nextInt(height) % 10) * yl;
				} while(x <= 0 && x > width && y <= 0 && y > height);
				if (isFree(x, y)) 
					mobs.add(new Enemy(x, y, Game.enemyAnim[0], Game.enemyAnim));
			}
			
			
		}
		
	}
	
	public boolean isFree(int x, int y) { 
		for (int i=0; i<mobs.size(); i++) {
			Mob m = mobs.get(i);
			if (x == m.x && y == m.y) { 
				return false;
			}
		}
		return true;
	}
	
	int i = 0;
	int s = 100; // spawning speed.
	public boolean tick(boolean right, boolean left, boolean shooting, int xMouse) {
		
		i ++;
		if (i >= 7200) i = 0;
		if (i % s == 0) generateLevel(); // 
		
		for (int i=0; i<mobs.size(); i++) { 
			Mob m = mobs.get(i);
			if (m instanceof Player) {
				((Player) m).tick(right, left, shooting, xMouse);
			}
			
			if (m.tick() || m.life <= 0)
				mobs.remove(i);
		}
		
		return false;
	}
	public void draw(Bitmap bitmap) {
		
		for (int i=0; i<mobs.size(); i++) {			
			mobs.get(i).draw(bitmap);
		}
		
	}
	
}
