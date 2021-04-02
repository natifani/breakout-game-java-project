
package components;
import javax.swing.ImageIcon;

import icons.BrickComponent;
import icons.ComponentContext;

public class Brick implements Surface {
	
	private int x;
	private int y;
	private final int width = 64;
	private final int height = 32;
	private int tier;
	private int colour;
	private boolean inPlay;
	private ImageIcon brick;
	
	public Brick(int x, int y) { 	// initialize brick
		
		this.x = x;
		this.y = y;
		this.tier = 0;
		this.colour = 1;
		this.inPlay = true;
		
	}
	
	
	public void hit() {		// if brick is hit
		
		if (tier > 0) {		// if tier is > 0 and the colour is 1, then we  decrease the tier of the brick
			
			if (colour == 1) {
				
				tier--;
				colour = 5;
			}
			else {
				
				colour--;
			}
		}
		else {
			 
			if (colour == 1) {		// if tier is 0 and the colour is 1, than the brick is not in play anymore 
				
				inPlay = false;
			}
			else {
				
				colour--;
			}
		}
		
		this.setIcon(colour, tier);		// change icon
	}
	
	public int getX() {
		
		return x;
	}
	
	public int getY() {
		
		return y;
	}
	
	public boolean checkInPlay() {
		
		return inPlay;
	}
	
	public ImageIcon getIcon() {
		
		return brick;
	}
	
	public int getColour() {
		
		return colour;
	}
	
	public int getTier() {
		
		return tier;
	}
	
	public int getWidth() {
		
		return width;
	}

	public int getHeight() {
		
		return height;
	}
	
	public void setX(int x) {
		
		this.x = x;
	}
	
	public void setY(int y) {
		
		this.y = y;
	}
	
	public void setColour(int colour) {
		
		this.colour = colour;
	}
	
	public void setTier(int tier) {
		
		this.tier = tier;
	}
	
	public void setIcon(int colour, int tier) {
		
		this.brick = (new ComponentContext(new BrickComponent())).getComponent()[4 * (colour - 1) + tier];
	}

}
