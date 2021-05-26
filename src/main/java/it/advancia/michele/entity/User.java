package it.advancia.michele.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name = "utenti")
public class User implements Serializable
{

	private static final long serialVersionUID = 2878510245172729270L;
	@Id
	private String username;
	@Column(name = "nome")
	private String nome;
	@Column(name = "cognome")
	private String cognome;
	@Column(name = "password")
	private String password;
	@OneToOne
	private ListaRisultati operazioni;

	public ListaRisultati getOperazioni()
	{
		return operazioni;
	}

	public void setOperazioni(ListaRisultati operazioni)
	{
		this.operazioni = operazioni;
	}

	public String getUsername()
	{
		return username;
	}

	public void setUsername(String username)
	{
		this.username = username;
	}

	public String getNome()
	{
		return nome;
	}

	public void setNome(String nome)
	{
		this.nome = nome;
	}

	public String getCognome()
	{
		return cognome;
	}

	public void setCognome(String cognome)
	{
		this.cognome = cognome;
	}

	public String getPassword()
	{
		return password;
	}

	public void setPassword(String password)
	{
		this.password = password;
	}
}
