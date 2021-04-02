

package icons;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;

public class PaddleComponent implements Component {

	private BufferedImage blocks;
	
	
	public PaddleComponent() {
		
		try {
			
			blocks = ImageIO.read(new File(System.getProperty("user.dir") + "\\elements\\breakout1.png"));
		} catch (IOException e) {
			
			e.printStackTrace();
		}
	}
	
	public ImageIcon[] getComponent() {		// cuts the paddle icons from the photo
		
		ImageIcon[] paddles = new ImageIcon[16];
		
		int i = 0;
		int x = 0;
		int y = 128;
		
		for (int j = 0; j <= 3; ++j) {
			
			paddles[i] = new ImageIcon(blocks.getSubimage(x, y, 64, 32));
			i++;
			paddles[i] = new ImageIcon(blocks.getSubimage(x + 64, y, 128, 32));
			i++;
			paddles[i] = new ImageIcon(blocks.getSubimage(x + 192, y, 192, 32));
			i++;
			paddles[i] = new ImageIcon(blocks.getSubimage(x, y + 32, 256, 32));
			i++;
			
			y = y + 64;
			
		}
		
		return paddles;
	}
	
	

}
