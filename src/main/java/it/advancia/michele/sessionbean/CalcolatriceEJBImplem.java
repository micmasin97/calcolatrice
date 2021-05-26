package it.advancia.michele.sessionbean;

import javax.ejb.Stateless;

import it.advancia.michele.entity.RisultatiCalcolatrice;

@Stateless
public class CalcolatriceEJBImplem implements CalcolatriceEJB
{

	public RisultatiCalcolatrice somma(double a, double b)
	{
		RisultatiCalcolatrice risultato = new RisultatiCalcolatrice();
		risultato.setA(a);
		risultato.setB(b);
		risultato.setRisultato(a+b);
		risultato.setOperazione("+");
		return risultato;
	}

	@Override
	public RisultatiCalcolatrice differenza(double a, double b)
	{
		return null;
	}

	@Override
	public RisultatiCalcolatrice moltiplicazione(double a, double b)
	{
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public RisultatiCalcolatrice divisione(double a, double b)
	{
		// TODO Auto-generated method stub
		return null;
	}

}
