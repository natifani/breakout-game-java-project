
package icons;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;

public class HeartComponent implements Component{

	private BufferedImage blocks;
	
	public HeartComponent() {
		
		try {
			
			blocks = ImageIO.read(new File(System.getProperty("user.dir") + "\\elements\\breakout1.png"));
		} catch (IOException e) {
			
			e.printStackTrace();
		}
	}
	
	public ImageIcon[] getComponent() {		// cuts the health icons from the photo
		
		ImageIcon[] hearts = new ImageIcon[2];
		
		int x = 256;
		int y = 96;
		
		hearts[0] = new ImageIcon(blocks.getSubimage(x, y, 20, 18));
		
		x = x + 20;
		
		hearts[1] = new ImageIcon(blocks.getSubimage(x, y, 20, 18));
		
		return hearts;
	}

}
