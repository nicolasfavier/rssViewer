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
	private boolean bitem, btitle, bdescription, blink, bdate, blanguage, bcreator, bcategory;
	private Article Atemp;

	public List<Article> getNewsSynthese() {
		return newsSynthese;
	}

	public void parseRss(String rssUri) {

		try {

			SAXParserFactory factory = SAXParserFactory.newInstance();
			SAXParser saxParser = factory.newSAXParser();

			DefaultHandler handler = new DefaultHandler() {

				public void startElement(String uri, String localName,
						String qName, Attributes attributes)
						throws SAXException {

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
					if(qName.equals("category")){
						bcategory = true;
					}
					
					

				}

				public void endElement(String uri, String localName,
						String qName) throws SAXException {

					if(qName.equals("item")){
						bitem = false;
						newsSynthese.add(Atemp);
					}
				}

				public void characters(char ch[], int start, int length)
						throws SAXException {

					
					if(bitem && btitle){
						String new_title = new String(ch,start,length);
						Atemp.setTitle(new_title);
						btitle = false;
					}
					
					if(bitem && bdescription){
						String new_description = new String(ch,start,length);
						Atemp.setDescription(new_description);
						bdescription = false;
					}
					
					if(bitem && blink){
						String new_link = new String(ch,start,length);
						Atemp.setLink(new_link);
						blink = false;
					}
					
					if(bitem && bdate){
						String new_date = new String(ch,start,length);
						Atemp.setDate(new_date);
						bdate = false;
					}
					
					if(bitem && blanguage){
						String new_language = new String(ch,start,length);
						Atemp.setLanguage(new_language);
						blanguage = false;
					}
					
					if(bitem && bcreator){
						String new_creator = new String(ch,start,length);
						Atemp.setCreator(new_creator);
						bcreator = false;
					}
					if(bitem && bcategory){
						String new_category = new String(ch,start,length);
						Atemp.setcategory(new_category);
						bcategory = false;
					}
				}

			};

			saxParser.parse(rssUri, handler);

		} catch (Exception e) {
			e.printStackTrace();
		}

	}

}
