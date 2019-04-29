import java.awt.Color;
import java.awt.Font;
import java.awt.event.MouseEvent;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

public class AlertMessage extends JPanel {
	
	private JLabel background;
	private JLabel notification;
	
	public AlertMessage(int x, int y) {
		setLayout(null);
		setBounds(x, y, 504, 303);
		setVisible(false);
		HoverableButton ok = new HoverableButton("/ImageAssets/OKNotHover.png", "/ImageAssets/OKHover.png") {
			@Override
			public void mousePressed(MouseEvent arg0) {
				AlertMessage.this.setVisible(false);
				AlertMessage.this.okPressed();
				AlertMessage.this.setNotification("");
			}
		};
		ok.setBounds(371, 250, 108, 32);
		this.add(ok);
		
		this.notification = new JLabel("",SwingConstants.CENTER);
		notification.setFont(new Font("Arial", Font.PLAIN, 21));
		notification.setForeground(Color.white);
		notification.setBounds(111, 170, 289, 36);
		this.add(notification);
		
		this.background = new JLabel();
		background.setBounds(0, 0, 504, 303);
		this.add(background);
		
		
	}
	
	public void okPressed() {
		
	}
	
	public void setNotification(String string) {
		notification.setVisible(true);
		notification.setText(string);
	}
	
	public void setMessage(String fileName) {
		setVisible(true);
		this.background.setIcon(new ImageIcon(getClass().getResource("/ImageAssets/"+fileName+".png")));
	}

}
