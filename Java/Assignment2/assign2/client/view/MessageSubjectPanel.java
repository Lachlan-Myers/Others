package assign2.client.view;

import assign2.client.controller.*;
import java.awt.*;
import java.util.*;
import javax.swing.*;

public class MessageSubjectPanel extends JPanel {

	private MessageBoardFrame frame;
	
	public MessageSubjectPanel(MessageBoardFrame frame) {
		this.frame = frame;
		setSize(200, 480);
		setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
	}

	
	public void setMessages(ArrayList<String> messages) {
		this.removeAll();
		for(String i: messages) {
			JLabel label = new JLabel(i);
			label.addMouseListener(new SetMessageListener(frame));
			this.add(label);
		}
	}

}
