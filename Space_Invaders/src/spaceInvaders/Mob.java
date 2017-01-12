package spaceInvaders;

public class Mob {
	
	protected int x, y;
	protected Sprite sprite;
	protected int life = 100;
	
	int anim = 0;
	int animSpeed = 50;
	Sprite[] animSprite = new Sprite[1];
	
	public Mob(int x, int y, Sprite sprite, Sprite[] anim) {
		this.x = x;
		this.y = y;
		this.sprite = sprite;
		this.animSprite = anim;
	}
	
	public void draw(Bitmap bitmap) {
		
		bitmap.drawSprite(sprite, x, y);
	}
	
	public boolean tick() {
		
		if (anim > 7500) anim = 0;
		else anim ++;
		
		if (anim % animSpeed > 10) { 
			sprite = animSprite[0];
		}else {
			sprite = animSprite[1];
		}
		
		return false;
	}
	
	public void setSprite(Sprite s) { 
		this.sprite = s;
	}
	
	public void setSpriteAnimation(Sprite[] anim) { 
		this.animSprite = anim;
	}
	
	public void setX(int x) { this.x = x; }
	public void setY(int y) { this.y = y; }
	
}
