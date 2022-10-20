package snakecomponents;

import java.awt.Graphics;

import segregation.PaintComponent;
import utils.Constants;

public class SnakeSection implements PaintComponent {
	private int x, y;
	private int i,j;
	
	public SnakeSection() {
		
	}

	public SnakeSection(int i, int j) {
		this.i = i;
		this.j = j;
		
		this.calculateXAndYLocation();
	}

	public void calculateXAndYLocation() {
		this.x = Constants.GAME_X_LOCATION + this.i * Constants.SNAKE_SECTION_WIDTH;
		this.y = Constants.GAME_Y_LOCATION + this.j * Constants.SNAKE_SECTION_HEIGHT;
	}

	@Override
	public void drawComponent(Graphics g) {
		g.fill3DRect(x, y, Constants.SNAKE_SECTION_WIDTH, Constants.SNAKE_SECTION_HEIGHT, true);
	}
	
	public void setXYLocation(int x, int y) {
		this.x = x;
		this.y = y;
	}

	public int getX() {
		return x;
	}

	public void setX(int x) {
		this.x = x;
	}

	public int getY() {
		return y;
	}

	public void setY(int y) {
		this.y = y;
	}

	public int getI() {
		return i;
	}

	public void setI(int i) {
		this.i = i;
	}

	public int getJ() {
		return j;
	}

	public void setJ(int j) {
		this.j = j;
	}
}
