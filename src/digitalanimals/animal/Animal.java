package digitalanimals.animal;

import digitalanimals.food.FoodType;

public abstract class Animal implements AnimalVital {
	private int				id;
	private int				age;
	private int				price;
	private int				health;
	private String			nick;
	private FoodType		foodType;
	private AnimalActivity	activity;

	/** Getter */
	public int getId() {
		return id;
	}

	/** Getter */
	public int getAge() {
		return age;
	}

	/** Getter */
	public int getHealth() {
		return health;
	}

	/** Getter */
	public String getNick() {
		return nick;
	}

	/** Getter */
	public FoodType getFoodType() {
		return foodType;
	}

	/** Getter */
	public AnimalActivity getActivity() {
		return activity;
	}

	/** Getter */
	public int getPrice() {
		return price;
	}

	/** Setter */
	protected void setPrice(int price) {
		this.price = price;
	}

	/** Setter */
	protected void setId(int id) {
		this.id = id;
	}

	/** Setter */
	protected void setAge(int age) {
		this.age = age;
	}

	/** Setter */
	protected void setHealth(int health) {
		this.health = health;
	}

	/** Setter */
	protected void setNick(String nick) {
		this.nick = nick;
	}

	/** Setter */
	protected void setFoodType(FoodType foodType) {
		this.foodType = foodType;
	}

	/** Setter */
	protected void setActivity(AnimalActivity activity) {
		this.activity = activity;
	}

}
