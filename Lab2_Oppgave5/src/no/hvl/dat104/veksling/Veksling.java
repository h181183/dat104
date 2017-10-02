package no.hvl.dat104.veksling;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class Veksling
 */
@WebServlet("/veksling")
public class Veksling extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String belop = request.getParameter("belop");
		String innValuta = request.getParameter("innvaluta");
		String utValuta = request.getParameter("utvaluta");
		String nySum;
		
		ExchangeRate rate = ExchangeRateService.getRate(innValuta, utValuta);
		if(rate.success) {
			try {
				double temp = BeregningNyValuta.beregnSum(belop, rate);
				nySum = "" + temp;
			} catch (Exception e){
				nySum = null;
			}
		} else {
			nySum = null;
		}
		
		response.setContentType("text/html; charset=UTF-8");
		PrintWriter out = response.getWriter();
		
		
		out.println("<!DOCTYPE html>");
		out.println("<html>");
		out.println("<head>");
		out.println("<script>");
		out.println("function goBack(){");
		out.println("window.history.back();");
		out.println("}");
		out.println("</script>");
		out.println("<meta charset=\"UTF-8\">");
		out.println("<title>Lab2_Oppgave5</title>");
		out.println("</head>");
		out.println("<body>");
		out.println("<h1>Valutaveksling resultat</h1>");
		if (nySum == null) {
			out.println("<p>Noe gikk galt, vennligst prøv igjen.</p>");			
		} else {
			out.println("<p>" + belop + " " + innValuta + " blir " + nySum + " " + utValuta + "</p>");
		}
		out.println("<br/>");
		out.println("<button onclick=\"goBack()\">Gå tilbake</button>");
		out.println("</body>");
		out.println("</html>");
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
