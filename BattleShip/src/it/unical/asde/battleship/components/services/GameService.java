package it.unical.asde.battleship.components.services;

import java.util.HashMap;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import it.unical.asde.battleship.game.Lobby;
import it.unical.asde.battleship.model.Grid;

@Service
public class GameService
{

    @Autowired
    LobbyService lobbyService;

    // K = Username, V = Grid
    private HashMap<String, Grid> gridOwner;

    private HashMap<String, Grid> gridChallenger;

    // K = Username, V = boolean
    private HashMap<String, Boolean> isReadyOwner;

    private HashMap<String, Boolean> isReadyChallenger;

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
