import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.MouseEvent;

import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

public class ManagerLogin extends JPanel{

	private JPasswordField userPassword;
	private JTextField userName;
	private Database database;
	private HoverableButton userLogIn;
	private AlertMessage alertMessage;
	
	public ManagerLogin(Database database) {
		super();
		this.database = database;
		setLayout(null);
		setBounds(290, 0, 990, 720);
		setBackground(new Color(49, 50, 62));	
		
		alertMessage = new AlertMessage(243, 210);
		this.add(alertMessage);
	
		
		userName = new JTextField();
		userName.setBounds(482, 274, 200, 30);
		userName.setBackground(new Color(223, 224, 237));
		this.add(userName);
		
		userPassword = new JPasswordField();
		userPassword.setBounds(482, 315, 200, 30);
		userPassword.setBackground(new Color(223, 224, 237));
		this.add(userPassword);
		
		userLogIn = new HoverableButton("/ImageAssets/LoginNotHover.png", "/ImageAssets/LoginHover.png") {
			@Override
			public void mousePressed(MouseEvent arg0) {
				super.mousePressed(arg0);
				String password = new String(userPassword.getPassword());
				
				if (database.validateLogin(userName.getText(), password)) {
					if (database.authorizedPersonnel(userName.getText())) {
						openTableLayoutView(userName.getText());
						close();
					} else {
						alertMessage.setMessage("NotAuthorised");
					}
				} else {
					alertMessage.setMessage("IncorrectLogIn");
				}
			}
		};
		userLogIn.setBounds(406, 430, 198, 49);
		this.add(userLogIn);
	}
	
	public void close() {
		this.userName.setText("");
		this.userPassword.setText("");
		this.setVisible(false);
	}

	public void openTableLayoutView(String username) {
		// Server Page will override this function

	}
	
	@Override
	protected void paintComponent(Graphics arg) {
		// TODO Auto-generated method stub
		super.paintComponent(arg);
		Graphics2D arg0 = (Graphics2D) arg;
		
		arg0.setColor(Color.white);
		arg0.fillRoundRect(243, 195, 504, 330, 20, 20);
		arg0.setColor(Color.BLACK);
		arg0.setFont(new Font("Arial", Font.PLAIN, 32));
		arg0.drawString("Manager Login", 395, 235);
		arg0.setFont(new Font("Arial", Font.PLAIN, 24));
		arg0.drawString("User Name:", 339, 297);
		arg0.drawString("Password:", 356, 338);
		
	}
	
}
