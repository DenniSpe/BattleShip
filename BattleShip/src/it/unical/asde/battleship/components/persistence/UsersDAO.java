package it.unical.asde.battleship.components.persistence;

import javax.annotation.PostConstruct;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import it.unical.asde.battleship.model.User;

@Repository
public class UsersDAO {
	
	@Autowired
	private SessionFactory sessionFactory;
	
	@PostConstruct
	public void init() {
		save(new User("Dennis","1234"));
		save(new User("Francesco","abcd"));
		save(new User("Pierpaolo","P1"));
		save(new User("Dario","dario"));
		
	}
	
	
	public void save(User user) {
		Session session = sessionFactory.openSession();

		Transaction tx = null;
		try {
			tx = session.beginTransaction();
			session.save(user);
			tx.commit();

		} catch (Exception e) {
			tx.rollback();
		}
		session.close();

	}
	
	
	public boolean checkCredentials(User user) {
		Session openSession = sessionFactory.openSession();
		Query<User> query = openSession.createQuery("from User as u where u.username=:n and u.password=:p", User.class)
			.setParameter("n", user.getUsername()).setParameter("p", user.getPassword());
		
		boolean result = query.uniqueResult() != null;
		openSession.close();
		return result;
	}

}
