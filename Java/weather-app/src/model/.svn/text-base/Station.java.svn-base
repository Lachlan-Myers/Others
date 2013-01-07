package model;


import java.util.ArrayList;
import java.util.GregorianCalendar;
import java.util.ListIterator;

/*
 * This class will store 1 to many weather objects that scrapes from the GOM website
 * @author Liam & Simon & Kiet
 */
public class Station {

	public static final int VIC = 1;
	public static final int NSW = 2;
	public static final int QLD = 3;
	public static final int SA = 4;
	public static final int WA = 5;
	public static final int NT = 6;
	public static final int TAS = 7;
	public static final int ACT = 8;
	
	private String ID;
	private String name;
	private String region;
	private String state;
	private int stateId;
	
    ArrayList<Weather> list=new ArrayList<Weather>();
    
    /*
     * Set up the constructor for the Station class
     * @param ID ID of the station
     * @param name Name of the station
     * @param state State where the station is in
     * @param stateId ID of the state where the station is in
     */
    public Station(String ID, String name, String state, int stateId)
    {
    	this.ID=ID;
    	this.name=name;
    	this.state=state;
    	this.stateId = stateId;
    }
    
//    public Station(String ID, String name, String region, String state, int stateId)
//    {
//    	this.ID=ID;
//    	this.name=name;
//    	this.region=region;
//    	this.state=state;
//    	this.stateId = stateId;
//    }
//    
    
	public String getID() {
		return ID;
	}

	public String getName() {
		return name;
	}

	public String getState() {
		return state;
	}
	
	public int getStateId() {
		return stateId;
	}
	
	public String toString() {
		return name;
	}
	
	/*
	public ArrayList<Weather> fetchDay(GregorianCalendar date) {
		//fetch day here
		return list;
	}
	
	public ArrayList<Weather> fetchWeek() {
		//fetch last 7 days
		return list;
	}*/
	public void addToList(Weather weather){
		list.add(weather);
	}
	public ArrayList<Weather> getList() {
		return list;
	}
	
	public ArrayList<Weather> fetchMonth(ArrayList<Weather> temp) {
		//fetch last 30 days
		return list;
	}

//	public void setRegion(String region) {
//		this.region = region;
//	}
//
//	public String getRegion() {
//		return region;
//	}
	
	
	/*
	 * This function will invoke saveWeather function from weather object and save necessary data
	 * into weatherData.csv file 
	 */
	public void saveStations() 
	{
		Weather temp;
		ListIterator<Weather> it=list.listIterator();
		while(it.hasNext())
		{
			temp=it.next();
			temp.saveWeather(name, state, ID, stateId);
		}
	}
	
}

