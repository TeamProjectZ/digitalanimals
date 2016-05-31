package digitalanimals.food;

import digitalanimals.food.foods.Beef;
import digitalanimals.food.foods.Chicken;
import digitalanimals.food.foods.Foliage;
import digitalanimals.food.foods.Wheat;

public class FoodFactory {
	private static FoodFactory instance;

	private FoodFactory() {

	}

	public static synchronized FoodFactory getInstance() {
		return (instance = instance == null ? new FoodFactory() : instance);
	}

	public Food getAnimal(FoodEnum foodEnum) {
		Food food = null;
		switch (foodEnum) {
			case CHICKEN:
				food = new Chicken(foodEnum);
				break;
			case BEEF:
				food = new Beef(foodEnum);
				break;
			case WHEAT:
				food = new Wheat(foodEnum);
				break;
			case FOLIAGE:
				food = new Foliage(foodEnum);
				break;
		}
		return food;
	}
}
