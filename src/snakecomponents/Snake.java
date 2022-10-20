package snakecomponents;

import java.awt.Graphics;
import java.util.Iterator;
import java.util.LinkedList;

import enums.SnakeDirection;
import segregation.PaintComponent;
import utils.Constants;
import utils.Utils;

public class Snake implements PaintComponent {

	private LinkedList<SnakeSection> body = new LinkedList<>();
	private SnakeDirection direction = SnakeDirection.RIGHT;
	boolean addNewSection = false;
	
	boolean gameOver = false;
	
	public Snake() {
		body.add(new SnakeSection(Utils.getRandomI(), Utils.getRandomJ()));

		this.setXAndYLocation(false, 0, 0, 0, 0);
	}

	public boolean updateLocation(SnakeSection food) {
		boolean isSnakeEating = false;
		int tempX = this.body.get(0).getX(), tempY = this.body.get(0).getY();
		int tempI = this.body.get(0).getI(), tempJ = this.body.get(0).getJ();
		
		if(this.gameOver == true) {
			return false;
		}
		
		if(this.isSnakeEating(food)) {
			isSnakeEating = true;
		}

		int newValue;
		switch (direction) {
		case UP:
			newValue = this.body.get(0).getJ() - 1;

			if(newValue < 0) {
				newValue = Constants.GAME_ROWS - 1;
			}
			
			this.body.get(0).setJ(newValue);
			break;
		case RIGHT:
			newValue = this.body.get(0).getI() + 1;
			
			if(newValue > Constants.GAME_COLUMNS -1) {
				newValue = 0;
			}
			this.body.get(0).setI(newValue);
			break;
		case DOWN:
			newValue = this.body.get(0).getJ() + 1;
			if(newValue > Constants.GAME_ROWS -1) {
				newValue = 0;
			}
			this.body.get(0).setJ(newValue);
			break;
		case LEFT:
			newValue = this.body.get(0).getI() - 1;
			if(newValue < 0) {
				newValue = Constants.GAME_COLUMNS - 1;
			}
			this.body.get(0).setI(newValue);
			break;
		default:
			break;
		}
		
		this.setXAndYLocation(isSnakeEating, tempX, tempY, tempI, tempJ);
		this.isColliding();

		return isSnakeEating;
	}
	
	public boolean isSnakeEating(SnakeSection food) {
		if(this.body.get(0).getI() == food.getI() && this.body.get(0).getJ() == food.getJ()) {
			this.addNewSection = true;
			return true;
		}
		
		return false;
	}
	
	public void setDirection(SnakeDirection direction) {
		if(this.gameOver == true) {
			return;
		}

		// Add validation for impossible scenarios
		if(direction == this.direction) {
			return;
		} else if(this.direction == SnakeDirection.UP && direction == SnakeDirection.DOWN) {
			return;
		} else if(this.direction == SnakeDirection.RIGHT && direction == SnakeDirection.LEFT) {
			return;
		} else if(this.direction == SnakeDirection.DOWN && direction == SnakeDirection.UP) {
			return;
		} else if(this.direction == SnakeDirection.LEFT && direction == SnakeDirection.RIGHT) {
			return;
		}

		this.direction = direction;
	}

	private void setXAndYLocation(boolean isSnakeEating, int currentX, int currentY, int currentI, int currentJ) {		
		int tempX, tempY, tempI, tempJ;
		this.body.get(0).calculateXAndYLocation();

		Iterator<SnakeSection> itr = this.body.iterator(); 
		
		boolean isHead = true;
		while (itr.hasNext()) {
			SnakeSection section = (SnakeSection) itr.next();

			if(!isHead) {
				tempX = section.getX();
				tempY = section.getY();
				tempI = section.getI();
				tempJ = section.getJ();
				
				section.setX(currentX);
				section.setY(currentY);
				section.setI(currentI);
				section.setJ(currentJ);
				
				currentX = tempX;
				currentY = tempY;
				currentI = tempI;
				currentJ = tempJ;
			} else {

				isHead = false;
			}
		}
		
		if(isSnakeEating) {
			SnakeSection section = new SnakeSection(currentI, currentJ);
			section.calculateXAndYLocation();
			this.body.add(section);
		}
	}
	
	private void isColliding() {
		boolean ignoreFirst = false;

		for(SnakeSection section: body) {
			if(ignoreFirst && this.body.get(0).getX() == section.getX() && this.body.get(0).getY() == section.getY()) {
				gameOver = true;
				System.out.println("game over");
			}
			ignoreFirst = true;
		}
	}

	@Override
	public void drawComponent(Graphics g) {

		g.setColor(Constants.BODY_COLOR);
		for(SnakeSection section: body) {
			section.drawComponent(g);
		}
		
		g.setColor(Constants.HEAD_COLOR);
		body.get(0).drawComponent(g);
	}
	
	public boolean isGameOver() {
		return gameOver;
	}
	
	public void setGameOver() {
		this.gameOver = true;
	}
	
	public SnakeSection getHead() {
		return this.body.get(0);
	}
	
	public LinkedList<SnakeSection> getBody() {
		return this.body;
	}
}
