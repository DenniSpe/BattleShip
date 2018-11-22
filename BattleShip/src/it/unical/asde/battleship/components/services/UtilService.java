package it.unical.asde.battleship.components.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import it.unical.asde.battleship.components.persistence.impl.MatchDAOImpl;
import it.unical.asde.battleship.components.persistence.impl.UsersDAOImpl;

@Service
public class UtilService {

	@Autowired
	private UsersDAOImpl userDAO;

	@Autowired
	private MatchDAOImpl matchDAO;

	public long numUsers() {
		return userDAO.numberOfUsers();
	}
	
	public long numMatchPlayed() {
		return userDAO.numberOfUsers();
	}

}
