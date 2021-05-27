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
import it.advancia.michele.sessionbean.UserManagerEJB;

@WebServlet("/UserServlet")
public class UserServlet extends HttpServlet
{
	private static final String ERROR_PAGE = "error.jsp";
	private static final long serialVersionUID = 1L;

	@EJB
	private UserManagerEJB userManager;
	
	@EJB
	private CalcolatriceEJB calcolatrice;

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
	{
		try 
		{
			request.setAttribute("error", "impossibile accedere al login/registrazione mediante il metodo get, per favore utilizza la pagina index.jsp per eseguire le operazioni");
			request.getRequestDispatcher(ERROR_PAGE).forward(request, response);
		}
		catch(ServletException | IOException e)
		{
			e.printStackTrace();
		}
	}	

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
	{
		String operation = request.getParameter("submit");
		User user=null;
		try
		{
			if (operation.equals("login"))
			{
				user = userManager.login(request.getParameter("username"), request.getParameter("password"));
			}
			else if(operation.equals("register"))
			{
				user = userManager.register(request.getParameter("usernameRegister"), request.getParameter("passwordRegister"), request.getParameter("nome"), request.getParameter("cognome"), request.getParameter("conferma"));
			}
			else
			{
				request.setAttribute("error", "Non sono consentite operazioni esterne a login o register");
				request.getRequestDispatcher(ERROR_PAGE).forward(request, response);
			}
			List<RisultatiCalcolatrice> risultati = calcolatrice.getRisultati(user);
			request.setAttribute("resultList", risultati);
			request.getSession().setAttribute("user", user);
			
			request.getRequestDispatcher("loggedPage.jsp").forward(request, response);
			//response.sendRedirect("loggedPage.jsp");
		} catch (UserManagerException e)
		{
			request.setAttribute("error", e.toString());
			request.getRequestDispatcher(ERROR_PAGE).forward(request, response);
		}
		catch(ServletException | IOException e)
		{
			e.printStackTrace();
		}
	}
	

}
