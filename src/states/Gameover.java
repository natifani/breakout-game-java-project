
package states;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

import main.Game;
import usages.CustomFont;

// a Gameover class' panel shows when the player loses all of his lives
public class Gameover extends JPanel implements States, MouseListener {
	
	private Image background;
	private JButton returnMenu;
	private Game game;
	
	public Gameover(Game game) {
		
		setLayout(new BorderLayout());
		this.game = game;
		background = Toolkit.getDefaultToolkit().createImage(System.getProperty("user.dir") + "\\elements\\background.png");
		
		setupPanel();
	}
	
	public void setupPanel() {
		
		JPanel centerPanel = new JPanel(new BorderLayout());
		
		CustomFont customFont = new CustomFont();
		JLabel text = new JLabel("GAME OVER", SwingConstants.CENTER);
		text.setForeground(Color.WHITE);
		text.setFont(customFont.deriveFont(64f));
		centerPanel.add(text, BorderLayout.CENTER);
		
		returnMenu = new JButton("RETURN TO MENU");
		returnMenu.setOpaque(false);
		returnMenu.setContentAreaFilled(false);
		returnMenu.setBorderPainted(false);
		returnMenu.setForeground(Color.WHITE);
		returnMenu.setFont(customFont.deriveFont(32f));
		returnMenu.addMouseListener(this);
		centerPanel.add(returnMenu, BorderLayout.SOUTH);
		
		centerPanel.setOpaque(false);
		add(centerPanel, BorderLayout.CENTER);
	}
	
	public void paintComponent(Graphics g) {
		
		super.paintComponent(g);
		g.drawImage(background, 0, 0, this.getWidth() + 50, this.getHeight() + 50, this);		
		
	}

	public void mouseClicked(MouseEvent e) {
		
		if(e.getSource() == returnMenu) {	
			
			game.updatePanel("home");
		}
		
	}

	@Override
	public void mousePressed(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseEntered(MouseEvent e) {
		
		
		
	}

	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

}
