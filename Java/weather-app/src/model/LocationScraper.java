package model;

import java.net.*;
import java.io.*;
import java.util.*;

public class LocationScraper{

	
	private URL link;
	private ArrayList<Station> stationsList = new ArrayList<Station>();
	private String state;
	private int stateId;
	private String code;
	Station station;
	
	public LocationScraper(int stateId){
					
		try{	
			this.link = new URL("http://www.bom.gov.au/inside/itb/dm/idcodes/tables/current/climate_products.shtml");
		}//try
		catch (MalformedURLException e) {
			e.printStackTrace();
		}
		
		switch (stateId){	
			case Station.VIC:
				this.code = new String(">IDCJDW3");
				this.state = "VIC";
				this.stateId = stateId; 
				break;
			case Station.NSW:
				this.code = new String(">IDCJDW2");
				this.state = "NSW";
				this.stateId = stateId; 
				break;
			case Station.QLD:
				this.code = new String(">IDCJDW4");
				this.state = "QLD";
				this.stateId = stateId; 
				break;
			case Station.SA:
				this.code = new String(">IDCJDW5");
				this.state = "SA";
				this.stateId = stateId; 
				break;
			case Station.WA:
				this.code = new String(">IDCJDW6");
				this.state = "WA";
				this.stateId = stateId; 
				break;
			case Station.NT:
				this.code = new String(">IDCJDW8");
				this.state = "NT";
				this.stateId = stateId; 
				break;
			case Station.TAS:
				this.code = new String(">IDCJDW7");
				this.state = "TAS";
				this.stateId = stateId; 
				break;
			case Station.ACT:
				this.code = new String(">IDCJDW2");
				this.state = "ACT";
				this.stateId = stateId; 
				break;
			default:
				this.code = new String(">IDCJDW2");
				this.stateId = stateId; 
				break;
		}//switch		
	}//constructor
	
	/*
	 * 
	 * @return ArrayList<Station>
	 * 
	 * returns an ArrayList of Station objects.
	 */
	public ArrayList<Station> fetchStations(){
		String inputLine, temp, stationName, productCode;
//		String csvLink = new String();
//		String dailyLink = new String();
		
		try{
			//connect to the web page
			Connector con = new Connector(this.link);
			BufferedReader in = con.getPageReader();
			
			//read each line of HTML
			while ((inputLine = in.readLine()) != null){
				//check to see if the code from the switch statement is on this line of HTML
				if(inputLine.contains(this.code)){
					
					temp = inputLine.substring(inputLine.indexOf(this.code)).substring(0);
					productCode = temp.substring(1, temp.indexOf("<"));
					
					//read the next two lines
					inputLine = in.readLine();
					inputLine = in.readLine();
					//System.out.println(temp);
					if(!inputLine.contains("newplace")){
						if(inputLine.contains(",")){
							temp = inputLine.substring(inputLine.indexOf("for ")).substring(4);
							stationName = temp.substring(0, temp.indexOf(","));
						}
						else{
							temp = inputLine.substring(inputLine.indexOf("for ")).substring(4);
							stationName = temp.substring(0, temp.indexOf("<"));
						}
						station = new Station(productCode, stationName, this.state, this.stateId);
						this.stationsList.add(station);
					}
				}
			}//while
		}
		catch(Exception e){
			e.printStackTrace();
		}
		return stationsList;
	}
		
		
		
		
		
//		try{
//			//connect to the URL
//			Connector con = new Connector(this.link);
//			BufferedReader in = con.getPageReader();
//			
//			String inputLine;
//			
//			//Read each line of the HTML 
//			while ((inputLine = in.readLine()) != null) {
//				if (inputLine.contains("<td class=\"rowlevel1\" colspan=\"16\"><a name=")){
//					//These lines get the Region Name
//					String temp = inputLine.substring(inputLine.indexOf("></a>")).substring(5);
//					String regionName = temp.substring(0, temp.indexOf("<"));
//					this.regionName = regionName;
//				}
//				if (inputLine.contains("owleftcolumn\"><a href=\"/products/")) {
//					//String nextLink = inputLine.substring(inputLine.lastIndexOf("owleftcolumn\"><a href=\"/products/")).substring(23, 62);
//					//System.out.println("link: " + nextLink);
//					
//					//These lines parse the Station name
//					String temp = inputLine.substring(inputLine.indexOf("owleftcolumn\"><a href=\"/products/")).substring(64);
//					String stationName = temp.substring(0, temp.indexOf("<"));
//					this.stationName = stationName;
//					
//					//create an act station, (ie. no region)
//					if(stateId == Station.ACT){
//						station = new Station("ID", this.stationName, this.state, this.stateId);
//						
//					}
//					
//					//create the station object for other states
//					else{
//						station = new Station("ID", this.stationName, this.regionName, this.state, this.stateId);
//					}
//					this.stationsList.add(station);
//				}
//			}
//		}
//		catch(Exception e){
//			e.printStackTrace();
//		}
//		return stationsList;
	//}
	
	public URL getLink(){
		return this.link;
	}
	public ArrayList<Station> getStations(){
		return this.stationsList;
	}
}
