package assign2.client.controller;

import assign2.client.view.MessageBoardFrame;
import java.awt.event.*;
import javax.swing.JButton;

public class SetTopicListener implements ActionListener {
	private MessageBoardFrame frame;
	
	public SetTopicListener(MessageBoardFrame frame) {
		super();
		this.frame = frame;
	}

	
	public void actionPerformed(ActionEvent e) {
		frame.setMessages(((JButton)e.getSource()).getText());
	}

}
