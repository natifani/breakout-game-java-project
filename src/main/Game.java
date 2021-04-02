
package main;
import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import components.Score;
import states.CountDown;
import states.Gameover;
import states.HighScore;
import states.Home;
import states.PaddleSelect;
import states.Play;
import states.Username;
import states.Victory;
import usages.Sound;

// Game class is the game's main class
// the game is built up by a "state machine", Game class is connected to each state
// as a frame, it contains all the panels, it uses cardLayout to interchange them
public class Game extends JFrame implements KeyListener {
	
	private JPanel screens;
	private Home home;
	private PaddleSelect paddleS;
	private HighScore highscore;
	private CountDown countdown;
	private Play play;
	private Victory victory;
	private Gameover gameover;
	private Username username;
	private CardLayout card = new CardLayout();
	private int level = 1;
	private ArrayList<Score> scores = new ArrayList<>();
	private Sound sound;
	
	public Game() {
		
		sound = new Sound(System.getProperty("user.dir") + "\\elements\\music.wav");
        sound.loop();
        
		setTitle("Breakout");
		setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
		setLayout(new BorderLayout());
		setResizable(false);
		setSize(1200,800);
	
		home = new Home(this);
		paddleS = new PaddleSelect(this);
		highscore = new HighScore(this);
		countdown = new CountDown(this);
		play = new Play(this);
		victory = new Victory(this);
		gameover = new Gameover(this);
		username = new Username(this);
		
		screens = new JPanel(card);
		screens.add(home, "home");
		screens.add(paddleS, "paddleselect");
		screens.add(highscore, "highscore");
		screens.add(countdown, "countdown");
		screens.add(play, "play");
		screens.add(victory, "victory");
		screens.add(gameover,"gameover");
		screens.add(username, "username");
		
		add(screens, BorderLayout.CENTER);
		
		setVisible(true);
		
		readFromFile();
		
		setFocusable(true);
		addKeyListener(this);
		
	}
	
	public void readFromFile() {		// at every start, it loads the top 10 highscores
		
		Stream<String> stream;
		try {
			stream = Files.lines(Paths.get(System.getProperty("user.dir") + "\\elements\\scores.txt"));
			
			stream.forEach(row -> {
				
				String[] data = row.split("-");
				String username = data[0];
				int score = Integer.parseInt(data[1]);
				scores.add(new Score(score, username));
				
			});
			
		} catch (IOException e) {
			
			e.printStackTrace();
		}
		
		highscore.setScores(scores);
	}
		
	public void overwriteFile(ArrayList<Score> scores) {	// overwrites scores.txt file, the highscores are updated
		
		File f1 = new File(System.getProperty("user.dir") + "\\elements\\scores.txt");
		f1.delete();
		
		File fnew = new File(System.getProperty("user.dir") + "\\elements\\scores.txt");
		
		try {
			
			FileWriter fw = new FileWriter(fnew);
			for(int i = 0; i < 10; ++i) {
				
				fw.write(scores.get(i).getUsername() + "-" + scores.get(i).getScore() + "\n");
			}
			
			fw.close();
		} catch (IOException e) {
		
			e.printStackTrace();
		}
		
	}
	
	public void updatePanel(String name) {
		
		card.show(screens, name);

	}
	
	public void incrementLevel() {
		
		level++;
	}
	
	public void startCountDown() {		// count down panel
		
		updatePanel("countdown");
		countdown.countingDown(getLevel());
	
	}
	
	public void startGame(int level) {	// firstly, the level is set up than the game is started
		
		int currentPaddle = paddleS.getCurrentPaddle();
		play.setupLevel(currentPaddle, level);
		play.playGame(currentPaddle, level);
	}
	
	public void checkScore() {	// using streams, the score of the current player is checked, if it is in the top 10 highscores or not
		
		int score = play.getScore();
		String name = username.getUsername();
		List<Score> bigger = scores.stream().filter(x -> x.getScore() > score).collect(Collectors.toList());	
		if (bigger.size() < 10) {		
		
			List<Score> smaller = scores.stream().filter(x -> x.getScore() <= score).collect(Collectors.toList());	
			ArrayList<Score> updatedScores = new ArrayList<>();
			updatedScores.addAll(bigger);
			updatedScores.add(new Score(score, name));
			updatedScores.addAll(smaller);
			highscore.setScores(updatedScores);
			overwriteFile(updatedScores);
		}
		
	}
	
	public void setLevel(int level) {
		
		this.level = level;
	}
	
	public void setScores(ArrayList<Score> scores) {
		
		highscore.setScores(scores);
	}
	
	
	public void setScore(int score) {
		
		play.setScore(score);
	}
	
	
	public ArrayList<Score> getHighScores() {
		
		return scores;
	}
		
	public int getLevel() {
		
		return level;
	}
	
	public String getUsername() {
		
		return username.getUsername();
	}

	public void keyPressed(KeyEvent e) {
		
		if(e.getKeyCode() == KeyEvent.VK_M) {
			
			if (sound.checkSound()) {
				
				sound.stop();
			}
			else {
				
				sound.loop();
			}
		}
		
	}
	
	
	@Override
	public void keyTyped(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void keyReleased(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}
	
	public static void main(String[] args) {
		
		new Game();
	}


}
