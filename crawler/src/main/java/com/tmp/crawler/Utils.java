package com.tmp.crawler;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Utils {

	public static List<Map<String, String>> getLinks(String url, String Content) {

		String regexURL = "<a.*?href=('|\")(.*?)('|\").*?>";
		Pattern p = Pattern.compile(regexURL);
		Matcher m = p.matcher(Content);
		List<Map<String, String>> list = new ArrayList<Map<String, String>>();

		while (m.find()) {
			Map<String, String> map = new HashMap<String, String>();
			map.put("REFERER", url);
			map.put("url", m.group(2));
			list.add(map);
		}
		return list;
	}
	
	public static String getFixedUrl(String url, String Referer) {
		String returnUrl = null;
		if(Pattern.matches("^javascript.*?", url)){
		} else if (url.startsWith("http://")) {
			returnUrl = url;
		} else if (url.startsWith("/")) {
			int pos = Referer.indexOf("/", 7);

			if (pos != -1) {
				String baseUrl = Referer.substring(0, pos);
				returnUrl = baseUrl + url;
			} else {
				String baseUrl = Referer;
				returnUrl = baseUrl + url;
			}
		} else {

			int pos = Referer.lastIndexOf("/");
			if (pos != -1) {
				String baseUrl = Referer.substring(0, pos);
				returnUrl = baseUrl + "/" + url;
			}
		}
		return returnUrl;
	}
	
	public static void getLocation(String address) {
		
	}
	
}
