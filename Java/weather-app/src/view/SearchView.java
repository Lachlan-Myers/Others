package view;


import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.*;
import java.util.*;

import javax.swing.*;
import javax.swing.Box.Filler;

import controller.SearchController;

import model.*;

public class SearchView extends JPanel {
	
	public static final String[] states = { "", "VIC", "NSW", "QLD", "SA", "WA", "NT", "TAS", "ACT"};
	public static final int[] stateMap  = { -1, Station.VIC, Station.NSW, Station.QLD, Station.SA, Station.WA, Station.NT, Station.TAS, Station.ACT };
	private JComboBox _stateList, _stationList;
	private JButton _selectButton, _favouriteButton;
	
	private SearchController _searchController;
	
	public SearchView(Favourite favouritesModel) {
		super();
		
		// Create a controller to handle events.
		_searchController = new SearchController(this, favouritesModel);
		
		this.setLayout(new BoxLayout(this, BoxLayout.X_AXIS));
		this.setBorder(BorderFactory.createTitledBorder("Select Station"));
		
		// Restrict vertical resizing.
		this.setMinimumSize(new Dimension(0, 200));
		this.setMaximumSize(new Dimension(Short.MAX_VALUE, 200));
		
		this.add(Box.createHorizontalStrut(10));
		
		JPanel searchPanel = new JPanel();
		searchPanel.setLayout(new GridLayout(0, 1));
		
		// Set up the state list, and attach an event listener which triggers the controller.
		_stateList = new JComboBox(states);
		_stateList.addActionListener(_searchController);
		
		JLabel stateLabel = new JLabel("State:");
				
		searchPanel.add(stateLabel);
		searchPanel.add(_stateList);
		
		_stationList = new JComboBox();
		JLabel stationLabel = new JLabel("Station:");
		
		searchPanel.add(stationLabel);
		searchPanel.add(_stationList);
		
		searchPanel.add(Box.createVerticalStrut(5));
		
		// Set up the 'select station' button, when clicked it will trigger a change in the model,
		// causing the currently selected station to change, updating the relevant views that are
		// observing these changes.
		_selectButton = new JButton("Select");
		_selectButton.addActionListener(_searchController);
		_favouriteButton = new JButton("Favourite This!");
		_favouriteButton.addActionListener(_searchController);
		searchPanel.add(_selectButton);
		searchPanel.add(_favouriteButton);
		
		
		this.add(searchPanel);
		
		this.add(Box.createHorizontalStrut(10));
	}
	
	public JComboBox getStateList() {
		return _stateList;
	}
	
	public JComboBox getStationList() {
		return _stationList;
	}
	
	public JButton getSelectButton() {
		return _selectButton;
	}
	
	public JButton getFavouriteButton() {
		return _favouriteButton;
	}
	
	public SearchController getController() {
		return _searchController;
	}
}
