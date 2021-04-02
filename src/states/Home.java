
package states;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import main.Game;
import usages.CustomFont;

// a Home class is the main menu of the game
public class Home extends JPanel implements States, ActionListener, MouseListener{
	
	private Image background;
	private JButton startButton;
	private JButton exitButton;
	private JButton highscoreButton;
	private Game game;
	
	public Home(Game game) {

		setLayout(new BorderLayout());
		this.game = game;
		
		background = Toolkit.getDefaultToolkit().createImage(System.getProperty("user.dir") + "\\elements\\background.png");
		
		setupPanel();
		
				
	}
	

	public void setupPanel() {
		
		JLabel title = new JLabel("BREAKOUT", SwingConstants.CENTER);
		title.setForeground(Color.WHITE);
		
		startButton = new JButton("START");
		startButton.setForeground(Color.WHITE);
		startButton.setOpaque(false);
		startButton.setContentAreaFilled(false);
		startButton.setBorderPainted(false);
		startButton.addActionListener(this);
		startButton.addMouseListener(this);
		
		exitButton = new JButton("EXIT");
		exitButton.setForeground(Color.WHITE);
		exitButton.setOpaque(false);
		exitButton.setContentAreaFilled(false);
		exitButton.setBorderPainted(false);
		exitButton.addActionListener(this);
		exitButton.addMouseListener(this);
		
		highscoreButton = new JButton("HIGH SCORES");
		highscoreButton.setForeground(Color.WHITE);
		highscoreButton.setOpaque(false);
		highscoreButton.setContentAreaFilled(false);
		highscoreButton.setBorderPainted(false);
		highscoreButton.addActionListener(this);
		highscoreButton.addMouseListener(this);
		
		CustomFont customFont = new CustomFont();
		title.setFont(customFont.deriveFont(64f));
		startButton.setFont(customFont.deriveFont(32f));
		exitButton.setFont(customFont.deriveFont(32f));
		highscoreButton.setFont(customFont.deriveFont(32f));
			
		JPanel buttons = new JPanel();
		buttons.setLayout(new FlowLayout());
		buttons.add(startButton);
		buttons.add(highscoreButton);
		buttons.add(exitButton);
		buttons.setOpaque(false);
		
		add(title,BorderLayout.CENTER);
		add(buttons, BorderLayout.SOUTH);
	}

	
	public void paintComponent(Graphics g) {
		
		super.paintComponent(g);
		g.drawImage(background, 0, 0, this.getWidth() + 50, this.getHeight() + 50, this);
	}
	
	public void actionPerformed(ActionEvent e) {
		
		if (e.getSource() == exitButton) {	
			
			System.exit(0);
		}
		
		if (e.getSource() == startButton) {	
			
			game.updatePanel("username");
		}
		
		if (e.getSource() == highscoreButton) {
			
			game.updatePanel("highscore");
		}
		
	}

	public void mouseEntered(MouseEvent e) {
		
		if (e.getSource() == exitButton) {
			
			exitButton.setForeground(Color.CYAN);
		}
		
		if (e.getSource() == startButton) {
			
			startButton.setForeground(Color.CYAN);
		}
		
		if (e.getSource() == highscoreButton) {
			
			highscoreButton.setForeground(Color.CYAN);
		}
		
	}


	public void mouseExited(MouseEvent e) {
		
		if (e.getSource() == exitButton) {
			
			exitButton.setForeground(Color.WHITE);
		}
		
		if (e.getSource() == startButton) {
			
			startButton.setForeground(Color.WHITE);
		}
		
		if (e.getSource() == highscoreButton) {
			
			highscoreButton.setForeground(Color.WHITE);
		}
		
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}


	@Override
	public void mousePressed(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}


	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}


}
