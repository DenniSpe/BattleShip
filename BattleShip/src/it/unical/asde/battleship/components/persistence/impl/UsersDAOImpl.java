package it.unical.asde.battleship.components.persistence.impl;

import javax.annotation.PostConstruct;

import org.hibernate.Session;
import org.hibernate.query.Query;
import org.springframework.stereotype.Repository;

import it.unical.asde.battleship.components.persistence.UsersDAO;
import it.unical.asde.battleship.components.persistence.global.AbstractBaseDAO;
import it.unical.asde.battleship.model.User;

@Repository
public class UsersDAOImpl extends AbstractBaseDAO<User, Long> implements UsersDAO {

	public UsersDAOImpl() {
		super(User.class);
		// TODO Auto-generated constructor stub
	}

	//++REFACTORED++
//	@PostConstruct
//	public void init() {
////		save(new User("Dennis","1234"));
////		save(new User("Francesco","abcd"));
////		save(new User("Pierpaolo","P1"));
////		save(new User("Dario","dario"));
//
//	}

//	public void save(User user) {
//		Session session = sessionFactory.openSession();
//
//		Transaction tx = null;
//		try {
//			tx = session.beginTransaction();
//			session.save(user);
//			tx.commit();
//			System.out.println("Data saved");
//
//		} catch (Exception e) {
//			tx.rollback();
//		}
//		session.close();
//
//	}
	//++REFACTORED++
	
	
	public User checkCredentials(User user) {
		Session openSession = sessionFactory.openSession();
		Query<User> query = openSession.createQuery("from User as u where u.username=:n and u.password=:p", User.class)
				.setParameter("n", user.getUsername()).setParameter("p", user.getPassword());

		User result = query.uniqueResult();
		openSession.close();
		return result;
	}

	public boolean userExists(String username) {
		Session openSession = sessionFactory.openSession();
		Query<User> query = openSession.createQuery("from User as u where u.username=:n", User.class).setParameter("n",
				username);

		boolean result = query.uniqueResult() != null;
		openSession.close();
		return result;
	}

	@Override
	public long numberOfUsers() {
		// TODO Auto-generated method stub
		Session session = sessionFactory.openSession();
		Query countQuery = session.createQuery("Select count (id) from User");
		long count = (long) countQuery.uniqueResult();
		session.close();
		return count;
	}

}
