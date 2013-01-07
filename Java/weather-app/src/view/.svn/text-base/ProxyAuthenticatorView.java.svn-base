package view;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;

public class ProxyAuthenticatorView extends JDialog {
	
	private JTextField _proxyAddress;
	private int _dialogResult;
	
	public ProxyAuthenticatorView() {
		this.setMinimumSize(new Dimension(300, 110));
		this.setResizable(false);
		this.setModal(true);
		this.setLocationByPlatform(true);
		this.setTitle("Network Connection");
		
		_dialogResult = JOptionPane.CANCEL_OPTION;
		
		Container container = this.getContentPane();
		container.setLayout(new BoxLayout(container, BoxLayout.Y_AXIS));
		
		JPanel address = new JPanel();
		address.setLayout(new FlowLayout(FlowLayout.LEFT));

		JLabel labelAddress = new JLabel("Proxy address:");
		_proxyAddress = new JTextField(25);
		
		address.add(labelAddress);
		address.add(_proxyAddress);
		
		JPanel controls = new JPanel();
		controls.setLayout(new FlowLayout(FlowLayout.RIGHT));
		
		JButton okButton = new JButton("OK");
		okButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				_dialogResult = JOptionPane.OK_OPTION;
				close();
			}
		});
		
		JButton cancelButton = new JButton("Cancel");
		cancelButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				close();
			}
		});
		
		controls.add(okButton);
		controls.add(cancelButton);
		
		this.getRootPane().setDefaultButton(okButton);

		container.add(address);
		container.add(controls);
	}
	
	public void close() {
		this.setVisible(false);
	}

	public int getDialogResult() {
		return _dialogResult;
	}
	
	public void setProxyAddress(String proxy) {
		_proxyAddress.setText(proxy);
	}

	public String getProxyAddress() {
		return _proxyAddress.getText();
	}
}
