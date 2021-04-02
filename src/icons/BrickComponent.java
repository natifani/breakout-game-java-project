

package icons;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;

public class BrickComponent implements Component {

	private BufferedImage blocks;
	
	public BrickComponent() {
		
		try {
			
			blocks = ImageIO.read(new File(System.getProperty("user.dir") + "\\elements\\breakout1.png"));
		} catch (IOException e) {
			
			e.printStackTrace();
		}
	}
	
	public ImageIcon[] getComponent() {		// cuts brick icons from the given photo
		
		ImageIcon[] bricks = new ImageIcon[22];
		
		int i = 0;
		int x;
		int y;
		
		for (y = 0; y < 3; ++y) {
			for (x = 0; x < 6; ++x) {
				
				bricks[i] = new ImageIcon(blocks.getSubimage(x * 64, y * 32, 64, 32));
				i++;
			}
		}
		
		y = 128;
		for (x = 0; x < 3; ++x) {
			
			bricks[i] = new ImageIcon(blocks.getSubimage(x * 64, y, 64, 32));
			i++;
		}
		
		bricks[i] = new ImageIcon(blocks.getSubimage(320, 128, 64, 32));
		
		return bricks;
	}

}
