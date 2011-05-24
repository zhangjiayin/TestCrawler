package com.tmp.crawler;

import java.util.HashMap;

public abstract class BaseFetcher implements Fetcher {
	
	private String url;
	private int timeout = 1000;
	private boolean followRedirect = true;
	private HashMap<String, String> headers = new HashMap<String, String>();
	
	public void setUrl(String url) {
		this.url = url;
	}

	public String getUrl() {
		return url;
	}
	
	public int getTimeout() {
		return timeout;
	}

	public void setTimeout(int timeout) {
		this.timeout = timeout;
	}

	public void setUserAgent(String ua) {
		this.headers.put("User-Agent", ua);
	}

	public void setFollowRedirects(boolean redirect) {
		this.followRedirect = redirect;
	}

	public boolean isFollowRedirect() {
		return followRedirect;
	}
	public void setFollowRedirect(boolean followRedirect) {
		this.followRedirect = followRedirect;
	}

	public void setHeader(String name, String value) {
		this.headers.put(name, value);
	}

	public HashMap<String, String> getHeaders() {
		return headers;
	}

	public void setHeaders(HashMap<String, String> headers) {
		this.headers = headers;
	}

	public abstract String getContent();
	
}
