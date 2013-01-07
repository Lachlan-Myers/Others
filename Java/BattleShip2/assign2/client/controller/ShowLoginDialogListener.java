package assign2.client.controller;

import assign2.client.view.*;
import java.awt.event.*;

public class ShowLoginDialogListener implements ActionListener {
	
	private MessageBoardFrame frame;
	
	public ShowLoginDialogListener(MessageBoardFrame f) {
		frame = f;
	}
	
	public void actionPerformed(ActionEvent e) {
		new LoginDialog(frame);
	}

}
