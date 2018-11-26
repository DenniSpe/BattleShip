package it.unical.asde.battleship.components.services;

import java.util.HashMap;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import it.unical.asde.battleship.game.Lobby;
import it.unical.asde.battleship.model.Grid;

@Service
public class GameService {
	
	@Autowired
	LobbyService lobbyService;
	
	// K = Username, V = Grid
	private HashMap<String, Grid> gridOwner; 
	private HashMap<String, Grid> gridChallenger;
	
	
	// K = Username, V = boolean
	private HashMap<String, Boolean> isReadyOwner;
	private HashMap<String, Boolean> isReadyChallenger;
	
	@PostConstruct
	public void init() {
		gridOwner = new HashMap<>();
		gridChallenger = new HashMap<>();
		
		isReadyOwner = new HashMap<>();
		isReadyChallenger = new HashMap<>();
	}
	
	public void startGame(int lobbyID) {
		Lobby currentLobby = lobbyService.getLobby(lobbyID);
		
		gridOwner.put(currentLobby.getOwner(), new Grid());
		gridChallenger.put(currentLobby.getChallenger(), new Grid());
		
		isReadyOwner.put(currentLobby.getOwner(), false);
		isReadyChallenger.put(currentLobby.getChallenger(), false);
	}
	
	public void putShipOwner(int lobbyID, int row, int col, int numShip, int dir) {
		Lobby currentLobby = lobbyService.getLobby(lobbyID);
		gridOwner.get(currentLobby.getOwner()).setShip(row, col, numShip, dir);
	}
	
	public void putShipChallenger(int lobbyID, int row, int col, int numShip, int dir) {
		Lobby currentLobby = lobbyService.getLobby(lobbyID);
		
		gridChallenger.get(currentLobby.getChallenger()).setShip(row, col, numShip, dir);
	}
	
	public boolean hasShipOwner(int lobbyID, int row, int col) {
		Lobby currentLobby = lobbyService.getLobby(lobbyID);
		
		return gridOwner.get(currentLobby.getOwner()).hasShip(row, col);
	}
	
	public boolean hasShipChallenger(int lobbyID, int row, int col) {
		Lobby currentLobby = lobbyService.getLobby(lobbyID);
		
		return gridChallenger.get(currentLobby.getChallenger()).hasShip(row, col);
	}

	public boolean isAlreadyGuessedOwner(int lobbyID, int row, int col) {
		Lobby currentLobby = lobbyService.getLobby(lobbyID);
		
		return gridOwner.get(currentLobby.getOwner()).alreadyGuessed(row, col);
	}
	
	public boolean isAlreadyGuessedChallenger(int lobbyID, int row, int col) {
		Lobby currentLobby = lobbyService.getLobby(lobbyID);
		
		return gridChallenger.get(currentLobby.getChallenger()).alreadyGuessed(row, col);
	}
	
	public Grid getOwnerGrid(int lobbyID) {
		Lobby currentLobby = lobbyService.getLobby(lobbyID);
		
		return gridOwner.get(currentLobby.getOwner());
	}

	public Grid getChallengerGrid(int lobbyID) {
		Lobby currentLobby = lobbyService.getLobby(lobbyID);
		
		return gridChallenger.get(currentLobby.getChallenger());
	}
	
	public void ownerIsReady(int lobbyID) {
		Lobby currentLobby = lobbyService.getLobby(lobbyID);
		
		isReadyOwner.put(currentLobby.getOwner(), true);
	}
	
	public void challengerIsReady(int lobbyID) {
		Lobby currentLobby = lobbyService.getLobby(lobbyID);
		
		isReadyChallenger.put(currentLobby.getChallenger(), true);
	}
	
	public boolean usersAreReady(int lobbyID) {
		Lobby currentLobby = lobbyService.getLobby(lobbyID);
		
		return isReadyOwner.get(currentLobby.getOwner()) && isReadyChallenger.get(currentLobby.getChallenger());
	}
	
	// TODO Before to delete the Lobby in the LobbyService (CHECK THE CONTROLLER CLASS, NOT THE SERVICE ONE),
	// Call this method to avoid NullPointerException
	public void deleteGame(int lobbyID) {
		Lobby l = lobbyService.getLobby(lobbyID);
		
		gridOwner.remove(l.getOwner());
		gridChallenger.remove(l.getChallenger());
	}
	

}
