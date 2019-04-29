import java.awt.Color;
import java.awt.event.MouseEvent;

import javax.swing.JPanel;

public class SettingsView extends JPanel {

	private Database database;
	private SettingsWindow addUser;
	private SettingsWindow deleteUser;
	private SettingsWindow editPassword;
	private HoverableButton backButton;
	private AlertMessage alertMessage;
	
	public SettingsView(Database database) {
		this.database = database;
		setLayout(null);
		setBounds(290, 0, 990, 720);
		setBackground(new Color(49, 50, 62));
		this.setVisible(false);
		
		alertMessage = new AlertMessage(243,212) {
			@Override
			public void okPressed() {
				// TODO Auto-generated method stub
				super.okPressed();
				SettingsView.this.addUser.getUserName().setVisible(true);
				SettingsView.this.addUser.getPassword().setVisible(true);
				SettingsView.this.addUser.getUserName().setText("");
				SettingsView.this.addUser.getPassword().setText("");
				SettingsView.this.addUser.getSubmit().setVisible(true);
				
				SettingsView.this.deleteUser.getUserName().setVisible(true);
				SettingsView.this.deleteUser.getUserName().setText("");;
				SettingsView.this.deleteUser.getSubmit().setVisible(true);
				
				SettingsView.this.editPassword.getUserName().setVisible(true);
				SettingsView.this.editPassword.getPassword().setVisible(true);
				SettingsView.this.editPassword.getUserName().setText("");
				SettingsView.this.editPassword.getPassword().setText("");
				SettingsView.this.editPassword.getSubmit().setVisible(true);
			}
		};
		this.add(alertMessage);
		
		this.addUser = new SettingsWindow(216, 335) {
			@Override
			public void clickArea1Pressed() {
				deleteUser.open();
			}
			
			@Override
			public void clickArea2Pressed() {
				editPassword.open();
			}
			
			@Override
			public void submitPressed() {
				SettingsView.this.addUser();
			}
		};
		addUser.setBack("AddUser");
		this.add(addUser);
		
		
		this.deleteUser = new SettingsWindow(97, 335) {
			@Override
			public void clickArea1Pressed() {
				addUser.open();
			}
			
			@Override
			public void clickArea2Pressed() {
				editPassword.open();
			}
			
			@Override
			public void submitPressed() {
				SettingsView.this.deleteUser();
			}
		};
		deleteUser.setBack("DeleteUser");
		deleteUser.noPassword();
		deleteUser.close();
		this.add(deleteUser);
		
		
		
		this.editPassword = new SettingsWindow(97, 216) {
			@Override
			public void clickArea1Pressed() {
				addUser.open();
			}
			
			@Override
			public void clickArea2Pressed() {
				deleteUser.open();
			}
			
			@Override
			public void submitPressed() {
				SettingsView.this.editUser();
			}
		};
		editPassword.setBack("EditUser");
		editPassword.close();
		this.add(editPassword);
	
		this.backButton = new HoverableButton("/ImageAssets/BackButton.png", "/ImageAssets/BackButton.png") {
			@Override
			public void mousePressed(MouseEvent arg0) {
				SettingsView.this.close();
			}
		};
		backButton.setBounds(15, 15, 30, 30);
		this.add(backButton);
		

		
	}

	public void open() {
		this.setVisible(true);
	}

	public void close() {
		setVisible(false);
		
	}
	
	public void addUser() {
		String username = addUser.getUserName().getText();
		String password = addUser.getPassword().getText();
		if (database.existingUser(username)) {
			addUser.getUserName().setVisible(false);
			addUser.getPassword().setVisible(false);
			addUser.getSubmit().setVisible(false);
			alertMessage.setMessage("ExistingUser");
		} else {
			addUser.getUserName().setVisible(false);
			addUser.getPassword().setVisible(false);
			addUser.getSubmit().setVisible(false);
			database.addUser(username, password);
			alertMessage.setMessage("UserAddSuccess");
		}
	}

	public void deleteUser() {
		String userDelete = deleteUser.getUserName().getText();
		if (database.existingUser(userDelete)) {
			deleteUser.getUserName().setVisible(false);
			deleteUser.getSubmit().setVisible(false);
			alertMessage.setMessage("ServerDeleteSuccess");
			database.deleteUser(userDelete);
		} else {
			deleteUser.getUserName().setVisible(false);
			deleteUser.getSubmit().setVisible(false);
			alertMessage.setMessage("UserDoesNotExist");
		}
	}

	public void editUser() {
			String username = editPassword.getUserName().getText();
			String password = editPassword.getPassword().getText();
			if (database.existingUser(username)) {
				editPassword.getUserName().setVisible(false);
				editPassword.getPassword().setVisible(false);
				editPassword.getSubmit().setVisible(false);
				alertMessage.setMessage("PasswordUpdateSuccess");
				database.editPassword(username, password);
			} else {
				editPassword.getUserName().setVisible(false);
				editPassword.getPassword().setVisible(false);
				editPassword.getSubmit().setVisible(false);
				alertMessage.setMessage("UserDoesNotExist");
			}
	}
}
