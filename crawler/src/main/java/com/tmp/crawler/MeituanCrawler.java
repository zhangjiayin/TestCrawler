package com.tmp.crawler;

import java.io.UnsupportedEncodingException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class MeituanCrawler extends SiteCrawler {
	public MeituanCrawler(String url, String urlPattern) {
		super(url, urlPattern);
	}
	
	public static void main(String[] argv){
		SiteCrawler crawler = new MeituanCrawler("http://www.meituan.com", "meituan.com");
		crawler.process();
	}
	
	@Override
	public void processContents(String url, String content, int depth) {
		if(Pattern.matches("http://\\w+.meituan.com/deal/\\d+.html", url)){
			/**
			Pattern titlePattern = Pattern.compile("<h1>(.*?)</h1>",Pattern.MULTILINE );
			Matcher titleMatcher = titlePattern.matcher(content);
			titleMatcher.find();
			String title = titleMatcher.group(1).replaceAll("<a.*?a>", "");
			
			Pattern pricePattern = Pattern.compile("<p class=\"deal-price\"><strong>¥(.*?)</strong>",Pattern.MULTILINE );
			Matcher priceMatcher = pricePattern.matcher(content);
			priceMatcher.find();
			String price = priceMatcher.group(1).replaceAll("<a.*?a>", "");
			 
			System.out.println(price);
			*/
			String patternString = "<h1>(.*?)</h1>.*?<p class=\"deal-price\"><strong>¥(.*?)</strong>.*?<td><del>¥(\\d+)</del></td>.*?<td>(.*?)</td>.*?<td>¥(.*?)</td>.*? diff=\"(\\d+)\".*?<p class=\"deal-buy-tip-top\"><strong>(\\d+)</strong>.*?class=\"deal-buy-tip-btm\">.*?<strong>(\\d+)</strong>.*?<div class=\"deal-buy-cover-img\"><img src=\"(.*?)\"/></div>.*?<ul class=\"deal-detail-t cf\">.*?<li class=\"col first\">.*?<h2>提示</h2>.*?<ul>(.*?)</ul>.*?<h2>亮点</h2>.*?<ul class=\"highlight\">(.*?)</ul>";
			Pattern pattern = Pattern.compile(patternString,Pattern.MULTILINE | Pattern.DOTALL );
			Matcher matcher = pattern.matcher(content);
			matcher.find();
			
			String title = matcher.group(1).replaceAll("<a.*?a>", "");
			String price = matcher.group(2);
			String orgPrice = matcher.group(3);
			String discount = matcher.group(4);
			String savePrice = matcher.group(5);
			String leftTime = matcher.group(6);
			String buyCount = matcher.group(7);
			String minBuyCount = matcher.group(8);
			String img = matcher.group(9);
			String promat = matcher.group(10);
			String highlight = matcher.group(11);
			
			
			String addressString = "<div id=\"side-business\"><h2>.*?</h2>(.*?)</div>.*?<div class=\"go-top\">";
			Pattern addressPattern = Pattern.compile(addressString,Pattern.MULTILINE | Pattern.DOTALL );
			Matcher addressMatcher = addressPattern.matcher(content);
			addressMatcher.find();
			String addressText = addressMatcher.group();

			System.out.println("url:" + url);
			System.out.println("title:" + title);
			System.out.println("price:" + price);
			System.out.println("orgPrice:" + orgPrice);
			System.out.println("discount:" + discount);
			System.out.println("savePrice:" + savePrice);
			System.out.println("leftTime:" + leftTime);
			System.out.println("buyCount:" + buyCount);
			System.out.println("minBuyCount:" + minBuyCount);
			System.out.println("img:" + img);
			System.out.println("promat:" + promat);
			System.out.println("highlight:" + highlight);
			System.out.println("address:" + addressText);			
		}
	}
	
	public void anaylizeAddr(String Address){
		
	}
}
