package view;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

import model.*;

public class StationView extends JPanel {
	private SearchView _top;
	private FavouritesView _bottom;
	
	public StationView(Favourite favouritesModel) {
		super();
	
		this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
		
		_top = new SearchView(favouritesModel);
		_bottom = new FavouritesView(favouritesModel);
		
		this.add(_top);
		this.add(Box.createVerticalStrut(20));
		this.add(_bottom);
	}
	
	
	
}
