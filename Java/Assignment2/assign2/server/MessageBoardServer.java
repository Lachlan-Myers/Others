package assign2.server;


import java.io.*;
import java.net.MalformedURLException;
import java.rmi.*;
import java.rmi.server.*;
import java.sql.*; 
import java.util.*;

import javax.swing.JOptionPane;

import assign2.model.Assign2;



public class MessageBoardServer extends UnicastRemoteObject implements Assign2 {
		
	public MessageBoardServer() throws RemoteException, SQLException {}
	
	public static Connection createConnection() {
		Connection dbConnection;
		final String CONN_STRING = "jdbc:oracle:thin:@emu.cs.rmit.edu.au:1521:GENERAL";
		try {
			
			Class.forName("oracle.jdbc.driver.OracleDriver");
			dbConnection = DriverManager.getConnection(CONN_STRING, "s3237663", "secretariat");
			return dbConnection;
		}
		catch(Exception ex) {
			JOptionPane.showMessageDialog(new JOptionPane(), ex.getMessage(), "Error!",
					JOptionPane.ERROR_MESSAGE);
			System.exit(1);
		}
		return null;
	}
	
	public ArrayList<String> getTopics() throws RemoteException, SQLException,
										ClassNotFoundException {
		ArrayList<String> topics = new ArrayList<String>();
		final String QUERY = "SELECT name FROM topic";
		
		Connection dbConnection = createConnection();
		Statement dbStatement = dbConnection.createStatement();
		ResultSet results = dbStatement.executeQuery(QUERY);
		
		while(results.next()) {
			topics.add(results.getString(1));
		}
		dbConnection.close();
		return topics;
	}
	
	public ArrayList<String> getMessages(String topic) throws RemoteException, SQLException,
														ClassNotFoundException {
		ArrayList<String> messages = new ArrayList<String>();
		final String QUERY = "SELECT subject FROM message WHERE topic = '" + topic + "'";
		Connection dbConnection = createConnection();
		Statement dbStatement = dbConnection.createStatement();
		ResultSet results = dbStatement.executeQuery(QUERY);
		
		while(results.next()) {
			messages.add(results.getString(1));
		}
		dbConnection.close();
		return messages;
	}

	
	public String getWelcomeMessage() throws SQLException, ClassNotFoundException, RemoteException {
		final String QUERY = "SELECT message FROM introduction";
		ResultSet results = runQuery(QUERY);
		results.next();
		String message = results.getString(1);
		return message;
	}
	
	public String getDescription(String topic) throws SQLException, ClassNotFoundException, RemoteException {
		final String QUERY = "SELECT Description FROM Topic WHERE Name = '" + topic + "'";
		ResultSet results = runQuery(QUERY);
		results.next();
		String message = results.getString(1);
		return message;
	}
	
	public String getMessage(String topic) throws SQLException, ClassNotFoundException, RemoteException {
		final String QUERY = "SELECT Text FROM Message WHERE Subject = '" + topic + "'";
		ResultSet results = runQuery(QUERY);
		results.next();
		String message = results.getString(1);
		return message;
	}
	
	public boolean login(String user, String pass) throws SQLException, ClassNotFoundException, RemoteException {
		final String QUERY = "SELECT Username FROM Users WHERE Username = '" + user + "' AND Password = '" + pass + "'";
		ResultSet results = runQuery(QUERY);
		
		if(!results.next())
			return false;
		if(Integer.parseInt(results.getString(4)) == 1) {
			JOptionPane.showMessageDialog(new JOptionPane(), "You're suspended!", "Sorry!",
					JOptionPane.ERROR_MESSAGE);
			return false;
		}
		return true;
	}
	
	public boolean createTopic(String name, String desc) throws SQLException, ClassNotFoundException, RemoteException {
		final String QUERY = "INSERT INTO Topic VALUES('"+name+"','"+desc+"')";
		Connection dbConnection = createConnection();
		Statement dbStatement = dbConnection.createStatement();
		dbStatement.executeUpdate(QUERY);
		return true;
	} 
	
	public boolean register(String name, String pass) throws SQLException, ClassNotFoundException, RemoteException {
		final String QUERY = "INSERT INTO Users VALUES('"+name+"','"+pass+"','User',0)";
		Connection dbConnection = createConnection();
		Statement dbStatement = dbConnection.createStatement();
		dbStatement.executeUpdate(QUERY);
		return true;
	} 
	
	public ResultSet runQuery(String query) throws SQLException {
		Connection dbConnection = createConnection();
		Statement dbStatement = dbConnection.createStatement();
		ResultSet results = dbStatement.executeQuery(query);
		return results;
	}
	
	public static void main(String args[]) {
		try {
			
			//Sets up server
			System.out.print("Setting up server object...");
			MessageBoardServer serverObject = new MessageBoardServer();
			Naming.rebind("rmi://localhost:1099/MessageBoardServer", serverObject);
			System.out.println("ready!");
			
		}
		catch(RemoteException remEx) {
			System.err.println("Remote Exception!");
			System.err.println(remEx.getMessage());
		}
		catch(MalformedURLException urlEx) {
			System.err.println("URLException!");
			System.err.println(urlEx.getMessage());
		}
		catch(Exception ex) {
			ex.printStackTrace();
		}
		
	}

}
