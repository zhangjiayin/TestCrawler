package com.tmp.crawler;

public interface Fetcher {
	public void setUrl(String url);
	public void setTimeout(int timeout);
	public void setUserAgent(String ua);
	public void setFollowRedirects(boolean redirect);
	public void setHeader(String name, String value);
	public String getContent();
}
