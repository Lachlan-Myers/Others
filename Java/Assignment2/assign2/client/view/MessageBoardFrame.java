package assign2.client.view;

import assign2.client.controller.*;
import assign2.model.*;
import java.awt.*;
import java.rmi.*;
import java.sql.*;

import javax.swing.*;


public class MessageBoardFrame extends JFrame {

	private Assign2 serverConnection;
	private MessageSubjectPanel messages;
	private TopicPanel topics;
	private JTextArea messageBody;
	private boolean isLoggedIn;
	private JMenuBar menuBar;
	private JMenu userMenu;
	private JMenuItem login;
	private JMenuItem register;
	private JMenuItem logout;
	private JMenuItem newTopic;
	
	public MessageBoardFrame(Assign2 sc) {
		try {
			isLoggedIn = false;
			
			serverConnection = sc;
			topics = new TopicPanel(serverConnection, this);
			messages = new MessageSubjectPanel(this);
			
			menuBar = new JMenuBar();
			userMenu = new JMenu("User");
			login = new JMenuItem("Login");
			register = new JMenuItem("Register");
			logout = new JMenuItem("Logout");
			newTopic = new JMenuItem("Create a new Topic");
			
			login.addActionListener(new ShowLoginDialogListener(this));
			register.addActionListener(new ShowRegisterDialogListener(this));
			logout.addActionListener(new ShowLogoutDialogListener(this));
			newTopic.addActionListener(new ShowTopicDialogListener(this));
			
			
			if(isLoggedIn == true)
			{
				userMenu.add(logout);
				userMenu.add(newTopic);
			}
			else
			{
				userMenu.add(login);
				userMenu.add(register);
			}
			
			menuBar.add(userMenu);
			
			
			messageBody = new JTextArea();
			messageBody.setEditable(false);
			messageBody.setBackground(Color.LIGHT_GRAY);
			messageBody.setForeground(Color.WHITE);
			messageBody.setLineWrap(true);
			
			setLayout(new BorderLayout());
			setJMenuBar(menuBar);
			add(topics, BorderLayout.NORTH);
			add(messages, BorderLayout.WEST);
			add(messageBody, BorderLayout.CENTER);
			
			setSize(640, 480);
			setTitle("Message Board");
			setDefaultCloseOperation(EXIT_ON_CLOSE);
			JOptionPane.showMessageDialog(new JOptionPane(), serverConnection.getWelcomeMessage());
			setVisible(true);
		}
		catch(Exception e) {
			/*JOptionPane.showMessageDialog(new JOptionPane(), e.getMessage());*/
			e.printStackTrace();
			System.exit(1);
		}
	}

	public void setMessages(String topic) {
		try {
			JOptionPane.showMessageDialog(new JOptionPane(), serverConnection.getDescription(topic));
			messages.setMessages(serverConnection.getMessages(topic));
			validate();
		}
		catch(Exception e) {
			JOptionPane.showMessageDialog(new JOptionPane(), e.getMessage());
		}
	}
	
	public void showIndividualMessage(String subject) {
		try {
			messageBody.setText(serverConnection.getMessage(subject));
		}
		catch(Exception e) {
			JOptionPane.showMessageDialog(new JOptionPane(), e.getMessage());
		}
	}
	
	public void login(String user, String pass) {
		try {
			if(isLoggedIn)
				JOptionPane.showMessageDialog(new JOptionPane(), "You're already logged in!");
			else {
				if(serverConnection.login(user, pass))
					isLoggedIn = true;
			}
		}
		catch(Exception e) {
			JOptionPane.showMessageDialog(new JOptionPane(), e.getMessage(), "Exception!", 
					JOptionPane.ERROR_MESSAGE);
		}
	}

	public void register(String user, String pass) {
		try {
				if(serverConnection.register(user, pass))
					isLoggedIn = true;			
		}
		catch(Exception e) {
			JOptionPane.showMessageDialog(new JOptionPane(), e.getMessage(), "Exception!", 
					JOptionPane.ERROR_MESSAGE);
		}

		
	}
	
	public void createTopic(String name, String desc) {
		try {
			
				if(serverConnection.createTopic(name, desc))
					JOptionPane.showMessageDialog(new JOptionPane(), "Topic "+ name + "was created.", "Topic Created", 
							JOptionPane.INFORMATION_MESSAGE);
			
		}
		catch(Exception e) {
			JOptionPane.showMessageDialog(new JOptionPane(), e.getMessage(), "Exception!", 
					JOptionPane.ERROR_MESSAGE);
		}
	}
	
	public void logout() {
		JOptionPane.showMessageDialog(new JOptionPane(), "You are now logged out.", "Logout Successful",
				JOptionPane.INFORMATION_MESSAGE);
		isLoggedIn = false;
	}

}
