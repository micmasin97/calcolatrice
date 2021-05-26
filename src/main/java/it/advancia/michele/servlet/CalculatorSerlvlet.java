package it.advancia.michele.servlet;

import java.io.IOException;

import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import it.advancia.michele.entity.RisultatiCalcolatrice;
import it.advancia.michele.sessionbean.CalcolatriceEJB;

@WebServlet("/CalculatorSerlvlet")
public class CalculatorSerlvlet extends HttpServlet
{
	private static final long serialVersionUID = 1L;

	@EJB
	private CalcolatriceEJB calcolatrice;

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
	{
		RisultatiCalcolatrice risultato = actions(request);
		request.setAttribute("operator1", risultato.getA());
		request.setAttribute("operator2", risultato.getB());
		request.setAttribute("result", risultato.getRisultato());
		request.getRequestDispatcher("loggedPage.jsp").forward(request, response);
	}

	private RisultatiCalcolatrice actions(HttpServletRequest request)
	{
		RisultatiCalcolatrice risultato = null;
		switch (request.getParameter("operation"))
		{
		case "add":
			risultato = calcolatrice.somma(Double.parseDouble(request.getParameter("operator1")), Double.parseDouble(request.getParameter("operator2")));
			break;
		default:
			risultato= new RisultatiCalcolatrice();
			risultato.setA(0);
			risultato.setB(0);
			risultato.setRisultato(0);
		}
		return risultato;
	}

}
