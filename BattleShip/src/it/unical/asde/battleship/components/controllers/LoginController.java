package it.unical.asde.battleship.components.controllers;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import it.unical.asde.battleship.components.services.LoginService;
import it.unical.asde.battleship.components.services.UtilService;
import it.unical.asde.battleship.model.User;

@Controller
public class LoginController
{

    @Autowired
    private LoginService loginService;

    @Autowired
    private UtilService utilService;

    @PostMapping("/login")
    @ResponseBody
    public String loginAttempt(@RequestParam final String uname, @RequestParam final String pwd, final HttpSession session,
            final Model model)
    {

        final User user = loginService.checkCredentials(uname, pwd);
        if (user != null)
        {
            session.setAttribute("username", user.getUsername());
            session.setAttribute("user", user);

            utilService.setPlayingUser(user);

            return "CORRECT";
        }

        model.addAttribute("error", "Wrong credentials!");
        return "ERROR";
    }

    @GetMapping("/logout")
    public String logout(final HttpSession session)
    {
        utilService.deletePlayingUser((User) session.getAttribute("user"));

        session.invalidate();
        return "redirect:/";
    }

}
