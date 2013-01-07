package view;

import java.awt.Container;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.ScrollPaneConstants;

import org.xhtmlrenderer.simple.FSScrollPane;
import org.xhtmlrenderer.simple.XHTMLPanel;

public class AboutView extends JDialog {

	private XHTMLPanel _panel;
	private int _dialogResult;

	public AboutView() {
		this.setMinimumSize(new Dimension(460, 300));
		this.setResizable(false);
		this.setModal(true);
		this.setLocationByPlatform(true);
		this.setTitle("About");

		Container cont = this.getContentPane();
		cont.setLayout(new BoxLayout(cont, BoxLayout.Y_AXIS));

		_dialogResult = JOptionPane.OK_OPTION;

		_panel = new XHTMLPanel(new BrowserUserAgent());
		_panel.setDocument("html/about.html");
		//_panel.setMinimumSize(new Dimension(360, 240));

		// Make small fonts anti-aliased.
		_panel.getSharedContext().getTextRenderer().setSmoothingThreshold(0.1f);

		// Set up a scroll pane for the panel.
		FSScrollPane scrollPane = new FSScrollPane(_panel);
		scrollPane.setBorder(BorderFactory.createEmptyBorder());
		scrollPane.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		scrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED);

		JPanel controls = new JPanel();
		controls.setLayout(new FlowLayout(FlowLayout.RIGHT));
		controls.setMaximumSize(new Dimension(Short.MAX_VALUE, 20));

		JButton okButton = new JButton("OK");
		this.getRootPane().setDefaultButton(okButton);
		okButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				close();
			}
		});

		controls.add(okButton);

		cont.add(scrollPane);
		cont.add(controls);
	}

	public void close() {
		this.setVisible(false);
	}

	public int getDialogResult() {
		return _dialogResult;
	}
}
