package view;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Observable;
import java.util.Observer;
import java.util.Vector;

import javax.swing.*;

import model.*;

public class FavouritesView extends JPanel implements Observer {
	
	private Favourite _favouritesModel;
	
	private JList _favouritesList;
	private JScrollPane _favouritesScroller;
	private JButton _remove;
	private JButton _select;
	
	public FavouritesView(Favourite favouritesModel) {
		super();
		
		// Set the favourites model, and register ourselves as an observer, so we can be notified
		// of any changes to the model.
		_favouritesModel = favouritesModel;
		_favouritesModel.addObserver(this);
	
		this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
		this.setBorder(BorderFactory.createTitledBorder("Favourites"));
		
		JPanel listPanel = new JPanel();
		listPanel.setLayout(new BoxLayout(listPanel, BoxLayout.Y_AXIS));
		listPanel.setBorder(BorderFactory.createEmptyBorder(5, 10, 5, 10));
		
		_favouritesList = new JList();
		_favouritesList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		_favouritesList.setLayoutOrientation(JList.VERTICAL);
		
		// Fetch initial favourited stations.
		updateList();
	
		_favouritesScroller = new JScrollPane(_favouritesList);
		_favouritesScroller.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
		_favouritesScroller.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED);
		
		listPanel.add(_favouritesScroller);
		
		_remove = new JButton("Remove");
		_remove.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent event) {
				// Remove the selected station from the user's favourites.
				Station station = (Station)_favouritesList.getSelectedValue();
				if (station != null) {
					_favouritesModel.removeStation(station);
				}
			}
		});
		
		_select = new JButton("Select");
		_select.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent event) {
				// Display the selected favourite.
				if (_favouritesList.getSelectedValue() != null)
					_favouritesModel.setCurrentStation((Station)_favouritesList.getSelectedValue());
			}
		});
		
		JPanel buttonPanel = new JPanel();
		buttonPanel.setBorder(BorderFactory.createEmptyBorder(0, 5, 0, 5));
		buttonPanel.setLayout(new FlowLayout(FlowLayout.LEFT));
		buttonPanel.setMaximumSize(new Dimension(Short.MAX_VALUE, 25));
		
		buttonPanel.add(_select);
		buttonPanel.add(_remove);
		
		this.add(listPanel);
		this.add(buttonPanel);
	}
	
	public void updateList() {
		// Update the list of 'favourited' stations.
		
		Vector<Station> stations = new Vector<Station>();
		
		for (Station s : _favouritesModel.getStations().values()) {
			stations.add(s);
		}
		
		_favouritesList.setListData(stations);
		
		this.updateUI();
	}

	public void update(Observable observeObject, Object obj) {
		// Something changed within the model, propagate the changes.
		
		// Update list of stations.
		if (observeObject instanceof Favourite)
		{
			updateList();
		}
	}
}
