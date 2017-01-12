package spaceInvaders;

public class Bitmap {
	
	public int[] pixels;
	public int width, height;
	
	public Bitmap(int width, int height) { 
		this.width = width;
		this.height = height;
		
		pixels = new int[width*height];
	}
	
	
	public void drawSprite(Sprite sprite, int xp, int yp) { 
		
		for (int x=0; x<sprite.width; x++) {
			int xx = x + xp;
			for (int y=0; y<sprite.height; y++) { 
				int yy = y + yp; 
				if (xx < 0 || xx >= width || yy < 0 || yy >= height) break;
				
				int col = sprite.pixels[x + y * sprite.width];
				if (col != 16777215)
					pixels[xx + yy * width] = col;
			}
		}
		
	}
	
	public void drawTexture(SpriteSheet sheet, int xp, int yp) { 
		
		for (int x=0; x<sheet.width; x++) {
			int xx = x + xp;
			for (int y=0; y<sheet.height; y++) { 
				int yy = y + yp; 
				if (xx < 0 || xx >= width || yy < 0 || yy >= height) break;
				
				
				pixels[xx + yy * width] = sheet.pixels[x + y * sheet.width];
			}
		}
		
	}
	
	public void draw(Bitmap bitmap, int xp, int yp) { 
		for (int x=0; x<bitmap.width; x++) {
			int xx = x + xp;
			for (int y=0; y<bitmap.height; y++) { 
				int yy = y + yp;
				if (xx < 0 || xx >= width || yy < 0 || yy >= height) continue;
				
				pixels[xx + yy * width] = bitmap.pixels[x + y * bitmap.width];
			}
		}
	}
	
	//(x-a)2 + (y-b)2 = r2
	public void drawCircle(int xp, int yp, int radius, int colour) {
		/*
		for(int i=x;i<radius*2;i++)
	        for(int j=x;j<radius*2;j++){
	            int d= (int) Math.sqrt((i-radius)*(i-radius)+(j-radius)*(j-radius));
	            
	                if(d<radius)
	                    pixels[i+j*width]=colour;//346346
	        }*/
		for (int x=0; x<radius*2; x++) {
			int xx = x + xp;
			if (xx < 0 || xx >= width) continue;
			for (int y=0; y<radius*2; y++) { 
				int yy = y + yp;
				if (yy < 0 || yy >= height) continue;
				
				
				int dist = (int)Math.sqrt( (x-radius)*(x-radius)+(y-radius)*(y-radius) );
				
				if (dist<radius)
					pixels[xx + yy * this.width] = colour;
			}
		}
	}
	
	public void clear() { 
		for (int i=0; i<pixels.length; i++) 
			pixels[i] = 0;
	}
	
	
	
}
