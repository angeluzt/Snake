package utils;

import java.awt.Color;

public class Constants {

	public static final int GAME_X_LOCATION = 300;
	public static final int GAME_Y_LOCATION = 100;
	
	public static final int GAME_WIDTH = 700;
	public static final int GAME_HEIGHT = 700;
	
	// is forbidden to set GAME_ROWS = 1 and GAME_COLUMNS = 1
	// because it generates an infinite loop in the creation of the food 
	public static final int GAME_ROWS = 10;
	public static final int GAME_COLUMNS = 10;

	public static final int SNAKE_SECTION_WIDTH = GAME_WIDTH / GAME_COLUMNS;
	public static final int SNAKE_SECTION_HEIGHT = GAME_HEIGHT / GAME_ROWS;
	
	public static Color HEAD_COLOR = Color.RED;
	public static Color BODY_COLOR = Color.GREEN;
	public static Color FOOD_COLOR = Color.WHITE;
	public static Color BOARD_COLOR = Color.BLACK;
}
