package it.unical.asde.battleship.components.controllers;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import it.unical.asde.battleship.components.services.LoginService;
import it.unical.asde.battleship.model.User;

@Controller
public class LoginController
{

    @Autowired
    LoginService loginService;

    @PostMapping("/login")
    public String loginAttempt(@RequestParam final String uname, @RequestParam final String pwd, final HttpSession session,
            final Model model)
    {

        if (loginService.checkCredentials(uname, pwd))
        {
            session.setAttribute("username", uname);
            session.setAttribute("user", new User(uname, pwd));

            return "redirect:/";
        }
        model.addAttribute("error", "Wrong credentials!");
        return "log_reg";
    }

    @GetMapping("/logout")
    public String logout(final HttpSession session)
    {
        session.invalidate();
        return "log_reg";
    }

}
