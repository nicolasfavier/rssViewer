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

				}

				public void endElement(String uri, String localName,
						String qName) throws SAXException {

					// TODO handle parsing

				}

				public void characters(char ch[], int start, int length)
						throws SAXException {

					// TODO handle parsing

				}

			};

			saxParser.parse(rssUri, handler);

		} catch (Exception e) {
			e.printStackTrace();
		}

	}

}
