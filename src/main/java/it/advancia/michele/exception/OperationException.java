package it.advancia.michele.exception;

public class OperationException extends Exception
{

	private static final long serialVersionUID = -4191314918936881141L;
	
	public OperationException(String message)
	{
		super(message);
	}
	@Override
	public String toString()
	{
		return "Si è verificato un errore durante l'operazione, tipo errore:"+getMessage();
	}

}
