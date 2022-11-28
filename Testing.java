package hw8;

public class Testing {

	public static void main(String[] args) {
		Inventory inventory = new Inventory();
		
		inventory.addTB(123);
		inventory.addTB(234);
		inventory.addTB(345);
		
		System.out.println(inventory.containsText(234));
		
		inventory.delete(124);
		System.out.println(inventory.containsText(234));

		inventory.addName("Pride and Prejudice", 123);
		inventory.addPrice(19.99, 123);
		inventory.addQuantity(10, 123);
		
		inventory.traverseInventory(inventory.root);
		
		System.out.println(inventory.list);
	}
}