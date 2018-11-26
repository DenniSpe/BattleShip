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
	
	@PostConstruct
	public void init() {
		gridOwner = new HashMap<>();
		gridChallenger = new HashMap<>();
	}
	
	public void startGame(int lobbyID) {
		Lobby currentLobby = lobbyService.getLobby(lobbyID);
		
		gridOwner.put(currentLobby.getOwner(), new Grid());
		gridChallenger.put(currentLobby.getChallenger(), new Grid());
	}
	
	public void putShipOwner(int lobbyID, int row, int col, int numShip, int dir) {
		Lobby currentLobby = lobbyService.getLobby(lobbyID);
		gridOwner.get(currentLobby.getOwner()).setShip(row, col, numShip, dir);
	}
	
	public void putShipChallenger(int lobbyID, int row, int col, int numShip, int dir) {
		Lobby currentLobby = lobbyService.getLobby(lobbyID);
		gridChallenger.get(currentLobby.getChallenger()).setShip(row, col, numShip, dir);
		
		System.out.println("===== sono dentro putshipchallenger");
		
		gridChallenger.get(currentLobby.getChallenger()).print();
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
	// TODO Before to delete the Lobby in the LobbyService (CHECK THE CONTROLLER CLASS, NOT THE SERVICE ONE),
	// Call this method to avoid NullPointerException
	public void deleteGame(int lobbyID) {
		Lobby l = lobbyService.getLobby(lobbyID);
		
		gridOwner.remove(l.getOwner());
		gridChallenger.remove(l.getChallenger());
	}
	

}
