package it.unical.asde.battleship.components.controllers;

import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;


@Controller
public class IndexController {

	@GetMapping("/")
	public String goToIndex(Model model, HttpSession session) {
		
		if (session.getAttribute("user") != null) {
			
			model.addAttribute("welcome", "Welcome "+session.getAttribute("user"));
			return "index";
		
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
