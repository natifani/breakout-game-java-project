
package states;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

import main.Game;
import usages.CustomFont;

// a Username class' panel is used to ask for the player's username
public class Username extends JPanel implements States, ActionListener {
	
	private Image background;
	private String username;
	private JTextField textField;
	private Game game;
	private JButton continueButton;
	private JButton loadButton;
	
	public Username(Game game) {
		
		setLayout(new BorderLayout());
		background = Toolkit.getDefaultToolkit().createImage(System.getProperty("user.dir") + "\\elements\\background.png");
		
		this.game = game;
		
		setupPanel();
		
	}
	
	
	public void setupPanel() {
		
		CustomFont customFont = new CustomFont();
		continueButton = new JButton("CONTINUE");
		continueButton.setFont(customFont.deriveFont(32f));
		continueButton.setForeground(Color.WHITE);
		continueButton.setOpaque(false);
		continueButton.setContentAreaFilled(false);
		continueButton.setBorderPainted(false);
		continueButton.addActionListener(this);
		continueButton.setHorizontalAlignment(SwingConstants.RIGHT);
		add(continueButton, BorderLayout.NORTH);
		
		JPanel textPanel = new JPanel();
		BoxLayout box = new BoxLayout(textPanel, BoxLayout.Y_AXIS);
		textPanel.setLayout(box);
		
		JLabel giveNameLabel = new JLabel("Give your name below (only letters):");
		giveNameLabel.setAlignmentX(JLabel.CENTER_ALIGNMENT);
		giveNameLabel.setForeground(Color.WHITE);
		giveNameLabel.setFont(customFont.deriveFont(44f));
		textPanel.add(giveNameLabel);
		
		textField = new JTextField();
		textField.setPreferredSize(new Dimension(800,60));
		textField.setMaximumSize(textField.getPreferredSize());
		textField.setForeground(Color.WHITE);
		textField.setFont(customFont.deriveFont(48f));
		textField.setOpaque(false);
		textField.setAlignmentX(JTextField.CENTER_ALIGNMENT);
		textPanel.add(textField);
		
		textPanel.setOpaque(false);
		
		loadButton = new JButton("LOAD GAME");
		loadButton.setFont(customFont.deriveFont(32f));
		loadButton.setForeground(Color.WHITE);
		loadButton.setOpaque(false);
		loadButton.setContentAreaFilled(false);
		loadButton.setBorderPainted(false);
		loadButton.addActionListener(this);
		add(loadButton, BorderLayout.SOUTH);
		
		add(textPanel, BorderLayout.CENTER);
	}
	
	public void paintComponent(Graphics g) {
		
		super.paintComponent(g);
		g.drawImage(background, 0, 0, this.getWidth() + 50, this.getHeight() + 50, this);
	}
	
	public String getUsername() {
		
		return username;
	}
	
	private boolean checkLetters(String str) {	
		
		str = str.toLowerCase();
		char[] charArray = str.toCharArray();
		if (charArray.length == 0) {
			
			return false;
		}
		for(int i = 0; i < charArray.length; ++i) {
			
			if(charArray[i] < 'a' || charArray[i] > 'z') {
				
				return false;
			}
		}
		
		return true;
	}
 
	@Override
	public void actionPerformed(ActionEvent e) {
		
		username = textField.getText();
		
		if (checkLetters(username) && e.getSource() == continueButton) {	
			
			game.updatePanel("paddleselect");
		}
		
		if (e.getSource() == loadButton) {	
			
			File selectedFile = null;
			JFileChooser j = new JFileChooser();
			j.setCurrentDirectory(new File(System.getProperty("user.dir") + "\\saves"));
			int choice = j.showOpenDialog(null);
			if (choice == JFileChooser.APPROVE_OPTION) {
			
				selectedFile = j.getSelectedFile();
				
				try {
					
					Scanner fileReader = new Scanner(selectedFile);						
					username = fileReader.nextLine();	
					textField.setText(username);
					int level;
					level = fileReader.nextInt();	
					game.setLevel(level);	
					fileReader.close();
				} catch (FileNotFoundException e1) {
					
					e1.printStackTrace();
				}
				
				
			}
		
		}
	}

}
