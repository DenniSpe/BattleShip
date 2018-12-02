package it.unical.asde.battleship.components.persistence.impl;

import java.util.List;


import org.hibernate.Session;
import org.hibernate.query.Query;
import org.springframework.stereotype.Repository;

import it.unical.asde.battleship.components.persistence.MatchDAO;
import it.unical.asde.battleship.components.persistence.global.AbstractBaseDAO;
import it.unical.asde.battleship.model.Match;
import it.unical.asde.battleship.model.User;

@Repository
public class MatchDAOImpl extends AbstractBaseDAO<Match, Long> implements MatchDAO {

	public MatchDAOImpl() {
		super(Match.class);
	}

	@Override
	public long numMatchPlayed() {
		Session session = sessionFactory.openSession();
		Query countQuery = session.createQuery("Select count (id) from Match");
		long count = (long) countQuery.uniqueResult();
		session.close();
		return count;
	}

	@Override
	public long countUserMatches(User user) {
		Session session = sessionFactory.openSession();
		String hql = "select count(id)from Match where challenger.username = :ch or creator.username = :ow";
		Query query = session.createQuery(hql);
		query.setParameter("ch", user.getUsername());
		query.setParameter("ow", user.getUsername());
		long count = (long) query.uniqueResult();
		session.close();
		return count;
	}

	@Override
	public List<Match> getUserMatches(User user, int start, int count) {
		Session session = sessionFactory.openSession();
		String hql = "from Match where challenger.username = :ch or creator.username = :ow";
		Query query = session.createQuery(hql);
		query.setFirstResult(start);
		query.setMaxResults(count);
		query.setParameter("ch", user.getUsername());
		query.setParameter("ow", user.getUsername());
		List<Match> matches = query.list();
		session.close();
		return matches;

	}

}
