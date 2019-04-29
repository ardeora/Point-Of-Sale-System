import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.xml.crypto.Data;

public class MenuView extends JPanel implements MouseListener {

	private EntreeView entreeView;
	private SidesView sidesView;
	private Database database;
	private int tableID;
	private int lastCustomer;
	private String username;
	private JLabel custNumDisplayer;
	private HoverableButton checkOutButton;
	private HoverableButton placeOrderButton;
	private AlertMessage alertMessage;

	public MenuView(Database database) {
		super();
		this.database = database;
		setLayout(null);
		setBounds(290, 0, 990, 720);
		setBackground(new Color(49, 50, 62));
		
		alertMessage = new AlertMessage(243,168);
		this.add(alertMessage);
		
		HoverableButton backButton = new HoverableButton("/ImageAssets/BackNotHover.png", "/ImageAssets/BackHover.png") {
			@Override
			public void mousePressed(MouseEvent arg0) {
				close();
				entreeView.setVisible(true);
				sidesView.setVisible(false);
				System.out.print("USERNAME: " + username);
				openTableLayoutView(username);
			}
		};
		backButton.setBounds(25, 26, 50, 44);
		this.add(backButton);
		
		entreeView = new EntreeView(25, 24);
		sidesView =  new SidesView(24, 24);
		entreeView.setSidesView(sidesView);
		sidesView.setEntreeView(entreeView);
		custNumDisplayer = new JLabel("Customer 1", SwingConstants.RIGHT);
		custNumDisplayer.setFont(new Font("Arial", Font.BOLD, 28));
		custNumDisplayer.setForeground(new Color(50, 50, 62));
		custNumDisplayer.setBounds(555,22,400,50);
		this.add(custNumDisplayer);
		this.add(entreeView);
		this.add(sidesView);
		setupBottomButtons();
		this.setVisible(false);
	}
	
	private void setupBottomButtons() {
		placeOrderButton = new HoverableButton("/ImageAssets/PlaceOrderNotHover.png", "/ImageAssets/PlaceOrderHover.png") {
			@Override
			public void mousePressed(MouseEvent arg0) {
				if (MenuView.this.lastCustomer < 4) {

					ArrayList<Integer[]> EntreeOrders = MenuView.this.entreeView.getOrderEntered();
					int entreeCount = 0;
					for (int i = 0; i < EntreeOrders.size(); i++) {
						entreeCount += EntreeOrders.get(i)[1];
					}

					if (entreeCount <= 1) {
						ArrayList<Integer[]> SideOrders = MenuView.this.sidesView.getOrderEntered();
						int sidesCount = 0;
						for (int i = 0; i < SideOrders.size(); i++) {
							sidesCount += SideOrders.get(i)[1];
						}
						if (sidesCount <= 2) {
							alertMessage.setMessage("OrderSent");
							String orderDate = new SimpleDateFormat("MM/dd/yyyy").format(new Date());
							String orderTime = new SimpleDateFormat("HH:mm").format(new Date());
							Customer newCustomer = new Customer(tableID, lastCustomer + 1, orderDate, orderTime);
							newCustomer.getFoodlist().addAll(EntreeOrders);
							newCustomer.getFoodlist().addAll(SideOrders);
							database.getOrders().add(newCustomer);
							System.out.print(database.getOrders().get(0));
							database.reserveNewTable(MenuView.this.username, MenuView.this.tableID);
							updateLastCustomer();
							database.updateTablesReserved();
							database.updateOrderFile();
							MenuView.this.entreeView.resetCounters();
							MenuView.this.sidesView.resetCounters();
						} else {
							alertMessage.setMessage("SidesError");
						}

					} else {
						alertMessage.setMessage("OneEntree");;
					}

				} else {
					alertMessage.setMessage("NoSeats");
				}
			}
		};
		placeOrderButton.setBounds(798, 654, 166, 40);
		this.add(placeOrderButton);
		
		checkOutButton = new HoverableButton("/ImageAssets/CheckOutNotHover.png", "/ImageAssets/CheckOutHover.png") {
			@Override
			public void mousePressed(MouseEvent arg0) {
				MenuView.this.openCheckoutView(tableID,username);
			}
		};
		checkOutButton.setBounds(24, 654, 166, 40);
		this.add(checkOutButton);
	}
	
	public void openCheckoutView(int tableID,String username) {
		// Override Function
	}
	
	
	public void displayMenuView(String username, int tableID){
		setVisible(true);
		this.username = username;
		this.tableID = tableID;
		updateLastCustomer();
	}
	
	public void updateLastCustomer() {
		this.lastCustomer = database.getLastCustomer(tableID);
		updateUserInterface();
	}
	public void updateUserInterface() {
		if (lastCustomer == 4) {
			custNumDisplayer.setText("No Seats Available");
		} else {
			custNumDisplayer.setText("Customer " + (lastCustomer + 1));
		}
		
		if (lastCustomer == 0) {
			checkOutButton.setVisible(false);
		} else {
			checkOutButton.setVisible(true);
		}
	}
	
	public void openTableLayoutView(String username) {
		// Server Page will override this function

	}
	
	public void close() {
		setVisible(false);
	}
	
	public void prepareForShutDown() {
		close();
		entreeView.setVisible(true);
		sidesView.setVisible(false);
	}
	@Override
	protected void paintComponent(Graphics arg0) {
		super.paintComponent(arg0);
		arg0.setColor(Color.WHITE);
		arg0.fillRoundRect(0, 0, 990, 720, 30, 30);
	}
	
	@Override
	public void mouseClicked(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseEntered(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseExited(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mousePressed(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseReleased(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}

}
