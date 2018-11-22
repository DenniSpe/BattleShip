package it.unical.asde.battleship.components.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import it.unical.asde.battleship.components.persistence.impl.UsersDAOImpl;
import it.unical.asde.battleship.model.User;

@Service
public class RegistrationService {
	
	@Autowired
	private UsersDAOImpl usersDAO;
	
	public void insertUser(User user)
	{
		usersDAO.save(user);
	}
	
	public boolean userExists(String username) 
	{
		return usersDAO.userExists(username);
		
	}

}
