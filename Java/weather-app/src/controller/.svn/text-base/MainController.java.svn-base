package controller;

import java.awt.Frame;
import java.awt.GraphicsEnvironment;
import java.awt.Point;
import java.awt.Rectangle;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;

import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.WindowConstants;

import view.*;
import model.*;

public class MainController implements WindowListener {
	private MainView _mainView;
	private Favourite _favouritesModel;
	private AppSettings _appSettings;
	
	public MainController(MainView mainView, Favourite favouritesModel) {
		// Store the main view and favourites model references.
		_mainView = mainView;
		_favouritesModel = favouritesModel;
		
		// Create the appsettings object to fetch and store app settings.
		_appSettings = new AppSettings();
	}

	public void windowActivated(WindowEvent e) {
	}

	public void windowClosed(WindowEvent e) {
	}

	public void windowClosing(WindowEvent e) {
		// Prompt user to exit.
		/*int n = JOptionPane.showConfirmDialog(_mainView.getWindow(),"Are you sure you want to exit?",
				"Quit",JOptionPane.YES_NO_OPTION);
	  
		if (n == JOptionPane.YES_OPTION) {
			// Save favourites to file.
			_favouritesModel.saveFavourites();
			
			// Exit app.
			System.exit(0);
		}*/
		
		// Save favourites to file.
		_favouritesModel.saveFavourites();
		
		// Store the window position, size and whether it's maximized. If it is maximized, don't overwrite position and size,
		// keep the old values.
		if ((_mainView.getWindow().getExtendedState() & JFrame.MAXIMIZED_BOTH) != 0) {
			_appSettings.set("windowmaximized", "true");
		}
		else {
			_appSettings.set("windowsize", Integer.toString(_mainView.getWindow().getSize().width) + "x" + Integer.toString(_mainView.getWindow().getSize().height));
			_appSettings.set("windowposition", Integer.toString(_mainView.getWindow().getLocation().x) + "," + Integer.toString(_mainView.getWindow().getLocation().y));
			_appSettings.set("windowmaximized", "false");
		}
		
		// Store the proxy settings.
		if (RMITProxy.getProxyAddress() != null)
			_appSettings.set("proxyaddress", RMITProxy.getProxyAddress());
		else {
			// Remove the proxy property, if it exists.
			_appSettings.delete("proxyaddress");
		}
		
		// Save app settings to file.
		while (true) {
			try {
				_appSettings.saveSettings();
			}
			catch (Exception exc) {
				// Prompt user to retry.
				int opt = JOptionPane.showConfirmDialog(_mainView.getWindow(), "An error occurred while saving application settings.\nTry to save settings again?",
						"Error", JOptionPane.YES_NO_CANCEL_OPTION);
			  
				if (opt == JOptionPane.YES_OPTION)
					continue;
				else if (opt == JOptionPane.NO_OPTION)
					break;
				else
					return;
			}
			
			break;
		}
		
		// Exit app.
		System.exit(0);
	}

	public void windowDeactivated(WindowEvent e) {
	}

	public void windowDeiconified(WindowEvent e) {
	}

	public void windowIconified(WindowEvent e) {
	}

	public void windowOpened(WindowEvent e) {
	}
	
	public void loadSettings() {
		// Try to load favourites from file.
		try {
			_favouritesModel.loadFile();
		}
		catch (Exception exc) {
			// Show an error message to the user.
			JOptionPane.showMessageDialog(_mainView.getWindow(), "An error occurred while loading information from file.\n" +
					"Some or all favourites and weather data may not be available.", "Error", JOptionPane.ERROR_MESSAGE);
		}
		
		// Load app settings from file.
		try {
			_appSettings.loadSettings();
		}
		catch (Exception exc) {
			// Show an error message to the user.
			JOptionPane.showMessageDialog(_mainView.getWindow(), "An error occurred while loading settings from file.\n" +
					"Some settings may have been reset.", "Error", JOptionPane.ERROR_MESSAGE);
		}
		
		// Set window position and size based on settings file.
		setWindowProperties();
		
		// Set the proxy address, if it exists.
		String proxy = _appSettings.get("proxyaddress");
		if (proxy != null) {
			RMITProxy.byPassProxy(proxy);
		}
		
	}
	
	public void setWindowProperties() {
		// Try to set window position and size.
		int x = 5, y = 5;
		int width = 800, height = 600;
		boolean maximized = false;
		
		String position = _appSettings.get("windowposition");
		String size = _appSettings.get("windowsize");
		String isMaximized = _appSettings.get("windowmaximized");
		
		if (position != null) {
			String[] tokens = position.split(",");
			if (tokens.length == 2) {
				int xPos, yPos;
				try {
					xPos = Integer.parseInt(tokens[0]);
					yPos = Integer.parseInt(tokens[1]);
					
					x = xPos;
					y = yPos;
				}
				catch (Exception exc) {
				}
			}
		}
		
		if (size != null) {
			String[] tokens = size.split("x");
			if (tokens.length == 2) {
				int tempWidth, tempHeight;
				try {
					tempWidth = Integer.parseInt(tokens[0]);
					tempHeight = Integer.parseInt(tokens[1]);
					
					width = tempWidth;
					height = tempHeight;
				}
				catch (Exception exc) {
				}
			}
		}
		
		if (isMaximized != null) {
			if (isMaximized.equals("true"))
				maximized = true;
		}
		
		setWindowPosition(x, y);
		setWindowSize(width, height);
		setWindowMaximized(maximized);
	}
	
	private void setWindowPosition(int x, int y) {
		// If the given x,y coords would cause an 800x600 window to be displayed
		// off-screen, then change the coordinates so it would fit.
		GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
		Rectangle screen = ge.getMaximumWindowBounds();
		if ((screen.x + x + 800) > screen.width) {
			x = Math.max(0, screen.width - 800);
		}
		else if (x < 0) {
			x = 0;
		}
		
		if ((screen.y + y + 600) > screen.height) {
			y = Math.max(0, screen.height - 600);
		}
		else if (y < 0) {
			y = 0;
		}

		_mainView.getWindow().setLocation(x, y);
	}
	
	private void setWindowSize(int width, int height) {
		// If the current position plus the specified size causes the window to exceed
		// the screen dimensions, reduce the position until it fits. If it still doesn't fit,
		// reduce the dimensions so it fits (but is still 800x600).
		GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
		Rectangle screen = ge.getMaximumWindowBounds();
		Point curPos = _mainView.getWindow().getLocation();
		
		if ((curPos.x + width) > screen.width) {
			curPos.x = Math.max(0, screen.width - width);
		}
		
		if ((curPos.y + height) > screen.height) {
			curPos.y = Math.max(0, screen.height - height);
		}
		
		if (curPos.x + width > screen.width) {
			width = Math.max(800, screen.width - curPos.x - 5);
		}
		
		if (curPos.y + height > screen.height) {
			height = Math.max(600, screen.height - curPos.y - 5);
		}
		
		_mainView.getWindow().setLocation(curPos);
		_mainView.getWindow().setSize(width, height);
	}

	private void setWindowMaximized(boolean maximized) {
		if (maximized)
			_mainView.getWindow().setExtendedState(_mainView.getWindow().getExtendedState() | JFrame.MAXIMIZED_BOTH);
		else
			_mainView.getWindow().setExtendedState(_mainView.getWindow().getExtendedState() & ~JFrame.MAXIMIZED_BOTH);
	}
	
	public void showNetworkConnDialog() {
		ProxyAuthenticatorView proxyView = new ProxyAuthenticatorView();
		
		String existingProxy = RMITProxy.getProxyAddress();
		if (existingProxy != null)
			proxyView.setProxyAddress(existingProxy);
		
		proxyView.setVisible(true);
		
		if (proxyView.getDialogResult() == JOptionPane.OK_OPTION) {
			if (proxyView.getProxyAddress() != null && proxyView.getProxyAddress().length() > 0) {
				// Set the proxy.
				RMITProxy.byPassProxy(proxyView.getProxyAddress());
			}
			else {
				// Remove the proxy.
				RMITProxy.byPassProxy(null);
			}
		}
	}
	
	public void showAboutDialog() {
		AboutView aboutView = new AboutView();
		aboutView.setVisible(true);
	}
	
	
}
