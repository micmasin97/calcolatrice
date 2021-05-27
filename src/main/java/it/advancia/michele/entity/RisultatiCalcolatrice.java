package it.advancia.michele.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EntityResult;
import javax.persistence.FieldResult;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.SqlResultSetMapping;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlAccessorOrder;
import javax.xml.bind.annotation.XmlTransient;
import javax.xml.bind.annotation.XmlType;


@Entity
@Table(name = "Risultati")
@SqlResultSetMapping(name = "RisultatiListaUser", entities =
{ @EntityResult(entityClass = RisultatiCalcolatrice.class, fields =
{ @FieldResult(name = "id", column = "id"), @FieldResult(name = "a", column = "valore_a"), @FieldResult(name = "operazione", column = "operazione"), @FieldResult(name = "b", column = "valore_b"), @FieldResult(name = "risultato", column = "risultato"), @FieldResult(name = "risultati", column = "risultati_id"), }) })
@XmlType(propOrder =
{ "id", "a", "operazione", "b", "risultato" })
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

	@XmlTransient
	public ListaRisultati getRisultati()
	{
		return risultati;
	}

	public void setRisultati(ListaRisultati risultati)
	{
		this.risultati = risultati;
	}

}
