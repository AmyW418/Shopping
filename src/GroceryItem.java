/**
 * This class defines a GroceryItem object. Contains the parameterized
 * constructor and the equals and toString methods
 * @author Junhao Shen, Amy Wang
 */

public class GroceryItem {
	private String name;
	private double price;
	private boolean taxable;

	/**
	 * Constructor for a new GroceryItem object
	 * @param name    of new Grocery item
	 * @param price   of new grocery item
	 * @param taxable of new grocery item
	 */
	public GroceryItem(String name, double price, boolean taxable) {
		this.name = name;
		this.price = price;
		this.taxable = taxable;
	}

	/**
	 * Checks if two the objects given are identical Checks for content over
	 * addresses
	 * @param obj of item to compare
	 * @return true if identical, false if not
	 */
	public boolean equals(Object obj) {

		if (obj == this) {
			return true;
		} else if (obj instanceof GroceryItem) { // check for content inside objects
			GroceryItem copy = (GroceryItem) obj;
			Boolean sameName = copy.getName().equals(getName());
			Boolean samePrice = copy.getPrice().equals(getPrice());
			Boolean sameTax = copy.getTaxable().equals(getTaxable());
			return sameName && samePrice && sameTax;
		}
		return false;
	}

	/**
	 * This method converts the content of GroceryItem object to expected string
	 * format
	 * @return the content of GroceryItem object in string format
	 */
	public String toString() {
		String tax = "";
		if (this.taxable) {
			tax = "is taxable";
		} else {
			tax = "tax free";
		}
		String ret = "·" + this.name + ": $" + String.format("%.2f", this.price) + " : " + tax;
		return ret;
	}

	/**
	 * Gets the private variable name and returns it
	 * @return name of item
	 */
	public String getName() {
		return this.name;
	}

	/**
	 * Gets the private variable price and returns it
	 * @return price of item
	 */
	public Double getPrice() {
		return this.price;
	}

	/**
	 * Gets the private variable taxable and returns it
	 * @return true if taxable, false if not taxable
	 */
	public Boolean getTaxable() {
		return this.taxable;
	}

}