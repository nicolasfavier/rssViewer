package utils;

import java.io.StringReader;
import java.io.StringWriter;
import java.io.Writer;

import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;

import model.Synthese;

import com.google.gson.Gson;


/***
 * 
 * @author nicolas
 * 
 *	had general functions like parse, unparse...
 */
public class FormatManager {

		public static String getXMLFromRss(String[] rssUri) {
			String resultXml = "";
			
			//TODO get xml from rssUri, 
			//create the synthese
			//transform it in xml
			//use Dom or sax
			
			return resultXml;
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
			//on converti de gson a json
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
					Transformer transformer = tFactory.newTransformer(new javax.xml.transform.stream.StreamSource("C:\\Users\\Jean Mabru\\rssViewer\\src\\main\\resource\\myfichierxsl.xsl"));
					//Transformer transformer = tFactory.newTransformer(new javax.xml.transform.stream.StreamSource("http://localhost/myfichierxsl.xsl"));
	
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
