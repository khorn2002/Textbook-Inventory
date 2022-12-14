package hw8;

import java.util.HashSet;

public class Inventory {
	
	//declarations
	Textbook root;
	HashSet<Integer> list;
	String output = "";
	
	//constructor
	public Inventory() {
		root = null;
		list = new HashSet<Integer>();
	}
	
	//add a textbook to inventory
	private Textbook addTextbook(Textbook current, int sku) {
		if (current == null) {
			return new Textbook(sku);
		}
		if(sku < current.sku) {
			current.left = addTextbook(current.left, sku);
		}
		else if(sku > current.sku) {
			current.right = addTextbook(current.right, sku);
		}
		else {
			return current;
		}
		return current;
	}
	
	public void addTB(int sku) {
		root = addTextbook(root, sku);
	}
	
	//check for a book
		//if the book is there, return true
		// else, return false
	private boolean containsTB(Textbook current, int sku) {
		if (current == null) {
			return false;
		}
		if (sku == current.sku) {
			return true;
		}
		return sku < current.sku ? containsTB(current.left, sku): containsTB(current.right, sku);
	}
	public boolean containsText(int sku) {
		return containsTB(root, sku);
	}
	
	//return textbook from sku #
	private Textbook getBook(Textbook current, int sku) {
		if (current == null) {
			return null;
		}
		else if (sku == current.sku) {
			return current;
		}
		else if (sku < current.sku) {
			return getBook(current.left, sku);
		}
		else {
			return getBook(current.right, sku);
		}
	}
	
	//delete a book from the tree
	private Textbook findBook(Textbook current, int sku) {
		if (current == null) {
			return null;
		}
		if (sku == current.sku) {
			if (current.left == null && current.right == null) {
			    return null;
			}
			else if (current.right == null) {
			    return current.left;
			}

			else if (current.left == null) {
			    return current.right;
			}
		}
		else if (sku < current.sku) {
			current.left = findBook(current.left, sku);
			return current;
		}
		else {
			current.right = findBook(current.right, sku);
			return current;
		}
		int smallestValue = findSmallestValue(current.right);
		current.sku = smallestValue;
		current.right = findBook(current.right, smallestValue);
		return current;
		
	}
	private int findSmallestValue(Textbook root) {
	    return root.left == null ? root.sku : findSmallestValue(root.left);
	}
	public void delete(int sku) {
		root = findBook(root, sku);
	}
	
	//update textbook name
	public void addName(String name, int sku) {
		
		if (getBook(root, sku) == null) {
			System.out.println("This book has not been added to the system");
		}
		else {
			getBook(root, sku).setTitle(name);
		}
	}
	
	//add number of textbooks
	public void addQuantity(int quantity, int sku) {
		if (getBook(root, sku) == null) {
			System.out.println("This book has not been added to the system");
		}
		else {
			getBook(root, sku).setQuantity(quantity);;
		}
	}
	
	//add price of textbook
	public void addPrice(double price, int sku) {
		if (getBook(root, sku) == null) {
			System.out.println("This book has not been added to the system");
		}
		else {
			getBook(root, sku).setPrice(price);;
		}
	}
	
	//print textbook properties
	public void printBook(int sku) {
		String error = "This book has not been added to the system this is whats happening";
		String book = "";
		if (getBook(root, sku) == null) {
			output = error;
		}
		else {
			if(getBook(root, sku).title.length() >= 12) {
				book = getBook(root, sku).sku + "\t" + getBook(root, sku).title + "\t" + getBook(root, sku).price + "\t\t\t" + getBook(root, sku).quantity + "\n";
			}
			else {
				book = getBook(root, sku).sku + "\t" + getBook(root, sku).title + "\t\t" + getBook(root, sku).price + "\t\t\t" + getBook(root, sku).quantity + "\n";
			}
			
			output = output.concat(book);
		}
	}
	
	//add skus to inventory
	public void traverseInventory(Textbook node) {
		
		if (node != null) {
			traverseInventory(node.left);
			list.add(node.sku);
			traverseInventory(node.right);
		}
	}
}