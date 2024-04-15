package brickBreakerGame;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.JPanel;

@SuppressWarnings("serial")
public class GamePanel extends JPanel implements KeyListener, ActionListener {
	// ball fields
	private final static int ballRadius = 20;
	private static int ballX = (BrickBreaker.getLength() / 2) - (ballRadius / 2);
	private static int ballY = BrickBreaker.getHeight() / 2;

	// player paddle fields
	private final static int paddleWidth = 80;
	private final static int paddleHeight = 30;
	private static int paddleX = (BrickBreaker.getLength() / 2) - paddleWidth / 2;
	private static int paddleY = BrickBreaker.getHeight() - paddleHeight;

	// constructor
	public GamePanel() {
		// Allows for key input to be checked
		addKeyListener(this);

		// Allows the focus to be set to the custom JPanel
		setFocusable(true);

		// Allows the tab and shift + tab keys to be used. Currently disabled.
		setFocusTraversalKeysEnabled(false);
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

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
	} // action performed

	@Override
	public void keyPressed(KeyEvent e) {
		if (e.getKeyCode() == KeyEvent.VK_LEFT) {
			System.out.println("LEFT");
		} else if (e.getKeyCode() == KeyEvent.VK_RIGHT) {
			System.out.println("RIGHT");
		} // if
		System.out.println(e.getKeyChar());
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