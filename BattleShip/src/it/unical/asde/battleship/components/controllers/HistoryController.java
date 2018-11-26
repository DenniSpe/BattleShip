package it.unical.asde.battleship.components.controllers;

import java.util.List;
import java.util.Optional;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

import it.unical.asde.battleship.components.services.UtilService;
import it.unical.asde.battleship.model.Match;
import it.unical.asde.battleship.model.User;

@Controller
public class HistoryController {

	@Autowired
	private UtilService utilService;

	@GetMapping("/userHistory")
	public String userhistory(HttpSession session, Model model, @RequestParam Optional<String> page,
			@RequestParam Optional<String> amount) {

		if (session.getAttribute("user") != null) {

			User user = (User) session.getAttribute("user");
			long totalRows = utilService.getCountMatches(user);
			int pageNo = 1;
			int count = 20;
			if (amount.isPresent()) {
				try {
					count = Integer.parseInt(amount.get());
					if (count < 1 || count > 100) {
						count = 20;
					}
				} catch (Exception e) {
					// TODO: handle exception
					count = 20;
				}
			}

			int totalPages = (int) ((totalRows % count == 0) ? totalRows / count : totalRows / count + 1);
			if (page.isPresent()) {
				try {
					pageNo = Integer.parseInt(page.get());
					if (pageNo < 1) {
						return "redirect:/userHistory";
					} else if (pageNo > totalPages) {
						return "redirect:/userHistory?page=" + totalPages;
					}
				} catch (Exception e) {
					// TODO: handle exception
					pageNo = 1;
				}
			}

			List<Match> matches = utilService.getUserMatches(user, pageNo, count);

			model.addAttribute("matches", matches);
			model.addAttribute("page", pageNo);
			model.addAttribute("amount", count);
			model.addAttribute("totalPages", totalPages);

			return "user_history";
		}
		return "redirect:/";
	}

}
