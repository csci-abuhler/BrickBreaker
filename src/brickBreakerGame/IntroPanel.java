package brickBreakerGame;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JPanel;

@SuppressWarnings("serial")
public class IntroPanel extends JPanel implements ActionListener {
	private JButton begin = new JButton("BEGIN");
	private JButton quit = new JButton("QUIT");
	//GridLayout gridLayout = new GridLayout(0,2);
	
	// Constructor
	public IntroPanel() {
		add(begin);
		add(quit);
		
		//setLayout(gridLayout);
		
		begin.setPreferredSize(new Dimension(120, 50));
		
		begin.setVisible(true);
		quit.setVisible(true);
		
		begin.addActionListener(e -> actionPerformed(e));
		quit.addActionListener(e -> exit(e));
	} // constructor
	
	// Draws the images to the board
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		
		g.setColor(Color.RED);
		
		g.drawString("WELCOME TO BRICK BREAKER", ((BrickBreaker.getLength() / 2) - 100), (BrickBreaker.getHeight() / 2));
		
		g.dispose();
	} // paint component
	
	// Below sets the size of the introduction screen.
	public Dimension getPreferredSize() {
		return new Dimension(BrickBreaker.getLength(), BrickBreaker.getHeight());
	} // get preferred size

	// Below changes the screen to the game screen.
	@Override
	public void actionPerformed(ActionEvent e) {
		BrickBreaker.changeScreen();
	} // action performed
	
	// Below exits the application
	public void exit(ActionEvent e) {
		System.exit(0);
	} // action performed
} // class
