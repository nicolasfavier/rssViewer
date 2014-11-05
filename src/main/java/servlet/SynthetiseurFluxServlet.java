package servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import utils.FormatManager;



/**
 * Servlet implementation class SynthetiseurFluxServlet
 */
@WebServlet("/synthetiseurFlux")
public class SynthetiseurFluxServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

    /**
     * Default constructor. 
     */
    public SynthetiseurFluxServlet() {
    	super();
    }


    protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {


    	String[] rssTab = {"http://www.lemondeinformatique.fr/flux-rss/thematique/toutes-les-actualites/rss.xml",
    						"http://www.sport.fr/rss/Liverpool.xml"};
  
    	rssTab = supprimer_doublon(rssTab);
		String format = "html";
		String result = "";
		PrintWriter out = response.getWriter();

		if (request.getParameter("format") != null) {
			format = request.getParameter("format");
		}

		response.setCharacterEncoding("UTF-8");

		if (format.equals("xml")) {
			response.setContentType("text/xml");
			result = FormatManager.getXMLFromRss(rssTab);
		}
		else if (format.equals("html")){
			response.setContentType("text/html");
			result = FormatManager.getHTML(rssTab);	
		}

		else if(format.equals("json")){
			response.setContentType("text/json");
			result = FormatManager.getJson(rssTab);			
		}		

		out.println(result);

	}
    
    protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {

    	//String[] rssTab;
    	//rssTab= request.getParameterValues("rssTab");
    	
    	
    	String[] rssTab = {"http://www.lemondeinformatique.fr/flux-rss/thematique/toutes-les-actualites/rss.xml"};
    	
    	
		String format = "xml";
		String result = "";
		PrintWriter out = response.getWriter();

		if (request.getParameter("format") != null) {
			format = request.getParameter("format");
		}

		response.setCharacterEncoding("UTF-8");

		if (format.equals("xml")) {
			response.setContentType("text/xml");
			result = FormatManager.getXMLFromRss(rssTab);
		}
		else if (format.equals("html")){
			response.setContentType("text/html");
			result = FormatManager.getHTML(rssTab);	
		}

		else if(format.equals("json")){
			response.setContentType("text/json");
			result = FormatManager.getJson(rssTab);			
		}		

		out.println(result);

	}
    
    public static String[] supprimer_doublon(String[] args)
  	{
 
	    List<String> list = Arrays.asList(args);
	    Set<String> set = new HashSet(list);
	 
	    String[] result = new String[set.size()];
	    result = (String[]) set.toArray(result);
	 
	   return result;
     }
    
    public static boolean check_url(String urlString) throws IOException{
    	
    	URL url = new URL(urlString);
    	HttpURLConnection huc = (HttpURLConnection) url.openConnection();
    	int responseCode = huc.getResponseCode();
    	boolean b;
    	
    	if(responseCode != 404){
    	    b = true;
    	}else{
    	    b = false;
    	}
    	
    	return b;
    	
    }
    
    
}
