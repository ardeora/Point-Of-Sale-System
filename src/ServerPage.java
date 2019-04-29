import java.awt.Color;
import java.io.File;
import java.io.PrintWriter;
import java.util.Scanner;

import javax.swing.JFrame;
import javax.swing.JPanel;

public class ServerPage extends JPanel {
	
	private Database database;
	private ServerLogin serverLogin;
	private TableLayoutView tableLayoutView;
	private MenuView menuView;
	private CheckOutView checkOutView;
	private SideBar sideBar;
	
	public ServerPage(Database database) {
		super();
		this.database = database;
		setLayout(null);
		setBounds(0, 0, 1280, 720);
		setBackground(Color.WHITE);
		setVisible(false);
		
		
		serverLogin = new ServerLogin(database) {
			@Override
			public void openTableLayoutView(String username) {
				tableLayoutView.displayTableLayoutView(username);
			}
		};
		
		tableLayoutView = new TableLayoutView(database) {
			@Override
			public void openMenuView(String username, int TableID) {
				menuView.displayMenuView(username, TableID);
			}
		};
		
		menuView = new MenuView(database) {
			public void openTableLayoutView(String username) {
				tableLayoutView.displayTableLayoutView(username);
			}
			
			@Override
			public void openCheckoutView(int tableID, String username) {
				close();
				checkOutView.displayCheckOutView(tableID, username);
			}
		};
		
		checkOutView = new CheckOutView(database) {
			@Override
			public void openTableLayoutView(String username) {
				close();
				tableLayoutView.displayTableLayoutView(username);
			}
		};
		
		sideBar = new SideBar() {
			@Override
			public void homePressed() {
				ServerPage.this.homePressed();
			}
			
			@Override
			public void managePressed() {
				ServerPage.this.managePressed();
			}
		};
		
		this.add(sideBar);
		this.add(serverLogin);
		this.add(tableLayoutView);
		this.add(menuView);
		this.add(checkOutView);
	}
	
	public void homePressed() {
		shutDown();
	}

	public void managePressed() {
		shutDown();
	}
	
	public void shutDown() {
		this.setVisible(false);
		serverLogin.setVisible(true);
		tableLayoutView.setVisible(false);
		menuView.prepareForShutDown();;
	}
	
	public void open() {
		this.setVisible(true);
	}
}
