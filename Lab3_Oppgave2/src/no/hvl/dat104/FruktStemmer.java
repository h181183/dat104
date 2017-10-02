package no.hvl.dat104;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class FruktStemmer
 */
@WebServlet("/fruktstemmer")
public class FruktStemmer extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private int[] stemmer = new int[4];



	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		
		String stemme = request.getParameter("frukt");
		
		if (stemme.equals("eple")) {
			stemmer[0] = stemmer[0] + 1;
		} else if (stemme.equals("appelsin")) {
			stemmer[1] = stemmer[1] + 1;
		} else if (stemme.equals("plomme")) {
			stemmer[2] = stemmer[2] + 1;
		} else if (stemme.equals("kiwi")) {
			stemmer[3] = stemmer[3] + 1;
		}
		
		request.getSession().setAttribute("listen", stemmer);
		response.sendRedirect("fruktstemmer");

//		doGet(request, response);
	}
	
	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		stemmer = (int[]) request.getSession().getAttribute("listen");
		
		response.setContentType("text/html");
		
		PrintWriter out = response.getWriter();
		
		out.print("<!DOCTYPE html>");
		out.print("<html>");
		out.print("<head>");
		out.print("<meta charset=\"ISO-8859-1\">");
		out.print("</head>");
		out.print("<body>");
		out.print("<h1>Her kan du stemme en gang til:</h1>");
		out.print("<br/>");
		out.print("<form action=\"fruktstemmer\">\r\n");
		out.print("	<fieldset>\r\n");
		out.print("		<legend>Velg din favorittfrukt</legend>\r\n");
		out.print("			<p><input type=\"radio\" name=\"frukt\" value=\"eple\" />Eple</p>\r\n");
		out.print("			<p><input type=\"radio\" name=\"frukt\" value=\"appelsin\" />Appelsin</p>\r\n");
		out.print("			<p><input type=\"radio\" name=\"frukt\" value=\"plomme\" />Plomme</p>\r\n");
		out.print("			<p><input type=\"radio\" name=\"frukt\" value=\"kiwi\" />Kiwi</p>\r\n");
		out.print("			<input type=\"submit\" value=\"Stem\" />\r\n");
		out.print("	</fieldset>\r\n");
		out.print("</form>");
		out.print("");
		out.print("<p>" + stemmer[0] + " har stemt på eple.</p>");
		out.print("<p>" + stemmer[1] + " har stemt på appelsin.</p>");
		out.print("<p>" + stemmer[2] + " har stemt på plomme.</p>");
		out.print("<p>" + stemmer[3] + " har stemt på kiwi.</p>");
		out.print("</html>");
		
		
//		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

}
