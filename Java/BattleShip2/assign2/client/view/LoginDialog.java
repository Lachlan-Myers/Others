package assign2.client.view;

import assign2.client.controller.*;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;


public class LoginDialog extends JDialog {
	
	private MessageBoardFrame frame;
	private JTextField username;
	private JPasswordField pass;
	private JButton login, cancel;
	
	
	public LoginDialog(MessageBoardFrame frame) {
		this.frame = frame;
		setLayout(new GridLayout(3, 2));
		
		username = new JTextField();
		pass = new JPasswordField();
		
		login = new JButton("Login");
		cancel = new JButton("Cancel");
		add(new JLabel("Username"));
		add(username);
		
		add(new JLabel("Password"));
		add(pass);
		
		add(login);
		login.addActionListener(new LoginListener(frame, username, pass));
		
		add(cancel);
		cancel.addActionListener(new CloseListener(this));
		
		setSize(400, 200);
		setVisible(true);
	}
}
