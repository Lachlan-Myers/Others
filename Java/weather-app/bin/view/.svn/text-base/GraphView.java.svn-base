package view;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.util.Observable;
import java.util.Observer;

import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;

import controller.GraphViewController;

import model.Favourite;
import model.Station;
import model.WeatherTimeFrame;


public class GraphView extends JPanel implements Observer {
	
	private Favourite _favouritesModel;
	private GraphPanel _graphPanel;
	private GraphViewController _graphController;
	private JPanel _timeframePanel;
	private JComboBox _timeframeSelector;
	private Station _currentStation;
	
	public GraphView(Favourite favouritesModel) {
		
		_favouritesModel = favouritesModel;
		_favouritesModel.addObserver(this);
		
		_currentStation = _favouritesModel.getCurrentStation();
		
		_graphController = new GraphViewController(this);
		
		_graphPanel = new GraphPanel();
		
		this.setLayout(new BorderLayout());
		
		this.add(_graphPanel);
		
		_timeframePanel = new JPanel();
		_timeframePanel.setLayout(new FlowLayout(FlowLayout.LEFT));
		_timeframePanel.add(new JLabel("Timeframe: "));
		_timeframeSelector = new JComboBox();
		_timeframeSelector.addActionListener(_graphController);
		_timeframePanel.add(_timeframeSelector);
		
		this.add(_timeframePanel, BorderLayout.PAGE_END);
	}
	
	public void update(Observable observeObject, Object obj) {
		
		if (observeObject instanceof Favourite) {
			
			// Has the station changed?
			if (_favouritesModel.getCurrentStation() != null && _favouritesModel.getCurrentStation() != _currentStation) {
				_currentStation = _favouritesModel.getCurrentStation();
				
				// New station selected, tell the controller to probe the station and find
				// out how much weather information it has.
				// Store the timeframes in the combo box.
				_timeframeSelector.removeAllItems();
				
				WeatherTimeFrame[] timeframes = _graphController.getStationTimeframes(_currentStation);
				if (timeframes != null) {
					for (WeatherTimeFrame tf : timeframes) {
						_timeframeSelector.addItem(tf);
					}
				}
				
				// Select the first item, if it exists. This will update the graph (via the controller).
				// For some reason, there is an empty item added.
				// TODO: Find out why empty item appears at index 0, and fix it.
				if (_timeframeSelector.getItemCount() > 1)
					_timeframeSelector.setSelectedIndex(1);
				else if (_timeframeSelector.getItemCount() > 0)
					_timeframeSelector.setSelectedIndex(0);
				
			}
			else if (_favouritesModel.getCurrentStation() == null) {
				// Clear the combo box of all entries, no station is selected.
				_timeframeSelector.removeAllItems();
			}
			
			_currentStation = _favouritesModel.getCurrentStation();
		}
		
	}
	
	public WeatherTimeFrame getSelectedTimeframe() {
		return (WeatherTimeFrame)_timeframeSelector.getSelectedItem();
	}
	
	public GraphPanel getGraphPanel() {
		return _graphPanel;
	}

}
