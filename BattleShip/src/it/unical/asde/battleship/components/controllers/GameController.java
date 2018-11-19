package it.unical.asde.battleship.components.controllers;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import it.unical.asde.battleship.components.services.GameService;
import it.unical.asde.battleship.game.Lobby;
import it.unical.asde.battleship.model.User;

@Controller
public class GameController {
	
	@Autowired
	private GameService gameService;
	
	@GetMapping("/lobbies")
	public String showLobbies(Model model) {
		
		model.addAttribute("lobbies", gameService.getLobbies());
		
		return "index";
	}
	
	@PostMapping("/new_lobby")
	public String createLobby(Model model, @RequestParam String lobby_name, @RequestParam String lobby_owner, HttpSession session) {
		
		User owner = (User) session.getAttribute("user");
		Lobby myLobby = new Lobby(lobby_name, lobby_owner);
		gameService.addLobby(owner.getUsername(), myLobby);
		//E' necessario caricarle tutte qui?
		model.addAttribute("lobbies", gameService.getLobbies());
		model.addAttribute("lobby", myLobby);
		return "redirect:/waiting";
	}
	
	
	@GetMapping("/waiting")
	public String waiting(Model model, HttpSession session)
	{
		Lobby l = gameService.getLobby((String)session.getAttribute("username"));
		model.addAttribute("lobby", l);
		return "lobby";
	}
	
	@GetMapping("/join_lobby")
	public String joinLobby(Model model, HttpSession session, @RequestParam String lobby_owner, @RequestParam String lobby_challenger) {

		
		Lobby myLobby = gameService.getLobby(lobby_owner);
		
		myLobby.setChallenger(lobby_challenger);
		
		gameService.addLobby(lobby_owner, myLobby);
		
		model.addAttribute("lobby", myLobby);
		
		return "lobby";
	}

}
