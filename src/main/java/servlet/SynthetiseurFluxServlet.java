package servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.Synthese;



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
	
		Synthese ma_synthese = new Synthese(montab,"");
		ma_synthese.printListArticle();


		// return data from rss with different formats
	}


}
