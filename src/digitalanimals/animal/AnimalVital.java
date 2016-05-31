/**
 * Project: DigitalAnimals AnimalVital.java
 */
package digitalanimals.animal;

import digitalanimals.food.Food;

public interface AnimalVital {
	public int currentHealth();

	public void eat(Food food);

	public int currentAge();

	public boolean isAlive();

}
