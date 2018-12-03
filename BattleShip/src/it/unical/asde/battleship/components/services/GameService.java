package it.unical.asde.battleship.components.services;

import java.util.Date;
import java.util.HashMap;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import it.unical.asde.battleship.components.persistence.MatchDAO;
import it.unical.asde.battleship.model.Grid;
import it.unical.asde.battleship.model.Lobby;
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

    
    @PostConstruct
    public void init()
    {
        gridOwner = new HashMap<>();
        gridChallenger = new HashMap<>();

        isReadyOwner = new HashMap<>();
        isReadyChallenger = new HashMap<>();
    }
    
    public void startGame(final int lobbyID)
    {
        final Lobby currentLobby = lobbyService.getLobby(lobbyID);

        gridOwner.put(currentLobby.getOwner(), new Grid());
        gridChallenger.put(currentLobby.getChallenger(), new Grid());

        isReadyOwner.put(currentLobby.getOwner(), false);
        isReadyChallenger.put(currentLobby.getChallenger(), false);
    }
    
    // Check if in the grid there are ships
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
    	
    	//System.out.println("PRINT MATCH : "+match);
		
		matchDao.save(match);
    	
    	return false;
    }
    
   
    // Set the user that has positioned his boats
    public void userIsReady(int lobbyID, boolean isOwner) {
    	Lobby currentLobby = lobbyService.getLobby(lobbyID);
    	
    	if(isOwner) {
    		isReadyOwner.put(currentLobby.getOwner(), true);
    	}
    	else {
    		isReadyChallenger.put(currentLobby.getChallenger(), true);
    	}
    }
    
    // The two player have finished the game
    public void deleteGame(final int lobbyID)
    {
        final Lobby currentLobby = lobbyService.getLobby(lobbyID);

        gridOwner.remove(currentLobby.getOwner());
        gridChallenger.remove(currentLobby.getChallenger());
        
        isReadyOwner.remove(currentLobby.getOwner());
        isReadyChallenger.remove(currentLobby.getChallenger());
    }

  
    public Grid getGrid(int lobbyID, boolean isOwner) {
    	Lobby currentLobby = lobbyService.getLobby(lobbyID);
    	
    	if(isOwner) {
    		return gridOwner.get(currentLobby.getOwner());
    	}
    	else {
    		return gridChallenger.get(currentLobby.getChallenger());
    	}
    }

    public void putShip(int lobbyID, int row, int col, int numShip, int dir, boolean isOwner, String boatName) {
    	final Lobby currentLobby = lobbyService.getLobby(lobbyID);
    	
    	if(isOwner) {
    		gridOwner.get(currentLobby.getOwner()).setShip(row, col, numShip, dir, boatName);
    	}
    	else {
    		gridChallenger.get(currentLobby.getChallenger()).setShip(row, col, numShip, dir, boatName);
    	}
    }
    
   
    // Check if the users have positioned their boats
    public boolean usersAreReady(final int lobbyID)
    {
        final Lobby currentLobby = lobbyService.getLobby(lobbyID);

        return isReadyOwner.get(currentLobby.getOwner()) && isReadyChallenger.get(currentLobby.getChallenger());
    }
    
    public void clearGrid(String username) {
    	
    	if(gridOwner.containsKey(username)) {
    		gridOwner.remove(username);
    	}
    	if(gridChallenger.containsKey(username)){
    		gridChallenger.remove(username);
    	}
    }

}
