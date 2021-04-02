
package states;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import components.Score;
import main.Game;
import usages.CustomFont;

// a HighScore class is used to show the top 10 highscores on a panel
public class HighScore extends JPanel implements States, ActionListener{
	
	private Image background;
	private Game game;
	private JButton returnButton;
	private JLabel[] scores;

	public HighScore(Game game) {
		
		setLayout(new BorderLayout());
		this.game = game;
		
		background = Toolkit.getDefaultToolkit().createImage(System.getProperty("user.dir") + "\\elements\\background.png");
		
		setupPanel();
		
		
	}
	
	public void setupPanel() {
		
		CustomFont customFont = new CustomFont();
		
		returnButton = new JButton("BACK");
		returnButton.setFont(customFont.deriveFont(32f));
		returnButton.setForeground(Color.WHITE);
		returnButton.setOpaque(false);
		returnButton.setContentAreaFilled(false);
		returnButton.setBorderPainted(false);
		returnButton.addActionListener(this);
		returnButton.setHorizontalAlignment(SwingConstants.LEFT);
		add(returnButton, BorderLayout.NORTH);
		
		JPanel highscoresPanel = new JPanel(new BorderLayout());
		
		JLabel highscoreText = new JLabel("HIGH SCORES", SwingConstants.CENTER);
		highscoreText.setForeground(Color.WHITE);
		highscoreText.setFont((new CustomFont()).deriveFont(64f));
		highscoresPanel.add(highscoreText, BorderLayout.NORTH);
		
		JPanel highscores = new JPanel();
		BoxLayout box = new BoxLayout(highscores, BoxLayout.Y_AXIS);
		highscores.setLayout(box);
		
		scores = new JLabel[10];	
		for(int i = 0; i < 10; ++i) {
			
			scores[i] = new JLabel();
			scores[i].setAlignmentX(0.1f);
			scores[i].setForeground(Color.WHITE);
			scores[i].setFont(customFont.deriveFont(48f));
			highscores.add(scores[i]);
			
		}
		
		highscores.setOpaque(false);
		
		highscoresPanel.add(highscores, BorderLayout.CENTER);
		highscoresPanel.setOpaque(false);
		add(highscoresPanel, BorderLayout.CENTER);
	}
	
	public void setScores(ArrayList<Score> scoresArray) {
		
		for(int i = 0; i < 10; ++i) {
			
			scores[i].setText(i + 1 + ". " + scoresArray.get(i).getUsername() + " - " + scoresArray.get(i).getScore());
		}
	}
	 
	public void paintComponent(Graphics g) {
		
		super.paintComponent(g);
		g.drawImage(background, 0, 0, this.getWidth() + 50, this.getHeight() + 50, this);		
		
	}

	public void actionPerformed(ActionEvent e) {
	
		if (e.getSource() == returnButton) {	
			
			game.updatePanel("home");
		}
	}
	
}
