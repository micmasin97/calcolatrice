package it.advancia.michele.sessionbean;

import java.util.List;

import javax.ejb.Local;

import it.advancia.michele.entity.RisultatiCalcolatrice;
import it.advancia.michele.entity.User;
import it.advancia.michele.exception.OperationException;

@Local
public interface CalcolatriceEJB
{
	RisultatiCalcolatrice somma(double a, double b, User user);

	RisultatiCalcolatrice differenza(double a, double b, User user);

	RisultatiCalcolatrice moltiplicazione(double a, double b, User user);

	RisultatiCalcolatrice divisione(double a, double b, User user) throws OperationException;

	List<RisultatiCalcolatrice> getRisultati(User user);
}
