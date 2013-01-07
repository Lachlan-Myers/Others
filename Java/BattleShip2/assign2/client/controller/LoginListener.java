package assign2.client.controller;

import assign2.client.view.*;
import java.awt.event.*;
import javax.swing.JTextField;
public class LoginListener implements ActionListener {
	private String username, password;
	private MessageBoardFrame frame;
	
	public LoginListener(MessageBoardFrame f, JTextField user, JTextField pass) {
		frame = f;
		username = user.getText();
		password = pass.getText();
	}
	
	public void actionPerformed(ActionEvent e) {
		frame.login(username, password);
	}
}
