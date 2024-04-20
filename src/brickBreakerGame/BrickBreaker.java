package brickBreakerGame;

import java.awt.Color;

import javax.swing.JFrame;
import javax.swing.SwingUtilities;

// Main class where the user interface is designed. 
public class BrickBreaker {
	private final static int boardLength = 500;
	private final static int boardHeight = 800;

	// Returns the board length.
	protected static int getLength() {
		return boardLength;
	} // length getter

	// Returns the board height. 
	protected static int getHeight() {
		return boardHeight;
	} // height getter

	// Main method that calls the user interface method.
	public static void main(String args[]) {
		SwingUtilities.invokeLater(new Runnable() {
			public void run() {
				showUI();
			} // run
		}); // SwingUtilities
	} // main

	// Method where the user interface is set up. 
	protected static void showUI() {
		// Creating the frame
		JFrame frame = new JFrame("BRICK BREAKER");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		GamePanel gp = new GamePanel();
		gp.setBackground(Color.BLACK);
		
		frame.add(gp);
		frame.setSize(boardLength, boardHeight);

		frame.setResizable(false);
		frame.setLocationRelativeTo(null);
		
		frame.pack();
		frame.setVisible(true);
	} // show UI
} // class