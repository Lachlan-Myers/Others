package model;

import java.io.*;
import java.util.Hashtable;
import java.util.Map;

/**
 * Loads and stores settings for the application in a file.
 *
 */
public class AppSettings {
	
	public final static String FILENAME = "appsettings.conf";
	private Map<String, String> _settings;
	
	public AppSettings() {
		_settings = new Hashtable<String, String>();
	}

	/**
	 * Load settings from application file.
	 * @throws IOException If reading the file failed. Some or all data might be missing.
	 */
	public void loadSettings() throws IOException {
		
		try {
			BufferedReader reader = new BufferedReader(new FileReader(FILENAME));
			
			String line;
			while ((line = reader.readLine()) != null) {
				parseLine(line);
			}
			
			reader.close();
		}
		catch (FileNotFoundException exc) {
			// Do nothing, because the file will be created upon calling saveSettings().
		}
		
	}
	
	/**
	 * Save settings to file.
	 */
	public void saveSettings() throws IOException {
		BufferedWriter writer = new BufferedWriter(new FileWriter(FILENAME));
		
		// Get the environment's default newline character. If that fails, use '\n' as default.
		String newline = "\n";
		try {
			newline = System.getProperty("line.separator");
		}
		catch (Exception exc) {
		}
		
		for (String name : _settings.keySet()) {
			writer.write(name + " = " + _settings.get(name) + newline);
		}
		
		writer.close();
	}
	
	/**
	 * Set the specified property value. Replaces existing value, if any.
	 * @param name Name of property to set.
	 * @param value Value to set to.
	 */
	public void set(String name, String value) {
		_settings.put(name, value);
	}
	
	/**
	 * Get the specified property value.
	 * @param name Name of property to get.
	 * @return Property value. Null if not found.
	 */
	public String get(String name) {
		if (_settings.containsKey(name))
			return _settings.get(name);
		else
			return null;
	}
	
	/**
	 * Removes the specified property, if it exists. Otherwise, it does nothing.
	 * @param name Property to remove.
	 */
	public void delete(String name) {
		if (_settings.containsKey(name))
			_settings.remove(name);
	}
	
	private void parseLine(String line) {
		String[] tokens = line.split("=");
		if (tokens == null || tokens.length < 2)
			return;
		
		_settings.put(tokens[0].trim(), tokens[1].trim());
	}
}
