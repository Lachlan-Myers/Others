package controller;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import model.Station;

import org.w3c.dom.Document;
import org.w3c.dom.Node;

public class WeatherPageModifier {
	
	public static final Pattern _dynamicMarkerPattern = Pattern.compile("\\$\\{weatherapp:.+\\}");
	public static final String _identMarker = "weatherapp_dynamic_"; 
	
	public WeatherPageModifier() {
	}
	
	public static void getProperties(WeatherPropertyCallback callback, Document d, Node n)
	{
		// Recursively search elements for an 'id' attribute that begins with 'weatherapp_dynamic_'.
		// For every one of these elements, look for a dynamic content marker (e.g: ${weatherapp:stationname}), and
		// replace it with the correct value.
		if (n.getAttributes() != null && n.getAttributes().getLength() > 0)
		{
			Node attr = n.getAttributes().getNamedItem("id");
			if (attr != null && attr.getTextContent() != null)
			{
				if (attr.getTextContent().startsWith(_identMarker))
				{					
					if (n.getTextContent() != null)
					{
						Matcher m = _dynamicMarkerPattern.matcher(n.getTextContent());
						while (m.find())
						{
							// Get the property name.
							String propName;
							String[] tokens = m.group().split(":");
							if (tokens.length == 2) {
								// Replace this property with the dynamic value (and remove the ending '}' char).
								propName = tokens[1].replace("}", "");
							}
							else {
								// No valid property found.
								continue;
							}
							
							// Call the callback function to handle changing the node's property.
							callback.weatherPropertyCallback(d, n, propName);
						}
					}
					
				}
			}
		}
		
		// Search child nodes, if any.
		if (n.getChildNodes() != null)
		{
			for (int i = 0; i < n.getChildNodes().getLength(); i++)
			{
				getProperties(callback, d, n.getChildNodes().item(i));
			}
		}
	}
	
	public static Node findNode(Node parent, String id) {
		
		// Search this 'parent' node.
		if (parent.getAttributes() != null && parent.getAttributes().getLength() > 0)
		{
			Node attr = parent.getAttributes().getNamedItem("id");
			if (attr != null && attr.getTextContent() != null)
			{
				if (attr.getTextContent().equals(id))
				{
					// Return this node.
					return parent;
				}
			}
		}
		
		// Search child nodes, if any.
		if (parent.getChildNodes() != null)
		{
			for (int i = 0; i < parent.getChildNodes().getLength(); i++)
			{
				Node n = findNode(parent.getChildNodes().item(i), id);
				if (n != null)
					return n;
			}
		}
		
		// No matches found.
		return null;
	}

	
}
