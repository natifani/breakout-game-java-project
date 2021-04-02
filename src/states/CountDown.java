
package states;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.Timer;

import main.Game;
import usages.CustomFont;

// a Countdown class is a panel which is used to count down before the game starts
public class CountDown extends JPanel implements States {

	private Image background;
	private JLabel second;
	private Game game;
	private Timer timer;
	private int counter;
	
	public CountDown(Game game) {
		
		setLayout(new BorderLayout());
		this.game = game;
		
		background = Toolkit.getDefaultToolkit().createImage(System.getProperty("user.dir") + "\\elements\\background.png");
		
		setupPanel();
	}
	
	public void setupPanel() {
		
		second = new JLabel("READY", SwingConstants.CENTER);
		second.setForeground(Color.WHITE);
		second.setFont((new CustomFont()).deriveFont(96f));
		add(second, BorderLayout.CENTER);
	}
	
	
	public void countingDown(int level) {
		
		second.setText("LEVEL " + String.valueOf(level));
		counter = 4;	
		timer = new Timer(1000, new ActionListener() {	// timer is used to create the count down feature

			@Override
			public void actionPerformed(ActionEvent e) {
				
				if (counter == 4) {		
					
					second.setText("READY");
				} else {
					
					second.setText(String.valueOf(counter));
				}
				
				counter--;
				if (counter == -1) {
					
					timer.stop();
					game.updatePanel("play");	// panel is changed and the game is ready to start
					game.startGame(level);
				}
			}
			
		}); 
		
		timer.start();
	}
	
	public void paintComponent(Graphics g) {
		
		super.paintComponent(g);
		g.drawImage(background, 0, 0, this.getWidth() + 50, this.getHeight() + 50, this);		
		
	}
}
