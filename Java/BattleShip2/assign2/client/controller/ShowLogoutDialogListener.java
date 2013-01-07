package assign2.client.controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import assign2.client.view.MessageBoardFrame;
import assign2.client.view.RegisterDialog;

public class ShowLogoutDialogListener implements ActionListener {

	private MessageBoardFrame frame;
	
	public ShowLogoutDialogListener(MessageBoardFrame f) {
		frame = f;
	}
		
	public void actionPerformed(ActionEvent e) {
			
			frame.logout();
	}

}
