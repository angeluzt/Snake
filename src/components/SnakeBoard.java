package components;

import java.awt.Graphics;

import enums.SnakeDirection;
import segregation.PaintComponent;
import snakecomponents.Snake;
import snakecomponents.SnakeSection;
import utils.Constants;
import utils.Utils;

public class SnakeBoard implements PaintComponent {

	private Snake snake;
	private SnakeSection food;

	public SnakeBoard() {
		snake = new Snake();
		this.generateRandomFoodLocation();
	}
	
	@Override
	public void drawComponent(Graphics g) {
		g.setColor(Constants.BOARD_COLOR);
		g.fillRect(Constants.GAME_X_LOCATION, Constants.GAME_Y_LOCATION, Constants.GAME_WIDTH, Constants.GAME_HEIGHT);
		
		g.setColor(Constants.FOOD_COLOR);
		this.food.drawComponent(g);
		
		snake.drawComponent(g);
	}
	
	public void moveSnake() {
		if(this.snake.isGameOver()) {
			return;
		}

		boolean isEating = this.snake.updateLocation(food);
		
		if(this.snake.getBody().size() == Constants.GAME_COLUMNS * Constants.GAME_ROWS) {
			System.out.println("WINER!!");
			this.snake.setGameOver();
		}
		if(isEating) {
			this.generateRandomFoodLocation();
		}
	}
	
	private void generateRandomFoodLocation() {
		boolean continueLoop = true;
		int randomI, randomJ;
		
		while(continueLoop) {
			
			randomI = Utils.getRandomI();
			randomJ = Utils.getRandomJ();
			
			if(randomI != this.snake.getHead().getI() || randomJ != this.snake.getHead().getJ()) {
				if(this.snake.getBody().isEmpty()) {
					this.food = new SnakeSection(randomI, randomJ);
					this.food.calculateXAndYLocation();
					
					return;
				} else {
					for(SnakeSection section: this.snake.getBody()) {
						if(randomI != section.getI() || randomJ != section.getJ()) {
							this.food = new SnakeSection(randomI, randomJ);
							this.food.calculateXAndYLocation();
							
							return;
						}
					}
				}
			}
		}
	}
	
	public void resetGame() {
		this.snake = new Snake();
		this.generateRandomFoodLocation();
	}

	public void setDirection(SnakeDirection direction) {
		this.snake.setDirection(direction);
	}
}
