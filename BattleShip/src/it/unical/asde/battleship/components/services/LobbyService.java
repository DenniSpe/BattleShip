package it.unical.asde.battleship.components.services;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

import javax.annotation.PostConstruct;

import org.springframework.stereotype.Service;

import it.unical.asde.battleship.game.Lobby;

@Service
public class LobbyService {
	
	private int lobbyID = 0;
	private Map<Integer, Lobby> lobbies;

	
	@PostConstruct
	public void init() {
		
		lobbies = new HashMap<>();
		lobbies.put(this.getCurrentLobbyID(),new Lobby(this.assignLobbyID(),"Room1","Dennis"));
		lobbies.put(this.getCurrentLobbyID(),new Lobby(this.assignLobbyID(),"Room2","Francesco"));
		lobbies.put(this.getCurrentLobbyID(),new Lobby(this.assignLobbyID(),"Stanza","Dario"));
		lobbies.put(this.getCurrentLobbyID(),new Lobby(this.assignLobbyID(),"Stanza5","Manuel"));
	}
	
	
	public LobbyService() {
		super();
	}

	
	public int assignLobbyID() {
		return lobbyID++;
	}
	
	public int getCurrentLobbyID() {
		return lobbyID;
	}

	public Collection<Lobby> getLobbies() {
		return lobbies.values(); 
	}

	public Lobby getLobby(int id)
	{
		return this.lobbies.get(id);
	}

	public void addLobby(Lobby lobby) {
		this.lobbies.put(lobby.getId(), lobby);
	}
	
	public void deleteLobby(int id) {
		this.lobbies.remove(id);
	}
	
	public void addChallenger(Lobby lobby, String challenger) {
		this.lobbies.get(lobby.getId()).setChallenger(challenger);
	}
	

}
