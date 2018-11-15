package it.unical.asde.battleship.components.controllers;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import it.unical.asde.battleship.components.services.LoginService;

@Controller
public class IndexController {
	
	@Autowired
	LoginService loginService;
	
	@GetMapping("/")
	public String goToIndex(Model model, HttpSession session) {
		
		if (session.getAttribute("user") != null) {

			model.addAttribute("welcome", "Welcome "+session.getAttribute("user"));
			return "index";
		}
		return "login";
	}
	
	
	@PostMapping("/login")
	public String loginAttempt(@RequestParam String uname, @RequestParam String pwd, HttpSession session, Model model) {
		
		if(loginService.checkCredentials(uname, pwd)) {
			session.setAttribute("user", uname);
			return "redirect:/";
		}
		model.addAttribute("error", "Wrong credentials!");
		return "login";
}
	
	
	

}
