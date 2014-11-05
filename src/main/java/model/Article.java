package model;

public class Article {

	//TODO create model wich map the rss
	//TODO getters and setters
	
	private String m_title;
	private String m_description;
	private String m_link;
	private String m_date;
	private String m_language;
	private String m_creator;
	
	public Article(
			) {
		m_title = "";
		m_description = "";
		m_link = "";
		m_date = "";
		m_language = "";
		m_creator = "";
	}
	
	public Article(String title, String description, String link, String date,
			String language, String creator) {
		super();
		m_title = title;
		m_description = description;
		m_link = link;
		m_date = date;
		m_language = language;
		m_creator = creator;
	}

	public String getTitle() {
		return m_title;
	}

	public void setTitle(String m_title) {
		this.m_title = m_title;
	}

	public String getDescription() {
		return m_description;
	}

	public void setDescription(String m_description) {
		this.m_description = m_description;
	}

	public String getLink() {
		return m_link;
	}

	public void setLink(String m_link) {
		this.m_link = m_link;
	}

	public String getDate() {
		return m_date;
	}

	public void setDate(String m_date) {
		this.m_date = m_date;
	}

	public String getLanguage() {
		return m_language;
	}

	public void setLanguage(String m_language) {
		this.m_language = m_language;
	}

	public String getCreator() {
		return m_creator;
	}

	public void setCreator(String m_creator) {
		this.m_creator = m_creator;
	}
	
	
	
}
