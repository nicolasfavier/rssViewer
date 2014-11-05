package model;

public class BodyContent {
	private String format;
	private String[] rssTab;
	
	public BodyContent(){}
	
	public BodyContent(String format, String[] rssTab) {
		super();
		this.format = format;
		this.rssTab = rssTab;
	}
	public String getFormat() {
		return format;
	}
	public void setFormat(String format) {
		this.format = format;
	}
	public String[] getRssTab() {
		return rssTab;
	}
	public void setRssTab(String[] rssTab) {
		this.rssTab = rssTab;
	}
	
	
}
