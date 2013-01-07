
/* 
Software provided by...

Sunaryo Halim 
Lau Chak Han 
Fario Subastian 
Louis Parengkuan 
Tommy Yakin 
*/

//package view.map.utility;
package model;


//import java.io.BufferedReader;
import java.io.IOException;
//import java.io.InputStreamReader;
import java.net.*;


import java.util.*;

/**
 * 
 * This class will setup up Proxy for your network
 * @author Kiet Tran
 * 
 */
public class RMITProxy
{
	private static String _proxyAddress = null;
	/*
	 * There are two proxies at RMIT one for wireless and one for LAN
	 * Wireless one is: "aproxy.rmit.edu.au" without the quotes
	 * RMIT LAN is: "proxy.rmit.edu.au" without the quotes
	 * @param proxy input your network's proxy and port is always 8080
	 * @return void
	 */
   public static void byPassProxy( String proxy)
   {
	  _proxyAddress = proxy;
      // Get the system properties and add a few more settings
	  // and set up the proxies 
	  if (proxy == null)
	  {
		  // Remove proxy.
		  try
		  {
			  System.getProperties().remove("http.proxyHost");
			  System.getProperties().remove("http.proxyPort");
		  }
		  catch (Exception exc)
		  {
		  }
		  
		  return;
	  }
	  
      Properties proxyConn = System.getProperties();
      proxyConn.put("http.proxyHost", proxy);
      proxyConn.put("http.proxyPort", "8080");
      
   }
   
   public static void testProxy() throws MalformedURLException, IOException
   { 
      
      // Connect to the page required to scrape from
         URL url = new URL("http://www.google.com.au/");
      // encode the user name and password for connection
      // String encoded = new String
         //(Base64Coder.encode(new String(username+":"+password).getBytes()));
         URLConnection connection = url.openConnection ();
         //connection.setRequestProperty("Proxy-Authorization", "Basic "
           //    + encoded);
 // Get the input stream for later reading
         //in = new BufferedReader(new InputStreamReader(connection
           //    .getInputStream()));
            
         connection.connect();

      
   }
   
   public static String getProxyAddress()
   {
	   return _proxyAddress;
   }
}