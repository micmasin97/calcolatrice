package it.advancia.michele.servlet;

import java.io.IOException;
import java.util.List;

import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import it.advancia.michele.entity.RisultatiCalcolatrice;
import it.advancia.michele.entity.User;
import it.advancia.michele.exception.OperationException;
import it.advancia.michele.exception.UserManagerException;
import it.advancia.michele.sessionbean.CalcolatriceEJB;

@WebServlet("/CalculatorSerlvlet")
public class CalculatorSerlvlet extends HttpServlet
{
	private static final long serialVersionUID = 1L;

	@EJB
	private CalcolatriceEJB calcolatrice;

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
	{
		try
		{
			List<RisultatiCalcolatrice> risultati;
			if (request.getParameter("submit").equals("="))
			{
				RisultatiCalcolatrice risultato = actions(request);
				request.setAttribute("operator1", risultato.getA());
				request.setAttribute("operator2", risultato.getB());
				request.setAttribute("result", risultato.getRisultato());
				risultati = calcolatrice.getRisultati((User) request.getSession().getAttribute("user"));
			}
			else if(request.getParameter("submit").equals("delete_all"))
			{
				calcolatrice.clearList((User) request.getSession().getAttribute("user"));
				risultati=null;
			}
			else
			{
				risultati = calcolatrice.getRisultatiFiltrati((User) request.getSession().getAttribute("user"),request.getParameter("type"));
			}
			request.setAttribute("resultList", risultati);
			request.getRequestDispatcher("loggedPage.jsp").forward(request, response);
		} catch (OperationException | UserManagerException e)
		{
			request.setAttribute("error", e.toString());
			request.getRequestDispatcher("error.jsp").forward(request, response);
		}
	}

	private RisultatiCalcolatrice actions(HttpServletRequest request) throws OperationException, UserManagerException
	{
		if ((User) request.getSession().getAttribute("user") == null)
		{
			throw new UserManagerException("Non sei loggato");
		}
		RisultatiCalcolatrice risultato = null;
		check(request.getParameter("operator1"), request.getParameter("operator2"));
		switch (request.getParameter("operation"))
		{
		case "+":
			risultato = calcolatrice.somma(Double.parseDouble(request.getParameter("operator1")), Double.parseDouble(request.getParameter("operator2")), (User) request.getSession().getAttribute("user"));
			break;
		case "-":
			risultato = calcolatrice.differenza(Double.parseDouble(request.getParameter("operator1")), Double.parseDouble(request.getParameter("operator2")), (User) request.getSession().getAttribute("user"));
			break;
		case "*":
			risultato = calcolatrice.moltiplicazione(Double.parseDouble(request.getParameter("operator1")), Double.parseDouble(request.getParameter("operator2")), (User) request.getSession().getAttribute("user"));
			break;
		case "/":
			risultato = calcolatrice.divisione(Double.parseDouble(request.getParameter("operator1")), Double.parseDouble(request.getParameter("operator2")), (User) request.getSession().getAttribute("user"));
			break;
		default:
			throw new OperationException("Operazione non ammessa, lista operazioni ammesse: +, -, /, *");
		}
		return risultato;
	}

	private void check(String a, String b) throws OperationException
	{
		if (a.equals(""))
		{
			if (b.equals(""))
			{
				throw new OperationException("Operandi nulli");
			}
			throw new OperationException("Operando 1 vuoto");
		}
		if (b.equals(""))
		{
			throw new OperationException("Operando 2 vuoto");
		}
	}

}
