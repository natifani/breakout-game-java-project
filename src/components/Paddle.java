
package components;
import javax.swing.ImageIcon;

import icons.ComponentContext;
import icons.PaddleComponent;


public class Paddle implements Surface {
	
	private int x;
	private int y;
	private int dx;
	private int skin = 0;
	private int size;
	private int width = 128;
	private final int height = 32;
	private ImageIcon paddle;
	private ImageIcon[] paddles;
	
	public Paddle(int skin, int x, int y) {		// initialize paddle
		
		this.x = x / 2 - 64;
		this.y = y - 48;
		this.skin = skin;
		this.size = 1;
		this.dx = 0;
		
		this.paddles =(new ComponentContext(new PaddleComponent())).getComponent();
		this.paddle = paddles[4 * skin + size];
		
	}
	
	public void initPosition(int x, int y) {		// initialize position
		
		this.x = x / 2 - width / 2;
		this.y = y - 48;
	}
	
	public void incrementSize() {					// increment the size of the paddle
		
		if (size < 3) {
			
			size++;
			width += 64;
			this.paddle = paddles[4 * skin + size];
		}
	}
	
	public void decrementSize() {					// decrement the size of the paddle
		
		if (size > 0) {
			
			size--;
			width -= 64;
			this.paddle = paddles[4 * skin + size];
		}
		
	}
	
	public void setDX(int dx) {
		
		this.dx = dx;
	}
	
	public void setX(int x) {
		
		this.x = x;
	}
	
	public void setY(int y) {
		
		this.y = y;
	}
	
	public void setWidth(int width) {
		
		this.width = width;
	}
	
	public int getDX() {
		
		return dx;
	}
	
	public int getX() {
		
		return x;
	}
	
	public int getY() {
		
		return y;
	}
	
	public int getWidth() {
		
		return width; 
	}
	
	public int getHeight() {
		
		return height;
	}
	
	public int getSkin() {
		
		return skin;
	}
	
	public ImageIcon getIcon() {
		
		return paddle;
	}


}
