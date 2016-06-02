/**
 * Project: DigitalAnimals AnimalName.java
 */
package digitalanimals.animal;

import digitalanimals.food.FoodType;

public enum AnimalEnum {
	CAT("Cat", FoodType.MEAT, 100, 5000),
	ELEPHANT("Elephant", FoodType.OTHER, 1000, 24000),
	PEACOCK("Peacock", FoodType.OTHER, 200, 10000),
	WOLF("Wolf",FoodType.MEAT, 400, 8000), 
	OWL("Owl", FoodType.MEAT, 100, 15000), 
	TIGER("Tiger", FoodType.MEAT, 600, 20000);

	private String		name;
	private FoodType	foodType;
	private int health;
	private int price;

	private AnimalEnum(String name, FoodType foodType, int health, int price) {
		this.name = name;
		this.foodType = foodType;
		this.health = health;
		this.price = price;		
	}

	/** Getter */
	public String getName() {
		return name;
	}

	/** Getter */
	public FoodType getFoodType() {
		return foodType;
	}
	
	/** Getter */
	public int getHealth() {
		return health;
	}
	
	/** Getter */
	public int getPrice() {
		return price;
	}

}
