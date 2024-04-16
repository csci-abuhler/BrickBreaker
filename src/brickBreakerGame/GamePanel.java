package brickBreakerGame;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.JPanel;

// class that extends JPanel, and where the main game will be updated
@SuppressWarnings("serial")
public class GamePanel extends JPanel implements KeyListener {

	// ball fields
	private final static int ballRadius = 20;
	private static int ballX = (BrickBreaker.getLength() / 2) - (ballRadius / 2);
	private static int ballY = BrickBreaker.getHeight() / 2;
	private int ballVelocity = 5;
	private int ballVelocityX = ballVelocity;
	private int ballVelocityY = ballVelocity;	

	// player paddle fields
	private final static int paddleWidth = 100;
	private final static int paddleHeight = 30;
	private final int paddleVelocity = 15;
	private static int paddleX = (BrickBreaker.getLength() / 2) - paddleWidth / 2;
	private static int paddleY = BrickBreaker.getHeight() - paddleHeight;

	Thread thread;

	// constructor
	public GamePanel() {
		// Allows for key input to be checked
		addKeyListener(this);

		// Allows the focus to be set to the custom JPanel
		setFocusable(true);

		// Allows the tab and shift + tab keys to be used. Currently disabled.
		setFocusTraversalKeysEnabled(false);

		// update ball position
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

	// sets the size of the game board
	public Dimension getPreferredSize() {
		return new Dimension(BrickBreaker.getLength(), BrickBreaker.getHeight());
	} // get preferred size

	// overridden paint component method
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);

		// the ball
		g.setColor(Color.RED);
		g.fillOval(ballX, ballY, ballRadius, ballRadius);

		// the player's board
		g.setColor(Color.DARK_GRAY);
		g.fillRect(paddleX, paddleY, paddleWidth, paddleHeight);

		g.dispose();
	} // paintComponent

	// updates the position of the ball
	public void updateBallPosition() {
		if (ballX <= 0) {
			ballVelocityX *= -1;
		} // if
		
		if (ballX >= (BrickBreaker.getLength() - ballRadius)) {
			ballVelocityX *= -1;
		} // if
		
		if (ballY <= 0) {
			ballVelocityY *= -1;
		} // if
		
		if (ballY >= (BrickBreaker.getHeight() - ballRadius)) {
			ballVelocityY *= -1;
		} // if
		 
		ballX += ballVelocityX;
		ballY += ballVelocityY;
	} // action performed

	@Override
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