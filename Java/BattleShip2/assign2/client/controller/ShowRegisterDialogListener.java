package assign2.client.controller;

import assign2.client.view.*;
import java.awt.event.*;

public class ShowRegisterDialogListener implements ActionListener{
		
	private MessageBoardFrame frame;
		
	public ShowRegisterDialogListener(MessageBoardFrame f) {
			frame = f;
	}
		
	public void actionPerformed(ActionEvent e) {
			new RegisterDialog(frame);
	}

}
