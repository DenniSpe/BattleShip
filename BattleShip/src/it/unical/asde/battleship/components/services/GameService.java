package it.unical.asde.battleship.components.services;

import java.util.Date;
import java.util.HashMap;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import it.unical.asde.battleship.components.persistence.MatchDAO;
import it.unical.asde.battleship.game.Lobby;
import it.unical.asde.battleship.model.Grid;
import it.unical.asde.battleship.model.Match;

@Service
public class GameService
{

    @Autowired
    private LobbyService lobbyService;

    @Autowired
    private UtilService utilService;
    
    @Autowired
    private MatchDAO matchDao;

    // K = Username, V = Grid
    private HashMap<String, Grid> gridOwner;

    private HashMap<String, Grid> gridChallenger;

    // K = Username, V = boolean
    private HashMap<String, Boolean> isReadyOwner;

    private HashMap<String, Boolean> isReadyChallenger;

    public boolean hasMoreShips(int lobbyID, boolean isOwner) {
    	Lobby currentLobby = lobbyService.getLobby(lobbyID);
    	
    	Grid grid = new Grid();
    	if(isOwner) {
    		grid = gridChallenger.get(currentLobby.getChallenger());
    	}
    	else {
    		grid = gridOwner.get(currentLobby.getOwner());
    	}
    	
    	for (int i = 1; i < 11; i++) {
    		for (int j = 1; j < 11; j++) {
    			if(grid.getContent(i, j) != -1 && grid.getContent(i, j) != 1 && grid.getContent(i, j) != 0)
    				return true;
    		}
		}
    	
    	currentLobby.setEndingTimeStamp(new Date());
    	
    	Match match = new Match();
    	match.setEndTime(currentLobby.getEndingTimeStamp());
    	match.setStartTime(currentLobby.getStartingTimeStamp());
    	match.setWonCreator(isOwner);
    	match.setChallenger(utilService.getPlayingUser(currentLobby.getChallenger()));
    	match.setCreator(utilService.getPlayingUser(currentLobby.getOwner()));
    	match.setMatchName(currentLobby.getName());
    	
    	System.out.println("PRINT MATCH : "+match);
		
		matchDao.save(match);
    	
    	return false;
    }
    
   
    
    public void challengerIsReady(final int lobbyID)
    {
        final Lobby currentLobby = lobbyService.getLobby(lobbyID);

        isReadyChallenger.put(currentLobby.getChallenger(), true);
    }

    // TODO Before to delete the Lobby in the LobbyService (CHECK THE CONTROLLER CLASS, NOT THE SERVICE ONE),
    // Call this method to avoid NullPointerException
    public void deleteGame(final int lobbyID)
    {
        final Lobby currentLobby = lobbyService.getLobby(lobbyID);

        gridOwner.remove(currentLobby.getOwner());
        gridChallenger.remove(currentLobby.getChallenger());
        
        isReadyOwner.remove(currentLobby.getOwner());
        isReadyChallenger.remove(currentLobby.getChallenger());
    }

    public Grid getChallengerGrid(final int lobbyID)
    {
        final Lobby currentLobby = lobbyService.getLobby(lobbyID);

        return gridChallenger.get(currentLobby.getChallenger());
    }

    public Grid getOwnerGrid(final int lobbyID)
    {
        final Lobby currentLobby = lobbyService.getLobby(lobbyID);

        return gridOwner.get(currentLobby.getOwner());
    }

    public boolean hasShipChallenger(final int lobbyID, final int row, final int col)
    {
        final Lobby currentLobby = lobbyService.getLobby(lobbyID);

        return gridChallenger.get(currentLobby.getChallenger()).hasShip(row, col);
    }

    public boolean hasShipOwner(final int lobbyID, final int row, final int col)
    {
        final Lobby currentLobby = lobbyService.getLobby(lobbyID);

        return gridOwner.get(currentLobby.getOwner()).hasShip(row, col);
    }

    @PostConstruct
    public void init()
    {
        gridOwner = new HashMap<>();
        gridChallenger = new HashMap<>();

        isReadyOwner = new HashMap<>();
        isReadyChallenger = new HashMap<>();
    }

    public boolean isAlreadyGuessedChallenger(final int lobbyID, final int row, final int col)
    {
        final Lobby currentLobby = lobbyService.getLobby(lobbyID);

        return gridChallenger.get(currentLobby.getChallenger()).alreadyGuessed(row, col);
    }

    public boolean isAlreadyGuessedOwner(final int lobbyID, final int row, final int col)
    {
        final Lobby currentLobby = lobbyService.getLobby(lobbyID);

        return gridOwner.get(currentLobby.getOwner()).alreadyGuessed(row, col);
    }

    public void ownerIsReady(final int lobbyID)
    {
        final Lobby currentLobby = lobbyService.getLobby(lobbyID);

        isReadyOwner.put(currentLobby.getOwner(), true);
    }

    public void putShipChallenger(final int lobbyID, final int row, final int col, final int numShip, final int dir)
    {
        final Lobby currentLobby = lobbyService.getLobby(lobbyID);

        gridChallenger.get(currentLobby.getChallenger()).setShip(row, col, numShip, dir);
    }

    public void putShipOwner(final int lobbyID, final int row, final int col, final int numShip, final int dir)
    {
        final Lobby currentLobby = lobbyService.getLobby(lobbyID);
        gridOwner.get(currentLobby.getOwner()).setShip(row, col, numShip, dir);
    }

    public void startGame(final int lobbyID)
    {
        final Lobby currentLobby = lobbyService.getLobby(lobbyID);

        gridOwner.put(currentLobby.getOwner(), new Grid());
        gridChallenger.put(currentLobby.getChallenger(), new Grid());

        isReadyOwner.put(currentLobby.getOwner(), false);
        isReadyChallenger.put(currentLobby.getChallenger(), false);
    }

    public boolean usersAreReady(final int lobbyID)
    {
        final Lobby currentLobby = lobbyService.getLobby(lobbyID);

        return isReadyOwner.get(currentLobby.getOwner()) && isReadyChallenger.get(currentLobby.getChallenger());
    }

}
