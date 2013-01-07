package assign2.client.view;

import assign2.client.controller.*;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;


public class TopicDialog extends JDialog {
	
	private MessageBoardFrame frame;
	private JTextField name;
	private JTextField desc;
	private JButton create, cancel;
	
	
	public TopicDialog(MessageBoardFrame frame) {
		this.frame = frame;
		setLayout(new GridLayout(3, 2));
		
		name = new JTextField();
		desc = new JTextField();
		
		create = new JButton("Create");
		cancel = new JButton("Cancel");
		add(new JLabel("Topic Name"));
		add(name);
		
		add(new JLabel("Description"));
		add(desc);
		
		add(create);
		create.addActionListener(new TopicListener(frame, name, desc));
		
		add(cancel);
		cancel.addActionListener(new CloseListener(this));
		
		setSize(400, 200);
		setVisible(true);
	}
}
