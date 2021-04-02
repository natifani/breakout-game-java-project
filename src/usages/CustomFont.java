
package usages;
import java.awt.Font;
import java.awt.FontFormatException;
import java.awt.GraphicsEnvironment;
import java.io.File;
import java.io.IOException;

public class CustomFont {
	
	private Font customFont;
	
	public CustomFont() {
		
		try {
		
			customFont = Font.createFont(Font.TRUETYPE_FONT, new File(System.getProperty("user.dir") + "\\elements\\font.ttf"));
			GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
			ge.registerFont(customFont);
			
		} catch (IOException e) {
			e.printStackTrace();
		} catch (FontFormatException e) {
			e.printStackTrace();
		}
		 
	}

	public Font deriveFont(float f) {	
		
		return customFont.deriveFont(f);
	}

}
