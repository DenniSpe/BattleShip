package it.unical.asde.battleship.components.persistence;

import java.util.List;

import it.unical.asde.battleship.components.persistence.global.BaseDAO;
import it.unical.asde.battleship.model.Match;
import it.unical.asde.battleship.model.User;

public interface MatchDAO extends BaseDAO<Match, Long> {

	public long numMatchPlayed();

	public List<Match> getUserMatches(User user, int start, int count);

	public long countUserMatches(User user);

}
