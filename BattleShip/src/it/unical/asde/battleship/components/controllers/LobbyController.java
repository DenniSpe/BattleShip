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
	public String showLobbies(Model model, HttpSession session) {
		
			if(session.getAttribute("user")!=null) {
			
			System.out.println("===================================== INIZIO LOBBIES =====================================");
			
			System.out.println("====================== SIZE DELLE LOBBIES = "+lobbyService.getLobbies().size());
			
			model.addAttribute("lobbies", lobbyService.getLobbies());
			
			
			System.out.println("===================================== FINE LOBBIES =====================================");
			
			return "index";
		}
			
		return "redirect:/";
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
		
		return "redirect:/insideLobby?id="+myLobby.getId();

}
	
	
	@GetMapping("/insideLobby")
	public String insideLobby(@RequestParam String id, Model model) {
//		String some = (String) model.asMap().get("lobbyId");
		int idLobby = Integer.parseInt(id);
		Lobby mylobby = lobbyService.getLobby(idLobby);
		model.addAttribute("lobbies", lobbyService.getLobbies());
		model.addAttribute("lobby", mylobby);
		model.addAttribute("currentLobbyID", mylobby.getId());
		return "lobby";
	}
	
	@PostMapping("/waiting")
	@ResponseBody
	public DeferredResult<String> waiting(Model model, HttpSession session, @RequestParam String lobbyID) throws InterruptedException
	{
		System.out.println("===================================== INIZIO WAITING =====================================");
		int ID = Integer.parseInt(lobbyID);
		final Lobby l = lobbyService.getLobby(ID);
		model.addAttribute("lobby", l);
	
		DeferredResult<String> output = new DeferredResult<>();
		ForkJoinPool.commonPool().submit(() -> {
			System.out.println("tertwertert "+l.getId());
			output.setResult(l.getChallenger()==null ? "" : l.getChallenger());
		});
		
			
		System.out.println("===================================== FINE WAITING =====================================");
		
		return output;
		
	}
	
	@GetMapping("/join_lobby")
	public String joinLobby(Model model, HttpSession session, @RequestParam String lobby_id) {

		if(session.getAttribute("user")!=null) {
			User user = (User)session.getAttribute("user");
			System.out.println("===================================== INIZIO JOIN LOBBY =====================================");
			int id = Integer.parseInt(lobby_id);
		
			Lobby myLobby = lobbyService.getLobby(id);
			System.out.println("LOBBY ID= "+myLobby.getId());
			System.out.println("NAME= "+myLobby.getName());
			System.out.println("OWNER= "+myLobby.getOwner());
			System.out.println(" CHALLENGER= "+myLobby.getChallenger());
			if(myLobby.getChallenger()==null) {
				System.out.println("LOBBY ID= "+myLobby.getId());
				System.out.println("NAME= "+myLobby.getName());
				System.out.println("OWNER= "+myLobby.getOwner());
				System.out.println(" CHALLENGER= "+myLobby.getChallenger());
				
				myLobby.setChallenger(user.getUsername());
				
				lobbyService.addLobby(myLobby);
				
				model.addAttribute("lobby", myLobby);
				model.addAttribute("currentLobbyID", myLobby.getId());
				
				System.out.println("======================JOIN LOBBY CHALLENGER AGGIUNTO ========"+myLobby.getChallenger());
				System.out.println("======================JOIN LOBBY ID ========"+myLobby.getId());
				
				System.out.println("===================================== FINE JOIN LOBBY =====================================");
				
				return "lobby";
			}	
		
		}
		return "redirect:/";
	}
	
	
	@GetMapping("/quit_lobby")
	public String quit(@RequestParam String lobby_id, HttpSession session) {
		
		int id = Integer.parseInt(lobby_id);		
		
		if(session.getAttribute("username").equals(lobbyService.getLobby(id).getOwner()) && lobbyService.getLobby(id).getChallenger()!=null)
		{
//			existsOwner = false;
			lobbyService.deleteLobby(id);
			
			//lobbyService.getLobby(id).setChallenger(lobbyService.getLobby(id).getChallenger());
		}
		else if(session.getAttribute("username").equals(lobbyService.getLobby(id).getOwner()) && lobbyService.getLobby(id).getChallenger()==null) {
			lobbyService.deleteLobby(id);
		}
		else {
		lobbyService.getLobby(id).setChallenger(null);
		}
		return "index";
	}
	
	@PostMapping("/checkOwner")
	@ResponseBody
	public DeferredResult<String> checkOwner(@RequestParam String lobby_id){//@RequestParam String lobbyID){
		
		int id = -1;
		try {
			id = Integer.parseInt(lobby_id);
		}catch (Exception e) {
			// TODO: handle exception
		}
		Lobby fakeLobby = new Lobby();
		fakeLobby.setId(id);
		
		DeferredResult<String> output = new DeferredResult<>();
		ForkJoinPool.commonPool().submit(() -> {
			
			boolean isLobby = lobbyService.getLobbies().contains(fakeLobby);
			//String o = (!lobbyService.getLobby(ID).getOwner().equals(null) ? "owner" : "notOwner");
			
			//System.out.println("O IN JAVA "+o);
			//output.setResult(o);
			if(isLobby)
				output.setResult("owner");
			else
				output.setResult("notOwner");
		});
		
		return output;
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
