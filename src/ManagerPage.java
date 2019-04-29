import java.awt.Color;
import java.awt.event.MouseEvent;

import javax.swing.JPanel;

public class ManagerPage extends JPanel {

	private Database database;
	private ManagerLogin managerLogin;
	private TableLayoutView tableLayoutView;
	private MenuView menuView;
	private CheckOutView checkOutView;
	private SideBar sideBar;
	private HoverableButton settings;
	private SettingsView settingsView;
	
	public ManagerPage(Database database) {
		this.database = database;
		setLayout(null);
		setBounds(0, 0, 1280, 720);
		setBackground(Color.WHITE);
		setVisible(false);
		
		settingsView = new SettingsView(database);
		this.add(settingsView);
		
		settings = new HoverableButton("/ImageAssets/Settings.png", "/ImageAssets/Settings.png") {
			@Override
			public void mousePressed(MouseEvent arg0) {
				settingsView.open();
			}
		};
		settings.setBounds(25,665,35,35);
		settings.setVisible(false);
		this.add(settings);
		
		managerLogin = new ManagerLogin(database) {
			@Override
			public void openTableLayoutView(String username) {
				ManagerPage.this.openSettings();
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
				ManagerPage.this.homePressed();;
			}
			
			@Override
			public void servePressed() {
				ManagerPage.this.servePressed();
			}
		};
		
		this.add(sideBar);
		this.add(managerLogin);
		this.add(tableLayoutView);
		this.add(menuView);
		this.add(checkOutView);
	}
	
	public void homePressed() {
		shutdown();
	}

	public void servePressed() {
		shutdown();
	}
	
	public void shutdown() {
		this.setVisible(false);
		managerLogin.setVisible(true);
		tableLayoutView.setVisible(false);
		settingsView.setVisible(false);
		closeSettings();
		menuView.prepareForShutDown();;
	}
	
	public void openSettings() {
		settings.setVisible(true);
	}
	
	public void closeSettings() {
		settings.setVisible(false);
	}
	
	public void open() {
		this.setVisible(true);
	}
}
