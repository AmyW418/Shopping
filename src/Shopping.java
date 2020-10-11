import java.util.Scanner; // Import the Scanner class

/**
 *This class handles the user input and responds to given commands
 *Checks whether given command is acceptable and continues
 *@author Amy Wang, Junhao Shen
 */

public class Shopping {

	/**
	 * I/O for checking out the shopping bag. Calls salesPrice and salesTax methods.
	 * Prints out checkout information to the console.
	 * @param bag  of bag to checkout
	 * @param size of how many items in bag.
	 */
	private void checkoutBag(ShoppingBag bag, int size) {
		if (size == 1) {
			System.out.println("**Checking out 1 item.");
		} else {
			System.out.println("**Checking out " + size + " items.");
		}

		bag.print();
		Double salePrice = bag.salesPrice();
		Double saleTax = bag.salesTax();
		System.out.printf("*Sales total: $%.2f\n", salePrice);
		System.out.printf("*Sales tax: $%.2f\n", saleTax);
		System.out.printf("*Total amount paid: $%.2f\n", salePrice + saleTax);
		bag.emptyBag();
	}

	/**
	 * Handles different commands in I/O for the program. Uses scanner class to read
	 * user input and process commands, calling on respective methods to
	 * successfully add, remove, display, checkout, or quit.
	 */
	public void run() {
		Scanner userInput = new Scanner(System.in);
		ShoppingBag bag = new ShoppingBag();
		System.out.println("Let's start shopping!");

		while (Input.hasNextLine()user) {
			String tokens[] = userInput.nextLine().split(" ");

			if (tokens[0].equals("Q")) { // If command given is Q, follow sequence for Quit
				int size = bag.getSize();
				if (size == 0) {
					System.out.println("Thanks for shopping with us!");
				} else {
					this.checkoutBag(bag, size);
					System.out.println("Thanks for shopping with us!");
				}
				break;

			} else if (tokens[0].equals("A")) { // If command is A, follow sequence for Add
				GroceryItem item = new GroceryItem(tokens[1], Double.parseDouble(tokens[2]),
						Boolean.parseBoolean(tokens[3]));
				bag.add(item);
				System.out.println(tokens[1] + " added to the bag.");

			} else if (tokens[0].equals("R")) { // If command is R, follow sequence for Remove
				GroceryItem item = new GroceryItem(tokens[1], Double.parseDouble(tokens[2]),
						Boolean.parseBoolean(tokens[3]));
				if (bag.remove(item)) {
					System.out.println(tokens[1] + " " + tokens[2] + " removed.");
				} else {
					System.out.println("Unable to remove, this item is not in the bag.");
				}

			} else if (tokens[0].equals("P")) { // If command is P, follow sequence for Display
				int size = bag.getSize();
				if (size == 0) {
					System.out.println("The bag is empty!");
				} else if (size == 1) {
					System.out.println("**You have 1 item in the bag.");
				} else {
					System.out.println("**You have " + size + " items in the bag.");
					bag.print();
					System.out.println("**End of list");
				}

			} else if (tokens[0].equals("C")) { // If command is C, follow sequence for Checkout
				int size = bag.getSize();
				if (size == 0) {
					System.out.println("Unable to checkout, the bag is empty!");
				} else {
					this.checkoutBag(bag, size);
				}

			} else { // Command does not equal Q, A, R, C, or P, therefore it is invalid.
				System.out.println("Invalid Command!");
			}

		}

	}
}