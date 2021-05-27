package it.advancia.michele.service.rest;

import java.io.IOException;
import java.util.Base64;
import java.util.List;
import java.util.StringTokenizer;

import javax.persistence.EntityManager;
import javax.ws.rs.container.ContainerRequestContext;
import javax.ws.rs.container.ContainerRequestFilter;
import javax.ws.rs.core.Response;
import javax.ws.rs.ext.Provider;


import it.advancia.michele.entity.User;
import it.advancia.michele.providers.EMProvider;


@Provider
public class LoginFilter implements ContainerRequestFilter
{

	private static final String AUTHORIZATION_HEADER_KEY = "Authorization";
	private static final String AUTHORIZATION_HEADER_PREFIX = "Basic ";

	@Override
	public void filter(ContainerRequestContext requestContext) throws IOException
	{
		String path = requestContext.getUriInfo().getPath();
		String user = path.split("/")[1];
		List<String> authHeader = requestContext.getHeaders().get(AUTHORIZATION_HEADER_KEY);
		if (authHeader != null && !authHeader.isEmpty())
		{
			// se authheader contiene qualcosa
			String authToken = authHeader.get(0);
			// rimuovo la stringa token(cos� da avere poi solo la parte codificata)
			authToken = authToken.replaceFirst(AUTHORIZATION_HEADER_PREFIX, "");
			// e la decodifico
			String decodedString = new String(Base64.getDecoder().decode(authToken));
			StringTokenizer tokens = new StringTokenizer(decodedString, ":");
			String username = tokens.nextToken();
			String password = tokens.nextToken();
			if (username.equals(user))
			{
				EntityManager entityManager = EMProvider.getEntityManager();
				entityManager.getTransaction().begin();
				User account = entityManager.find(User.class, user);
				if (account.getPassword().equals(password))
				{
					entityManager.close();
					return;
				}
				entityManager.close();
			}
		}
		Response unauthorizedStatus = Response.status(Response.Status.UNAUTHORIZED).entity("User cannot access to the resource. ").build();
		// blocco l'invio della response e invio la risposta personalizzata
		requestContext.abortWith(unauthorizedStatus);
	}

}
