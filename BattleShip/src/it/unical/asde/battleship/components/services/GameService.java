package it.unical.asde.battleship.components.services;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.PostConstruct;

import org.springframework.stereotype.Service;

import it.unical.asde.battleship.game.Lobby;
import it.unical.asde.battleship.model.User;

@Service
public class GameService {
	
	private Map<String, Lobby> lobbies;
	//private List<Lobby> lobbies;

	
	@PostConstruct
	public void init() {
//		lobbies = new ArrayList<>();
//		
//		lobbies.add(new Lobby("Room1",new User("Dennis","1234")));
//		lobbies.add(new Lobby("Room2",new User("Francesco","5678")));
//		lobbies.add(new Lobby("Stanza",new User("Dario","9123")));
//		lobbies.add(new Lobby("Stanza5",new User("Manuel","borr")));
		
		lobbies = new HashMap<>();
		lobbies.put("Dennis",new Lobby("Room1","Dennis"));
		lobbies.put("Francesco",new Lobby("Room2","Francesco"));
		lobbies.put("Dario",new Lobby("Stanza","Dario"));
		lobbies.put("Manuel",new Lobby("Stanza5","Manuel"));
	}
	
	
	public GameService() {
		super();
		//lobbies = new ArrayList<>();
	}


	public Collection<Lobby> getLobbies() {
		return lobbies.values(); 
	}

	public Lobby getLobby(String owner)
	{
		return this.lobbies.get(owner);
	}

	public void addLobby(String owner, Lobby lobby) {
		this.lobbies.put(owner, lobby);
	}
	
	public void deleteLobby(String owner) {
		this.lobbies.remove(owner);
	}
	
	public void addChallenger(String challenger, String owner) {
		//Lobby myLobby = this.lobbies.get(index)
		//TODO
		//Prendere come parametro anche il nome della Lobby (Deve essere univoco quindi..)
		// ed aggiungere questo challenger alla lobby
		//conviene usare una map 
		
		this.lobbies.get(owner).setChallenger(challenger);
	}
	

}
