package view;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.List;
import java.util.GregorianCalendar;
import java.util.Observable;
import java.util.Observer;

import javax.swing.*;

import org.w3c.dom.Document;
import org.xhtmlrenderer.*;
import org.xhtmlrenderer.simple.FSScrollPane;
import org.xhtmlrenderer.simple.XHTMLPanel;

import controller.WeatherControllerSingleDay;

import model.Favourite;
import model.Station;
import model.Weather;



public class WeatherView extends JPanel implements Observer {

	private Favourite _favouritesModel;
	private WeatherControllerSingleDay _weatherController;
	
	private XHTMLPanel _weatherPanel;
	private JPanel _datePanel;
	private JComboBox _yearSelect;
	private JComboBox _monthSelect;
	private JComboBox _daySelect;
	private Station _currentStation;
	
	
	public WeatherView(Favourite favouritesModel) {
		super();
	
		// Register ourselves as an observer for the favourites model.
		_favouritesModel = favouritesModel;
		_favouritesModel.addObserver(this);
		
		_currentStation = _favouritesModel.getCurrentStation();
		
		// Create a controller to handle fetching dynamic weather data.
		_weatherController = new WeatherControllerSingleDay();
		
		this.setLayout(new BorderLayout());
		
		// Create the XHTML renderer panel that will render the weather content.
		_weatherPanel = new XHTMLPanel(new BrowserUserAgent());
		_weatherPanel.setDocument("html/empty.html");
		
		// Make small fonts anti-aliased.
		_weatherPanel.getSharedContext().getTextRenderer().setSmoothingThreshold(0.1f);
		
		// Set up a scroll pane for the panel.
		FSScrollPane scrollPane = new FSScrollPane(_weatherPanel);
		scrollPane.setBorder(BorderFactory.createEmptyBorder());
		scrollPane.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		scrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED);
		
		this.add(scrollPane);
		
		// Create the date panel, which holds a list of available weather data for the currently
		// selected station.
		_datePanel = new JPanel();
		_datePanel.setLayout(new FlowLayout(FlowLayout.LEFT));
		_datePanel.add(new JLabel("Date: "));
		
		_yearSelect = new JComboBox();
		_yearSelect.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent event) {
				// If the user selects a valid year value, then update the list of available
				// months for that year.
				try {
					int year = Integer.parseInt(_yearSelect.getSelectedItem().toString());
					updateMonths(year);
				}
				catch (Exception exc) {
					
				}
			}
		});
		
		_monthSelect = new JComboBox();
		_monthSelect.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent event) {
				// If the user selects a valid month value, then update the list of available
				// days for that month and year. Subtract one from the month because it's zero-based,
				// but displayed with a base of +1.
				try {
					int year = Integer.parseInt(_yearSelect.getSelectedItem().toString());
					int month = Integer.parseInt(_monthSelect.getSelectedItem().toString()) - 1;
					updateDays(month, year);
				}
				catch (Exception exc) {
					
				}
			}
		});
		
		_daySelect = new JComboBox();
		JButton btnGo = new JButton("Go");
		btnGo.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent event) {
				displayWeather();
			}
		});
		
		_datePanel.add(_yearSelect);
		_datePanel.add(_monthSelect);
		_datePanel.add(_daySelect);
		_datePanel.add(btnGo);
		
		this.add(_datePanel, BorderLayout.PAGE_END);
	}
	

	public void update(Observable observeObject, Object obj) {
		if (observeObject instanceof Favourite) {
			
			// Has the currently selected station changed?
			if (_currentStation != _favouritesModel.getCurrentStation()) {
				
				// Set the new station.
				_currentStation = _favouritesModel.getCurrentStation();
				
				// Show an empty page if there is no current station, and clear the date selectors.
				if (_currentStation == null) {
					_weatherPanel.setDocument("html/empty.html");
					_daySelect.removeAllItems();
					_monthSelect.removeAllItems();
					_yearSelect.removeAllItems();
				}
				else {
					// Update list of weather information this station has (by date).
					updateYears();
					
					// Auto-select the latest weather data.
					if (_yearSelect.getItemCount() > 0) {
						_yearSelect.setSelectedIndex(_yearSelect.getItemCount() - 1);
						if (_monthSelect.getItemCount() > 0) {
							_monthSelect.setSelectedIndex(_monthSelect.getItemCount() - 1);
							if (_daySelect.getItemCount() > 0) {
								_daySelect.setSelectedIndex(_daySelect.getItemCount() - 1);
								displayWeather();
							}
						}
					}
				}
				
				this.updateUI();
			}
		}
		
		
	}
	
	private void displayWeather() {
		
		// Parse the selected date.
		int year, month, day;
		GregorianCalendar date;
		try {
			year = Integer.parseInt(_yearSelect.getSelectedItem().toString());
			month = Integer.parseInt(_monthSelect.getSelectedItem().toString()) - 1;
			day = Integer.parseInt(_daySelect.getSelectedItem().toString());
			date = new GregorianCalendar(year, month, day);
		}
		catch (Exception exc) {
			return;
		}
		
		if (_currentStation != null) {
			
			_weatherPanel.setDocument("html/weather_singleday.html");
		
			// Tell the controller to update the document data.
			_weatherController.setDynamicContent(_weatherPanel.getDocument(), _currentStation, date);
			
			// Update document in panel (this is the recommended way to update it).
			_weatherPanel.setDocument(_weatherPanel.getDocument());
		}
		
		this.updateUI();
	}
	
	private void updateYears() {
		_yearSelect.removeAllItems();
		
		List<String> years = _weatherController.getYears(_currentStation);
		for (String s : years) {
			_yearSelect.addItem(s);
		}
	}
	
	private void updateMonths(int year) {
		_monthSelect.removeAllItems();
		
		List<String> months = _weatherController.getMonths(_currentStation, year);
		for (String s : months) {
			_monthSelect.addItem(s);
		}
	}
	
	private void updateDays(int month, int year) {
		_daySelect.removeAllItems();
		
		List<String> days = _weatherController.getDays(_currentStation, month, year);
		for (String s : days) {
			_daySelect.addItem(s);
		}
	}
	
}
