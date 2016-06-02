package digitalanimals.food.foods;

import digitalanimals.food.Food;
import digitalanimals.food.FoodEnum;

public class Foliage extends Food {
	public Foliage() {

	}

	public Foliage(FoodEnum food) {
		this.setName(food.getName());
		this.setEnergy(food.getEnergy());
		this.setPrice(food.getPrice());
		this.setSize(food.getSize());
		this.setType(food.getType());
		this.setQuality(1);
	}
}
