package utils;

import java.io.StringReader;
import java.io.StringWriter;
import java.io.Writer;

import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;

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
				Synthese ma_synthese = new Synthese(rssUri,"sort");

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

		/*	
		 * Description public static String getJson(String[] rssUri)  :
		 * Cette fonction permet de transformer un objet java de type list<Article> en format Json (on retourne un string qui sera interpreter)
		 * Pour passer de java a json, on utilise la librairie gson 			
		*/
		public static String getJson(String[] rssUri) {
			String resultJson = "";
			//le format JSON permet de transmettre ma ressource (mon article) au client
			Synthese ma_synthese = new Synthese(rssUri,"");
			ma_synthese.printListArticle();
	
			//Gson est une librairie qui permet de passer de java a gson et inversement
			//creation d'un objet gson
			Gson gson = new Gson();		
			//on converti de java a json en utilisant la librairie gson
			resultJson = gson.toJson(ma_synthese.getSynthese());	
			
			return (resultJson);
		}
		
		
		
		/* 
		 * Description public static String getHTML(String[] rssUri)
		 * Cette fonction permet de tranformer un string xml et xsl en un string html
		 * que l'on affiche ensuite
		 */
		public static String getHTML(String[] rssUri) {
			String resultHtml = "";
			
			try {
					Writer wr = new StringWriter();
					
					//on recupere dans un string le flux xml
					String xml = FormatManager.getXMLFromRss(rssUri);
					//String xml = "<article><title>TITRE</title><desc>KIKOU</desc><link>sdufgdshjfgdhjs</link></article>";

					//A TransformerFactory instance can be used to create Transformer and Templates objects.
					//creation d'une nouvelle instance TransformerFactory
					TransformerFactory tFactory = TransformerFactory.newInstance();
					
					//An instance of this abstract class (transformer) can transform a source tree into a result tree.
					//on recupere notre fichier xsl sous forme de type transformer
					//Transformer transformer = tFactory.newTransformer(new javax.xml.transform.stream.StreamSource("C:\\Users\\Jean Mabru\\rssViewer\\resource\\myfichierxsl.xsl"));
					
					//il faut mettre le fichier xsl a la racine du dossier dans wamp (www), le fichier sera alors disponible 
					Transformer transformer = tFactory.newTransformer(new javax.xml.transform.stream.StreamSource("D:\\workspace\\Eclipse\\rssViewer\\resource\\myfichierxsl.xsl"));
	
					//on prend notre transformer xsl
					//la methode "transform" recois en parametre le string xml et le string de sortie 
					//cette methode combine le tout pour obtenir notre "stream" de sortie html
					transformer.transform(new javax.xml.transform.stream.StreamSource(new StringReader(xml)), new javax.xml.transform.stream.StreamResult(wr));
					
					//on convertie le stream en string
					resultHtml = wr.toString();
					} 
			catch (Exception e) {
					e.printStackTrace();
				}			
			return resultHtml;
		}
}
