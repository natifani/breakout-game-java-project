
package states;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;

import main.Game;
import usages.CustomFont;
import usages.Sound;
import icons.ComponentContext;
import icons.PaddleComponent;

// a PaddleSelect class' panel the player can choose the colour of the paddle
public class PaddleSelect extends JPanel implements States, MouseListener {
	
	private Image background;
	private ImageIcon[] paddles;
	private JLabel leftArrow;
	private JLabel rightArrow;
	private JLabel paddle;
	private JLabel continueText;
	private Game game;
	private int currentPaddle = 0;
	private Sound selectSound;
	private Sound noselectSound;
	
	public PaddleSelect(Game game) {
		
		setLayout(new BorderLayout());
		this.game = game;
		
		background = Toolkit.getDefaultToolkit().createImage(System.getProperty("user.dir") + "\\elements\\background.png");
		
		setupPanel();
		
		selectSound = new Sound(System.getProperty("user.dir") + "\\elements\\select.wav");
		noselectSound = new Sound(System.getProperty("user.dir") + "\\elements\\no-select.wav");
		
		BufferedImage arrows = null;
		
		try {
			
			arrows = ImageIO.read(new File(System.getProperty("user.dir") + "\\elements\\arrows2.png"));
		} catch (IOException e) {
			
			e.printStackTrace();
		}
		
		JPanel selectPaddle = new JPanel();
		selectPaddle.setLayout(new FlowLayout(FlowLayout.CENTER, 50, 40));
		selectPaddle.setOpaque(false);
		
		leftArrow = new JLabel();
		leftArrow.setIcon(new ImageIcon(arrows.getSubimage(0, 0, 60, 60)));
		rightArrow = new JLabel();
		rightArrow.setIcon(new ImageIcon(arrows.getSubimage(60, 0, 60, 60)));
		
		leftArrow.addMouseListener(this);
		rightArrow.addMouseListener(this);
		
		paddles = (new ComponentContext(new PaddleComponent())).getComponent();
		
		paddle = new JLabel();
		paddle.setIcon(paddles[4 * currentPaddle + 1]);
		
		selectPaddle.add(leftArrow);
		selectPaddle.add(paddle);
		selectPaddle.add(rightArrow);
	
		add(selectPaddle,BorderLayout.SOUTH);

	}
	
	public void setupPanel() {
		
		JPanel texts = new JPanel(new BorderLayout());
		
		JLabel selectText = new JLabel("Select your paddle with left or right!", SwingConstants.CENTER);
		selectText.setForeground(Color.WHITE);
		selectText.setFont((new CustomFont()).deriveFont(32f));
		texts.add(selectText, BorderLayout.CENTER);
		
		JPanel startText = new JPanel(new FlowLayout());
		continueText = new JLabel("START GAME", SwingConstants.CENTER);
		continueText.setPreferredSize(new Dimension(150,40));
		continueText.setForeground(Color.WHITE);
		continueText.setFont((new CustomFont()).deriveFont(24f));
		continueText.addMouseListener(this);
		startText.add(continueText);
		startText.setOpaque(false);
		texts.add(startText, BorderLayout.SOUTH);
		texts.setOpaque(false);
		add(texts, BorderLayout.CENTER);
	}
	
	public int getCurrentPaddle() {	
		
		return currentPaddle;
	}
	
	public void paintComponent(Graphics g) {
		
		super.paintComponent(g);
		g.drawImage(background, 0, 0, this.getWidth() + 50, this.getHeight() + 50, this);		
		
	}
	
	public void mouseClicked(MouseEvent e) {
		
		
		if (e.getSource() == leftArrow) {	
			
			if (currentPaddle != 0) {
		 		
				selectSound.play();
		 		currentPaddle = currentPaddle - 1;
		 		paddle.setIcon(paddles[4 * currentPaddle + 1]);
		 	}
			
			else {
				
				noselectSound.play();
			}
			
		}
		
	   if (e.getSource() == rightArrow) {	
		   
		   if (currentPaddle != 3) {
   			
			selectSound.play();
   			currentPaddle = currentPaddle + 1;
   			paddle.setIcon(paddles[4 * currentPaddle + 1]); 
   			
   		  }
		  
		   else {
			   
			   noselectSound.play();
		   }
	   }
	   
	   if (e.getSource() == continueText) {		
		   
		   game.setScore(0);
		   game.startCountDown();
		   
	   }
		
	}
	
	public void mouseEntered(MouseEvent e) {
		
		if(e.getSource() == continueText) {
			
			continueText.setForeground(Color.CYAN);
		}
		
	}

	public void mouseExited(MouseEvent e) {
		
		if(e.getSource() == continueText) {
			
			continueText.setForeground(Color.WHITE);
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

	
}
