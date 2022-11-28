package hw8;

import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JButton;

public class Main {

	private JFrame frame;
	private JTextField quantity;
	private JTextField price;
	private JTextField title;
	private JTextField SKU;
	private JButton add_button;
	private JButton remove_button;
	private JTextArea textArea;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Main window = new Main();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	/**
	 * Create the application.
	 */
	public Main() {
		initialize();
		Inventory inventory = new Inventory();
		
		add_button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (SKU.getText().matches(".*[A-Za-z].*") || price.getText().matches(".*[A-Za-z].*") || quantity.getText().matches(".*[A-Za-z].*") || (SKU.getText().matches("")) || (price.getText().matches("")) || (quantity.getText().matches("")) || (title.getText().matches(""))) {
					JOptionPane.showMessageDialog(frame, "An error was found in your input.");
					SKU.setText("");
					title.setText("");
					price.setText("");
					quantity.setText("");
				} else {
					int sku = Integer.parseInt(SKU.getText());
					double priceVal = Double.parseDouble(price.getText());
					int quantityVal = Integer.parseInt(quantity.getText());
					String name = title.getText();
					
					inventory.output = "";
					inventory.addTB(sku);
					inventory.addName(name, sku);
					inventory.addPrice(priceVal, sku);
					inventory.addQuantity(quantityVal, sku);
					inventory.traverseInventory(inventory.root);
					
					for (Integer num: inventory.list) {
						inventory.printBook(num);
					}
					
					//reset input boxes for user
					SKU.setText("");
					title.setText("");
					price.setText("");
					quantity.setText("");
				}
				
				//set text to output
				textArea.setText(inventory.output);
			}
		});
		
		
		remove_button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int sku = Integer.parseInt(SKU.getText());
				
				inventory.output = "";
				inventory.delete(sku);
				inventory.list.remove(sku);
				
				
				for (Integer num: inventory.list) {
					inventory.printBook(num);
				}
				
				//reset input boxes for user
				SKU.setText("");
				title.setText("");
				price.setText("");
				quantity.setText("");
				
				//set text to output
				textArea.setText(inventory.output);
			}
		});	
	}
	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 675, 403);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JLabel HEADER_LABEL = new JLabel("INVENTORY");
		HEADER_LABEL.setBounds(285, 6, 79, 16);
		frame.getContentPane().add(HEADER_LABEL);
		
		JLabel SKU_LABEL = new JLabel("SKU:");
		SKU_LABEL.setBounds(6, 40, 39, 16);
		frame.getContentPane().add(SKU_LABEL);
		
		JLabel TITLE_LABEL = new JLabel("TITLE:");
		TITLE_LABEL.setBounds(6, 66, 39, 16);
		frame.getContentPane().add(TITLE_LABEL);
		
		JLabel PRICE_LABEL = new JLabel("PRICE:");
		PRICE_LABEL.setBounds(295, 40, 61, 16);
		frame.getContentPane().add(PRICE_LABEL);
		
		JLabel QUANTITY_LABEL = new JLabel("QUANTITY:");
		QUANTITY_LABEL.setBounds(294, 66, 70, 16);
		frame.getContentPane().add(QUANTITY_LABEL);
		
		quantity = new JTextField();
		quantity.setBounds(376, 61, 203, 26);
		frame.getContentPane().add(quantity);
		quantity.setColumns(10);
		
		price = new JTextField();
		price.setBounds(376, 35, 203, 26);
		frame.getContentPane().add(price);
		price.setColumns(10);
		
		title = new JTextField();
		title.setBounds(88, 61, 203, 26);
		frame.getContentPane().add(title);
		title.setColumns(10);
		
		SKU = new JTextField();
		SKU.setBounds(88, 35, 203, 26);
		frame.getContentPane().add(SKU);
		SKU.setColumns(10);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(6, 132, 663, 237);
		frame.getContentPane().add(scrollPane);
		
		textArea = new JTextArea();
		scrollPane.setViewportView(textArea);
		
		JLabel SKU_LABEL_HEADER = new JLabel("SKU");
		SKU_LABEL_HEADER.setBounds(16, 104, 61, 16);
		frame.getContentPane().add(SKU_LABEL_HEADER);
		
		JLabel TITLE_LABEL_HEADER = new JLabel("TITLE");
		TITLE_LABEL_HEADER.setBounds(120, 104, 61, 16);
		frame.getContentPane().add(TITLE_LABEL_HEADER);
		
		JLabel PRICE_LABEL_HEADER = new JLabel("PRICE");
		PRICE_LABEL_HEADER.setBounds(285, 104, 61, 16);
		frame.getContentPane().add(PRICE_LABEL_HEADER);
		
		JLabel QUANTITY_LABEL_HEADER = new JLabel("QUANTITY");
		QUANTITY_LABEL_HEADER.setBounds(558, 104, 79, 16);
		frame.getContentPane().add(QUANTITY_LABEL_HEADER);
		
		add_button = new JButton("add");
		add_button.setBounds(591, 35, 78, 26);
		frame.getContentPane().add(add_button);
		
		remove_button = new JButton("remove");
		remove_button.setBounds(591, 61, 78, 29);
		frame.getContentPane().add(remove_button);
	}
}