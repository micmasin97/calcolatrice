package it.advancia.michele.service.rest;

import java.util.List;

import javax.ejb.EJB;
import javax.persistence.EntityManager;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import it.advancia.michele.entity.RisultatiCalcolatrice;
import it.advancia.michele.entity.User;
import it.advancia.michele.exception.OperationException;
import it.advancia.michele.providers.EMProvider;
import it.advancia.michele.sessionbean.CalcolatriceEJB;

@Path("{user}")
public class RestService
{

	@PathParam("user")
	private String user;

	@EJB
	private CalcolatriceEJB calcolatrice;

	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public List<RisultatiCalcolatrice> show()
	{
		EntityManager entityManager = EMProvider.getEntityManager();
		User userObj = entityManager.find(User.class, user);
		return calcolatrice.getRisultati(userObj);
	}

	@GET
	@Path("filter/{type}")
	@Produces(MediaType.APPLICATION_JSON)
	public List<RisultatiCalcolatrice> show(@PathParam("type") String type)
	{
		EntityManager entityManager = EMProvider.getEntityManager();
		User userObj = entityManager.find(User.class, user);
		return calcolatrice.getRisultatiFiltrati(userObj, type);
	}

	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public RisultatiCalcolatrice execute(RisultatiCalcolatrice risultato) throws OperationException
	{
		EntityManager entityManager = EMProvider.getEntityManager();
		User userObj = entityManager.find(User.class, user);
		RisultatiCalcolatrice risultatoCalcolato = null;
		switch (risultato.getOperazione())
		{
		case "+":
			risultatoCalcolato = calcolatrice.somma(risultato.getA(), risultato.getB(), userObj);
			break;
		case "-":
			risultatoCalcolato = calcolatrice.differenza(risultato.getA(), risultato.getB(), userObj);
			break;
		case "*":
			risultatoCalcolato = calcolatrice.moltiplicazione(risultato.getA(), risultato.getB(), userObj);
			break;
		case "/":
			risultatoCalcolato = calcolatrice.divisione(risultato.getA(), risultato.getB(), userObj);
			break;
		default:
			throw new OperationException("operazione non supportata");
		}
		return risultatoCalcolato;
	}
	
	@DELETE
	public void delete()
	{
		EntityManager entityManager = EMProvider.getEntityManager();
		User userObj = entityManager.find(User.class, user);
		calcolatrice.clearList(userObj);
	}
}
