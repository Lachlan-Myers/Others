package assign2.client.controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import assign2.client.view.MessageBoardFrame;
import assign2.client.view.RegisterDialog;
import assign2.client.view.TopicDialog;

public class ShowTopicDialogListener implements ActionListener {

	private MessageBoardFrame frame;
		
	public ShowTopicDialogListener(MessageBoardFrame f) {
			frame = f;
	}
		
	public void actionPerformed(ActionEvent e) {
			new TopicDialog(frame);
	}

}
