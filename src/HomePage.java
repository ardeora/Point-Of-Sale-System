import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class HomePage extends JPanel {

	private SideBar sideBar;
	
	public HomePage() {
		setLayout(null);
		setBounds(0, 0, 1280, 720);
		
		sideBar = new SideBar() {
			public void servePressed() {
				HomePage.this.servePressed();
			};
			
			@Override
			public void managePressed() {
				HomePage.this.managePressed();
			}
		};
		this.add(sideBar);
		
		JLabel background = new JLabel();
		background.setBounds(290, 0, 990, 720);
		background.setIcon(new ImageIcon(getClass().getResource("/ImageAssets/WelcomeScreen.png")));
		this.add(background);
		
	}
	
	public void close() {
		this.setVisible(false);
	}
	
	public void open() {
		this.setVisible(true);
	}
	
	public void servePressed() {
		
	}
	
	public void managePressed() {
		
	}
}
