/**
 * Project: DigitalAnimals FoodType.java
 */
package digitalanimals.food;

/**
 * @author Bondarenko Anton Michailovich
 * @version 1.0
 *
 */
public enum FoodType {
	MEAT("meat"), OTHER("other");
	private String typeName;

	private FoodType(String typeName) {
		this.typeName = typeName;
	}

	/** Getter */
	public String getTypeName() {
		return typeName;
	}

}
