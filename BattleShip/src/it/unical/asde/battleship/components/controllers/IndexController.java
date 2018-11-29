package it.unical.asde.battleship.components.controllers;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import it.unical.asde.battleship.components.services.GameService;
import it.unical.asde.battleship.components.services.LobbyService;
import it.unical.asde.battleship.components.services.UtilService;


@Controller
public class IndexController {

	@Autowired
	private UtilService utilService;
	
	@Autowired
	private LobbyService lobbyService;
	
	@GetMapping("/")
	public String goToIndex(Model model, HttpSession session) {
		
		model.addAttribute("userRegistered", utilService.numUsers());
		model.addAttribute("userOnline", utilService.getOnlineUsers());
		model.addAttribute("availableLobby", lobbyService.getNumberOfLobbies());
		
		if (session.getAttribute("username") != null) {
			
			model.addAttribute("welcome", "Welcome "+session.getAttribute("username"));
			return "redirect:/lobbies";
		
		}

		return "log_reg";
	}
	
	
	
	
	@GetMapping("/login_page")
	public String goToLogin() {
		return "login";
	}
	
	
	
	@GetMapping("reg_page")
	public String goToRegistration() {
		return "registration";
	}
	

}
