package model;

import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

import utils.RssParser;

public class Synthese {

	private List<Article> m_synthese = new ArrayList<Article>();
	
	public Synthese(){
		
	}
	
	//TODO add setters and getters

	public void printListArticle(){
		
		
		
		for(Article article : m_synthese){
			
			String title = article.getTitle();
			String date = article.getDate();
			String description = article.getDescription();
			String language = article.getLanguage();
			String link = article.getLink();
			String category = article.getcategory();
			
			System.out.println("Title:"+title);
			System.out.println("Date:"+date);
			System.out.println("description:"+description);
			System.out.println("Language:"+language);
			System.out.println("Link:"+link);
			System.out.println("category:"+category);
			
		}
		
	}
	
	
	public Synthese(String[] uris, String typeOfSort){
		
		RssParser temp_parser ;
		List<Article> temp_list ;
		
		for(String uri : uris){
			temp_parser = new RssParser();
			temp_parser.parseRss(uri);
			temp_list = temp_parser.getNewsSynthese();
			m_synthese.addAll(temp_list);
		
		}
		
		
	}

	public List<Article> getSynthese() {
		return m_synthese;
	}

}
