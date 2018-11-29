package it.unical.asde.battleship.components.controllers;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import it.unical.asde.battleship.components.services.RegistrationService;
import it.unical.asde.battleship.components.services.UtilService;
import it.unical.asde.battleship.model.User;

@Controller
public class RegistrationController
{

    @Autowired
    private RegistrationService registrationService;
    
    @Autowired
    private UtilService utilService;

    @PostMapping("/registration")
    @ResponseBody
    public String register(@RequestParam final String uname, @RequestParam final String pwd, @RequestParam final String rpt_pwd,
            final Model model, final HttpSession session)
    {

        if (!pwd.equals(rpt_pwd))
        {

            return "PSWD_EQUAL";
        }

        if (registrationService.userExists(uname))
        {

            return "USN_EXT";
        }

        final User u = new User(uname, pwd);
        registrationService.insertUser(u);
        session.setAttribute("username", uname);
        session.setAttribute("user", u);
        
        utilService.setPlayingUser(u);
        
        return "CORRECT";

    }

}
