/**
 * This class defines the ShoppingBag item. It contains methods of actions that
 * occur in the bag such as add, remove, etc.
 * @author Amy Wang, Junhao Shen
 */

public class ShoppingBag {
	private GroceryItem[] bag; // array-based implementation of the bag
	private int size; // number of items currently in the bag
	private int capacity; // current capacity

	/**
	 * Creates a new Shopping Bag object. Initializes array with a size 5, size is
	 * set to 0, and capacity is set to 5.
	 */
	public ShoppingBag() {
		this.bag = new GroceryItem[5];
		this.size = 0;
		this.capacity = 5;
	}

	/**
	 * Finds an item in the shopping bag. Calls on the equals method in GroceryItem
	 * class
	 * @param item to find in the bag.
	 * @return index of item in array if found, -1 if not found.
	 */
	private int find(GroceryItem item) {
		for (int i = 0; i < this.size; i++) {
			boolean found = this.bag[i].equals(item);
			if (found) {
				return i;
			}
		}
		return -1;
	}

	/**
	 * Increases the capacity of the shopping bag by 5. Initializes new array with
	 * larger capacity. Puts items from old bag into new bag and set new bag as
	 * current bag
	 */
	private void grow() {
		this.capacity = this.capacity + 5;
		GroceryItem[] newBag = new GroceryItem[this.capacity];
		for (int i = 0; i < this.size; i++) {
			newBag[i] = this.bag[i];
		}
		this.bag = newBag;
	}

	/**
	 * Adds a grocery item to the shopping bag. First checks amount of items in bag.
	 * If bag is full, calls grow method to increase capacity, and then adds item.
	 * @param item to add to the bag.
	 */
	public void add(GroceryItem item) {
		if (this.size + 1 > this.capacity) {
			this.grow();
		}
		this.bag[this.size] = item;
		this.size = this.size + 1;
	}

	/**
	 * Removes a grocery item from the shopping bag. Calls find method to get index
	 * of item to be removed. Removed item is replaced with last item in the bag,
	 * and the last item reference is now null.
	 * @param item to remove from the bag.
	 * @return true if successfully removed, false if item not found
	 */
	public boolean remove(GroceryItem item) {
		int index = this.find(item);
		if (index == -1) {
			return false;
		} else {
			GroceryItem temp = this.bag[this.size - 1];
			this.bag[index] = temp;
			this.size = this.size - 1;
			this.bag[this.size] = null;
			return true;
		}
	}

	/**
	 * Calculates the sale price of items in the bag.
	 * @return salesTotal - the sum of sale price of all items in the bag.
	 */
	public double salesPrice() {
		double salesTotal = 0;
		for (int i = 0; i < this.size; i++) {
			if (this.bag[i] != null) {
				salesTotal = salesTotal + this.bag[i].getPrice();
			}
		}
		return salesTotal;
	}

	/**
	 * Calculates the sum of tax of all taxable items in the bag.
	 * @return taxTotal - the sum of tax of all taxable items in the bag.
	 */
	public double salesTax() {
		double taxTotal = 0;
		for (int i = 0; i < this.size; i++) {
			if (this.bag[i] != null) {
				if (this.bag[i].getTaxable()) {
					double taxPercentage = 0.06625;
					taxTotal = taxTotal + (taxPercentage * this.bag[i].getPrice());
				}
			}
		}
		return taxTotal;
	}

	/**
	 * Prints out the items in the shopping bag. Calls toString method of the
	 * GroceryItem class to get string format.
	 */
	public void print() {
		for (int i = 0; i < this.size; i++) {
			if (this.bag[i] != null) {
				String itemDescription = this.bag[i].toString();
				System.out.println(itemDescription);
			}
		}
	}

	/**
	 * Gets the amount of current items in the shopping bag.
	 * @return size of items currently in bag.
	 */
	public int getSize() {
		return this.size;
	}

	/**
	 * Empties out the shopping bag and changes it back to default settings.
	 * Reinitializes array of size 5, size of 0, and capacity of 5.
	 */
	public void emptyBag() {
		this.bag = new GroceryItem[5];
		this.size = 0;
		this.capacity = 5;
	}

	/**
	 * Test bed main for testing test cases in test document
	 * @param args of main function
	 */
	public static void main(String args[]) {

		// test shopping bag constructor
		ShoppingBag testbag = new ShoppingBag();

		// test GroceryItem constructor
		GroceryItem testItem1 = new GroceryItem("bread", 3.97, false);
		GroceryItem testItem2 = new GroceryItem("apples", 2.84, false);
		GroceryItem testItem3 = new GroceryItem("napkins", 4.5, true);

		// testing add method
		testbag.add(testItem1);
		testbag.add(testItem2);
		testbag.print(); // print out contents of shopping bag

		// testing salesTax method
		System.out.printf("Case 1: $%.2f\n", testbag.salesTax()); // tax without taxable items
		testbag.add(testItem3); // add a taxable item to shopping bag
		System.out.printf("Case 2: $%.2f\n", testbag.salesTax()); // tax with taxable items

		// testing remove method
		testbag.print(); // prints out order of contents in bag
		System.out.println("Case 1: " + testbag.remove(testItem1)); // remove from beginning of bag
		testbag.print(); 
		System.out.println("Case 2: " + testbag.remove(testItem2)); // remove from end of bag
		testbag.print(); 
		System.out.println("Case 3: " + testbag.remove(testItem1)); // remove an item that isn't in bag
		testbag.print();

		// testing grow method
		testbag.emptyBag(); // reset bag to default settings
		System.out.println("Before growing: " + testbag.capacity); // initial capacity
		testbag.add(testItem1);
		testbag.add(testItem1);
		testbag.add(testItem1);
		testbag.add(testItem1);
		testbag.add(testItem1);
		testbag.add(testItem1); // add items to force bag to grow on its own
		System.out.println("Case 1: " + testbag.capacity);
		testbag.print(); // print items in bag to see if contains everything
		testbag.grow(); // test grow method directly
		System.out.println("Case 2: " + testbag.capacity); // capacity after growing

	}
}