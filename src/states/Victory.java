
package states;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.SwingConstants;

import main.Game;
import usages.CustomFont;

// Victory class' panel shows when the player won the game
public class Victory extends JPanel implements States, MouseListener {
	
	private Image background;
	private Game game;
	private JButton nextLevel;
	private JButton returnMenu;
	private JButton saveButton;
	
	public Victory(Game game) {
		
		setLayout(new BorderLayout());
		this.game = game;
		
		background = Toolkit.getDefaultToolkit().createImage(System.getProperty("user.dir") + "\\elements\\background.png");
	
		
		setupPanel();
		
	}
	

	public void setupPanel() {
		
		JPanel buttons = new JPanel(new FlowLayout());
		
		CustomFont customFont = new CustomFont();
		nextLevel = new JButton("NEXT LEVEL");
		nextLevel.setOpaque(false);
		nextLevel.setContentAreaFilled(false);
		nextLevel.setBorderPainted(false);
		nextLevel.setForeground(Color.WHITE);
		nextLevel.setFont(customFont.deriveFont(32f));
		nextLevel.addMouseListener(this);
		buttons.add(nextLevel);
		
		returnMenu = new JButton("RETURN");
		returnMenu.setOpaque(false);
		returnMenu.setContentAreaFilled(false);
		returnMenu.setBorderPainted(false);
		returnMenu.setForeground(Color.WHITE);
		returnMenu.setFont(customFont.deriveFont(32f));
		returnMenu.addMouseListener(this);
		buttons.add(returnMenu);
		
		saveButton = new JButton("SAVE AND RETURN");
		saveButton.setOpaque(false);
		saveButton.setContentAreaFilled(false);
		saveButton.setBorderPainted(false);
		saveButton.setForeground(Color.WHITE);
		saveButton.setFont(customFont.deriveFont(32f));
		saveButton.addMouseListener(this);
		buttons.add(saveButton);
		
		buttons.setOpaque(false);
		add(buttons, BorderLayout.SOUTH);
		
		JLabel congratulation = new JLabel("CONGRATS! YOU WON!", SwingConstants.CENTER);
		congratulation.setForeground(Color.WHITE);
		congratulation.setFont(customFont.deriveFont(64f));
		add(congratulation, BorderLayout.CENTER);
	}
	
	public void paintComponent(Graphics g) {
		
		super.paintComponent(g);
		g.drawImage(background, 0, 0, this.getWidth() + 50, this.getHeight() + 50, this);		
		
	}

	public void mouseClicked(MouseEvent e) {
		
		if (e.getSource() == nextLevel) {	
			
			game.incrementLevel();
			game.startCountDown();
			
		}
		
		if(e.getSource() == returnMenu) {	
			
			game.checkScore();
			game.updatePanel("home");
		}
		
		if(e.getSource() == saveButton) {	
			
			game.incrementLevel();
			FileWriter writeFile = null;
		    JFileChooser j = new JFileChooser();
		    j.setCurrentDirectory(new File(System.getProperty("user.dir") + "\\saves"));
		    int choice = j.showSaveDialog(null);
		    if (choice == JFileChooser.APPROVE_OPTION) {
			    try {
			    	writeFile = new FileWriter(j.getSelectedFile()+".txt");		
		            writeFile.write(game.getUsername() + "\n");
		            writeFile.write(game.getLevel() + "");
		            writeFile.flush();
		        } catch (Exception ex) {
		            ex.printStackTrace();
		        } finally {
		            if(writeFile != null) {
		                try {
		                    writeFile.close();
		                } catch (IOException ex) {
		                }
		            }
		    }
		  }
		    
	    game.checkScore();		
		game.updatePanel("home");	
		
		}
	}
	
	public void mouseEntered(MouseEvent e) {
		
		if(e.getSource() == nextLevel) {
			
			nextLevel.setForeground(Color.CYAN);
		}
		
		if(e.getSource() == returnMenu) {
			
			returnMenu.setForeground(Color.CYAN);
		}
		
		if (e.getSource() == saveButton) {
			
			saveButton.setForeground(Color.CYAN);
		}
		
	}

	public void mouseExited(MouseEvent e) {
		
		if(e.getSource() == nextLevel) {
			
			nextLevel.setForeground(Color.WHITE);
		}
		
		if(e.getSource() == returnMenu) {
			
			returnMenu.setForeground(Color.WHITE);
		}
		
		if (e.getSource() == saveButton) {
			
			saveButton.setForeground(Color.WHITE);
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
