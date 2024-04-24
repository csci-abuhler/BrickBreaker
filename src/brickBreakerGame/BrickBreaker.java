package brickBreakerGame;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.SwingUtilities;

// Main class where the user interface is designed. 
public class BrickBreaker implements ActionListener {
	// Board dimensions.
	private final static int boardLength = 500;
	private final static int boardHeight = 850;

	// Initializing the frame
	private static JFrame frame = new JFrame("BRICK BREAKER");

	// Introduction panel and the game panel
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

		// Creating the menu bar
		JMenuBar menuBar = new JMenuBar();
		JMenu menu = new JMenu("Options");

		JMenuItem start = new JMenuItem("Start");
		JMenuItem exit = new JMenuItem("Exit");

		// Action listeners are added.
		start.addActionListener(e -> begin(e));
		exit.addActionListener(e -> exit(e));

		menu.add(start);
		menu.add(exit);

		menuBar.add(menu);

		// The menu bar is set to be visible.
		frame.setJMenuBar(menuBar);
		menuBar.setVisible(true);

		ip.setBackground(Color.BLACK);
		gp.setBackground(Color.BLACK);

		frame.add(ip);

		ip.setFocusable(true);

		frame.setSize(new Dimension(boardLength, boardHeight));
		frame.setResizable(false);
		frame.setLocationRelativeTo(null);
		frame.pack();
		frame.setVisible(true);
	} // show UI

	// Below changes the screen to the game screen.
	public static void begin(ActionEvent e) {
		changeScreen();
	} // action performed

	// Below exits the application
	public static void exit(ActionEvent e) {
		System.exit(0);
	} // action performed

	@Override
	public void actionPerformed(ActionEvent e) {
		
	} // actionPerformed
} // class