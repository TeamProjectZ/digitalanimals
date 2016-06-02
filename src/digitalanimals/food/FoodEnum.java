/** 
 * Project: DigitalAnimals
 * FoodEnum.java  
 */
package digitalanimals.food;


/**
 * @author Bondarenko Anton Michailovich
 * @version 1.0
 *
 */
public enum FoodEnum {
	CHICKEN("Chicken",FoodType.MEAT,100,5,10),
	BEEF("Beef",FoodType.MEAT,180,7,60),
	WHEAT("Wheat",FoodType.OTHER,40,3,5),
	FOLIAGE("Foliage",FoodType.OTHER,10,2,3);
	
	private String name;
	private FoodType type;
	private int energy;
	private int price;
	private int size;
	
	private FoodEnum(String name, FoodType type, int energy, int size, int price){
		this.name=name;
		this.type=type;
		this.energy=energy;
		this.size=size;
		this.price=price;
	}
	
	/** Getter */
	public FoodType getType() {
		return type;
	}
	
	/** Getter */
	public int getEnergy() {
		return energy;
	}
	
	/** Getter */
	public int getPrice() {
		return price;
	}
	
	/** Getter */
	public int getSize() {
		return size;
	}
	
	/** Getter */
	public String getName() {
		return name;
	}
	
}
