package assign2.client;

import assign2.client.view.*;
import assign2.model.*;
import java.net.MalformedURLException;
import java.rmi.*;
import java.rmi.registry.*;
import java.util.*;

import javax.swing.JOptionPane;

public class MessageBoardClient {
	public static void main(String args[]) {
		if(args.length != 1) {
			System.err.println("Usage: java MessageBoardClient [server ip]");
		}
		else {
			try {
				Assign2 serverConnection = 
					(Assign2)Naming.lookup("rmi://" + args[0] + ":1099/MessageBoardServer");
				
				MessageBoardFrame f = new MessageBoardFrame(serverConnection);
				
				
			}
			catch(MalformedURLException urlEx) {
				JOptionPane.showMessageDialog(new JOptionPane(), "URL Error!", "URLException", 
						JOptionPane.ERROR_MESSAGE);
			}
			catch(ConnectException connectEx) {
				JOptionPane.showMessageDialog(new JOptionPane(), "Connection refused! Exiting...", 
						"ConnectException!", JOptionPane.ERROR_MESSAGE);
			}
			catch(Exception ex) {
				ex.printStackTrace();
			}
		}
	}
}
