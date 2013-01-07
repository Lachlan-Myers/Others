import javax.swing.SwingUtilities;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

import model.*;
import controller.*;
import view.*;

public class AppMain {

	/**
	 * Application entry point. Creates and passes control to the GUI.
	 * @param args Application arguments (unused)
	 */
	public static void main(String[] args) {
		runGUI();
	}
	
	public static void runGUI() {
		// Set the look-and-feel to system, which means the application's controls will look
		// like native controls.
		try {
			UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
		}
		catch (UnsupportedLookAndFeelException exc)
		{
			System.err.println("The system theme for this platform is not supported.\n" +
					"Falling back to default cross-platform theme.");
		}
		catch (Exception exc)
		{
			System.err.println("There was a problem trying to set the theme for this platform.\n" +
			"Falling back to default cross-platform theme.");
		}
		
		// Create the model.
		Favourite favouriteModel = new Favourite();
		
		// Create the main view.
		MainView mainView = new MainView(favouriteModel);
		
		// Run the view on the Event Dispatch Thread.
		SwingUtilities.invokeLater(mainView);
	}

}
