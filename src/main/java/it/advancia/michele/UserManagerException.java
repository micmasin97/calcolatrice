package it.advancia.michele;

import com.sun.xml.bind.v2.runtime.RuntimeUtil.ToStringAdapter;

public class UserManagerException extends Exception
{
	public UserManagerException(String message)
	{
		super(message);
	}
	@Override
	public String toString()
	{
		return "Si è verificato un errore durante l'operazione di login/registrazione, tipo errore:"+getMessage();
	}
}
