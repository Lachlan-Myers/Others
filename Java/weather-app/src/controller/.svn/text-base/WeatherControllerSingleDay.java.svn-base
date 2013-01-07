package controller;

import java.text.SimpleDateFormat;
import java.text.DateFormat;
import java.util.*;
import java.util.regex.*;

import javax.swing.JOptionPane;

import model.Station;
import model.Weather;
import model.WeatherScraper;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;

public class WeatherControllerSingleDay implements WeatherPropertyCallback {
	
	private Station _station;
	private Weather _weather;
		
	public WeatherControllerSingleDay() {
		_station = null;
		_weather = null;
	}
	
	/**
	 * Retrieves a list of available weather information by date.
	 * @param s Station to fetch weather information from.
	 * @return Sorted list of dates that have weather information.
	 */
	public List<GregorianCalendar> getAvailableDates(Station s) {
		ArrayList<GregorianCalendar> dates = new ArrayList<GregorianCalendar>();
		
		for (Weather w : s.getList()) {
			dates.add(w.getDate());
		}
		
		Collections.sort(dates);
		return dates;
	}
	
	public List<String> getYears(Station s) {
		List<GregorianCalendar> dates = getAvailableDates(s);
		List<Integer> years = new ArrayList<Integer>();
		
		// Construct a list of years. Note that we store a list of integers, rather than
		// strings. This is because we need to sort them later, and sorting Integer types works
		// properly, but sorting String types as numbers doesn't. We have a helper function to convert
		// back to a list of strings.
		boolean exists;
		for (GregorianCalendar g : dates) {
			
			exists = false;
			for (Integer year : years) {
				if (year.equals(g.get(Calendar.YEAR))) {
					exists = true;
					break;
				}
			}
			
			if (!exists)
				years.add(g.get(Calendar.YEAR));
		}
		
		Collections.sort(years);
		return intToStringList(years);
	}
	
	public List<String> getMonths(Station s, int year) {
		List<GregorianCalendar> dates = getAvailableDates(s);
		List<Integer> months = new ArrayList<Integer>();
		
		// Construct a list of months from the specified year.
		boolean exists;
		for (GregorianCalendar g : dates) {
			
			// We only want dates from the specified year.
			if (g.get(Calendar.YEAR) != year)
				continue;
			
			exists = false;
			for (Integer month : months) {
				if (month.equals(g.get(Calendar.MONTH) + 1)) {
					exists = true;
					break;
				}
			}
			
			if (!exists) {
				// Add one to the month, because it is zero-based.
				months.add(g.get(Calendar.MONTH) + 1);
			}
		}
		
		Collections.sort(months);
		return intToStringList(months);
	}
	
	public List<String> getDays(Station s, int month, int year) {
		List<GregorianCalendar> dates = getAvailableDates(s);
		List<Integer> days = new ArrayList<Integer>();
		
		// Construct a list of days from the specified month and year.
		for (GregorianCalendar g : dates) {
			
			// We only want dates from the specified month and year.
			if (g.get(Calendar.YEAR) != year || g.get(Calendar.MONTH) != month)
				continue;
			
			days.add(g.get(Calendar.DAY_OF_MONTH));
		}
		
		Collections.sort(days);
		return intToStringList(days);
	}
	
	/**
	 * Converts a list of integers to a list of strings.
	 * @param items List of integers to convert.
	 * @return List of strings, containing numbers in string format.
	 */
	private List<String> intToStringList(List<Integer> items) {
		List<String> list = new ArrayList<String>();
		for (Integer i : items) {
			list.add(Integer.toString(i));
		}
		
		return list;
	}
	

	/**
	 * Replaces any dynamic content markers in the specified DOM document with
	 * data from the Station object. This is done in-place on the document.
	 * @param doc DOM document containing dynamic markers to replace.
	 * @param curStation Station object to fetch data from.
	 */
	public void setDynamicContent(Document doc, Station s, GregorianCalendar date) {
		_station = s;
		
		// Fetch the specified day's weather. Use an empty weather item if it's not found.
		_weather = new Weather();
		_weather.setDate(new GregorianCalendar());
		
		Calendar weatherDate;
		for (Weather w : s.getList()) {
			weatherDate = w.getDate();
			
			if (date.get(Calendar.DAY_OF_MONTH) == weatherDate.get(Calendar.DAY_OF_MONTH)  &&
					date.get(Calendar.MONTH) == weatherDate.get(Calendar.MONTH) &&  
					date.get(Calendar.YEAR) == weatherDate.get(Calendar.YEAR)) {
				_weather = w;
				break;
			}
		}
				
		Element root = doc.getDocumentElement();
		Node body = root.getElementsByTagName("body").item(0);
		WeatherPageModifier.getProperties(this, doc, body);
		
		_station = null;
		_weather = null;
	}
	
	private void setDynamicProperty(Document d, Node n, String propName)
	{
		// Find the error node of the document, if any. This will record any errors that occur.
		Node errorNode = WeatherPageModifier.findNode(d.getDocumentElement().getElementsByTagName("body").item(0), "weatherapp_special_errormsg");
		
		if (_station == null) {
			if (errorNode != null) {
				errorNode.setTextContent("Error processing weather data.");
			}
			
			// Make all subsequent property values unknown.
			n.setTextContent("?");
			return;
		}
				
		if (propName.equals("stationname")) {	
			n.setTextContent(_station.getName());
			return;
		}
		
		if (_weather == null) {
			if (errorNode != null) {
				errorNode.setTextContent("Error processing weather data.");
			}
			
			// Make all subsequent property values unknown.
			n.setTextContent("?");
			return;
		}
		else {
		
			if (propName.equals("timespan")) {
				n.setTextContent("Daily Weather");
			}
			else if (propName.equals("date")) {
				n.setTextContent(new SimpleDateFormat("dd/MM/yyyy").format(_weather.getDate().getTime()));
			}
			else if (propName.equals("description")) {
				// Get the description and set it, along with the icon (based off the description).
				String desc = getDescription(_weather);
				n.setTextContent(desc);
				
				Node iconNode = WeatherPageModifier.findNode(d.getDocumentElement().getElementsByTagName("body").item(0), "weatherapp_special_icon");
				if (iconNode != null) {
					// Set icon to default if there aren't any matches.
					int icon = 50;
					
					if (desc.contains("sun") && !desc.contains("wind") && !desc.contains("rain")) {
						if (desc.contains("Very sunny"))
							icon = 36;
						else
							icon = 34;
					}
					else if (desc.contains("Cloudy") && !desc.contains("wind") && !desc.contains("rain")) {
						icon = 26;
					}
					else if (desc.contains("rain") && !desc.contains("wind")) {
						if (desc.contains("heavy") || desc.contains("moderate"))
							icon = 40;
						else
							icon = 11;
					}
					else if (desc.contains("wind") && !desc.contains("rain")) {
						if (desc.contains("Strong"))
							icon = 24;
						else
							icon = 23;
					}
					else if (desc.contains("rain") && desc.contains("wind")) {
						icon = 2;
					}
					
					if (iconNode.getAttributes() != null && iconNode.getAttributes().getLength() > 0)
					{
						Node attr = iconNode.getAttributes().getNamedItem("src");
						if (attr != null && attr.getTextContent() != null)
						{
							attr.setTextContent("images/weathericons/" + Integer.toString(icon) + ".png");
						}
						
					}
					
				}
			}
			else if (propName.equals("maxtemp")) {
				n.setTextContent(Float.toString(_weather.getMaxTemp()));
			}
			else if (propName.equals("mintemp")) {
				n.setTextContent(Float.toString(_weather.getMinTemp()));
			}
			else if (propName.equals("rain")) {
				n.setTextContent(Float.toString(_weather.getRain()));
			}
			else if (propName.equals("evaporation")) {
				n.setTextContent(Float.toString(_weather.getEvaporation()));
			}
			else if (propName.equals("sun")) {
				n.setTextContent(Float.toString(_weather.getSun()));
			}
			else if (propName.equals("gustdirection")) {
				if (_weather.getGustDirection() == null || _weather.getGustDirection().equals(""))
					n.setTextContent("N/A");
				else
					n.setTextContent(_weather.getGustDirection());
			}
			else if (propName.equals("gustspeed")) {
				n.setTextContent(Float.toString(_weather.getGustSpeed()));
			}
			else if (propName.equals("amtemperature")) {
				n.setTextContent(Float.toString(_weather.getAmTemp()));
			}
			else if (propName.equals("pmtemperature")) {
				n.setTextContent(Float.toString(_weather.getPmTemp()));
			}
			else if (propName.equals("amhumidity")) {
				n.setTextContent(Float.toString(_weather.getAmHumidity()));
			}
			else if (propName.equals("pmhumidity")) {
				n.setTextContent(Float.toString(_weather.getPmHumidity()));
			}
			else if (propName.equals("amcloud")) {
				n.setTextContent(Float.toString(_weather.getAmCloud()));
			}
			else if (propName.equals("pmcloud")) {
				n.setTextContent(Float.toString(_weather.getPmCloud()));
			}
			else if (propName.equals("amsealevelpressure")) {
				n.setTextContent(Float.toString(_weather.getAmSeaLevelPressure()));
			}
			else if (propName.equals("pmsealevelpressure")) {
				n.setTextContent(Float.toString(_weather.getPmSeaLevelPressure()));
			}
			else if (propName.equals("amwinddirection")) {
				if (_weather.getAmWindDirection() == null || _weather.getAmWindDirection().equals(""))
					n.setTextContent("N/A");
				else
					n.setTextContent(_weather.getAmWindDirection());
			}
			else if (propName.equals("pmwinddirection")) {
				if (_weather.getPmWindDirection() == null || _weather.getPmWindDirection().equals(""))
					n.setTextContent("N/A");
				else
					n.setTextContent(_weather.getPmWindDirection());
			}
			else if (propName.equals("amwindspeed")) {
				// Handle wind speed specifically, because it can either be a string like 'calm', or
				// a numeric value.
				if (_weather.getAmWindSpeed() == null || _weather.getAmWindSpeed().equals("")) {
					n.setTextContent("N/A");
				}
				else {
					try {
						n.setTextContent(Float.toString(Float.parseFloat(_weather.getAmWindSpeed())) + " km/h");
					}
					catch (NumberFormatException exc) {
						n.setTextContent(_weather.getAmWindSpeed());
					}
				}
			}
			else if (propName.equals("pmwindspeed")) {
				// Handle wind speed specifically, because it can either be a string like 'calm', or
				// a numeric value.
				if (_weather.getPmWindSpeed() == null || _weather.getPmWindSpeed().equals("")) {
					n.setTextContent("N/A");
				}
				else {
					try {
						n.setTextContent(Float.toString(Float.parseFloat(_weather.getPmWindSpeed())) + " km/h");
					}
					catch (NumberFormatException exc) {
						n.setTextContent(_weather.getPmWindSpeed());
					}
				}
			}
			else {
				// Unknown property.
				n.setTextContent("?");
			}
			
		}
		
	}
	
	private String getDescription(Weather w) {
		// Construct a description from the given weather information.
		StringBuffer desc = new StringBuffer();
		
		if (_weather.getSun() > 7.0)
			desc.append("Very sunny");
		else if (_weather.getSun() > 4.0)
			desc.append("Mostly sunny");
		else
			desc.append("Cloudy");
		
		if (_weather.getRain() > 7.0)
			desc.append(", heavy rain");
		else if (_weather.getRain() > 3.0)
			desc.append(", moderate rain");
		else if (_weather.getRain() > 1.0)
			desc.append(", light rain");
						
		if (_weather.getGustSpeed() > 70.0)
			desc.append(", strong winds");
		else if (_weather.getGustSpeed() > 50.0)
			desc.append(", windy");
		
		return desc.toString();
	}

	public void weatherPropertyCallback(Document d, Node n, String propName) {
		setDynamicProperty(d, n, propName);
	}
	
}
