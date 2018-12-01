package it.unical.asde.battleship.components.controllers;

import java.util.concurrent.ForkJoinPool;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.context.request.async.DeferredResult;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import it.unical.asde.battleship.components.services.GameService;
import it.unical.asde.battleship.components.services.LobbyService;
import it.unical.asde.battleship.model.Lobby;
import it.unical.asde.battleship.model.User;

@Controller
public class LobbyController
{

    @Autowired
    private LobbyService lobbyService;
    
    @Autowired
    private GameService gameService;

    @PostMapping("/checkOwner")
    @ResponseBody
    public DeferredResult<String> checkOwner(@RequestParam final String lobby_id)
    {
        int id = -1;
        try
        {
            id = Integer.parseInt(lobby_id);
        }
        catch (final Exception e)
        {
        	e.printStackTrace();
        }
        
        final Lobby fakeLobby = new Lobby();
        fakeLobby.setId(id);

        final DeferredResult<String> output = new DeferredResult<>();
        ForkJoinPool.commonPool().submit(() ->
            {

                final boolean isLobby = lobbyService.getLobbies().contains(fakeLobby);

                if (isLobby)
                {
                    output.setResult("owner");
                }
                else
                {
                    output.setResult("notOwner");
                }
            });

        return output;
    }

    
    @PostMapping("/new_lobby")
    public String createLobby(final Model model, @RequestParam final String lobby_name, @RequestParam final String lobby_owner,
            final HttpSession session)
    {

        //System.out.println("===================================== INIZIO NEW LOBBY =====================================");

        final Lobby myLobby = new Lobby(lobbyService.assignLobbyID(), lobby_name, lobby_owner);
        myLobby.setLobbyStarted(false);

        lobbyService.addLobby(myLobby);
        
        //TODO : E' necessario caricarle tutte qui?
        model.addAttribute("lobbies", lobbyService.getLobbies());
        //TODO : Il lobby ID non me lo posso ricavare con ${ lobby.id} ??
        //E' necessario salvarlo in "currentLobbyID" ?
        model.addAttribute("lobby", myLobby);
        model.addAttribute("currentLobbyID", myLobby.getId());

        //System.out.println("===================================== FINE NEW LOBBY =====================================");

        return "redirect:/insideLobby?id=" + myLobby.getId();

    }

    @GetMapping("/insideLobby")
    public String insideLobby(@RequestParam final String id, final Model model, HttpSession session)
    {
    	if(session.getAttribute("user")!=null) {
	        final int idLobby = Integer.parseInt(id);
	        final Lobby mylobby = lobbyService.getLobby(idLobby);
	        //TODO : Perchи inside lobbies ci servono tutte le lobby ?  
	        model.addAttribute("lobbies", lobbyService.getLobbies());
	        model.addAttribute("lobby", mylobby);
	        model.addAttribute("currentLobbyID", mylobby.getId());
	        return "lobby";
    	}
    	return "redirect:/";
    }

    @GetMapping("/join_lobby")
    public String joinLobby(final Model model, final HttpSession session, @RequestParam final String lobby_id)
    {

        if (session.getAttribute("user") != null)
        {
            final User user = (User) session.getAttribute("user");
            //System.out.println("===================================== INIZIO JOIN LOBBY =====================================");
            final int id = Integer.parseInt(lobby_id);
            final Lobby myLobby = lobbyService.getLobby(id);

            if (myLobby.getChallenger() == null && !user.getUsername().equalsIgnoreCase(myLobby.getOwner()))
            {
                myLobby.setChallenger(user.getUsername());
                lobbyService.addLobby(myLobby);

                //TODO : Anche qui, perchи ci salviamo l'id della lobby e non lo prendiamo direttamente
                // con ${ lobby.id }
                model.addAttribute("lobby", myLobby);
                model.addAttribute("currentLobbyID", myLobby.getId());

                //System.out.println("===================================== FINE JOIN LOBBY =====================================");

                return "lobby";
            }

        }
        return "redirect:/";
    }

    
    @GetMapping("/quit_lobby")
    public String quit(@RequestParam final String lobby_id, final HttpSession session)
    {

        final int id = Integer.parseInt(lobby_id);
        final String username = (String) session.getAttribute("username");
        if (username != null && username.equals(lobbyService.getLobby(id).getOwner())
                && lobbyService.getLobby(id).getChallenger() != null)
        {
        	gameService.deleteGame(id);
            lobbyService.deleteLobby(id);
        }
        else if (session.getAttribute("username").equals(lobbyService.getLobby(id).getOwner())
                && lobbyService.getLobby(id).getChallenger() == null)
        {
            lobbyService.deleteLobby(id);
        }
        else
        {
            lobbyService.getLobby(id).setChallenger(null);
        }
        
        return "index";
    }

    
    @PostMapping("/destroy_lobby")
    @ResponseBody
    public boolean destroy_lobby(@RequestParam String lobby_id) {
    	int id = Integer.parseInt(lobby_id);
    	
    	if(lobbyService.getLobbies().contains(lobbyService.getLobby(id)))
    	{
    		try {
    		//System.out.println("++++и+и+и+и+и+и+и+ INSIDE DESTROY LOBBY +и+и+ии+и+и+и+ии+и+и");
	    	gameService.deleteGame(id);
	        lobbyService.deleteLobby(id);
	        
	        }
    		catch(Exception e)
    		{
    			e.printStackTrace();
    			return false;
    		}
    	}
    	
    	return true;
    }
    
    
    
    
    @PostMapping("/refreshLobbyList")
    @ResponseBody
    public DeferredResult<String> refresh(final Model model)
    {

        //System.out.println("===================================== INIZIO REFRESH =====================================");
        model.addAttribute("lobbies", lobbyService.getLobbies());
        //System.out.println("SIZE DELLE LOBBIES = "+lobbyService.getLobbies())
        final DeferredResult<String> output = new DeferredResult<>();
        final ObjectMapper obj = new ObjectMapper();

        try
        {
            final String jsonArray = obj.writeValueAsString(lobbyService.getLobbies());
            //System.out.println(jsonArray);
            ForkJoinPool.commonPool().submit(() ->
                {
                    output.setResult(jsonArray);
                });
        }
        catch (final JsonProcessingException e)
        {
            e.printStackTrace();
        }

        //System.out.println("===================================== FINE REFRESH =====================================");

        return output;

    }

    @GetMapping("/lobbies")
    public String showLobbies(final Model model, final HttpSession session)
    {

        if (session.getAttribute("user") != null)
        {
            //System.out.println("===================================== INIZIO LOBBIES =====================================");

            model.addAttribute("lobbies", lobbyService.getLobbies());

            //System.out.println("===================================== FINE LOBBIES =====================================");

            return "index";
        }

        return "redirect:/";
    }

    

    @PostMapping("/waiting")
    @ResponseBody
    public DeferredResult<String> waiting(final Model model, final HttpSession session, @RequestParam final String lobbyID)
            throws InterruptedException
    {
        //System.out.println("===================================== INIZIO WAITING =====================================");
        final int ID = Integer.parseInt(lobbyID);
        final Lobby l = lobbyService.getLobby(ID);

        model.addAttribute("lobby", l);

        final DeferredResult<String> output = new DeferredResult<>();
        ForkJoinPool.commonPool().submit(() ->
            {
                output.setResult(l.getChallenger() == null ? "" : l.getChallenger() + ":" + l.isLobbyStarted());

            });

        //System.out.println("===================================== FINE WAITING =====================================");

        return output;
    }

}
