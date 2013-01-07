package model;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class WeatherTimeFrame {
	public List<Weather> WeatherItems;
	public String Description;
	public String StationName;
	
	public WeatherTimeFrame() {
		WeatherItems = new ArrayList<Weather>();
		Description = "";
		StationName = "";
	}
	
	public String toString() {
		return Description;
	}
}
