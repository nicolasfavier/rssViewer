package utils;

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
