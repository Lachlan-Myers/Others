package view;

import java.awt.*;
import java.awt.event.*;
import java.util.Observable;
import java.util.Observer;

import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.plaf.basic.BasicSplitPaneDivider;
import javax.swing.plaf.basic.BasicSplitPaneUI;

import controller.MainController;

import model.*;


public class MainView implements Runnable, Observer {

	private JFrame _frame;
	private JTabbedPane _tabPane;
	
	private Favourite _favouritesModel;
	private Station _currentStation;
	
	private StationView _stationView;
	private WeatherView _weatherView;
	private GraphView _graphView;
	private SplashView _splashView;
	
	private MainController _mainController;
	
	public MainView(Favourite favouritesModel) {
		
		// Add ourselves as an observer of the model, so we know when to show/hide
		// tabs.
		_favouritesModel = favouritesModel;
		_favouritesModel.addObserver(this);
		_currentStation = _favouritesModel.getCurrentStation();
				
		// Create the views.
		_stationView = new StationView(_favouritesModel);
		_weatherView = new WeatherView(_favouritesModel);
		_graphView = new GraphView(_favouritesModel);
		_splashView = new SplashView();
		
		// Create the main controller.
		_mainController = new MainController(this, _favouritesModel);

		// Create the GUI, but don't run it yet.
		initializeGUI();
	}
	
	private void initializeGUI() {
		// Create the main frame.
		_frame = new JFrame("Weather - Team Friday");
		_frame.setMinimumSize(new Dimension(800, 600));
		_frame.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		
		// Set the controller to handle window events, specifically, show a confirm dialog
		// to exit the app, and saving settings on exit.
		_frame.addWindowListener(_mainController);
		
		// Create the left and right panels.
		JPanel mainPanel, leftPanel, rightPanel;
		
		mainPanel = new JPanel();
		mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.LINE_AXIS));
		
		leftPanel = new JPanel();
		leftPanel.setLayout(new BoxLayout(leftPanel, BoxLayout.LINE_AXIS));
		leftPanel.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));
		leftPanel.setMinimumSize(new Dimension(230, Short.MAX_VALUE));
		leftPanel.setPreferredSize(new Dimension(230, Short.MAX_VALUE));
		
		rightPanel = new JPanel();
		rightPanel.setLayout(new BoxLayout(rightPanel, BoxLayout.LINE_AXIS));
		rightPanel.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));
		rightPanel.setMinimumSize(new Dimension(400, Short.MAX_VALUE));
		
		createStationPanel(leftPanel);
		createTabPane(rightPanel);
		createMenu(_frame);
		
		createSplitPanel(mainPanel, leftPanel, rightPanel);
		
		_frame.add(mainPanel);
		
		// Set the current station to 'null', which should cause all the views
		// to update.
		_favouritesModel.setCurrentStation(null);
		
		// Load the application settings.
		_mainController.loadSettings();
		
	}
	
	private void createSplitPanel(JPanel parent, JPanel left, JPanel right) {
		// Create the split panel, with left and right panels, and add it to the parent.
		JSplitPane splitPane = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT);
		
		// We don't want the divider to have a 'raised' border, because it doesn't look good. Override
		// the default platform behaviour.
		splitPane.setUI(new BasicSplitPaneUI() {
			public BasicSplitPaneDivider createDefaultDivider() {
				return new BasicSplitPaneDivider(this) {
					public void setBorder(Border b) {
					}
				};
			}
		});
		
		splitPane.setBorder(null);
		
		splitPane.setTopComponent(left);
		splitPane.setBottomComponent(right);
		
		parent.add(splitPane);
	}
	
	private void createStationPanel(JPanel parent) {
		// Add the station view to the parent panel.
		parent.add(_stationView);
	}
	
	private void createTabPane(JPanel parent) {
		// Create the tab pane that holds the splash, weather and graph views, and add it to
		// the parent panel.
		_tabPane = new JTabbedPane();
		
		_tabPane.addTab("Start Page", _splashView);
		
		parent.add(_tabPane);
	}
	
	private void createMenu(JFrame parent) {
		// Create the main menu bar, and set it to the parent frame.
		JMenuBar menu = new JMenuBar();
		JMenu fileMenu = new JMenu("File");
		JMenuItem exit = new JMenuItem("Exit");
		
		JMenu optionMenu = new JMenu("Options");
		JMenuItem networkItem = new JMenuItem("Network Connection...");
		
		JMenu helpMenu = new JMenu("Help");
		JMenuItem about = new JMenuItem("About...");
		
		menu.add(fileMenu);
		fileMenu.add(exit);
		
		menu.add(optionMenu);
		optionMenu.add(networkItem);
		
		menu.add(helpMenu);
		helpMenu.add(about);
		
		exit.addActionListener(new ActionListener() {
	  		public void actionPerformed(ActionEvent e) {
	 			_frame.dispatchEvent(new WindowEvent(_frame, WindowEvent.WINDOW_CLOSING));
	   		}
	   	});
		
		networkItem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				_mainController.showNetworkConnDialog();
			}
		});
		
		about.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				_mainController.showAboutDialog();
			}
		});
		
		parent.setJMenuBar(menu);
	}
	
	public void run() {
		// Show the main window.
		_frame.setVisible(true);
	}
	
	public JFrame getWindow() {
		return _frame;
	}
	
	public StationView getStationView() {
		return _stationView;
	}
	
	public WeatherView getWeatherView() {
		return _weatherView;
	}
	
	public GraphView getGraphView() {
		return _graphView;
	}

	public void update(Observable observeObject, Object obj) {
		if (observeObject instanceof Favourite) {
			// Has the currently selected station changed?
			if (_currentStation != _favouritesModel.getCurrentStation()) {
				// If it has changed to 'null', then remove the Weather and Graph tabs.
				// If it has changed from 'null' to a station, then add the tabs.
				// Otherwise, do nothing, the views will update themselves.
				boolean changeTab = false;
				
				if (_tabPane != null) {
					if (_currentStation != null && _favouritesModel.getCurrentStation() == null) {
						// Remove tabs.
						if (_tabPane.getTabCount() >= 3) {
							_tabPane.remove(2);
							_tabPane.remove(1);
						}
					}
					else if (_currentStation == null && _favouritesModel.getCurrentStation() != null) {
						// Add tabs.
						_tabPane.addTab("Weather", _weatherView);
						_tabPane.addTab("Graph", _graphView);
						changeTab = true;
					}
					else {
						changeTab = true;
					}
				}
				
				if (changeTab) {
					// If we're currently on the start page, then change to the weather tab.
					if (_tabPane.getTabCount() >= 3 && _tabPane.getSelectedIndex() == 0)
						_tabPane.setSelectedIndex(1);
				}
				
				// Update to the current station.
				_currentStation = _favouritesModel.getCurrentStation();
				
				_frame.repaint();
			}
		}
		
	}
	
		
}
