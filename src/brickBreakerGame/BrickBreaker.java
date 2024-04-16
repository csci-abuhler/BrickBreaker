package brickBreakerGame;

import java.awt.Color;

import javax.swing.JFrame;
import javax.swing.SwingUtilities;

public class BrickBreaker {
	private final static int boardLength = 500;
	private final static int boardHeight = 800;

	public static int getLength() {
		return boardLength;
	} // length getter

	public static int getHeight() {
		return boardHeight;
	} // height getter

	// main method
	public static void main(String args[]) {
		SwingUtilities.invokeLater(new Runnable() {
			public void run() {
				showUI();
			} // run
		}); // SwingUtilities
	} // main

	public static void showUI() {
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