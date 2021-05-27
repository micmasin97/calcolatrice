package it.advancia.michele.service.soap;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.ejb.EJB;
import javax.jws.WebMethod;
import javax.jws.WebResult;
import javax.jws.WebService;
import javax.persistence.EntityManager;
import javax.xml.ws.WebServiceContext;
import javax.xml.ws.handler.MessageContext;
import javax.xml.ws.http.HTTPException;

import it.advancia.michele.entity.RisultatiCalcolatrice;
import it.advancia.michele.entity.User;
import it.advancia.michele.exception.OperationException;
import it.advancia.michele.providers.EMProvider;
import it.advancia.michele.sessionbean.CalcolatriceEJB;


@WebService(serviceName = "Calcolatrice", portName = "CalcolatricePort")
public class ResultsService
{
	@Resource
	private WebServiceContext wsc;
	
	@EJB
	private CalcolatriceEJB calcolatrice;
	
	public User auth()
	{
		MessageContext context = wsc.getMessageContext();
		Map<String,List<String>> head =(Map)context.get(MessageContext.HTTP_REQUEST_HEADERS);
		List<String> userList = head.get("username");
		List<String> passwordList = head.get("password");
		String user="";
		String password="";
		if(userList != null && passwordList != null)
		{
			user= userList.get(0);
			password= passwordList.get(0);
		
			EntityManager entityManager = EMProvider.getEntityManager();
			User account = entityManager.find(User.class, user);
			if(account.getPassword().equals(password))
			{
				entityManager.close();
				return account;
			}
		}
		return null;
	}
	
	@WebMethod
	@WebResult(name="Operazione")
	public List<RisultatiCalcolatrice> showAll()
	{
		User user = auth();
		if(user==null)
			throw new HTTPException(401);
		return calcolatrice.getRisultati(user);
	}

	@WebMethod
	@WebResult(name="Risultato")
	public RisultatiCalcolatrice somma(Double a, Double b) throws OperationException
	{
		User user = auth();
		if(user==null)
			throw new HTTPException(401);
		if(a==null || b==null)
			throw new OperationException("uno degli operatori è nullo");
		return calcolatrice.somma(a, b, user);
	}
	
	@WebMethod
	@WebResult(name="Risultato")
	public RisultatiCalcolatrice differenza(Double a, Double b) throws OperationException
	{
		User user = auth();
		if(user==null)
			throw new HTTPException(401);
		if(a==null || b==null)
			throw new OperationException("uno degli operatori è nullo");
		return calcolatrice.differenza(a, b, user);
	}
	
	@WebMethod
	@WebResult(name="Risultato")
	public RisultatiCalcolatrice moltiplicazione(Double a, Double b) throws OperationException
	{
		User user = auth();
		if(user==null)
			throw new HTTPException(401);
		if(a==null || b==null)
			throw new OperationException("uno degli operatori è nullo");
		return calcolatrice.moltiplicazione(a, b, user);
	}
	
	@WebMethod
	@WebResult(name="Risultato")
	public RisultatiCalcolatrice divisione(Double a, Double b) throws OperationException
	{
		User user = auth();
		if(user==null)
			throw new HTTPException(401);
		if(a==null || b==null)
			throw new OperationException("uno degli operatori è nullo");
		return calcolatrice.divisione(a, b, user);
	}
	
	@WebMethod
	@WebResult(name="Operazione")
	public List<RisultatiCalcolatrice> show(String type)
	{
		User user = auth();
		if(user==null)
			throw new HTTPException(401);
		return calcolatrice.getRisultatiFiltrati(user, type);
	}
	
	@WebMethod
	public void clear()
	{
		User user = auth();
		if(user==null)
			throw new HTTPException(401);
		calcolatrice.clearList(user);
	}
}
