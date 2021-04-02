
package icons;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;


public class BallComponent implements Component{

	private BufferedImage blocks;
	
	public BallComponent() {
		
		try {
			
			blocks = ImageIO.read(new File(System.getProperty("user.dir") + "\\elements\\breakout1.png"));
		} catch (IOException e) {
			
			e.printStackTrace();
		}
	}


	public ImageIcon[] getComponent() {		// cuts the balls from the image at given coordinates
		
		ImageIcon[] balls = new ImageIcon[8];
		
		int x = 192;
		int y = 96;
		
		for (int i = 0; i < 4; ++i) {
			
			balls[i] = new ImageIcon(blocks.getSubimage(x, y, 16, 16));
			x = x + 16;
		}
		
		return balls;
	}
}
