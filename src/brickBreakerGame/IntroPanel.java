package brickBreakerGame;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import javax.swing.JPanel;

@SuppressWarnings("serial")
public class IntroPanel extends JPanel {	
	// Draws the images to the board
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		g.setColor(Color.RED);
		g.setFont(new Font("CourierNew", Font.PLAIN, 25));
		g.drawString("WELCOME TO BRICK BREAKER", ((BrickBreaker.getLength() / 10)), (BrickBreaker.getHeight() / 2));
		g.dispose();
	} // paint component
	
	// Below sets the size of the introduction screen.
	public Dimension getPreferredSize() {
		return new Dimension(BrickBreaker.getLength(), BrickBreaker.getHeight());
	} // get preferred size
} // class
