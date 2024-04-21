package brickBreakerGame;

import java.awt.Color;
import java.awt.FlowLayout;

import javax.swing.JFrame;
import javax.swing.SwingUtilities;

// Main class where the user interface is designed. 
public class BrickBreaker {
	// Board dimensions.
	private final static int boardLength = 500;
	private final static int boardHeight = 800;

	// Main screen.
	private static JFrame frame = new JFrame("BRICK BREAKER");

	private static IntroPanel ip = new IntroPanel();
	private static GamePanel gp = new GamePanel();

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
				playGame();
			} // run
		}); // SwingUtilities
	} // main

	// Below changes the main screen to the game screen.
	protected static void changeScreen() {
		ip.requestFocus(false);
		frame.setContentPane(gp);
		gp.requestFocus(true);
		frame.revalidate();
	} // change screen

	// Method where the user interface is set up.
	protected static void playGame() {
		// Creating the frame
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		ip.setBackground(Color.BLACK);
		gp.setBackground(Color.BLACK);

		frame.add(ip);
		ip.setFocusable(true);
		
		frame.setSize(boardLength, boardHeight);
		frame.setResizable(false);
		frame.setLocationRelativeTo(null);

		frame.pack();
		frame.setVisible(true);
	} // show UI
} // class