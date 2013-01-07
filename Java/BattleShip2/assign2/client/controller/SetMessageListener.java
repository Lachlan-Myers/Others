package assign2.client.controller;

import java.awt.event.*;
import javax.swing.*;
import assign2.client.view.*;

public class SetMessageListener extends MouseAdapter {
	private MessageBoardFrame frame;
	
	public SetMessageListener(MessageBoardFrame frame) {
		super();
		this.frame = frame;
	}
	
	public void mousePressed(MouseEvent e) {
		frame.showIndividualMessage(((JLabel)e.getSource()).getText());
	}
}
