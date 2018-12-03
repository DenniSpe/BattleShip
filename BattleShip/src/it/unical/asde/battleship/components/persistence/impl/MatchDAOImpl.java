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
		String hql = "select count(id) from Match where challenger.username = :ch or creator.username = :ow";
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

	@Override
	public long countUserWins(User user) {
		Session session = sessionFactory.openSession();
		String hql = "select count(id) from Match where creator.username = :ow and wonCreator = true";
		Query query = session.createQuery(hql);
		query.setParameter("ow", user.getUsername());
		long count = (long) query.uniqueResult();

		String hql2 = "select count(id) from Match where challenger.username = :ch and wonCreator = false";
		Query query2 = session.createQuery(hql2);
		query2.setParameter("ch", user.getUsername());
		long count2 = (long) query2.uniqueResult();

		session.close();
		return count + count2;
	}

	@Override
	public long countUserLooses(User user) {
		Session session = sessionFactory.openSession();
		String hql = "select count(id) from Match where creator.username = :ow and wonCreator = false";
		Query query = session.createQuery(hql);
		query.setParameter("ow", user.getUsername());
		long count = (long) query.uniqueResult();

		String hql2 = "select count(id) from Match where challenger.username = :ch and wonCreator = true";
		Query query2 = session.createQuery(hql2);
		query2.setParameter("ch", user.getUsername());

		long count2 = (long) query2.uniqueResult();

		session.close();
		return count + count2;
	}

	@Override
	public List matchTimes(User user) {
		Session session = sessionFactory.openSession();
		String hql = "select (minute(endTime)-minute(startTime)) as mins, matchName from Match where challenger.username = :ch or creator.username = :ow";
		Query query = session.createQuery(hql);
		query.setParameter("ow", user.getUsername());
		query.setParameter("ch", user.getUsername());
		List mins = query.list();

		session.close();
		return mins;
	}

}
