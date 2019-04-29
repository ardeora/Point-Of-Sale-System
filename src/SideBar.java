import java.awt.Color;
import java.awt.event.MouseEvent;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class SideBar extends JPanel {

	private HoverableButton home;
	private HoverableButton serve;
	private HoverableButton manage;
	
	public SideBar() {
		setLayout(null);
		setBounds(0,0,290,720);
		setButtons();
		
		JLabel background = new JLabel();
		background.setBounds(0,0,290,720);
		background.setIcon(new ImageIcon(getClass().getResource("/ImageAssets/SideBar.png")));
		this.add(background);
		
		
	}
	
	public void setButtons() {
		home = new HoverableButton("/ImageAssets/HomeNotHover.png", "/ImageAssets/HomeHover.png") {
			@Override
			public void mousePressed(MouseEvent arg0) {
				SideBar.this.homePressed();
			}
		};
		home.setBounds(26,272,240,48);
		this.add(home);
		
		serve = new HoverableButton("/ImageAssets/ServeNotHover.png", "/ImageAssets/ServeHover.png") {
			@Override
			public void mousePressed(MouseEvent arg0) {
				SideBar.this.servePressed();
			}
		};
		serve.setBounds(26,351,240,48);
		this.add(serve);
		
		manage = new HoverableButton("/ImageAssets/ManageNotHover.png", "/ImageAssets/ManageHover.png") {
			@Override
			public void mousePressed(MouseEvent arg0) {
				SideBar.this.managePressed();
			}
		};
		manage.setBounds(26,430,240,48);
		this.add(manage);
	}
	
	public void homePressed() {

	}

	public void servePressed() {

	}
	
	public void managePressed() {

	}
	
}
