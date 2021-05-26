package it.advancia.michele.entity;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "operazioni")
public class ListaRisultati
{
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int id;
	@OneToMany(mappedBy = "id", fetch = FetchType.EAGER, cascade = CascadeType.ALL)
	@Column(name = "operazione")
	private List<RisultatiCalcolatrice> risultato;

	public int getId()
	{
		return id;
	}

	public void setId(int id)
	{
		this.id = id;
	}

	public List<RisultatiCalcolatrice> getRisultato()
	{
		return risultato;
	}

	public void setRisultato(List<RisultatiCalcolatrice> risultato)
	{
		this.risultato = risultato;
	}

	public ListaRisultati()
	{
		risultato = new ArrayList<RisultatiCalcolatrice>();
	}
}
