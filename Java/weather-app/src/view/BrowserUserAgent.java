package view;

import java.awt.image.BufferedImage;
import java.io.BufferedInputStream;
import java.io.DataInputStream;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;

import javax.imageio.ImageIO;

import org.xhtmlrenderer.*;
import org.xhtmlrenderer.extend.UserAgentCallback;
import org.xhtmlrenderer.resource.CSSResource;
import org.xhtmlrenderer.resource.ImageResource;
import org.xhtmlrenderer.resource.XMLResource;
import org.xhtmlrenderer.swing.AWTFSImage;

public class BrowserUserAgent implements UserAgentCallback 
{
	private String _basePath;
	
	public BrowserUserAgent()
	{
		_basePath = "/resources/";
	}

	public String getBaseURL() {
		return _basePath;
	}
	
	public void setBaseURL(String arg0) {
		//_basePath = arg0;
	}
	

	public byte[] getBinaryResource(String arg0) {
		byte[] resData;
		InputStream resourceStream;
		
		try
		{
			resourceStream = getResource(arg0);
			
			resData = new byte[resourceStream.available()];
			resourceStream.read(resData);
			
			resourceStream.close();
		}
		catch (IOException exc)
		{
			exc.printStackTrace();
			return null;
		}
		
		return resData;
	}

	public CSSResource getCSSResource(String arg0) {
		return new CSSResource(getResource(arg0));
	}

	public ImageResource getImageResource(String arg0) {
		BufferedImage img;
		try {
			img = ImageIO.read(getResource(arg0));
			return new ImageResource(AWTFSImage.createImage(img));
			
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		return new ImageResource(null);
	}

	public XMLResource getXMLResource(String arg0) {
		return XMLResource.load(getResource(arg0));
	}

	public boolean isVisited(String arg0) {
		return false;
	}

	public String resolveURI(String arg0) {
		return arg0;
	}

	
	private InputStream getResource(String resourcePath)
	{
		// Remove base path from resource path if it's already in there.
		resourcePath = resourcePath.replaceAll("/*resources/*", "");
		InputStream is = this.getClass().getResourceAsStream(_basePath.concat(resourcePath));
		return is;
	}

}
