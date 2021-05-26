package it.advancia.michele.providers;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class EMProvider
{
	private static EntityManagerFactory provider;

	private EMProvider()
	{

	}

	public static EntityManager getEntityManager()
	{
		if (provider == null)
		{
			createFactory();
		}
		return provider.createEntityManager();
	}

	private static synchronized void createFactory()
	{
		if (provider == null)
		{
			provider = Persistence.createEntityManagerFactory("CalcolatriceEJB");
		}
	}
}
