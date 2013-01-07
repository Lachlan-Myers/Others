package model;

import java.io.*;
import java.net.*;

/*
 * This is a basic connector class for connecting to a URL and sending back
 * a BufferedReader.
 * 
 * the main purpose of the class is to provide a way through the RMIT proxy
 * which isnt functional
 */


public class Connector {
	
	URL url;
	
	/*
	 * @param url Takes a URL to connect to.
	 */
	
	public Connector(URL url){
		this.url = url;
	}
	
	public BufferedReader getPageReader(){
		BufferedReader in = null;
		
		try{
			URLConnection con = this.url.openConnection();
			in = new BufferedReader(new InputStreamReader(con.getInputStream()));
		}
		catch(Exception e){
			e.printStackTrace();
		}
		
		return in;
	}
}
