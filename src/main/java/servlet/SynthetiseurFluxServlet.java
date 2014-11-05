package servlet;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.BodyContent;
import utils.FormatManager;

import com.google.gson.Gson;



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

    	String[] rssTab = {};
    	String bodyRequest = getBody(request);
    	    	
		String format = "xml";
		
		Gson gson = new Gson();
		BodyContent BodyContentObject = gson.fromJson(bodyRequest, BodyContent.class);
		
		format = BodyContentObject.getFormat();
		rssTab = BodyContentObject.getRssTab();
		
		String result = "";
		PrintWriter out = response.getWriter();

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
    
    
    public static String getBody(HttpServletRequest request) throws IOException {

        String body = null;
        StringBuilder stringBuilder = new StringBuilder();
        BufferedReader bufferedReader = null;

        try {
            InputStream inputStream = request.getInputStream();
            if (inputStream != null) {
                bufferedReader = new BufferedReader(new InputStreamReader(inputStream));
                char[] charBuffer = new char[128];
                int bytesRead = -1;
                while ((bytesRead = bufferedReader.read(charBuffer)) > 0) {
                    stringBuilder.append(charBuffer, 0, bytesRead);
                }
            } else {
                stringBuilder.append("");
            }
        } catch (IOException ex) {
            throw ex;
        } finally {
            if (bufferedReader != null) {
                try {
                    bufferedReader.close();
                } catch (IOException ex) {
                    throw ex;
                }
            }
        }

        body = stringBuilder.toString();
        return body;
    }
    
    
}
