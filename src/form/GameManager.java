package form;

import java.awt.Graphics;
import java.awt.event.KeyEvent;

import javax.swing.JPanel;

import components.SnakeBoard;
import enums.SnakeDirection;

public class GameManager extends JPanel {

	private static final long serialVersionUID = 1L;

	SnakeBoard snakeBoard = new SnakeBoard();
	
	public GameManager() {
		snakeBoard = new SnakeBoard();
	}

	@Override
	public void paintComponent(Graphics g) {
		snakeBoard.drawComponent(g);
	}

	public void tick() {
		this.snakeBoard.moveSnake();
		this.repaint();
	}

	public void updateDirection(KeyEvent e) {
		switch (e.getKeyCode()) {
		case KeyEvent.VK_W:
			this.snakeBoard.setDirection(SnakeDirection.UP);
			break;
		case KeyEvent.VK_D:
			this.snakeBoard.setDirection(SnakeDirection.RIGHT);
			break;
		case KeyEvent.VK_S:
			this.snakeBoard.setDirection(SnakeDirection.DOWN);
			break;
		case KeyEvent.VK_A:
			this.snakeBoard.setDirection(SnakeDirection.LEFT);
			break;
		case KeyEvent.VK_R:
			this.snakeBoard.resetGame();
			break;
		default:
			break;
		}
	}
}
