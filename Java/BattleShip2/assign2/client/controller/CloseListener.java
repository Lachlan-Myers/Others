package assign2.client.controller;

import assign2.client.view.*;
import java.awt.event.*;

public class CloseListener implements ActionListener {
	private LoginDialog dialog;
	private RegisterDialog reg_dialog;
	private TopicDialog topic_dialog;
	
	public CloseListener(LoginDialog d) {
		dialog = d;
	}
	
	public CloseListener(RegisterDialog d) {
		reg_dialog = d;
	}
	
	public CloseListener(TopicDialog d) {
		topic_dialog = d;
	}
	
	public void actionPerformed(ActionEvent e) {
		dialog.dispose();
	}
}
