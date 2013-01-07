package assign2.client.view;

import assign2.model.*;
import assign2.client.controller.*;
import java.awt.*;
import java.util.*;
import javax.swing.*;

public class TopicPanel extends JPanel {
	private ArrayList<String> topics;
	private Assign2 connection;
	private MessageBoardFrame frame;
	private ArrayList<JButton> buttons;
	
	public TopicPanel(Assign2 connection, MessageBoardFrame frame) {
		this.connection = connection;
		this.frame = frame;
		buttons = new ArrayList<JButton>();
		
		try {
			topics = connection.getTopics();
			setSize(640, 50);
			setLayout(new FlowLayout());
			for(String i: topics) {
				JButton button = new JButton(i);
				button.addActionListener(new SetTopicListener(frame));
				buttons.add(button);
				add(button);
			}
		} 
		catch (Exception e) {
			e.printStackTrace();
		}
	}
}
