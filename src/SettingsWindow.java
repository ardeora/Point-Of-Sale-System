import java.awt.Color;
import java.awt.Font;
import java.awt.Point;
import java.awt.Rectangle;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class SettingsWindow extends JPanel implements MouseListener {

	private JLabel background;
	private Rectangle clickArea;
	private Rectangle clickAreaTwo;
	private JTextField userName;
	private JTextField password;
	
	private HoverableButton submit;
	
	
	public SettingsWindow(int x,int x2) {
		setLayout(null);
		setBounds(221, 152, 551, 386);
		addMouseListener(this);
		this.background = new JLabel();
		background.setBounds(0, 0, 551, 386);
		
		Font font = new Font("Arial", Font.PLAIN, 16);
		userName = new JTextField();
		userName.setBounds(270,136,140,24);
		userName.setFont(font);
		userName.setBackground(new Color(223, 224, 237));
		this.add(userName);
		
		password = new JTextField();
		password.setBounds(270,173,140,22);
		password.setFont(font);
		password.setBackground(new Color(223, 224, 237));
		this.add(password);
		
		submit = new HoverableButton("/ImageAssets/SubmitNotHover.png", "/ImageAssets/SubmitHover.png") {
			@Override
			public void mousePressed(MouseEvent arg0) {
				SettingsWindow.this.submitPressed();
			}
		};
		
		submit.setBounds(375, 326, 144, 37);
		this.add(submit);
		
		this.clickArea = new Rectangle(x, 0, 119, 31);
		this.clickAreaTwo = new Rectangle(x2, 0, 119, 31);
		this.add(background);
	}
	
	public JTextField getUserName() {
		return userName;
	}

	public JTextField getPassword() {
		return password;
	}
	
	

	public HoverableButton getSubmit() {
		return submit;
	}

	public void noPassword() {
		password.setVisible(false);
	}
	
	public void submitPressed() {
		
	}
	
	public void clickArea1Pressed() {
		
	}
	
	public void clickArea2Pressed() {
		
	}
	
	public void setBack(String path) {
		background.setIcon(new ImageIcon(getClass().getResource("/ImageAssets/" + path+ ".png")));
	}
	
	public boolean clicked(Point point, Rectangle clickArea) {
		if (point.getX() >= clickArea.getX() && point.getX() < (clickArea.getX() + clickArea.getWidth())
				&& point.getY() >= 0 && point.getY() < clickArea.getHeight()) {
			return true;
		} else {
			return false;
		}
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

	public void close() {
		this.password.setText("");
		this.userName.setText("");
		this.setVisible(false);
	}
	
	public void open() {
		this.setVisible(true);
	}
	@Override
	public void mousePressed(MouseEvent arg0) {
		
		Point pointClicked = arg0.getPoint();
		
		System.out.println("X:" + pointClicked.x);
		System.out.println("Y:" + pointClicked.y);
		if (clicked(pointClicked, clickArea)) {
			close();
			clickArea1Pressed();
		} else if (clicked(pointClicked, clickAreaTwo)) {
			close();
			clickArea2Pressed();
		}
		
	}

	@Override
	public void mouseReleased(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}

}
