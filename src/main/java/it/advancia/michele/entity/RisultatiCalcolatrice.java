package it.advancia.michele.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "Risultati")
public class RisultatiCalcolatrice
{
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int id;
	@Column(name = "operazione")
	private String operazione;
	@Column(name = "risultato")
	private double risultato;
	@Column(name = "valore_a")
	private double a;
	@Column(name = "valore_b")
	private double b;
	@ManyToOne
	private ListaRisultati risultati;
	public int getId()
	{
		return id;
	}
	public void setId(int id)
	{
		this.id = id;
	}
	public String getOperazione()
	{
		return operazione;
	}
	public void setOperazione(String operazione)
	{
		this.operazione = operazione;
	}
	public double getRisultato()
	{
		return risultato;
	}
	public void setRisultato(double risultato)
	{
		this.risultato = risultato;
	}
	public double getA()
	{
		return a;
	}
	public void setA(double a)
	{
		this.a = a;
	}
	public double getB()
	{
		return b;
	}
	public void setB(double b)
	{
		this.b = b;
	}
	public ListaRisultati getRisultati()
	{
		return risultati;
	}
	public void setRisultati(ListaRisultati risultati)
	{
		this.risultati = risultati;
	}

}
