package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;

import model.Station;
import model.Weather;
import model.WeatherComparator;
import model.WeatherScraper;
import model.WeatherTimeFrame;
import view.GraphPanel;
import view.GraphView;

public class GraphViewController implements ActionListener {
	
	private GraphView _graphView;
	
	public GraphViewController(GraphView graphView) {
		_graphView = graphView;
	}
	
	public WeatherTimeFrame[] getStationTimeframes(Station station) {
		// Determine which dates the station has information for, and return those.
		ArrayList<WeatherTimeFrame> timeframes = new ArrayList<WeatherTimeFrame>();
		List<Weather> weatherData = station.getList();
		
		if (weatherData != null && weatherData.size() > 0) {
			Collections.sort(weatherData, new WeatherComparator());
			
			// We need to separate the weather information by year, then by month.
			WeatherTimeFrame tf = null;
			Weather wlast = null;
			
			for (Weather w : weatherData) {
				if (tf == null || hasMonthChanged(wlast, w)) {
					timeframes.add(tf);
					tf = new WeatherTimeFrame();
					tf.StationName = station.getName();
					tf.Description = new SimpleDateFormat("MMMM yyyy").format(w.getDate().getTime());
				}
				
				tf.WeatherItems.add(w);
				wlast = w;
			}
			timeframes.add(tf);
		
			return timeframes.toArray(new WeatherTimeFrame[] {});
		}
		else {
			return null;
		}
	}
	
	private boolean hasMonthChanged(Weather last, Weather current) {
		Calendar cLast = last.getDate();
		Calendar cCurrent = current.getDate();

		if (cCurrent.get(Calendar.YEAR) != cLast.get(Calendar.YEAR))
			return true;
		else if (cCurrent.get(Calendar.MONTH) != cLast.get(Calendar.MONTH))
			return true;
		else
			return false;
	}

	public void actionPerformed(ActionEvent event) {
		// A timeframe has been selected from the combo box, update the graph so the
		// new data is displayed.
		
		WeatherTimeFrame timeframe = _graphView.getSelectedTimeframe();
		
		if (timeframe != null) {
			// Timeframe selected - fetch weather information to pass to graph.
			_graphView.getGraphPanel().updateGraph(timeframe.WeatherItems.toArray(new Weather[] {}), timeframe.StationName + " - " + timeframe.Description);
		}
		else {
			// Timeframe de-selected, set graph to 'no data'.
			_graphView.getGraphPanel().updateGraph(null, null);
		}
		
	}
}
