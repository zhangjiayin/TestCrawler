package com.tmp.crawler;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Map.Entry;

public class RawUrlFetcher extends BaseFetcher {

	@Override
	public String getContent() {
		String ret = null;
		try {
			URL url = new URL(this.getUrl());
			HttpURLConnection connection = (HttpURLConnection) url
					.openConnection();
			connection.setDoOutput(true);
			connection.setRequestMethod("GET");
			this.setUserAgent("Mozilla/5.0 (Windows; U; Windows NT 5.1; en-US) AppleWebKit/534.13 (KHTML, like Gecko) Chrome/9.0.597.98 Safari/534.13");
			for (Entry<String, String> entry : this.getHeaders().entrySet()) {  
				connection.setRequestProperty(entry.getKey(), entry.getValue());
			}
			
			connection.setConnectTimeout(this.getTimeout());
			HttpURLConnection.setFollowRedirects(this.isFollowRedirect());
			
			if (connection.getResponseCode() == HttpURLConnection.HTTP_OK) {
				StringBuffer sb = new StringBuffer();
				BufferedReader reader = new BufferedReader(
						new InputStreamReader(connection.getInputStream()));
				while (reader.ready()) {
					sb.append(reader.readLine() + "\n");
				}
				ret = sb.toString();
			} else {
				//TODO
			}
		} catch (MalformedURLException e) {
			//TODO LOG
			e.printStackTrace();
		} catch (IOException e) {
			//TODO LOG
			e.printStackTrace();
		} catch(Exception e ){
			e.printStackTrace();
		}
		return ret;
	}
}
