package view;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ComponentEvent;
import java.awt.event.ComponentListener;
import java.awt.image.BufferedImage;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.Observable;
import java.util.Observer;

import javax.swing.*;

import model.*;


public class GraphPanel extends JPanel implements CallbackInterface, Runnable {
	
	final private double GRAPH_RESIZE_FACTOR = 1.1;
	
	private ThreadedGraph _threadedGraph;
	private Thread _graphWorker;
	private boolean _hasThreadFinished;
	private Timer _resizeTimer;
	private Weather[] _weatherList;
	private String _graphTitle;
	
	public GraphPanel() {
		super();
		
		// Create a timer that fires an event to redraw the graph. Reset the timer
		// every time a 'resize' event is sent to the panel. This is because many
		// reset events are sent while the user is resizing the frame, but we don't
		// want to draw the graph all those times.
		_resizeTimer = new Timer(400, new ActionListener() {
			public void actionPerformed(ActionEvent event) {
				_resizeTimer.stop();
				
				// Redraw the graph.
				generateImage();
			}
		});
		
		this.addComponentListener(new ComponentListener() {
			public void componentResized(ComponentEvent e) {
				_resizeTimer.restart();
			}

			public void componentHidden(ComponentEvent arg0) {
			}

			public void componentMoved(ComponentEvent arg0) {
			}

			public void componentShown(ComponentEvent arg0) {
			}
		});
		
	
		_threadedGraph = new ThreadedGraph(this);
		_graphWorker = null;
		_weatherList = null;
		_graphTitle = "";
		_hasThreadFinished = false;
	}
	
	public void paint(Graphics graphics)
	{
		super.paint(graphics);
		graphics.clearRect(0, 0, this.getWidth(), this.getHeight());
		
		if (_weatherList != null && _weatherList.length > 0) {
			if (_hasThreadFinished && _threadedGraph.getImage() != null) {
				BufferedImage graphImage = _threadedGraph.getImage();
				if (graphImage != null) {
					// Draw the generated image.
					graphics.drawImage(graphImage, (this.getWidth() / 2) - (graphImage.getWidth() / 2), (this.getHeight() / 2) - (graphImage.getHeight() / 2), this);
				}	
			}
			else if (!_hasThreadFinished && (_graphWorker != null && _graphWorker.isAlive())) {
				// The graph is still being generated, draw a status message.
				graphics.drawString("Generating graph...", 5, 20);
			}
		
		}
		else {
			graphics.drawString("No data available.", 5, 20);	
		}
		
	}
	
	private void generateImage() {
		// Generate the graph image. This will call a worker thread that does the work, then
		// the thread will call back to our thread, and we refresh the GUI.
		
		if ((_weatherList != null && _weatherList.length > 0) && (this.getWidth() > 0 && this.getHeight() > 0)) {
			// Wait for the thread to finish, if it's currently running. This shouldn't cause too much of
			// a slowdown. There is no way to terminate the thread safely, so we just have to wait.
			if (_graphWorker != null)
				while (_graphWorker.isAlive());
			
			// Generate the graph image.
			String[] xAxisLabels = new String[_weatherList.length];
			for (int i = 0; i < _weatherList.length; i++) {
				xAxisLabels[i] = new SimpleDateFormat("dd").format(_weatherList[i].getDate().getTime());
			}
			
			_threadedGraph.setGraphProperties(xAxisLabels, "Day", "Temperature", _graphTitle, new String[] { "Maximum", "Minimum", "9am", "3pm" });
			double[][] data = new double[4][_weatherList.length];
			Calendar now = new GregorianCalendar();
			
			for (int i = 0; i < _weatherList.length; i++) {
				// If this item has today's date, and its max temp is 0, then make it equal to the minimum
				// temperature. This is because today doesn't have a maximum temperature yet.
				Calendar weatherDate = _weatherList[i].getDate();
				
				if (now.get(Calendar.DAY_OF_MONTH) == weatherDate.get(Calendar.DAY_OF_MONTH)  &&
						now.get(Calendar.MONTH) == weatherDate.get(Calendar.MONTH) &&  
						now.get(Calendar.YEAR) == weatherDate.get(Calendar.YEAR) &&
						_weatherList[i].getMaxTemp() < 0.1) 
					data[0][i] = _weatherList[i].getMinTemp();
				else
					data[0][i] = _weatherList[i].getMaxTemp();
				
				data[1][i] = _weatherList[i].getMinTemp();
				data[2][i] = _weatherList[i].getAmTemp();
				data[3][i] = _weatherList[i].getPmTemp();
			}
			
			_threadedGraph.setData(data);
			_threadedGraph.setSize((int)(this.getWidth() / GRAPH_RESIZE_FACTOR), (int)(this.getHeight() / GRAPH_RESIZE_FACTOR));
			_threadedGraph.prepareRun();
			
			// Start generating the image.
			_hasThreadFinished = false;
			_graphWorker = new Thread(_threadedGraph);
			_graphWorker.start();
		}
		
		// Update to draw loading message.
		this.repaint();
	}
	
	public void run() {
		// Graph should have finished drawing, or there was an error. Refresh the panel.
		_hasThreadFinished = true;
		this.updateUI();
	}
	
	public void onCallback(Object data) {
		// This callback is called on the worker thread, so we shouldn't modify
		// the UI here. Schedule an update some time in the near future on the Event
		// Dispatch Thread (it will run the run() method).
		SwingUtilities.invokeLater(this);
	}
	
	public void updateGraph(Weather[] weatherList, String graphTitle) {
		_weatherList = weatherList;
		_graphTitle = graphTitle;
		generateImage();
	}
	
	/*public void update(Observable observeObject, Object obj) {
		
		if (observeObject instanceof Favourite) {
			// 'Favourite' model has changed; has a new station been selected?
			Favourite favModel = (Favourite)observeObject;
			
			if (favModel.getCurrentStation() != _curStation){
				// New station selected, update the graph data.
				_curStation = favModel.getCurrentStation();
				generateImage();
			}
		}
		
	}
	
	public void setTimeframe(WeatherTimeFrame timeFrame) {
		_timeFrame = timeFrame;
	}
	
	public WeatherTimeFrame getTimeFrame() {
		return _timeFrame;
	}*/

}
