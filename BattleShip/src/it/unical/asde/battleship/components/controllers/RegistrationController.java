package it.unical.asde.battleship.components.controllers;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import it.unical.asde.battleship.components.services.RegistrationService;
import it.unical.asde.battleship.model.User;

@Controller
public class RegistrationController {

	
	@Autowired
	RegistrationService registrationService;
	
	@PostMapping("/registration")
	public String register(@RequestParam String uname, @RequestParam String pwd, @RequestParam String rpt_pwd, Model model, HttpSession session) {
		
		if(!pwd.equals(rpt_pwd)) {
			model.addAttribute("error_psw", "The passwords inserted don't correspond");
			return "registration";
		}
		
		if(registrationService.userExists(uname))
		{
			model.addAttribute("error_uname", "This username is already used. Please choose another one");
			return "registration";
		}
		
		User u = new User(uname, pwd); 
		registrationService.insertUser(u);
		session.setAttribute("username", uname);
		session.setAttribute("user",u);
		return "index";
		
		
	}
}
