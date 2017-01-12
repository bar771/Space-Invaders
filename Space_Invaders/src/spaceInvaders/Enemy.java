package spaceInvaders;

public class Enemy extends Mob {
	
	java.util.ArrayList<Shot> shots = Game.shots;
	java.util.ArrayList<Missile> missiles = Game.missiles;
	
	public Enemy(int x, int y, Sprite sprite, Sprite[] animSprite) {
		super(x, y, sprite, animSprite);
		
	}
	
	int i = 0;
	int s = 1500;
	public boolean tick() {
		super.tick();
		
		if (life <= 0)
			return true;
		
		for (int i=0; i<shots.size(); i++) { 
			Shot b = shots.get(i);
			
			int xMin = x;
			int xMax = x + sprite.width;
			int yMin = y;
			int yMax = y + sprite.height;
			
			//if (b.x >= x && b.x <= sprite.width && b.y >= y && b.y <= sprite.height) {
			if (b.x <= xMax && b.x >= xMin && b.y <= yMax && b.y >= yMin) {
				shots.remove(i);
				life = 0;
			}
			
		}
		
		if (i > 7200) i = 0;
		if (i % s == 0) 
			missiles.add(new Missile(x + (30/2-10), y, new Sprite(0, 0, 10, 24, SpriteSheet.bMissile)));
		i ++;
		
		
		//life --;
		/*
		if (anim > 7500) anim = 0;
		else anim ++;
		
		if (anim % animSpeed > 10) { 
			sprite = Sprite.enemyAnimO;
		}else {
			sprite = Sprite.enemyAnimT;
		}*/
		return false;
	}
	
	public void draw(Bitmap bitmap) { 
		if (life > 0)
			super.draw(bitmap);
	}
	
	
}
