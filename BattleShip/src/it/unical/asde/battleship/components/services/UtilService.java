package it.unical.asde.battleship.components.services;

import java.util.Date;
import java.util.HashMap;
import java.util.List;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import it.unical.asde.battleship.components.persistence.impl.MatchDAOImpl;
import it.unical.asde.battleship.components.persistence.impl.UsersDAOImpl;
import it.unical.asde.battleship.model.Match;
import it.unical.asde.battleship.model.User;

@Service
public class UtilService {

	@Autowired
	private UsersDAOImpl userDAO;

	@Autowired
	private MatchDAOImpl matchDAO;
	
	HashMap<String, User> playingUsers;

	@PostConstruct
	public void init() {
		User a = new User("mano", "mano");
		User b = new User("borro", "borro");
		
		playingUsers = new HashMap<>();

		userDAO.save(a);
		userDAO.save(b);

		Match m = new Match();
		m.setEndTime(new Date());
		m.setStartTime(new Date());
		m.setWonCreator(true);
		m.setChallenger(a);
		m.setCreator(b);
		m.setMatchName("Hard Match");

		Match m1 = new Match();
		m1.setEndTime(new Date());
		m1.setStartTime(new Date());
		m1.setWonCreator(true);
		m1.setChallenger(a);
		m1.setCreator(b);
		m1.setMatchName("Hard Match");

		Match m2 = new Match();
		m2.setEndTime(new Date());
		m2.setStartTime(new Date());
		m2.setWonCreator(true);
		m2.setChallenger(a);
		m2.setCreator(b);
		m2.setMatchName("Hard Match");

		Match m3 = new Match();
		m3.setEndTime(new Date());
		m3.setStartTime(new Date());
		m3.setWonCreator(true);
		m3.setChallenger(a);
		m3.setCreator(b);
		m3.setMatchName("Hard Match");
		Match m4 = new Match();
		m4.setEndTime(new Date());
		m4.setStartTime(new Date());
		m4.setWonCreator(true);
		m4.setChallenger(a);
		m4.setCreator(b);
		m4.setMatchName("Hard Match");

		Match m5 = new Match();
		m5.setEndTime(new Date());
		m5.setStartTime(new Date());
		m5.setWonCreator(true);
		m5.setChallenger(a);
		m5.setCreator(b);
		m5.setMatchName("Hard Match");
		Match m6 = new Match();
		m6.setEndTime(new Date());
		m6.setStartTime(new Date());
		m6.setWonCreator(true);
		m6.setChallenger(a);
		m6.setCreator(b);
		m6.setMatchName("Hard Match");

		Match m7 = new Match();
		m7.setEndTime(new Date());
		m7.setStartTime(new Date());
		m7.setWonCreator(true);
		m7.setChallenger(a);
		m7.setCreator(b);
		m7.setMatchName("Hard Match");

		matchDAO.save(m);
		matchDAO.save(m1);
		matchDAO.save(m2);
		matchDAO.save(m3);
		matchDAO.save(m4);
		matchDAO.save(m5);
		matchDAO.save(m6);
		matchDAO.save(m7);

	}

	public void setPlayingUser(User u) {
		playingUsers.put(u.getUsername(), u);
	}
	
	public User getPlayingUser(String username) {
		return playingUsers.get(username);
	}
	
	public void deletePlayingUser(User u) {
		playingUsers.remove(u.getUsername());
	}
	
	public long numUsers() {
		return userDAO.numberOfUsers();
	}

	public long numMatchPlayed() {
		return userDAO.numberOfUsers();
	}

	public List<Match> getUserMatches(User user, int page, int amount) {

//		long total = matchDAO.countUserMatches(user);
		int start = (page - 1) * amount;

		return matchDAO.getUserMatches(user, start, amount);

	}

	public long getCountMatches(User user) {

		return matchDAO.countUserMatches(user);

	}

}
