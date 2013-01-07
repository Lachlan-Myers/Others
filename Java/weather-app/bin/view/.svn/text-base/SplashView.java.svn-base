package view;

import java.awt.*;
import java.util.Observable;
import java.util.Observer;

import javax.swing.*;

import org.w3c.dom.Document;
import org.xhtmlrenderer.*;
import org.xhtmlrenderer.simple.FSScrollPane;
import org.xhtmlrenderer.simple.XHTMLPanel;

import model.Favourite;
import model.Station;



public class SplashView extends JPanel {

	private XHTMLPanel _splashPanel;
		
	public SplashView() {
		super();
		this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
		
		// Create the XHTML renderer panel that will render the splash page.
		_splashPanel = new XHTMLPanel(new BrowserUserAgent());
		_splashPanel.setDocument("html/splash.html");
		
		// Make small fonts anti-aliased.
		_splashPanel.getSharedContext().getTextRenderer().setSmoothingThreshold(0.1f);
		
		// Set up a scroll pane for the panel.
		FSScrollPane scrollPane = new FSScrollPane(_splashPanel);
		scrollPane.setBorder(BorderFactory.createEmptyBorder());
		scrollPane.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		scrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED);
		
		this.add(scrollPane);
	}
	
}
