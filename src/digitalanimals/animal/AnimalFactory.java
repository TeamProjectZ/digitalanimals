/**
 * Project: DigitalAnimals AnimalSelector.java
 */
package digitalanimals.animal;

import digitalanimals.animal.animals.Cat;
import digitalanimals.animal.animals.Elephant;
import digitalanimals.animal.animals.Owl;
import digitalanimals.animal.animals.Peacock;
import digitalanimals.animal.animals.Tiger;
import digitalanimals.animal.animals.Wolf;

/**
 * @author Bondarenko Anton Michailovich
 * @version 1.0
 *
 */
public class AnimalFactory {
	private static AnimalFactory instance;

	private AnimalFactory() {

	}

	public static synchronized AnimalFactory getInstance() {
		return (instance = instance == null ? new AnimalFactory() : instance);
	}

	public Animal getAnimal(AnimalEnum animalEnum) {
		Animal animal = null;
		switch (animalEnum) {
			case CAT:
				animal = new Cat(animalEnum);
				break;
			case ELEPHANT:
				animal = new Elephant(animalEnum);
				break;
			case PEACOCK:
				animal = new Peacock(animalEnum);
				break;
			case TIGER:
				animal = new Tiger(animalEnum);
				break;
			case OWL:
				animal = new Owl(animalEnum);
				break;
			case WOLF:
				animal = new Wolf(animalEnum);
				break;
		}
		return animal;
	}
}
