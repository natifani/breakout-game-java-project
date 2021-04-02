
package components;
import java.util.Random;
import javax.swing.ImageIcon;

import icons.BallComponent;
import icons.ComponentContext;

public class Ball {
	
	private int x;		
	private int y;		
	private int dx;	
	private int dy;		
	private int skin;
	private final int width = 16;
	private final int height = 16;
	private ImageIcon ball;
	private Random rand = new Random();
	
	public Ball(int skin, int x, int y) {
		
		this.setSkin(skin);		// initialize ball skin
		this.x = x / 2 - 8;		// initialize ball position
		this.y = y - 64;
		this.ball = (new ComponentContext(new BallComponent())).getComponent()[skin];	// initialize icon
		
		if (rand.nextInt(2) == 0) {		// randomize initial dx velocity
			
			this.dx = 2;
		}	
		else {
			
			this.dx = -2;
		}
		
		this.dy = -2;
		
	}
	
	
	public void initPosition(int x, int y) {		// initialize ball's position
		
		this.x = x / 2 - 8;
		this.y = y - 64;
	}
	
	
	public void initVelocity() {					// initialize ball's position
		
		if (rand.nextInt(2) == 0) {
			
			this.dx = 2;
		}	
		else {
			
			this.dx = -2;
		}
		
		this.dy = -2;
	}
	
	
	public boolean wallCollision(int frameWidth) {
		
		if (x <= 0) {
			
			setDX((-1) * dx);
			return true;
		}
		
		if (x >= frameWidth - width - 15) {
			
			setDX((-1) * dx);
			return true;
		}
		
		if (y <= width - 16) {
			
			setDY((-1) * dy);
			return true;
		}
		
		return false;
	}
	
	
	public boolean collides(Surface p) {
		
		if (this.x > p.getX() + p.getWidth() || p.getX() > this.x + this.width) {
			
			return false; 
		}
		
		if (this.y > p.getY() + p.getHeight() || p.getY() > this.y + this.height) {
			
			return false;
		}
		
		return true;
		
	}
	
	public void setX(int x) {
		
		this.x = x;
	}
	
	public void setY(int y) {
		
		this.y = y;
	}
	
	public void setDX(int dx) {
		
		this.dx = dx;
	}
	
	public void setDY(int dy) {
		
		this.dy = dy;
	}
	

	public void setSkin(int skin) {
		this.skin = skin;
	}
	
	
	public int getX() {
		
		return x;
	}
	
	public int getY() {
		
		return y;
	}
	
	public int getDX() {
		
		return dx;
	}
	
	public int getDY() {
		
		return dy;
	}
	
	public ImageIcon getIcon() {
		
		return ball;
	}


	public int getSkin() {
		return skin;
	}
	

}
