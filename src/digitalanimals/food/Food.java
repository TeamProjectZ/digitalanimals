package digitalanimals.food;

public abstract class Food {
	private int			id;
	private String		name;
	private int			energy;
	private int			price;
	private int			size;
	private FoodType	type;
	private int			quality;

	/** Getter */
	public int getId() {
		return id;
	}

	/** Getter */
	public String getName() {
		return name;
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
	public FoodType getType() {
		return type;
	}

	/** Getter */
	public int getQuality() {
		return quality;
	}

	/** Setter */
	public void setId(int id) {
		this.id = id;
	}

	/** Setter */
	public void setQuality(int quality) {
		this.quality = quality;
	}

	/** Setter */
	protected void setName(String name) {
		this.name = name;
	}

	/** Setter */
	protected void setEnergy(int energy) {
		this.energy = energy;
	}

	/** Setter */
	protected void setPrice(int price) {
		this.price = price;
	}

	/** Setter */
	protected void setSize(int size) {
		this.size = size;
	}

	/** Setter */
	protected void setType(FoodType type) {
		this.type = type;
	}

}
