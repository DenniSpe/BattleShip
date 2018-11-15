package it.unical.asde.battleship.components.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import it.unical.asde.battleship.components.persistence.UsersDAO;
import it.unical.asde.battleship.model.User;

@Service
public class LoginService {
	
	@Autowired
	private UsersDAO usersDAO;
	
	public boolean checkCredentials(String uname, String pwd) {
		return usersDAO.checkCredentials(new User(uname, pwd));
	}

}
