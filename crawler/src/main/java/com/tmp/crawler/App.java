package com.tmp.crawler;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.Reader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLEncoder;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Hello world!
 * 
 */
public class App {
	public static void main(String[] args) {
		RawUrlFetcher fetcher = new RawUrlFetcher();
		String url = "http://www.meituan.com/";
		fetcher.setUrl(url);
		String contents = fetcher.getContent();
		List<Map<String, String>> links = Utils.getLinks(url, contents);
		System.out.println(links);
	}
}
