package com.tmp.crawler;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class SiteCrawler {

	public int maxDepth = 5;
	public String url;
	public String urlPattern;
	public Set<String> urlSet = new HashSet<String>();

	public Fetcher fetcher = new RawUrlFetcher();

	public static void main(String[] argv) {
		SiteCrawler crawler = new SiteCrawler("http://www.meituan.com",
				"meituan.com");
		crawler.process();
	}

	public SiteCrawler(String url, String urlPattern) {
		this.url = url;
		this.urlPattern = urlPattern;
	}

	public boolean isAllowedCrawl(String url) {

		if (this.urlSet.contains(url)
				|| (this.url.indexOf(this.urlPattern) == -1)
				|| url == null 
				|| url.equals("")) {
			return false;
		}
		

		this.urlSet.add(url);

		return true;
	}

	public void process() {
		fetcher.setUrl(url);
		String contents = fetcher.getContent();
		this.processContents(url, contents, 0);
		List<Map<String, String>> links = Utils.getLinks(url, contents);

		this.process(links, 1);
	}

	public void processContents(String url, String content, int depth) {
		System.out.println(url + ", depth:" + depth);
	}

	public void process(List<Map<String, String>> urls, int depth) {

		if (depth == this.maxDepth) {
			return;
		}

		List<Map<String, String>> links = new ArrayList<Map<String, String>>();

		for (Map<String, String> map : urls) {

			String url = map.get("url");
			String mapReferer = map.get("REFERER");

			url = Utils.getFixedUrl(url, mapReferer);
			
			if (!this.isAllowedCrawl(url)) {
				continue;
			}

			fetcher.setHeader("REFERER", mapReferer);
			fetcher.setUrl(url);
			System.out.println(url);
			String contents = fetcher.getContent();

			if (contents == null) {
				continue;
			}
			this.processContents(url, contents, depth);
			List<Map<String, String>> lnks = Utils.getLinks(url, contents);

			links.addAll(lnks);
		}
		this.process(links, depth + 1);
	}
}
