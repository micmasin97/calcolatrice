package it.advancia.michele.sessionbean;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.Query;

import it.advancia.michele.entity.RisultatiCalcolatrice;
import it.advancia.michele.entity.User;
import it.advancia.michele.exception.OperationException;
import it.advancia.michele.providers.EMProvider;

@Stateless
public class CalcolatriceEJBImplem implements CalcolatriceEJB
{

	public RisultatiCalcolatrice somma(double a, double b, User user)
	{
		RisultatiCalcolatrice risultato = new RisultatiCalcolatrice();
		risultato.setA(a);
		risultato.setB(b);
		risultato.setRisultato(a + b);
		risultato.setOperazione("+");
		saveResult(risultato, user);
		return risultato;
	}

	@Override
	public RisultatiCalcolatrice differenza(double a, double b, User user)
	{
		RisultatiCalcolatrice risultato = new RisultatiCalcolatrice();
		risultato.setA(a);
		risultato.setB(b);
		risultato.setRisultato(a - b);
		risultato.setOperazione("-");
		saveResult(risultato, user);
		return risultato;
	}

	@Override
	public RisultatiCalcolatrice moltiplicazione(double a, double b, User user)
	{
		RisultatiCalcolatrice risultato = new RisultatiCalcolatrice();
		risultato.setA(a);
		risultato.setB(b);
		risultato.setRisultato(a * b);
		risultato.setOperazione("*");
		saveResult(risultato, user);
		return risultato;
	}

	@Override
	public RisultatiCalcolatrice divisione(double a, double b, User user) throws OperationException
	{
		if(b==0)
		{
			throw new OperationException("Impossibile dividere per 0");
		}
		RisultatiCalcolatrice risultato = new RisultatiCalcolatrice();
		risultato.setA(a);
		risultato.setB(b);
		risultato.setRisultato(a / b);
		risultato.setOperazione("/");
		saveResult(risultato, user);
		return risultato;
	}

	private boolean saveResult(RisultatiCalcolatrice risultato, User user)
	{
		EntityManager entityManager = EMProvider.getEntityManager();
		entityManager.getTransaction().begin();
		user.getOperazioni().getRisultato().add(risultato);
		risultato.setRisultati(user.getOperazioni());
		entityManager.merge(user);
		entityManager.merge(risultato);
		entityManager.getTransaction().commit();
		entityManager.close();
		return false;
	}

	@Override
	public List<RisultatiCalcolatrice> getRisultati(User user)
	{
		EntityManager entityManager = EMProvider.getEntityManager();
		Query q = entityManager.createNativeQuery("select r.id, r.valore_a, r.operazione, r.valore_b, r.risultato, r.risultati_id from Risultati r join operazioni o on r.risultati_id=o.id join Utenti u on u.operazioni_id=o.id where u.username=:user","RisultatiListaUser");
		q.setParameter("user", user.getUsername());
		List<RisultatiCalcolatrice> risultati = q.getResultList();
		return risultati;
	}
	
	@Override
	public List<RisultatiCalcolatrice> getRisultatiFiltrati(User user, String filtro)
	{
		EntityManager entityManager = EMProvider.getEntityManager();
		Query q;
		if(filtro.equals(""))
		{
			q = entityManager.createNativeQuery("select r.id, r.valore_a, r.operazione, r.valore_b, r.risultato, r.risultati_id from Risultati r join operazioni o on r.risultati_id=o.id join Utenti u on u.operazioni_id=o.id where u.username=:user","RisultatiListaUser");
		}
		else
		{
			q = entityManager.createNativeQuery("select r.id, r.valore_a, r.operazione, r.valore_b, r.risultato, r.risultati_id from Risultati r join operazioni o on r.risultati_id=o.id join Utenti u on u.operazioni_id=o.id where u.username=:user and r.operazione=:operazione","RisultatiListaUser");
			q.setParameter("operazione", filtro);
		}
		q.setParameter("user", user.getUsername());
		List<RisultatiCalcolatrice> risultati = q.getResultList();
		return risultati;
	}

	@Override
	public void clearList(User user)
	{
		EntityManager entityManager = EMProvider.getEntityManager();
		Query q = entityManager.createNativeQuery("delete r from Risultati r join operazioni o on r.risultati_id=o.id join Utenti u on u.operazioni_id=o.id where u.username=:user");
		q.setParameter("user", user.getUsername());
		q.executeUpdate();
		
	}

}
