package model;

import java.io.File;
import java.io.FileOutputStream;
import java.io.PrintStream;
import java.util.Calendar;
import java.util.GregorianCalendar;


/*
 * This weather class will store all the possible attributes about weather forecasts
 * @author Liam
 */
public class Weather {

	private GregorianCalendar date;
	private float maxTemp; //celcius
	private float minTemp;
	private float rain; //mm
	private float evaporation; //mm
	private float sun; //hrs
	
	//wind gust infor
	private String gustDirection;
	private float gustSpeed; //km/h
	private float gustTime;// not prase
	
	//9 am temperature
	private float amTemp; //celcius
	private float amHumidity; //%
	private String amWindSpeed; //km/h
	private String amWindDirection;
	private float amCloud; //oktas
	private float amSeaLevelPressure; //hPa
	
	//3 pm temperature
	private float pmTemp; //celcius
	private float pmHumidity; //%
	private String pmWindSpeed; //km/h
	private String pmWindDirection;
	private float pmCloud; //oktas
	private float pmSeaLevelPressure; //hPa
	
	public Weather(){
		
	}
	
	public Weather(GregorianCalendar date)
	{
		this.date=date;
	}
	public GregorianCalendar getDate() {
		return date;
	}

	public void setDate(GregorianCalendar date) {
		this.date = date;
	}

	public float getMaxTemp() {
		return maxTemp;
	}

	public void setMaxTemp(float maxTemp) {
		this.maxTemp = maxTemp;
	}

	public float getMinTemp() {
		return minTemp;
	}

	public void setMinTemp(float minTemp) {
		this.minTemp = minTemp;
	}

	public float getRain() {
		return rain;
	}

	public void setRain(float rain) {
		this.rain = rain;
	}

	public float getEvaporation() {
		return evaporation;
	}

	public void setEvaporation(float evaporation) {
		this.evaporation = evaporation;
	}

	public float getSun() {
		return sun;
	}

	public void setSun(float sun) {
		this.sun = sun;
	}

	public String getGustDirection() {
		return gustDirection;
	}

	public void setGustDirection(String gustDirection) {
		this.gustDirection = gustDirection;
	}

	public float getGustSpeed() {
		return gustSpeed;
	}

	public void setGustSpeed(float gustSpeed) {
		this.gustSpeed = gustSpeed;
	}

	public float getGustTime() {
		return gustTime;
	}

	public void setGustTime(float gustTime) {
		this.gustTime = gustTime;
	}

	public float getAmTemp() {
		return amTemp;
	}

	public void setAmTemp(float amTemp) {
		this.amTemp = amTemp;
	}

	public float getAmHumidity() {
		return amHumidity;
	}

	public void setAmHumidity(float amHumidity) {
		this.amHumidity = amHumidity;
	}

	public String getAmWindSpeed() {
		return amWindSpeed;
	}

	public void setAmWindSpeed(String amWindSpeed) {
		this.amWindSpeed = amWindSpeed;
	}

	public String getAmWindDirection() {
		return amWindDirection;
	}

	public void setAmWindDirection(String amWindDirection) {
		this.amWindDirection = amWindDirection;
	}

	public float getAmCloud() {
		return amCloud;
	}

	public void setAmCloud(float amCloud) {
		this.amCloud = amCloud;
	}

	public float getAmSeaLevelPressure() {
		return amSeaLevelPressure;
	}

	public void setAmSeaLevelPressure(float amSeaLevelPressure) {
		this.amSeaLevelPressure = amSeaLevelPressure;
	}

	public float getPmTemp() {
		return pmTemp;
	}

	public void setPmTemp(float pmTemp) {
		this.pmTemp = pmTemp;
	}

	public float getPmHumidity() {
		return pmHumidity;
	}

	public void setPmHumidity(float pmHumidity) {
		this.pmHumidity = pmHumidity;
	}

	public String getPmWindSpeed() {
		return pmWindSpeed;
	}

	public void setPmWindSpeed(String pmWindSpeed) {
		this.pmWindSpeed = pmWindSpeed;
	}

	public String getPmWindDirection() {
		return pmWindDirection;
	}

	public void setPmWindDirection(String pmWindDirection) {
		this.pmWindDirection = pmWindDirection;
	}

	public float getPmCloud() {
		return pmCloud;
	}

	public void setPmCloud(float pmCloud) {
		this.pmCloud = pmCloud;
	}

	public float getPmSeaLevelPressure() {
		return pmSeaLevelPressure;
	}

	public void setPmSeaLevelPressure(float pmSeaLevelPressure) {
		this.pmSeaLevelPressure = pmSeaLevelPressure;
	}
    /*
     * plain text file is used
     * format is same as the source csv file plus station name, region, state
     * Save input data into plain csv txt for better reading
     * @param stationName the name of the station
     * @param state the state of the station is in
     * @param ID of the station
     * @param StateID id of the state where the station is in
     */
	public void saveWeather(String stationName, String state, String ID, int StateId)
	{
		FileOutputStream out;
		PrintStream p;
		try
		{
			// Out put to weatherData.csv file with comma separated format
			out=new FileOutputStream("weatherData.csv", true);
			p=new PrintStream(out);
			p.print(","+state);
			p.print(","+stationName);
			p.print(","+ID);
			p.print(","+StateId);
			int day=date.get(Calendar.DATE);
			int month=(date.get(Calendar.MONTH))+1;
			int year=date.get(Calendar.YEAR);
			p.print(","+year+"-"+month+"-"+day);
			p.print(","+minTemp);
			p.print(","+maxTemp);
			p.print(","+rain);
			p.print(","+evaporation);
			p.print(","+sun);
			p.print(","+gustDirection);
			p.print(","+gustSpeed);
			p.print(","+gustTime);
			/*
			 * 9 am weather
			 */
			p.print(","+amTemp);
			p.print(","+amHumidity);
			p.print(","+amCloud);
			p.print(","+amWindDirection);
			p.print(","+amWindSpeed);
			p.print(","+amSeaLevelPressure);
			
			/*
			 * 3 pm weather
			 */
			p.print(","+pmTemp);
			p.print(","+pmHumidity);
			p.print(","+pmCloud);
			p.print(","+pmWindDirection);
			p.print(","+pmWindSpeed);
			p.print(","+pmSeaLevelPressure);
			p.print("\n");
			p.close();
		}
		catch(Exception e)
		{
			System.err.println("error writing file");
		}
	}

}
