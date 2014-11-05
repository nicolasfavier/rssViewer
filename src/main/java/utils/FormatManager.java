package utils;

import model.Synthese;

import com.google.gson.Gson;


import java.io.StringWriter;
import java.util.List;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import model.Article;
import model.Synthese;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;


/***
 * 
 * @author nicolas
 * 
 *	had general functions like parse, unparse...
 */
public class FormatManager {
	
		public FormatManager() {
		}

		public static String getXMLFromRss(String[] rssUri){
			
			String string_result = " ";
			
			try{

				String[] montab = {"http://www.lemondeinformatique.fr/flux-rss/thematique/toutes-les-actualites/rss.xml"};
				Synthese ma_synthese = new Synthese(montab,"");
				List<Article> final_list = ma_synthese.getSynthese();
				
				//create factory
				DocumentBuilderFactory documentFactory = DocumentBuilderFactory.newInstance();
				DocumentBuilder documentBuilder = documentFactory.newDocumentBuilder();
				Document document = documentBuilder.newDocument();
				
				//create element
				Element root = document.createElement("synthese");
				document.appendChild(root);
				
				for(Article current_article : final_list){
					root.appendChild(getArticle(document,current_article));
				}
				
				//transformation in String
				StringWriter writer = new StringWriter();
				StreamResult result = new StreamResult(writer);
				TransformerFactory transformerFactory = TransformerFactory.newInstance();
				Transformer transformer = transformerFactory.newTransformer();
				
				transformer.setOutputProperty(OutputKeys.INDENT, "yes");
				DOMSource source = new DOMSource(document);
				
				transformer.transform(source, result);
				writer.flush();
				
				string_result = writer.toString(); 
			}
			catch(ParserConfigurationException e){
				e.printStackTrace();
			}
			catch(TransformerException e){
				e.printStackTrace();
			}			
			return string_result;
		}
		
		//create article node
		public static Node getArticle(Document doc, Article current_article){
			Element article = doc.createElement("article");
			
			article.appendChild(getNode(doc, article, "title", current_article.getTitle()));
			article.appendChild(getNode(doc, article, "date", current_article.getDate()));
			article.appendChild(getNode(doc, article, "language", current_article.getLanguage()));
			article.appendChild(getNode(doc, article, "creator", current_article.getCreator()));
			article.appendChild(getNode(doc, article, "Description", current_article.getDescription()));
			article.appendChild(getNode(doc, article, "link", current_article.getLink()));
			
			return article;
		}
		
		//creation d'une feuille d'article
		public static Node getNode(Document doc, Element element, String name, String value){
			Element node = doc.createElement(name);
			node.appendChild(doc.createTextNode(value));
			return node;
		}

		/*	Description public static String getJson(String[] rssUri)
			Cette fonction permet de transformer un objet java de type list<Article> en format Json
			Pour passer de java a json, on utilise la librairie gson 
		*/
		public static String getJson(String[] rssUri) {
			String resultJson = "";
			//le format JSON permet de transmettre ma ressource (mon article) au client
			Synthese ma_synthese = new Synthese(rssUri,"");
			ma_synthese.printListArticle();
	
			//Gson est une librairie qui permet de passer de java a gson et inversement
			//creation d'un objet gson
			Gson gson = new Gson();		
			//on converti de gson a json
			resultJson = gson.toJson(ma_synthese.getSynthese());	
			
			return (resultJson);
		}

		public static String getHTML(String[] rssUri) {
			String resultHtml = "";
			
			//TODO get html from rssUri
			// to do so get the XML transform it with XSL file
		
			
			return resultHtml;
		}
}
