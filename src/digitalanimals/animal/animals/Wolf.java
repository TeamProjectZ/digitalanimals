package digitalanimals.animal.animals;

import digitalanimals.animal.Animal;
import digitalanimals.animal.AnimalEnum;
import digitalanimals.food.Food;

public class Wolf extends Animal {

	public Wolf() {

	}

	public Wolf(AnimalEnum animalEnum) {
		this.setAge(0);
		this.setHealth(animalEnum.getHealth());
		this.setPrice(animalEnum.getPrice());
		this.setFoodType(animalEnum.getFoodType());		
	}

	/* @see digitalanimals.animal.AnimalVital#currentHealth() */
	@Override
	public int currentHealth() {
		// TODO Auto-generated method stub
		return 0;
	}

	/* @see digitalanimals.animal.AnimalVital#eat(digitalanimals.food.Food) */
	@Override
	public void eat(Food food) {
		// TODO Auto-generated method stub

	}

	/* @see digitalanimals.animal.AnimalVital#currentAge() */
	@Override
	public int currentAge() {
		// TODO Auto-generated method stub
		return 0;
	}

	/* @see digitalanimals.animal.AnimalVital#isAlive() */
	@Override
	public boolean isAlive() {
		// TODO Auto-generated method stub
		return false;
	}

}
