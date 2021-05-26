package it.advancia.michele.sessionbean;

import javax.ejb.Local;

import it.advancia.michele.UserManagerException;
import it.advancia.michele.entity.User;

@Local
public interface UserManagerEJB
{
	public User login(String username, String password) throws UserManagerException;

	public User register(String username, String password, String nome, String cognome, String confirmPw) throws UserManagerException;

}
