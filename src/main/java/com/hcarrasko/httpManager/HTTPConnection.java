package com.hcarrasko.httpManager;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.zip.GZIPInputStream;
import java.util.zip.Inflater;
import java.util.zip.InflaterInputStream;

import org.apache.log4j.Logger;

public class HTTPConnection {
	
	final static Logger logger = Logger.getLogger(HTTPConnection.class);
	
	public String doHTTPRequest (String url) {
		
		StringBuilder response = new StringBuilder();
		
		try {
		    URL obj = new URL(url);
			HttpURLConnection con = (HttpURLConnection) obj.openConnection();
			
			InputStream inputStream = con.getInputStream();
	
			// Check if inputStream is GZipped
			if("gzip".equalsIgnoreCase(con.getContentEncoding())){
			    inputStream = new GZIPInputStream(inputStream);
			}else if("deflate".equalsIgnoreCase(con.getContentEncoding())){
			    inputStream = new InflaterInputStream(inputStream, new Inflater(true));
			} 
			BufferedReader sr = new BufferedReader(new InputStreamReader(inputStream));
			String inputLine;
		
			while ((inputLine = sr.readLine()) != null) {
				response.append(inputLine);
			}			
			sr.close();

		} catch (MalformedURLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return response.toString();
	}

}