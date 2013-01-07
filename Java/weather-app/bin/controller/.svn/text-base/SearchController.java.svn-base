package controller;

import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.GregorianCalendar;
import java.util.Hashtable;
import java.util.List;

import javax.swing.JOptionPane;

import model.*;
import view.*;

public class SearchController implements ActionListener {
	
	private SearchView _searchView;
	private Favourite _favouritesModel;
	
	private Hashtable<Integer, List<Station>> _cachedStations;
	
	public SearchController(SearchView searchView, Favourite favouritesModel) {
		_searchView = searchView;
		_favouritesModel = favouritesModel;
		
		_cachedStations = new Hashtable<Integer, List<Station>>();
	}
	
	public void actionPerformed(ActionEvent event) {
		
		if (event.getSource() == _searchView.getStateList()) {
			// Update the list of stations in the selected state.
			updateStationList();
		}
		else if (event.getSource() == _searchView.getSelectButton()) {
			// Update the currently selected station in the model.
			updateSelectedStation();
		}
		else if (event.getSource() == _searchView.getFavouriteButton()) {
			// Add the selected station to the favourites list.
			if (_searchView.getStationList().getSelectedItem() != null) {
				_favouritesModel.addStation((Station)_searchView.getStationList().getSelectedItem());
			}
		}
	}
	
	public void updateStationList() {
		// Fetch the selected state, and get its state id.
		String stateSelected = (String)_searchView.getStateList().getSelectedItem();
		
		_searchView.getStationList().removeAllItems();
		
		if (stateSelected != null && !stateSelected.equals("")) {
			for (int i = 0; i < SearchView.states.length; i++) {
				if (stateSelected.equals(SearchView.states[i])) {
					// Update the station list with stations from the selected state.
					List<Station> stationList = fetchStateStations(SearchView.stateMap[i]);
					
					for (Station s : stationList) {
						_searchView.getStationList().addItem(s);
					}
					
				}
			}
		}
	}
	
	public void updateSelectedStation() {
		// When the user changes the station, update the 'currently selected station' value
		// in the model, which will then trigger the appropriate views to update themselves.
		
		if (_searchView.getStationList().getSelectedItem() != null) {
			Station s = (Station)_searchView.getStationList().getSelectedItem();
			_favouritesModel.setCurrentStation(s);
		}
	}
	
	private List<Station> fetchStateStations(int stateId)
	{
		List<Station> stationList;
		
		// First check if the station list for that state is in the cache.
		if (_cachedStations.containsKey(stateId)) {
			// Fetch stations from cache.
			stationList = _cachedStations.get(stateId);
		}
		else {
			// Fetch stations from the location scraper, and cache the results.
			
			try {
				LocationScraper stationScraper = new LocationScraper(stateId);
				stationList = stationScraper.fetchStations();
				_cachedStations.put(stateId, stationList);
			}
			catch (Exception exc) {
				JOptionPane.showMessageDialog(null, "There was a problem getting the list of stations for the selected state.\n" +
						"Please check your network connection and try again.", "Error", JOptionPane.ERROR_MESSAGE);
				// Send back an empty list.
				return new ArrayList<Station>();
			}
			
			
		}
		
		// Sort the list in alphabetical order.
		Collections.sort(stationList, new StationComparator());
		
		return stationList;
	}

	
	
}
