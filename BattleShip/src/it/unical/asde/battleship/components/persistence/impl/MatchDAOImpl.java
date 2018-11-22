package it.unical.asde.battleship.components.persistence.impl;

import org.hibernate.Session;
import org.hibernate.query.Query;
import org.springframework.stereotype.Repository;

import it.unical.asde.battleship.components.persistence.MatchDAO;
import it.unical.asde.battleship.components.persistence.global.AbstractBaseDAO;
import it.unical.asde.battleship.model.Match;

@Repository
public class MatchDAOImpl extends AbstractBaseDAO<Match, Long> implements MatchDAO {

	public MatchDAOImpl() {
		super(Match.class);
	}

	@Override
	public long numMatchPlayed() {
		// TODO Auto-generated method stub
		Session session = sessionFactory.openSession();
		Query countQuery = session.createQuery("Select count (id) from Match");
		long count = (long) countQuery.uniqueResult();
		session.close();
		return count;
	}

}
