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
	
	@Autowired
	private GameService gameService;
	
	//K=username, v=User
	HashMap<String, User> playingUsers;

	@PostConstruct
	public void init() {
		User a = new User("mano", "mano");
		User b = new User("borro", "borro");
		User c = new User("de", "de");
		User d = new User("fra", "fra");
		User e = new User("da","da");
		
		playingUsers = new HashMap<>();

		userDAO.save(a);
		userDAO.save(b);
		userDAO.save(c);
		userDAO.save(d);
		userDAO.save(e);

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

		
		Match m8 = new Match();
		m8.setEndTime(new Date());
		m8.setStartTime(new Date());
		m8.setWonCreator(true);
		m8.setChallenger(d);
		m8.setCreator(c);
		m8.setMatchName("Stanza");
		
		Match m9 = new Match();
		m9.setEndTime(new Date());
		m9.setStartTime(new Date());
		m9.setWonCreator(true);
		m9.setChallenger(a);
		m9.setCreator(c);
		m9.setMatchName("ItalianRoom");
		
		Match m10 = new Match();
		m10.setEndTime(new Date());
		m10.setStartTime(new Date());
		m10.setWonCreator(true);
		m10.setChallenger(e);
		m10.setCreator(c);
		m10.setMatchName("Sfida");
		
		Match m11 = new Match();
		m11.setEndTime(new Date());
		m11.setStartTime(new Date());
		m11.setWonCreator(true);
		m11.setChallenger(e);
		m11.setCreator(d);
		m11.setMatchName("Match 1");
		
		Match m12 = new Match();
		m12.setEndTime(new Date());
		m12.setStartTime(new Date());
		m12.setWonCreator(true);
		m12.setChallenger(c);
		m12.setCreator(d);
		m12.setMatchName("Match Piana");
		
		Match m13 = new Match();
		m13.setEndTime(new Date());
		m13.setStartTime(new Date());
		m13.setWonCreator(true);
		m13.setChallenger(d);
		m13.setCreator(e);
		m13.setMatchName("Challange");
		
		matchDAO.save(m);
		matchDAO.save(m1);
		matchDAO.save(m2);
		matchDAO.save(m3);
		matchDAO.save(m4);
		matchDAO.save(m5);
		matchDAO.save(m6);
		matchDAO.save(m7);
		matchDAO.save(m8);
		matchDAO.save(m9);
		matchDAO.save(m10);
		matchDAO.save(m11);
		matchDAO.save(m12);
		matchDAO.save(m13);

	}

	public int getOnlineUsers() {
		return playingUsers.size();
	}
	
	public void setPlayingUser(User u) {
		playingUsers.put(u.getUsername(), u);
	}
	
	public User getPlayingUser(String username) {
		return playingUsers.get(username);
	}
	
	public void deletePlayingUser(User u) {
		playingUsers.remove(u.getUsername());
		clearGrid(u.getUsername());
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

	public void clearGrid(String username) {
		gameService.clearGrid(username);
	}
}
