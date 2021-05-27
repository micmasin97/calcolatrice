package it.advancia.michele.sessionbean;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;

import it.advancia.michele.entity.ListaRisultati;
import it.advancia.michele.entity.User;
import it.advancia.michele.exception.UserManagerException;
import it.advancia.michele.providers.EMProvider;

@Stateless
public class UserManagerEJBImpl implements UserManagerEJB
{

	@Override
	public User login(String username, String password) throws UserManagerException
	{
		EntityManager entityManager = EMProvider.getEntityManager();
		User user = entityManager.find(User.class, username);
		if(user==null)
		{
			entityManager.close();
			throw new UserManagerException("Lo username "+username + " non esiste");
		}
		else if(user.getPassword().equals(password))
		{
			entityManager.close();
			return user;
		}
		else
		{
			entityManager.close();
			throw new UserManagerException("Password errata");
		}
	}

	@Override
	public User register(String username, String password, String nome, String cognome, String confirmPw) throws UserManagerException
	{
		EntityManager entityManager = EMProvider.getEntityManager();
		User user = entityManager.find(User.class, username);
		if(password.equals(confirmPw)==false)
		{
			entityManager.close();
			throw new UserManagerException("La password inserita e la password convalida non sono uguali");
		}
		if(user!=null)
		{
			entityManager.close();
			throw new UserManagerException("Esiste già un utente con lo username: \""+username+"\"");
		}
		else
		{
			user = new User();
			entityManager.getTransaction().begin();
			user.setNome(nome);
			user.setCognome(cognome);
			user.setUsername(username);
			user.setPassword(password);
			ListaRisultati lista = new ListaRisultati();
			user.setOperazioni(lista);
			entityManager.persist(user);
			entityManager.persist(lista);
			entityManager.getTransaction().commit();
		}
		entityManager.close();
		return user;
	}
	

}
