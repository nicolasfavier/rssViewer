package servlet;

import java.io.IOException;
import java.io.PrintWriter;

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

    	String[] rssTab = {"http://www.lemondeinformatique.fr/flux-rss/thematique/toutes-les-actualites/rss.xml"};
    	rssTab[1] = "";
    	
		String format = "xml";
		String result = "";

		PrintWriter out = response.getWriter();

		if (request.getParameter("format") != null) {
			format = request.getParameter("format");
		}

		response.setCharacterEncoding("UTF-8");

		if (format == "xml") {
			response.setContentType("text/xml");
			result = FormatManager.getXMLFromRss(rssTab);
		}
		else if (format == "html"){
			response.setContentType("text/html");
			result = FormatManager.getHTML(rssTab);	
		}

		else if(format == "json"){
			response.setContentType("text/json");
			result = FormatManager.getJson(rssTab);			
		}		

		out.println(result);

	}
}
