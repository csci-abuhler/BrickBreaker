package brickBreakerGame;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.JPanel;

// Custom class that extends JPanel, and where the main game will be updated
@SuppressWarnings("serial")
public class GamePanel extends JPanel implements KeyListener {

	// Ball fields
	private final static int ballRadius = 20;
	private static int ballX = (BrickBreaker.getLength() / 2) - (ballRadius / 2);
	private static int ballY = BrickBreaker.getHeight() / 2;
	private int ballVelocity = 5;
	private int ballVelocityX = ballVelocity;
	private int ballVelocityY = ballVelocity;	

	// Player paddle fields
	private final static int paddleWidth = 100;
	private final static int paddleHeight = 30;
	private final int paddleVelocity = 10;
	private static int paddleX = (BrickBreaker.getLength() / 2) - paddleWidth / 2;
	private static int paddleY = BrickBreaker.getHeight() - paddleHeight;
	Rectangle paddle = new Rectangle(paddleX, paddleY, paddleWidth, paddleHeight);
	
	Thread thread;

	// Constructor
	public GamePanel() {
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
					Thread.sleep(10);
				} catch (InterruptedException ie) {
					ie.printStackTrace();
				} // try catch
				repaint();
			} while (true);
		}); // thread
		thread.start();
	} // constructor

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
		g.setColor(Color.DARK_GRAY);
		g.fillRect(paddleX, paddleY, paddleWidth, paddleHeight);

		g.dispose();
	} // paintComponent

	// updates the position of the ball
	public void updateBallPosition() {
		// Below checks if the ball hits the walls and ceiling and flips the ball velocity in the respective direction.
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

		// Below code is used to cause the ball to bounce off the floor. May be used later.
		/*
		if (ballY >= (BrickBreaker.getHeight() - ballRadius)) {
			ballVelocityY *= -1;
		} // if
		*/

		// Below checks if the ball hits the paddle.
		if (paddle.contains(ballX, ballY + ballRadius)) {
			ballVelocityY *= -1;
		} // if

		ballX += ballVelocityX;
		ballY += ballVelocityY;	
	} // action performed

	@Override
	// Below checks user input and changes the movement of the paddle based on the input.
	public void keyPressed(KeyEvent e) {
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
		repaint();
	} // key pressed

	@Override
	public void keyReleased(KeyEvent e) {
		// TODO Auto-generated method stub
	} // key released

	@Override
	public void keyTyped(KeyEvent e) {
		// TODO Auto-generated method stub
	} // key typed
} // class