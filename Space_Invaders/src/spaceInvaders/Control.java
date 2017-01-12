package spaceInvaders;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;

public class Control extends KeyAdapter implements MouseMotionListener, MouseListener {
	
	public int xMouse, yMouse;
	public boolean[] pressed = new boolean[160];
	public boolean[] mPressed = new boolean[4];
	
	public void keyPressed(KeyEvent e) {
		int key = e.getKeyCode();
		for (int i=0; i<pressed.length; i++) {
			if (key > 0 || key <= pressed.length)
				setKey(key, true);
		}
	}
	public void keyReleased(KeyEvent e) {
		int key = e.getKeyCode();
		for (int i=0; i<pressed.length; i++) {
			if (key > 0 || key <= pressed.length)
				setKey(key, false);
		}
	}
	
	public void mouseDragged(MouseEvent e) { xMouse = e.getX(); yMouse = e.getY(); }
	
	public void mouseMoved(MouseEvent e) { xMouse = e.getX(); yMouse = e.getY(); }
	
	private void setKey(int key, boolean pressed) {
		this.pressed[key] = pressed; 
	}
	
	private void setMouseKey(int key, boolean pressed) {
		this.mPressed[key] = pressed; 
	}
	
	public int getMouseX() { return xMouse; }
	public int getMouseY() { return yMouse; }
	
	public boolean getKey(int i) { return pressed[i]; }
	@Override
	public void mouseClicked(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void mousePressed(MouseEvent e) {
		int key = e.getButton();
		for (int i=0; i<mPressed.length; i++) {
			if (key > 0 || key <= mPressed.length)
				setMouseKey(key, true);
		}
		
		
	}
	@Override
	public void mouseReleased(MouseEvent e) {
		int key = e.getButton();
		for (int i=0; i<mPressed.length; i++) {
			if (key > 0 || key <= mPressed.length)
				setMouseKey(key, false);
		}
		
	}

}
