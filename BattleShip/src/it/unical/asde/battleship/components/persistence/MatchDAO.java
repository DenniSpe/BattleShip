package it.unical.asde.battleship.components.persistence;

import it.unical.asde.battleship.components.persistence.global.BaseDAO;
import it.unical.asde.battleship.model.Match;

public interface MatchDAO extends BaseDAO<Match, Long> {
	public long numMatchPlayed();
}
