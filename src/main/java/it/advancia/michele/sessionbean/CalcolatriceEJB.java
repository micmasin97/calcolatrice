package it.advancia.michele.sessionbean;

import javax.ejb.Local;

import it.advancia.michele.entity.RisultatiCalcolatrice;

@Local
public interface CalcolatriceEJB
{
	RisultatiCalcolatrice somma(double a, double b);
	
	RisultatiCalcolatrice differenza(double a, double b);
	
	RisultatiCalcolatrice moltiplicazione(double a, double b);
	
	RisultatiCalcolatrice divisione(double a, double b) throws Exception;
}
