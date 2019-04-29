import java.awt.Color;
import java.util.ArrayList;

import javax.swing.ImageIcon;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JOptionPane;

public class MainFrame extends JFrame {
	private Database database;
	private ServerPage serverPage;
	private HomePage homePage;
	private ManagerPage managerPage;
	
	public MainFrame() {
		super("Escape Restaurant Dining Services");
		setSize(1280, 742);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.database = new Database();
		
		this.homePage = new HomePage() {
			@Override
			public void servePressed() {
				this.close();
				serverPage.open();
			}
			
			@Override
			public void managePressed() {
				this.close();
				managerPage.open();
			}
			
		};
		
		this.serverPage = new ServerPage(database) {
			@Override
			public void homePressed() {
				super.homePressed();
				homePage.open();
			}
			
			@Override
			public void managePressed() {
				super.managePressed();
				managerPage.open();
			}
		};
		
		this.managerPage = new ManagerPage(database) {
			@Override
			public void homePressed() {
				super.homePressed();
				homePage.open();
			}
			
			@Override
			public void servePressed() {
				super.servePressed();
				serverPage.open();
			}
		};
		
		this.add(homePage);
		this.add(managerPage);
		this.add(serverPage);
		this.setVisible(true);
		this.validate();
	}

	public static void main(String[] args) {
		MainFrame trial = new MainFrame();
		Database tester = new Database();
		ArrayList<Integer[]> test = tester.getFoodList(3);
		System.out.print(tester.getPastOrders());
		for (int i = 0; i < test.size(); i++) {
			System.out.println(test.get(i)[0]);
			System.out.println(test.get(i)[1]);
		}
	}

}
