package it.unical.asde.battleship.components.persistence;

import it.unical.asde.battleship.components.persistence.global.BaseDAO;
import it.unical.asde.battleship.model.User;

public interface UsersDAO extends BaseDAO<User, Long> {

	public User checkCredentials(User user);

	public boolean userExists(String username);

	public long numberOfUsers();

}
