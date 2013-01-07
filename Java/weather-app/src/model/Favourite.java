package model;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;
import java.util.Map;
import java.util.Observable;

import javax.swing.JOptionPane;


/*
 * Contain a list of User's favourite stations
 */
public class Favourite extends Observable {
	Map<String, Station> stations=new HashMap<String, Station>();
	Station _currentStation;
	
	
	public Favourite()
	{
		_currentStation = null;
	}
	
	public void addStation(Station s)
	{
		stations.put(s.getName(), s);
		
		// Model has changed, notify attached observers.
		setChanged();
		notifyObservers();
	}
	/*
	 * @param s Station to be removed from favourite list
	 */
	public void removeStation(Station s)
	{
	    stations.remove(s.getName());

	    // Model has changed, notify attached observers.
		setChanged();
		notifyObservers();

	}
	
	/*
	 * Fetch a list of station
	 */
	public Map<String, Station> getStations()
	{
		return stations;
	}
	
	/**
	 * Change current station to a new selected station. If the selected station
	 * does not have any data, then fetch it. Also, if it does not have today's
	 * data, fetch it.
	 * @param station Station to change to.
	 */
	public void setCurrentStation(Station station)
	{
		if (station != null) {
			// If we don't have any weather data for the station, fetch it.
			WeatherScraper ws = new WeatherScraper(station);
			List<Weather> list = ws.fetchYearToDateWeather();
			if (station.getList().size() < 1) {
				for (Weather w : list) {
					station.addToList(w);
				}
			}
			
			// If we don't have today's weather, fetch it.
			Calendar now = new GregorianCalendar();
			Calendar weatherDate;
			boolean foundToday = false;
			
			for (Weather w : station.getList()) {
				weatherDate = w.getDate();
				
				if (now.get(Calendar.DAY_OF_MONTH) == weatherDate.get(Calendar.DAY_OF_MONTH)  &&
						now.get(Calendar.MONTH) == weatherDate.get(Calendar.MONTH) &&  
						now.get(Calendar.YEAR) == weatherDate.get(Calendar.YEAR)) {
					foundToday = true;
					break;
				}
			}
			
			if (!foundToday) {
				// Fetch today's weather.
				station.addToList(ws.getTodaysWeather());
			}
		}
		
		_currentStation = station;
		setChanged();
		notifyObservers();
	}
	
	public Station getCurrentStation()
	{
		return _currentStation;
	}
	
	/*
	 * this method saves the last 30 days' weather for all favoured stations 
	 */
	public void saveFavourites() 
	{
		/*
		 * delete file if it already exist
		 */
		File saveFile = new File("weatherData.csv");
		
		if(saveFile.exists())
		{
		    saveFile.delete();
		}
		try {
			saveFile.createNewFile();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		Station temp;
		for(String key : stations.keySet())
		{
            temp=stations.get(key);
            temp.saveStations();
		}
	}
	/*
	 * function to load saved file from fixed location
	 * call this function will create favourites 
	 * @return false if file is not found
	 */
	public boolean loadFile()
	{
		File saveFile = new File("weatherData.csv");
		if(!saveFile.exists())
		{
			System.out.println("file not found");
			return false;
		}
		String line;
		Station station = null;
		ArrayList<String> file=new ArrayList<String>();
		
        try
        {    
            BufferedReader in = new BufferedReader(new FileReader(saveFile));

            if (!in.ready())
                throw new IOException();

            while ((line = in.readLine()) != null)
                file.add(line);

            in.close();
        }
        catch (IOException e)
        {
            System.out.println(e);
        }
        String currentName="NULL";
		ListIterator<String> it=file.listIterator();
		while(it.hasNext())
		{
			String s=it.next();
			String[] values=parseFile(s);
			
			String state=values[1];
			String name=values[2];
			String id=values[3];
			int stateId=Integer.parseInt(values[4]);
			
			if(name.compareTo(currentName)!=0)
			{
				station=new Station(id, name, state, stateId);
				addStation(station);
				currentName=name;
			}
			String[] weatherData = new String[22];
			for(int i=1; i<weatherData.length; i++)
			{
				weatherData[i]=values[4+i];
			}
			WeatherScraper weatherScrape = new WeatherScraper();
			Weather temp = new Weather();
			Weather weather = weatherScrape.createWeatherObject(weatherData);
			//GregorianCalendar date=weather.getDate();
			//System.out.println(date.get(Calendar.DAY_OF_MONTH)+"/"+date.get(Calendar.MONTH)+"/"+date.get(Calendar.YEAR));
			station.addToList(weather);
		}
		return true;
	}
	private String[] parseFile(String line) {
		
		String[] values = new String[27];
			
			values = line.split(",");
			
		    return values;
	}
	
}
