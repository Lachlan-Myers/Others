package view;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Paint;
import java.awt.Shape;
import java.awt.Stroke;
import java.awt.image.BufferedImage;

import org.jCharts.axisChart.AxisChart;
import org.jCharts.chartData.AxisChartDataSet;
import org.jCharts.chartData.ChartDataException;
import org.jCharts.chartData.DataSeries;
import org.jCharts.properties.*;
import org.jCharts.types.ChartType;

/**
 * Creates a graph based on given parameters.
 * Intended to be run in a thread.
 * @author Daniel
 */
public class ThreadedGraph implements Runnable {

	private ChartProperties _chartProperties;
	private AxisProperties _axisProperties;
	private LegendProperties _legendProperties;
	private DataSeries _dataSeries;
	private AxisChart _axisChart;
	
	private String[] _xAxisLabels;
	private String _xAxisTitle;
	private String _yAxisTitle;
	private String _graphTitle;
	private String[] _graphLegends;
	private double[][] _graphData;
	
	private int _width;
	private int _height;
	
	private BufferedImage _image;
	private String _statusMessage;
	private boolean _hasFailed;
	private CallbackInterface _callback;
	
	
	public ThreadedGraph(CallbackInterface callback) {
		_image = null;
		_width = 640;
		_height = 480;
		_statusMessage = "";
		_hasFailed = false;
		_callback = callback;
	}
	
	public void setGraphProperties(String[] xAxisLabels, String xAxisTitle, String yAxisTitle, String graphTitle, String[] graphLegends) {
		_xAxisLabels = xAxisLabels;
		_xAxisTitle = xAxisTitle;
		_yAxisTitle = yAxisTitle;
		_graphTitle = graphTitle;
		_graphLegends = graphLegends;
	}
	
	public void setData(double[][] graphData) {
		_graphData = graphData;
	}
	
	public void setSize(int width, int height) {
		_width = width;
		_height = height;
		
		// Set values to default if they are 0 or below, otherwise rendering the graph throws an exception.
		if (_width < 1)
			_width = 640;
		
		if (_height < 1)
			_height = 480;
	}
	
	public void prepareRun() {
		_hasFailed = false;
		_statusMessage = "Generating graph...";
		_image = null;
	}
	
	public void run() {
				
		DataSeries data = new DataSeries(_xAxisLabels, _xAxisTitle, _yAxisTitle, _graphTitle);
		Paint[] paint = { Color.RED, Color.BLUE, Color.CYAN, Color.ORANGE };
		Stroke[] strokes = { LineChartProperties.DEFAULT_LINE_STROKE, LineChartProperties.DEFAULT_LINE_STROKE, LineChartProperties.DEFAULT_LINE_STROKE, LineChartProperties.DEFAULT_LINE_STROKE };
		Shape[] shapes = { PointChartProperties.SHAPE_SQUARE, PointChartProperties.SHAPE_SQUARE, PointChartProperties.SHAPE_SQUARE, PointChartProperties.SHAPE_SQUARE };
		
		LineChartProperties lineProps = new LineChartProperties(strokes, shapes);
		AxisChartDataSet dataSet;
		
		try {
			 dataSet = new AxisChartDataSet(_graphData, _graphLegends, paint, ChartType.LINE, lineProps);
		}
		catch (ChartDataException exc) {
			synchronized(this) {
				_hasFailed = true;
				_statusMessage = "An error occurred while generating the graph.";
			}
			exc.printStackTrace();
			notifyFinished();
			return;
		}
		
		data.addIAxisPlotDataSet(dataSet);
		
		ChartProperties chartProps = new ChartProperties();
		AxisProperties axisProps = new AxisProperties();
		LegendProperties legendProps = new LegendProperties();
		
		AxisChart axisChart = new AxisChart(data, chartProps, axisProps, legendProps, _width, _height);
		
		_image = new BufferedImage(_width, _height, BufferedImage.TYPE_INT_RGB);
		axisChart.setGraphics2D((Graphics2D)_image.getGraphics());
		
		try {		
			axisChart.render();
		}
		catch (PropertyException exc) {
			synchronized(this) {
				_hasFailed = true;
				_statusMessage = "An error occurred while generating the graph.";
			}
			exc.printStackTrace();
			notifyFinished();
			return;
		}
		catch (ChartDataException exc) {
			synchronized(this) {
				_hasFailed = true;
				_statusMessage = "An error occurred while generating the graph.";
			}
			exc.printStackTrace();
			notifyFinished();
			return;
		}
		
		// Finished drawing graph successfully.
		synchronized(this) {
			_hasFailed = false;
			_statusMessage = "";
		}
		notifyFinished();
	}
	
	private void notifyFinished() {
		// Call the callback function to signal that the graph has finished drawing.
		_callback.onCallback(null);
	}
	
	public int getWidth() {
		return _width;
	}
	
	public int getHeight() {
		return _height;
	}
	
	public synchronized BufferedImage getImage() {
		return _image;
	}

	public synchronized String getStatusMessage() {
		return _statusMessage;
	}
	
	public synchronized boolean getHasFailed() {
		return _hasFailed;
	}
}
