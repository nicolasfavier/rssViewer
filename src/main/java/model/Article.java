package model;

public class Article {

	//TODO create model wich map the rss
	//TODO getters and setters
	
	private String title;
	private String description;
	private String link;
	private String date;
	private String language;
	private String creator;
	
	public Article() {}
	
	public Article(String title, String description, String link, String date,
			String language, String creator) {
		super();
		this.title = title;
		this.description = description;
		this.link = link;
		this.date = date;
		this.language = language;
		this.creator = creator;
	}
	
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public String getLink() {
		return link;
	}
	public void setLink(String link) {
		this.link = link;
	}
	public String getDate() {
		return date;
	}
	public void setDate(String date) {
		this.date = date;
	}
	public String getLanguage() {
		return language;
	}
	public void setLanguage(String language) {
		this.language = language;
	}
	public String getCreator() {
		return creator;
	}
	public void setCreator(String creator) {
		this.creator = creator;
	}
	
	
	
}
