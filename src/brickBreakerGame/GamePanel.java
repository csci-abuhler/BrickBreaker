package brickBreakerGame;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.Random;

import javax.swing.JOptionPane;
import javax.swing.JPanel;

// Custom class that extends JPanel, and where the main game will be updated
@SuppressWarnings("serial")
public class GamePanel extends JPanel implements KeyListener {

	// Ball fields
	private final static int ballRadius = 20;
	private static int ballX = (BrickBreaker.getLength() / 2) - (ballRadius / 2);
	private static int ballY = BrickBreaker.getHeight() / 2;
	private final int ballVelocity = 1;
	private int ballVelocityX = generateVelocity();
	private int ballVelocityY = generateVelocity();
	private boolean begin = false;
	private int counter = 0;

	// Player paddle fields
	private final static int paddleWidth = 100;
	private final static int paddleHeight = 30;
	private final int paddleVelocity = 20;
	private static int paddleX = (BrickBreaker.getLength() / 2) - paddleWidth / 2;
	private static int paddleY = BrickBreaker.getHeight() - paddleHeight;
	Rectangle paddle = new Rectangle(paddleX, paddleY, paddleWidth, paddleHeight);

	// Thread made for the ball
	Thread thread;

	// The bricks of the wall in an array as well as the width and height fields
	private final int wallWidth = 8;
	private final int wallHeight = 7;
	Rectangle[] wall = new Rectangle[wallWidth * wallHeight];
	Rectangle[][] drawnWall = new Rectangle[wallWidth][wallHeight];
	private boolean initializeWall = true;

	// Fields for the bricks of the wall
	private final static int brickWidth = 50;
	private final static int brickHeight = 20;

	// Constructor
	protected GamePanel() {
		// Below allows for key input to be checked
		addKeyListener(this);

		// Below allows the focus to be set to the custom JPanel.
		setFocusable(true);

		// Below allows the tab and shift + tab keys to be used. Currently disabled.
		setFocusTraversalKeysEnabled(false);

		// Below we update the position of the ball.
		thread = new Thread(() -> {
			do {
				updateBallPosition();
				try {
					Thread.sleep(ballVelocity);
				} catch (InterruptedException err) {
					err.printStackTrace();
				} // try catch
			} while (true);
		}); // thread

		// Start the thread.
		thread.start();
	} // constructor

	// Below will be used to pick a random direction for the ball to go in upon
	// startup.
	protected int generateVelocity() {
		Random n = new Random();
		int[] pick = { -ballVelocity, ballVelocity };
		return pick[n.nextInt(2)];
	} // generate Velocity

	// Below sets the size of the game board.
	public Dimension getPreferredSize() {
		return new Dimension(BrickBreaker.getLength(), BrickBreaker.getHeight());
	} // get preferred size

	// Below is the overridden paint component method.
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);

		// Draw the ball
		g.setColor(Color.RED);
		g.fillOval(ballX, ballY, ballRadius, ballRadius);

		// Draw the player's board
		g.setColor(Color.GREEN);
		g.fillRect(paddleX, paddleY, paddleWidth, paddleHeight);

		// Draw the wall
		if (initializeWall) {
			setWall();
			initializeWall = false;
		} // if

		displayWall(g);

		g.dispose();
	} // paintComponent

	// updates the position of the ball
	protected void updateBallPosition() {
		// Start moving the ball when enter is pressed.
		if (begin) {
			ballX += ballVelocityX;
			ballY += ballVelocityY;

			// Below checks if the ball intersects one the wall blocks and removes it from
			// the board if true.
			for (int i = 0; i < wall.length; i++) {
				if (wall[i].contains(ballX, ballY)) {
					wall[i].x = -brickWidth;
					wall[i].y = -brickHeight;
					counter++;
				} // if
			} // for
		} // if

		/*
		 * Below checks if the ball hits the walls and ceiling and flips the ball
		 * velocity in the respective direction.
		 */
		// Left wall
		if (ballX <= 0) {
			ballVelocityX *= -1;
		} // if

		// Right wall
		if (ballX >= (BrickBreaker.getLength() - ballRadius)) {
			ballVelocityX *= -1;
		} // if

		// Ceiling
		if (ballY <= 0) {
			ballVelocityY *= -1;
		} // if

		// End the game if ball falls through the floor or all blocks are destroyed.
		if ((ballY >= (BrickBreaker.getHeight() - ballRadius))) {
			GamePanel.infoBox("YOU JUST LOST THE GAME!", "LOSE!");
		} else if (counter == (wallWidth * wallHeight)) {
			GamePanel.infoBox("YOU WON THE GAME!", "WIN!");
		} // if

		// Next few if statements check if the ball hits the paddle.
		if ((paddle.contains(ballX - ballRadius, ballY)) || (paddle.contains(ballX + ballRadius, ballY))) {
			ballVelocityX *= -1;
		} // if

		if ((paddle.contains(ballX, ballY + ballRadius)) || (paddle.contains(ballX, ballY - ballRadius))) {
			ballVelocityY *= -1;
		} // if

		repaint();
	} // action performed

	// Method used to pop a message on the screen when a specific event occurs.
	public static void infoBox(String infoMessage, String titleBar) {
		JOptionPane.showMessageDialog(null, infoMessage, titleBar, JOptionPane.INFORMATION_MESSAGE);
		System.exit(0);
	} // info box

	// Setting up the blocks for the ball to hit
	protected void setWall() {
		int spaces = 10;
		int calculateX = spaces;
		int calculateY = 0;
		int index = 0;
		drawnWall = new Rectangle[wallWidth][wallHeight];

		for (int i = 0; i < wallWidth; i++) {
			for (int j = 0; j < wallHeight; j++) {
				drawnWall[i][j] = new Rectangle(calculateX, calculateY, brickWidth, brickHeight);
				calculateY += 30;
			} // for
			calculateY = 0;
			calculateX += 60;
		} // for

		// Place the bricks into the wall array for them to be checked for intersection
		// with the ball.
		for (int i = 0; i < wallWidth; i++) {
			for (int j = 0; j < wallHeight; j++) {
				wall[index] = drawnWall[i][j];
				index++;
			} // for
		} // for
	} // set Wall

	// Below displays the wall to the board.
	protected void displayWall(Graphics g) {
		for (int i = 0; i < wallWidth; i++) {
			for (int j = 0; j < wallHeight; j++) {
				if (drawnWall[i][j] != null) {
					g.setColor(Color.ORANGE);
					g.fillRect(drawnWall[i][j].x, drawnWall[i][j].y, brickWidth, brickHeight);
				} // if
			} // for
		} // for
	} // displayWall

	@Override
	// Below checks user input and changes the movement of the paddle based on the
	// input.
	public void keyPressed(KeyEvent e) {
		if (e.getKeyCode() == KeyEvent.VK_ENTER) {
			begin = true;
		} // if

		if (e.getKeyCode() == KeyEvent.VK_LEFT) {
			if (!(paddleX <= 0)) {
				paddleX -= paddleVelocity;
			} // if
		} else if (e.getKeyCode() == KeyEvent.VK_RIGHT) {
			if (!(paddleX >= (BrickBreaker.getLength() - paddleWidth))) {
				paddleX += paddleVelocity;
			} // if
		} // if
		paddle.setBounds(paddleX, paddleY, paddleWidth, paddleHeight);
	} // key pressed

	@Override
	public void keyReleased(KeyEvent e) {

	} // key released

	@Override
	public void keyTyped(KeyEvent e) {

	} // key typed
} // class