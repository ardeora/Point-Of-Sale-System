import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.MouseEvent;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableModel;

public class CheckOutView extends JPanel {
	
	private Database database;
	private Menu menu;
	private JTable lineItems;
	private int tableID;
	private int rowSize;
	private int discount;
	private int tip;
	public int cashPaid;
	private JLabel subTotal;
	private JLabel discountLabel;
	private JLabel tableNo;
	private JLabel date;
	private JLabel salesTax;
	private JLabel total;
	private JLabel totalWithTip;
	private HoverableButton discountButton;
	private DefaultTableModel model;
	private HoverableButton tipButton;
	private HoverableButton cashButton;
	private HoverableButton cardButton;
	private InputMessage tipWindow;
	private InputMessage discountWindow;
	private InputMessage cardWindow;
	private InputMessage cashWindow;
	private JLabel background;
	private String username;
	private HoverableButton backButton;
	private AlertMessage sucessCheckOut;
	private Object[][] tableData; 
	
	public CheckOutView(Database database) {
		setLayout(null);
		setBounds(290, 0, 990, 720);
		this.setVisible(false);
		this.database = database;
		this.menu = new Menu();
		
		
		tipWindow = new InputMessage("TipInput") {
			@Override
			public void inputPressed(String input) {
				CheckOutView.this.tip = Integer.parseInt(input);
				CheckOutView.this.updateUserInterface();
			}
		};
		this.add(tipWindow);
		
		discountWindow = new InputMessage("DiscountInput") {
			@Override
			public void inputPressed(String input) {
				CheckOutView.this.discount = Integer.parseInt(input);
				CheckOutView.this.updateUserInterface();
			}
		};
		this.add(discountWindow);
		
		cardWindow = new InputMessage("CardInput") {
			@Override
			public void inputPressed(String input) {
				CheckOutView.this.removeTable(0,"");
			}
		};
		cardWindow.cardLayout();
		this.add(cardWindow);
		
		cashWindow = new InputMessage("CashInput") {
			public void inputPressed(String input) {
				CheckOutView.this.removeTable(1, input);
			};
		};
		this.add(cashWindow);
		
		sucessCheckOut = new AlertMessage(243, 168) {
			@Override
			public void okPressed() {
				CheckOutView.this.openTableLayoutView(username);
			}
		};
		this.add(sucessCheckOut);
		this.subTotal = new JLabel("",SwingConstants.RIGHT);
		subTotal.setBounds(830, 479, 100, 20);
		this.add(subTotal);
		
		this.discountLabel = new JLabel("",SwingConstants.RIGHT);
		discountLabel.setBounds(830, 509, 100, 20);
		this.add(discountLabel);
		
		this.salesTax = new JLabel("",SwingConstants.RIGHT);
		salesTax.setBounds(830, 539, 100, 20);
		this.add(salesTax);
		
		this.total = new JLabel("",SwingConstants.RIGHT);
		total.setBounds(830, 569, 100, 20);
		this.add(total);
		
		this.totalWithTip = new JLabel("",SwingConstants.RIGHT);
		totalWithTip.setBounds(830, 599, 100, 20);
		this.add(totalWithTip);
		
		this.tableNo = new JLabel("",SwingConstants.LEFT);
		tableNo.setBounds(13, 120, 100, 20);
		this.add(tableNo);
		
		this.date = new JLabel("",SwingConstants.RIGHT);
		date.setBounds(825, 120, 150, 20);
		this.add(date);
		
		this.backButton =  new HoverableButton("/ImageAssets/CheckOutBack.png", "/ImageAssets/CheckOutBack.png") {
			@Override
			public void mousePressed(MouseEvent arg0) {
				CheckOutView.this.openTableLayoutView(CheckOutView.this.username);;
			}
		};
		backButton.setBounds(18, 18, 30, 30);
		this.add(backButton);
		
		this.discountButton = new HoverableButton("/ImageAssets/Discount.png", "/ImageAssets/Discount.png") {
			@Override
			public void mousePressed(MouseEvent arg0) {
				CheckOutView.this.discountWindow.open();
			}
		};
		discountButton.setBounds(146, 658, 123, 32);
		this.add(discountButton);
		
		this.tipButton = new HoverableButton("/ImageAssets/AddTip.png", "/ImageAssets/AddTip.png") {
			@Override
			public void mousePressed(MouseEvent arg0) {
				CheckOutView.this.tipWindow.open();
			}
		};
		tipButton.setBounds(16, 658, 123, 32);
		this.add(tipButton);
		
		this.cashButton = new HoverableButton("/ImageAssets/Cash.png", "/ImageAssets/Cash.png") {
			@Override
			public void mousePressed(MouseEvent arg0) {
				CheckOutView.this.cashWindow.open();
			}
		};
		cashButton.setBounds(721, 658, 123, 32);
		this.add(cashButton);
		
		this.cardButton = new HoverableButton("/ImageAssets/Card.png", "/ImageAssets/Card.png") {
			@Override
			public void mousePressed(MouseEvent arg0) {
				CheckOutView.this.cardWindow.open();
			}
		};
		cardButton.setBounds(851, 658, 123, 32);
		this.add(cardButton);
		
		this.model = new DefaultTableModel(); 
		model.addColumn("Col1"); 
		model.addColumn("Col2");
		model.addColumn("Col3");
		model.addColumn("Col4");
		lineItems = new JTable(model);
		lineItems.setFont(new Font("Arial", Font.PLAIN, 14));
		lineItems.setBounds(12, 185, 966, 270);
		lineItems.setEnabled(false);
		lineItems.setRowHeight(22);
	
		background = new JLabel();
		background.setBounds(0, 0, 990, 720);
		background.setIcon(new ImageIcon(getClass().getResource("/ImageAssets/Invoice.png")));
		
		
	}
	
	public void displayCheckOutView(int tableID, String username){
		this.setVisible(true);
		this.tableID = tableID;
		this.username = username;
		
		ArrayList<Integer[]> foodList = database.getFoodList(tableID);
		int count = foodList.size();
		this.rowSize = count;
		this.tableData = new Object[foodList.size()][4];
		
		for (int i = 0; i < count; i++) {
			int foodCode = foodList.get(i)[0];
			int foodQty = foodList.get(i)[1];
			
			tableData[i][0] = menu.getFoodNames().get(foodCode);
			tableData[i][1] = foodQty;
			int price = menu.getFoodPrices().get(foodCode);
			tableData[i][2] = "$" + price + ".00";
			tableData[i][3] = "$" + (price * foodQty)+ ".00";
		}
		Object[] strings = {"Item","Qty","Price","Total"};
		
		
		model.setRowCount(0);
		
		for (int i = 0; i < tableData.length; i++) {
			model.addRow(tableData[i]);
		}

		lineItems.getColumnModel().getColumn(0).setPreferredWidth(660);
		lineItems.getColumnModel().getColumn(1).setPreferredWidth(15);
		lineItems.getColumnModel().getColumn(2).setPreferredWidth(40);
		lineItems.getColumnModel().getColumn(3).setPreferredWidth(66);
		this.add(lineItems);
		this.add(background);
		
		updateUserInterface();
	}
	
	
	public void updateUserInterface() {
		displayDiscountButton();
		setSubTotal(); 
		setTableNo();
		setDate();
		setDiscountLabel();
		setSalesTax();
		setTotal();
		setTotalWithTip();
		
	}
	
	public void setSubTotal() {
		int subTotal = 0;
		this.subTotal.setFont(new Font("Arial", Font.PLAIN, 18));
		for (int i = 0; i < rowSize; i++) {
			String store = (String) lineItems.getValueAt(i, 3);
			subTotal += Integer.parseInt(store.substring(1, 3));
		}
		
		this.subTotal.setText("$" + subTotal +".00");
	}
	
	public void setTableNo() {
		tableNo.setFont(new Font("Arial", Font.PLAIN, 16));
		this.tableNo.setText("Table No: " + this.tableID);
	}
	
	public void setDate() {
		date.setFont(new Font("Arial", Font.PLAIN, 16));
		String orderDate = new SimpleDateFormat("MM/dd/yyyy").format(new Date());
		date.setText("Date: " + orderDate);
	}
	
	public void setDiscountLabel() {
		this.discountLabel.setFont(new Font("Arial", Font.PLAIN, 18));
		String store = this.subTotal.getText();
		String store2 = store.split("\\.")[0].substring(1);
		double discountCalc = (this.discount * Double.parseDouble(store2)) / 100;
		String finalStr = String.format("$%.2f", discountCalc);
		discountLabel.setText(finalStr);
	}
	
	public void setSalesTax() {
		this.salesTax.setFont(new Font("Arial", Font.PLAIN, 18));
		String subtotal = this.subTotal.getText().split("\\.")[0].substring(1);
		String discount = this.discountLabel.getText().split("\\$")[1];
		Double minus = (Integer.parseInt(subtotal) - Double.parseDouble(discount)) * .07;
		String finalStr = String.format("$%.2f", minus);
		salesTax.setText(finalStr);
	}
	
	public void setTotal() {
		this.total.setFont(new Font("Arial", Font.BOLD, 18));
		String subtotal = this.subTotal.getText().split("\\.")[0].substring(1);
		String discount = this.discountLabel.getText().split("\\$")[1];
		String tax = this.salesTax.getText().split("\\$")[1];
		Double calc = Double.parseDouble(subtotal) + Double.parseDouble(tax) - Double.parseDouble(discount);
		String finalStr = String.format("$%.2f", calc);
		total.setText(finalStr);
	}
	
	public void setTotalWithTip() {
		this.totalWithTip.setFont(new Font("Arial", Font.BOLD, 18));
		String subtotal = this.subTotal.getText().split("\\.")[0].substring(1);
		String total = this.total.getText().split("\\$")[1];
		Double calc = ((Double.parseDouble(subtotal) * this.tip)/100) + Double.parseDouble(total);
		String finalStr = String.format("$%.2f", calc);
		totalWithTip.setText(finalStr);
	}
	
	public void displayDiscountButton() {
		if (database.authorizedPersonnel(this.username)) {
			discountButton.setVisible(true);
		} else {
			discountButton.setVisible(false);
		}
	}
	
	public void removeTable(int state, String money) {
		database.writeToPastOrders(this.tableID, this.tableData);
		database.orderCompleted(this.tableID);
		
		if (state == 0) {
			sucessCheckOut.setMessage("PaymentSuccess");;
		} else {
			double moneyEntered = Double.parseDouble(money);
			double totalDue = Double.parseDouble(this.totalWithTip.getText().split("\\$")[1]);
			double calc = moneyEntered - totalDue;
			String finalStr = String.format("$%.2f", calc);
			sucessCheckOut.setMessage("PaymentSuccess");;
			sucessCheckOut.setNotification("Change Tendered: " + finalStr);
		}
		
	}
	

	public void close() {
		this.setVisible(false);
	}
	public void openTableLayoutView(String username) {
		
	}
}
