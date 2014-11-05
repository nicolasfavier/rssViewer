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


	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String[] montab = {"http://www.lemondeinformatique.fr/flux-rss/thematique/toutes-les-actualites/rss.xml"};
		PrintWriter out = response.getWriter();
				
		//on instancie l'objet FormatManager en json.
		FormatManager myFormat = new FormatManager(); 
		//String result = FormatManager.getJson(montab);	//renvoi un string
		
		String result = FormatManager.getHTML(montab);
		
		//on affiche le json
		//http://jsonformatter.curiousconcept.com pour afficher ce que le client verra de notre json
		out.println(result);
		
		// return data from rss with different formats
	}


}
