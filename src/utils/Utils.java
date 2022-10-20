package utils;

import java.util.Random;

public class Utils {

	public static int getRandomI() {
		Random r = new Random();
		int low = 0;
		int high = Constants.GAME_COLUMNS;
		return r.nextInt(high-low) + low;
	}
	
	public static int getRandomJ() {
		Random r = new Random();
		int low = 0;
		int high = Constants.GAME_ROWS;
		return r.nextInt(high-low) + low;
	}
}
