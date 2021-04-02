
package states;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.util.ArrayList;
import java.util.Random;
import javax.swing.*;

import components.Ball;
import components.Brick;
import components.Paddle;
import icons.ComponentContext;
import icons.HeartComponent;
import main.Game;
import usages.CustomFont;
import usages.Sound;

// a Play class is meant to be used when the game is in process
public class Play extends JPanel implements States, MouseMotionListener, MouseListener {

	private Image background;
	private Game game;
	private Paddle paddle;						
	private ImageIcon paddleImage;				
	private Ball ball;
	private ImageIcon ballImage;	
	private ArrayList<Ball> balls;   			
	private ArrayList<ImageIcon> ballsImage;	
	private ArrayList<Brick> bricks;  			
	private ArrayList<ImageIcon> brickImage;	
	private JLabel[] hearts = new JLabel[3];	
	private ArrayList<ImageIcon> heartImage;	
	private int remainingLives = 3;					
	private JLabel continueText;
	private JLabel scoreLabel;
	private int score;							
	private int recoverPoints = 3000;					
	private int paddleScore;					
	private int ballScore;						
	private Timer timer;
	private int oldX = 0;
	private boolean serveState = false;			
	private Sound sound;
	
	public Play(Game game) {
		
		setLayout(new BorderLayout());
		this.game = game;
		
		background =  Toolkit.getDefaultToolkit().createImage(System.getProperty("user.dir") + "\\elements\\background.png");
			
		balls = new ArrayList<>();	
		bricks = new ArrayList<>();	
		brickImage = new ArrayList<>();
		heartImage = new ArrayList<>();
		ballsImage = new ArrayList<>();
		
		score = 0;
		paddleScore = 0;
		ballScore = 0;
		recoverPoints = 3000;
		remainingLives = 3;
		
		setupPanel();
		
		addMouseMotionListener(this);
		addMouseListener(this);
		
		
	}
	
	public void setupPanel() {
		
		JPanel topPanel = new JPanel(new BorderLayout());
		topPanel.setOpaque(false);
		
		JPanel life = new JPanel(new FlowLayout(FlowLayout.RIGHT));
		life.setOpaque(false);
		
		ComponentContext cc = new ComponentContext(new HeartComponent());
		heartImage.add(cc.getComponent()[0]);	
		heartImage.add(cc.getComponent()[1]);
		
		for(int i = 0; i < remainingLives; ++i) {
			
			hearts[i] = new JLabel();
			hearts[i].setIcon(heartImage.get(0));
			life.add(hearts[i]);
		}
		
		continueText = new JLabel("Click mouse to start!", SwingConstants.CENTER);
		continueText.setForeground(Color.WHITE);
		continueText.setFont((new CustomFont()).deriveFont(32f));
		continueText.setVisible(false);
		add(continueText,BorderLayout.CENTER);
		
		topPanel.add(life, BorderLayout.EAST);
		
		scoreLabel = new JLabel("SCORE: " + score, SwingConstants.CENTER);
		scoreLabel.setForeground(Color.WHITE);
		scoreLabel.setFont((new CustomFont()).deriveFont(32f));
		
		topPanel.add(scoreLabel, BorderLayout.WEST);
		
		add(topPanel, BorderLayout.NORTH);
	}
	
	public void setupPaddle(int currentPaddle) {
		
		paddle = new Paddle(currentPaddle, this.getWidth(), this.getHeight());
		paddleImage = paddle.getIcon();
	}
	
	public void setupBricks(int level) {	
	
		bricks.clear();						
		brickImage.clear();
		
		Random rand = new Random();
		Brick b;
		int numRows = rand.nextInt(5) + 1;	
		int numCols = rand.nextInt(7) + 7;
		
		if (numCols % 2 == 0) {
			
			numCols++;
		}
		
		
		int highestTier = (int) Math.min(3, Math.floor(level / 5));		
		int highestColour = (int) Math.min(5, level % 5 + 3);			
		
		boolean skipPattern, alternatePattern;
		boolean skipFlag, alternateFlag;
		int alternateColour1, alternateColour2, alternateTier1, alternateTier2;
		int solidColour, solidTier;
		
		for (int y = 1; y <= numRows; ++y) {		
			
			if (rand.nextInt(2) == 1) {				
				
				skipPattern = true;
			} else {
				
				skipPattern = false;
			}
			
			if (rand.nextInt(2) == 1) {				
				
				alternatePattern = true;
			} else {
				
				alternatePattern = false;
			}
			
			alternateColour1 = rand.nextInt(highestColour) + 1;		
			alternateColour2 = rand.nextInt(highestColour) + 1;
			alternateTier1 = rand.nextInt(highestTier + 1);			
			alternateTier2 = rand.nextInt(highestTier + 1);
			
			if (rand.nextInt(2) == 1) {	
				
				skipFlag = true;
			} else {
				
				skipFlag = false;
			}
			
			if (rand.nextInt(2) == 1) {
				
				alternateFlag = true;
			} else {
				
				alternateFlag = false;
			}
			
			solidColour = rand.nextInt(highestColour) + 1;		
			solidTier = rand.nextInt(highestTier + 1);
			
			for (int x = 1; x <= numCols; ++x) {
				
				if (skipPattern && skipFlag) {		
					
					skipFlag = !skipFlag;
					continue;
					
				} else {
					
					skipFlag = !skipFlag;
				}
				
				b = new Brick((x - 1) * 64 + (game.getWidth() - numCols * 64) / 2, y * 32);		
				
				if (alternatePattern) { 	
				
					if(alternateFlag) {		
					
						b.setColour(alternateColour1);
						b.setTier(alternateTier1);
						b.setIcon(alternateColour1, alternateTier1);
						alternateFlag = !alternateFlag;
						
					} else {			
					
						b.setColour(alternateColour2);
						b.setTier(alternateTier2);
						b.setIcon(alternateColour2, alternateTier2);
						alternateFlag = !alternateFlag;
					}
				}
				if (!alternatePattern) {		
					
					b.setColour(solidColour);
					b.setTier(solidTier);
					b.setIcon(solidColour, solidTier);
				}
				
				bricks.add(b);					
				brickImage.add(b.getIcon());
				
			}
			
			
		}
		
		
	}
	
	public void setupBall(int currentPaddle) {		
		
		balls.clear();		
		ballsImage.clear();
		ball = new Ball(currentPaddle, this.getWidth(), this.getHeight());	
		ballImage = ball.getIcon();
		balls.add(ball);		
		ballsImage.add(ballImage);

	}
	
	public void setHearts() {
		
		for (int i = 0; i < remainingLives; ++i) {	
			
			hearts[i].setIcon(heartImage.get(0));
		}
		
		for(int i = remainingLives; i < 3; ++i) {	
			
			hearts[i].setIcon(heartImage.get(1));
		}
	}
			
	public void setupLevel(int currentPaddle, int level) {

		if (level == 1) {	
			
			remainingLives = 3;
			recoverPoints = 3000;
		}
		paddleScore = 0;	
		ballScore = 0;
		
		setHearts();				
		setupPaddle(currentPaddle);	
		setupBall(currentPaddle);	
		setupBricks(level);			
		
	}
	
	public void setServeState(int currentPaddle, int level) {
		
		serveState = true;				
		continueText.setVisible(true);	
		paddle.decrementSize();			
		paddleImage = paddle.getIcon();
		paddle.initPosition(this.getWidth(), this.getHeight());		
		ball.initPosition(this.getWidth(), this.getHeight());		
		setHearts();					
	}
	
	
	public void moveBall() {
		
		for(int i = 0; i < balls.size(); ++i) {		
			
			Ball b = balls.get(i);
			b.setX(b.getX() + b.getDX());
			b.setY(b.getY() + b.getDY());
			
			if ( b.wallCollision(game.getWidth()) ) {	
				
				sound = new Sound(System.getProperty("user.dir") + "\\elements\\wall_hit.wav");
				sound.play();
			}
			
			
			if(b.getY() > game.getHeight()) {		
					
				balls.remove(i);
				ballsImage.remove(i);
			}
			
		}
			
	}
	
	
	public void playGame(int currentPaddle, int level) {
		
		timer = new Timer(1, new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				
				moveBall();							
				if (balls.size() == 0) {			
					
					if (remainingLives > 0) {		
						
						timer.stop();
						sound = new Sound(System.getProperty("user.dir") + "\\elements\\hurt.wav");
						sound.play();
						remainingLives--;
						setServeState(currentPaddle, level);
					} 
					else {							
						
						timer.stop();
						sound = new Sound(System.getProperty("user.dir") + "\\elements\\hurt.wav");
						sound.play();
						game.checkScore();
						game.updatePanel("gameover");
					}
					
				}
				
				if( checkCollision(currentPaddle) ) {		
					
					timer.stop();
					sound = new Sound(System.getProperty("user.dir") + "\\elements\\victory.wav");
					sound.play();
					game.updatePanel("victory");
				}
				
				repaint();
				
			}
			
		});
		
		timer.start();			
	}
	
	public boolean checkVictory() {		
		
		for(int i = 0; i < bricks.size(); ++i) {
			
			if(bricks.get(i).checkInPlay()) {
				
				return false;
			}
		}
		
		return true;
	}
	
	public void bounceBallonPaddle(Ball b, Paddle paddle) {		
		
		b.setY(paddle.getY() - 16);		
		b.setDY( - b.getDY());			
		
		if (b.getX() < paddle.getX() + paddle.getWidth() / 2 - 12 && paddle.getDX() < 0) {		
			
			if (b.getDX() > -16 && b.getDX() < 16) {		
				
				b.setDX(-2 * b.getDX());
			}
		}
		else {
			
			if (b.getX() > paddle.getX() + paddle.getWidth() / 2 + 12 && paddle.getDX() > 0) {	
				
				if (b.getDX() < 16 && b.getDX() > -16) {	
				
					b.setDX(2 * b.getDX());
				}
		
			}
			else
				if (b.getX() >= paddle.getX() + paddle.getWidth() / 2 - 12 && b.getX() <= paddle.getX() + paddle.getWidth() / 2 + 12) {		
					
					if (b.getDX() != -2 && b.getDX() != 2) {	
						
						b.setDX(b.getDX() / 2);
					}
				}
		}
		
		
	
	}
	
	public void bounceBallonBrick(Ball b, Brick br) {		
		
		if (b.getX() + 4 < br.getX() && b.getDX() > 0) {	
			
			b.setDX( - b.getDX());
			b.setX(br.getX() - 16);
			
		}
		
		else 
			if (b.getX() + 12 > br.getX() + br.getWidth() && b.getDX() < 0) {	
				
				b.setDX( - b.getDX());
				b.setX(br.getX() + 64);
			} 
			else
				if (b.getY() < br.getY()) {		
				  
				  b.setDY(- b.getDY());
				  b.setY(br.getY() - 16);
				} 
				else {
				  
				  b.setDY(- b.getDY());		
				  b.setY(br.getY() + 32);
				}
	}
	
	public void updateScores(Brick br, int currentPaddle) {
		
		
		score += (br.getTier() * 200 + br.getColour() * 25);		
		paddleScore += (br.getTier() * 200 + br.getColour() * 25);	
		ballScore += (br.getTier() * 200 + br.getColour() * 25);	
		
		if (paddleScore > 2500) {		
			
			paddle.incrementSize();
			paddleImage = paddle.getIcon();
			paddleScore = 0;
		}
		
		if (ballScore > 1000) {			
			
			if (balls.size() < 6) {
				
				Ball b1 = new Ball(currentPaddle, game.getWidth(), game.getHeight());	
				balls.add(b1);
				ballsImage.add(b1.getIcon());
				ballScore = 0;			
			}
		}
		
		scoreLabel.setText("SCORE: " + score);
		
		if (score > recoverPoints && recoverPoints <= 200000) {		
			
			remainingLives = Math.min(3, remainingLives + 1);		
			
			setHearts();											
			sound = new Sound(System.getProperty("user.dir") + "\\elements\\recover.wav");
			sound.play();
			
			if (recoverPoints < 200000) {							
				
				recoverPoints = Math.min(200000, 2 * recoverPoints);
			}
			else {
				
				recoverPoints++;
			}
			
		}
		
	}
	
	public boolean checkCollision(int currentPaddle) {
		
		for(int j = 0; j < balls.size(); ++j) {			
			Ball b = balls.get(j);
			
			if (b.collides(paddle)) {				
							
				bounceBallonPaddle(b, paddle);		
				sound = new Sound(System.getProperty("user.dir") + "\\elements\\paddle_hit.wav");
				sound.play();
			}
			
			for(int i = 0; i < bricks.size(); ++i) {		
				
				Brick br = bricks.get(i);
				if (br.checkInPlay() && b.collides(br)) {	
					
					updateScores(br, currentPaddle);		
					
					br.hit();								
					brickImage.set(i, br.getIcon());
					
					if (checkVictory()) {					
						
						return true;
					}
	
					bounceBallonBrick(b, br);				
					sound = new Sound(System.getProperty("user.dir") + "\\elements\\brick_hit.wav");
					sound.play();
				}
			}
		}
		
		return false;
		
	}
		
	public void setScore(int score) {
		
		this.score = score;
		scoreLabel.setText("SCORE: " + score);
	}
	
	public int getScore() {
		
		return score;
	}
	
	
	public void paintComponent(Graphics g) {
		
		super.paintComponent(g);
		g.drawImage(background, 0, 0, this.getWidth() + 50, this.getHeight() + 50, this);
		paddleImage.paintIcon(this, g, paddle.getX(), paddle.getY());
		
		for(int i = 0; i < ballsImage.size(); ++i) {
			
			ballsImage.get(i).paintIcon(this, g, balls.get(i).getX(), balls.get(i).getY());
		}
		
		for(int i = 0; i < bricks.size(); ++i) {
			
			if (bricks.get(i).checkInPlay()) {
				
				brickImage.get(i).paintIcon(this, g, bricks.get(i).getX(), bricks.get(i).getY());
			}
		}
		

	}
	
	public void mouseMoved(MouseEvent e) {
			
		if (e.getX() > oldX) {		
			
			paddle.setDX(1);
		}
		
		if (e.getX() < oldX) {
			
			paddle.setDX(-1);
		}
		
		if (paddle.getX() > 0) {
			
			paddle.setX(Math.min(game.getWidth() - paddle.getWidth() - 15, e.getX()));
			
		} else {
			
			paddle.setX(Math.max(0, e.getX()));
		}
		
		repaint();
		
	}

	public void mouseClicked(MouseEvent e) {
		
		if (serveState) {		
			
			continueText.setVisible(false);
			serveState = false;
			ball.initVelocity();	
			balls.add(ball);
			ballsImage.add(ball.getIcon());
			playGame(paddle.getSkin(), game.getLevel());	
		}
		
	}

	
	@Override
	public void mouseDragged(MouseEvent e) {
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

	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

}
