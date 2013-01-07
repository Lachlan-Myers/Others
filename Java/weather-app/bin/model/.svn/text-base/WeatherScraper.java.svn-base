package model;

import java.net.*;
import java.io.*;
import java.util.*;

public class WeatherScraper 
{
	
	private Station station;
	
	public WeatherScraper()
	{
		
	}
	
	public WeatherScraper(Station station)
	{	
		this.station = station;
	}
	
	/*
	 * This method will fetch the weather data for one month and return the ArrayList<Weather> object
	 * @return ArrayList<Weather>
	 */
	
	public ArrayList<Weather> fetchMonthlyWeather()
	{
		Weather weather;
		String productCode = this.station.getID();
		
		ArrayList<Weather> weatherList = new ArrayList<Weather>();
		URL latest;
		String inputLine;
		String [] todaysWeather = new String[22];
		
		
		try
		{
			latest = new URL("http://www.bom.gov.au/climate/dwo/201004/text/" + productCode + ".201004.csv");
			//connect to the URL
			Connector con = new Connector(latest);
			BufferedReader in = con.getPageReader();
			
			//loop through the lines of the csv file
			while ((inputLine = in.readLine()) != null)
			{
				if(inputLine.contains(",20"))
				{
					todaysWeather = parseCsv(inputLine);
					weather = createWeatherObject(todaysWeather);
					weatherList.add(weather);
				}
			}
		}
		catch(MalformedURLException e)
		{
			System.err.println(e.getMessage());
		}
		catch(IOException e)
		{
			System.err.println(e.getMessage());
		}
		
		return weatherList;
	}
	
	/*
	 * This method will fetch the weather data for all months of the current year and return the ArrayList<Weather> object
	 * @return ArrayList<Weather>
	 */
	
	public ArrayList<Weather> fetchYearToDateWeather()
	{
		Weather weather;
		String productCode = this.station.getID();
		Calendar calendar = new GregorianCalendar();
		int month = calendar.get(Calendar.MONTH) + 1;
		
		
			
		ArrayList<Weather> weatherList = new ArrayList<Weather>();
		//String csvLink = getLinks();
		URL latest;
		String inputLine, m;
		String [] todaysWeather = new String[22];
		
		while(month != 0)
		{
			try
			{
				
				if(month < 10)
				{
					m = Integer.toString(month);
					m = "0" + m;
				}
				else
				{
					m = Integer.toString(month); 
				}
				latest = new URL("http://www.bom.gov.au/climate/dwo/2010" + m + "/text/" + productCode + ".2010" + m + ".csv");
				//connect to the URL
				Connector con = new Connector(latest);
				BufferedReader in = con.getPageReader();
				
				//loop through the lines of the csv file
				while ((inputLine = in.readLine()) != null)
				{
					if(inputLine.contains(",20"))
					{
						todaysWeather = parseCsv(inputLine);
						weather = createWeatherObject(todaysWeather);
						weatherList.add(weather);
					}
				}
			}
			catch(MalformedURLException e)
			{
				System.err.println(e.getMessage());
			}
			catch(IOException e)
			{
				System.err.println(e.getMessage());
			}
			
			month--;
		}
		return weatherList;
	}

	
	/*
	 * This method puts all the parsed strings from the csv file into the 
	 * weather object eg temp, wind rain etc. 
	 * 
	 * @param list an array of strings from the parseCsv method
	 * @param weather a Weather object 
	 * @return Weather object
	 * 
	 */
	
	public Weather createWeatherObject(String[] list)
	{
		
		//data is updated in the csv in real time so if its morning then
		//there is no data for pm so the array is shorter than after 3pm
		//so we need the array size and an iterator
		Weather weather = new Weather();
		int arraySize = list.length;
		int i = 0;
		GregorianCalendar date;
		
		//Date
		i++;
		if(i < arraySize)
		{
			String[] values = list[1].split("-");
			date = new GregorianCalendar(Integer.parseInt(values[0]), Integer.parseInt(values[1])-1, Integer.parseInt(values[2]));
			weather.setDate(date);
		}
		
		//Minimum temp
		i++;
		if(i < arraySize && !list[2].equals(""))
		{
			weather.setMinTemp(Float.valueOf(list[2].trim()).floatValue());
		}
		
		//Maximum temp
		i++;
		if(i < arraySize && !list[3].equals(""))
		{	
			weather.setMaxTemp(Float.valueOf(list[3].trim()).floatValue());
		}
		
		//Rainfall
		i++;
		if(i < arraySize && !list[4].equals(""))
		{
			weather.setRain(Float.valueOf(list[4].trim()).floatValue());
		}
		
		//Evaporation
		i++;
		if(i < arraySize && !list[5].equals(""))
		{
			weather.setEvaporation(Float.valueOf(list[5].trim()).floatValue());
		}
		
		//Sunlight
		i++;
		if(i < arraySize && !list[6].equals(""))
		{
			weather.setSun(Float.valueOf(list[6].trim()).floatValue());
		}
		
		//Gust direction
		i++;
		if(i < arraySize)
		{
			weather.setGustDirection(list[7]);
		}
		
		//Gust speed
		i++;
		if(i < arraySize && !list[8].equals(""))
		{
			weather.setGustSpeed(Float.valueOf(list[8].trim()).floatValue());
		}
		
		//Gust time
		i++;
//		if(i < arraySize && !list[9].equals("")){
//			weather.setGustTime(Float.valueOf(list[9]);
//		}
		
		//9am temp
		i++;
		if(i < arraySize && !list[10].equals(""))
		{
			weather.setAmTemp(Float.valueOf(list[10].trim()).floatValue());
		}
		
		//9am humidity
		i++;
		if(i < arraySize && !list[11].equals(""))
		{
			weather.setAmHumidity(Float.valueOf(list[11].trim()).floatValue());
		}
		
		//9am cloud cover
		i++;
		if(i < arraySize && !list[12].equals(""))
		{
			weather.setAmCloud(Float.valueOf(list[12].trim()).floatValue());
		}
		
		//9am wind direction
		i++;
		if(i < arraySize)
		{
			weather.setAmWindDirection(list[13]);
		}
		
		//9am wind speed
		i++;
		if(i < arraySize)
		{
			//weather.setAmWindSpeed(Float.valueOf(list[14].trim()).floatValue());
			weather.setAmWindSpeed(list[14].trim());
		}
		
		//9am pressure
		i++;
		if(i < arraySize && !list[15].equals(""))
		{
			weather.setAmSeaLevelPressure(Float.valueOf(list[15].trim()).floatValue());
		}
		
		//3pm temp
		i++;
		if(i < arraySize && !list[16].equals(""))
		{
			weather.setPmTemp(Float.valueOf(list[16].trim()).floatValue());
		}
		
		//3pm humidity
		i++;
		if(i < arraySize && !list[17].equals(""))
		{
			weather.setPmHumidity(Float.valueOf(list[17].trim()).floatValue());
		}
		
		//3pm cloud
		i++;
		if(i < arraySize && !list[18].equals(""))
		{
			weather.setPmCloud(Float.valueOf(list[18].trim()).floatValue());
		}
		
		//3pm wind dir
		i++;
		if(i < arraySize)
		{
			weather.setPmWindDirection(list[19]);
			
		}
		
		//3pm wind speed
		i++;
		if(i < arraySize)
		{
			
			//weather.setPmWindSpeed(Float.valueOf(list[20].trim()).floatValue());
			weather.setPmWindSpeed(list[20]);
		}
		
		//3pm sealevel pressure
		i++;
		if(i < arraySize && !list[21].equals(""))
		{
			weather.setPmSeaLevelPressure(Float.valueOf(list[21].trim()).floatValue());
		}
		
		return weather; 
	}
	
	
	public Weather getTodaysWeather(){
		GregorianCalendar date = new GregorianCalendar();
		Weather weather = new Weather(date);
		return weather;
	}
	
	/*
	 * This method parses the csv file by the "," token. 
	 * String.split() method is used because StringTokenizer class ignores null values
	 * eg if there is no data between commas: "25,,,31,NW" etc..
	 * 
	 * @param line the line from the csv file to parse
 	 * @return values a String[] array with all the weather data values.
 	 * 
	 */
	
	private String[] parseCsv(String line)
	{	
		String[] values = new String[22];		
		values = line.split(",");
		return values;
	}
}

