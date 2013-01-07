package assign2.client.controller;

import assign2.client.view.*;
import java.awt.event.*;
import javax.swing.JTextField;
public class TopicListener implements ActionListener {
	private String topicName, topicDesc;
	private MessageBoardFrame frame;
	
	public TopicListener(MessageBoardFrame f, JTextField name, JTextField desc) {
		frame = f;
		topicName = name.getText();
		topicDesc = desc.getText();
	}
	
	public void actionPerformed(ActionEvent e) {
		frame.login(topicName, topicDesc);
	}
}
