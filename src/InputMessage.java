import java.awt.Color;
import java.awt.Font;
import java.awt.event.MouseEvent;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

public class InputMessage extends JPanel{
	private JLabel background;
	private JTextField inputEntered;
	private JPasswordField passwordField;

	public InputMessage(String fileName) {
		setLayout(null);
		setBounds(243, 168, 504, 303);
		setVisible(false);
		
		passwordField = new JPasswordField();
		passwordField.setHorizontalAlignment(JTextField.CENTER);
		passwordField.setFont(new Font("Arial", Font.PLAIN, 20));
		passwordField.setBackground(new Color(118, 119, 142));
		passwordField.setForeground(Color.white);
		passwordField.setBounds(202, 145, 100, 37);
		passwordField.setVisible(false);
		this.add(passwordField);
		
		inputEntered = new JTextField();
		inputEntered.setHorizontalAlignment(JTextField.CENTER);
		inputEntered.setFont(new Font("Arial", Font.PLAIN, 20));
		inputEntered.setBackground(new Color(118, 119, 142));
		inputEntered.setForeground(Color.white);
		inputEntered.setBounds(202, 145, 100, 37);
		this.add(inputEntered);
		
		
		HoverableButton ok = new HoverableButton("/ImageAssets/OKNotHover.png", "/ImageAssets/OKHover.png") {
			@Override
			public void mousePressed(MouseEvent arg0) {
				InputMessage.this.setVisible(false);
				String input = InputMessage.this.inputEntered.getText();
				if (input.compareTo("")==0) {
					input = 0+"";
				}
				InputMessage.this.inputPressed(input);
				InputMessage.this.inputEntered.setText("");
				InputMessage.this.passwordField.setText("");;
			}
		};
		ok.setBounds(371, 250, 108, 32);
		this.add(ok);

		this.background = new JLabel();
		background.setBounds(0, 0, 504, 303);
		this.background.setIcon(new ImageIcon(getClass().getResource("/ImageAssets/"+fileName+".png")));
		this.add(background);
	}

	public void open() {
		setVisible(true);
	}
	
	public void cardLayout() {
		passwordField.setVisible(true);
	}
	
	public void inputPressed(String input) {
		
	}
}
