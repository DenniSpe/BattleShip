package it.unical.asde.battleship.components.services;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

import javax.annotation.PostConstruct;

import org.springframework.stereotype.Service;

import it.unical.asde.battleship.game.Lobby;

@Service
public class LobbyService
{

    private int lobbyID = 0;

    private Map<Integer, Lobby> lobbies;

    public LobbyService()
    {
        super();
    }

    public void addChallenger(final Lobby lobby, final String challenger)
    {
        lobbies.get(lobby.getId()).setChallenger(challenger);
    }

    public void addLobby(final Lobby lobby)
    {
        lobbies.put(lobby.getId(), lobby);
    }

    public int assignLobbyID()
    {
        return lobbyID++;
    }

    public void deleteLobby(final int id)
    {
        lobbies.remove(id);
    }

    public int getCurrentLobbyID()
    {
        return lobbyID;
    }

    public Collection<Lobby> getLobbies()
    {
        return lobbies.values();
    }

    public Lobby getLobby(final int id)
    {
        return lobbies.get(id);
    }

    @PostConstruct
    public void init()
    {

        lobbies = new HashMap<>();
        //        lobbies.put(getCurrentLobbyID(), new Lobby(assignLobbyID(), "Room1", "Dennis", false));
        //        lobbies.put(getCurrentLobbyID(), new Lobby(assignLobbyID(), "Room2", "Francesco", false));
        //        lobbies.put(getCurrentLobbyID(), new Lobby(assignLobbyID(), "Stanza", "Dario", false));
        //        lobbies.put(getCurrentLobbyID(), new Lobby(assignLobbyID(), "Stanza5", "Manuel", false));
    }

}
