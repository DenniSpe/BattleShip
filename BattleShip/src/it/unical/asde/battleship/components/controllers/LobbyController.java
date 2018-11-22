package it.unical.asde.battleship.components.controllers;

import java.util.concurrent.ForkJoinPool;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jmx.support.ObjectNameManager;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.context.request.async.DeferredResult;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import it.unical.asde.battleship.components.services.LobbyService;
import it.unical.asde.battleship.game.Lobby;
import it.unical.asde.battleship.model.User;

@Controller
public class LobbyController {
	
	@Autowired
	private LobbyService lobbyService;
	
	
	
	@PostMapping("/refreshLobbyList")
	@ResponseBody
	public DeferredResult<String> refresh(Model model) {
		
		System.out.println("===================================== INIZIO REFRESH =====================================");
		model.addAttribute("lobbies", lobbyService.getLobbies());
		//System.out.println("SIZE DELLE LOBBIES = "+lobbyService.getLobbies())
		DeferredResult<String> output = new DeferredResult<>();
		ObjectMapper obj = new ObjectMapper();
		try {
			final String jsonArray = obj.writeValueAsString(lobbyService.getLobbies());
			System.out.println(jsonArray);
			ForkJoinPool.commonPool().submit(() -> {
				output.setResult(jsonArray);
			});
		} catch (JsonProcessingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		System.out.println("===================================== FINE REFRESH =====================================");
		
		
		
		
		return output;
		
	}
	
	
	
	
	@GetMapping("/lobbies")
	public String showLobbies(Model model) {
		
		System.out.println("===================================== INIZIO LOBBIES =====================================");
		
		System.out.println("====================== SIZE DELLE LOBBIES = "+lobbyService.getLobbies().size());
		
		model.addAttribute("lobbies", lobbyService.getLobbies());
		
		
		System.out.println("===================================== FINE LOBBIES =====================================");
		
		return "index";
	}
	
	@PostMapping("/new_lobby")
	public String createLobby(Model model, @RequestParam String lobby_name, @RequestParam String lobby_owner, HttpSession session) {
		
		System.out.println("===================================== INIZIO NEW LOBBY =====================================");
		
		User owner = (User) session.getAttribute("user");
		Lobby myLobby = new Lobby(lobbyService.assignLobbyID(),lobby_name, lobby_owner);
		System.out.println("OWNER ======================="+owner.getUsername());
		System.out.println("LOBBY ID ======================="+myLobby.getId());
		lobbyService.addLobby(myLobby);
		//E' necessario caricarle tutte qui?
		model.addAttribute("lobbies", lobbyService.getLobbies());
		model.addAttribute("lobby", myLobby);
		model.addAttribute("currentLobbyID", myLobby.getId());
		
		System.out.println("===================================== FINE NEW LOBBY =====================================");
		
		return "lobby";
	}
	
	
	@PostMapping("/waiting")
	@ResponseBody
	public DeferredResult<String> waiting(Model model, HttpSession session, @RequestParam String lobbyID) throws InterruptedException
	{
		System.out.println("===================================== INIZIO WAITING =====================================");
		int ID = Integer.parseInt(lobbyID);
		Lobby l = lobbyService.getLobby(ID);
		model.addAttribute("lobby", l);
	
		DeferredResult<String> output = new DeferredResult<>();
		ForkJoinPool.commonPool().submit(() -> {
			output.setResult(l.getChallenger());
		});

		
			System.out.println();
		System.out.println("===================================== FINE WAITING =====================================");
		
		return output;
		
	}
	
	@GetMapping("/join_lobby")
	public String joinLobby(Model model, HttpSession session, @RequestParam String lobby_owner, @RequestParam String lobby_challenger, @RequestParam String lobby_id) {

		System.out.println("===================================== INIZIO JOIN LOBBY =====================================");
		int id = Integer.parseInt(lobby_id);
		
		Lobby myLobby = lobbyService.getLobby(id);
		
		System.out.println("LOBBY ID= "+myLobby.getId());
		System.out.println("NAME= "+myLobby.getName());
		System.out.println("OWNER= "+myLobby.getOwner());
		System.out.println(" CHALLENGER= "+myLobby.getChallenger());
		
		myLobby.setChallenger(lobby_challenger);
		
		lobbyService.addLobby(myLobby);
		
		model.addAttribute("lobby", myLobby);
		model.addAttribute("currentLobbyID", myLobby.getId());
		
		System.out.println("======================JOIN LOBBY CHALLENGER AGGIUNTO ========"+myLobby.getChallenger());
		System.out.println("======================JOIN LOBBY ID ========"+myLobby.getId());
		
		System.out.println("===================================== FINE JOIN LOBBY =====================================");
		
		return "lobby";
	}
	
	
	@GetMapping("/quit_lobby")
	public String quit(@RequestParam String lobby_id) {
		
		int id = Integer.parseInt(lobby_id);
		lobbyService.getLobby(id).setChallenger(null);
		return "index";
	}
	
//	@GetMapping("/getEvents")
//	@ResponseBody
//	public DeferredResult<String> getEvents(HttpSession session) {
//
//		DeferredResult<String> output = new DeferredResult<>();
//		ForkJoinPool.commonPool().submit(() -> {
//			try {
//				//output.setResult(eventsService.nextEvent(session.getId()));
//				output.setResult(eventsService.giveMeChallenger(session.getId()));
//			} catch (InterruptedException e) {
//				output.setResult("An error occurred during event retrieval");
//			}
//		});
//
//		return output;
//}
	
	
	

}
