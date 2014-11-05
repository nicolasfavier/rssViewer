package utils;

import java.util.ArrayList;
import java.util.List;

import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;

import model.Article;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

public class RssParser {

	private List<Article> newsSynthese = new ArrayList<Article>();
	private boolean bitem, btitle, bdescription, blink, bdate, blanguage, bcreator;
	private Article Atemp;

	public List<Article> getNewsSynthese() {
		return newsSynthese;
	}

	public void parseRss(String rssUri) {

		// uri example
		// "http://www.lemondeinformatique.fr/flux-rss/thematique/toutes-les-actualites/rss.xml"
		
		
		
		try {

			SAXParserFactory factory = SAXParserFactory.newInstance();
			SAXParser saxParser = factory.newSAXParser();

			DefaultHandler handler = new DefaultHandler() {

				public void startElement(String uri, String localName,
						String qName, Attributes attributes)
						throws SAXException {

					// TODO handle parsing
					if(qName.equals("item")){
						Atemp= new Article();
						bitem = true;
					}
					
					if(qName.equals("title")){
						btitle = true;
					}
					
					if(qName.equals("description")){
						bdescription = true;
					}
					
					if(qName.equals("link")){
						blink = true;
					}
					
					if(qName.equals("dc:date")){
						bdate = true;
					}
					
					if(qName.equals("dc:language")){
						blanguage = true;
					}
					
					if(qName.equals("dc:creator")){
						bcreator = true;
					}
					
					

				}

				public void endElement(String uri, String localName,
						String qName) throws SAXException {

					// TODO handle parsing
					if(qName.equals("item")){
						bitem = false;
						newsSynthese.add(Atemp);
					}
					
					
					if(qName.equals("title")){
						btitle = false;
					}
					
					if(qName.equals("description")){
						bdescription = false;
					}
					
					if(qName.equals("link")){
						blink = false;
					}
					
					if(qName.equals("dc:date")){
						bdate = false;
					}
					
					if(qName.equals("dc:language")){
						blanguage = false;
					}
					
					if(qName.equals("dc:creator")){
						bcreator = false;
					}
				}

				public void characters(char ch[], int start, int length)
						throws SAXException {

					// TODO handle parsing
					
					if(bitem && btitle){
						String new_title = new String(ch,start,length);
						Atemp.setTitle(new_title);
					}
					
					if(bitem && bdescription){
						String new_description = new String(ch,start,length);
						Atemp.setDescription(new_description);
					}
					
					if(bitem && blink){
						String new_link = new String(ch,start,length);
						Atemp.setLink(new_link);
					}
					
					if(bitem && bdate){
						String new_date = new String(ch,start,length);
						Atemp.setDate(new_date);
					}
					
					if(bitem && blanguage){
						String new_language = new String(ch,start,length);
						Atemp.setLanguage(new_language);
					}
					
					if(bitem && bcreator){
						String new_creator = new String(ch,start,length);
						Atemp.setCreator(new_creator);
					}
					
	
					

				}

			};

			saxParser.parse(rssUri, handler);

		} catch (Exception e) {
			e.printStackTrace();
		}

	}

}
